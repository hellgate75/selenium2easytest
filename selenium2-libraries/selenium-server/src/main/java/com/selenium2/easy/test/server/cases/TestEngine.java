package com.selenium2.easy.test.server.cases;

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.PatternFilenameFilter;
import com.selenium2.easy.test.server.automated.WebDriverSelector;
import com.selenium2.easy.test.server.automated.multithread.TestCaseResult;
import com.selenium2.easy.test.server.automated.multithread.UserCaseResult;
import com.selenium2.easy.test.server.automated.multithread.WebDriverParallelFactory;
import com.selenium2.easy.test.server.cases.BaseTestCase.TIMER_TYPE;
import com.selenium2.easy.test.server.exceptions.FrameworkException;
import com.selenium2.easy.test.server.utils.SeleniumUtilities;
import com.selenium2.easy.test.server.xml.XMLTestCase;
import com.selenium2.easy.test.server.xml.XMLTestGroup;

public class TestEngine implements Callable<UserCaseResult>{
	static {
		if (System.getProperty("log4j.configurationFile")==null)
			System.setProperty("log4j.configurationFile", "log4j2.xml");
	}
	private static Logger logger = LoggerFactory.getLogger("com.service.restfy.selenium.server");
	private static final String REPORT_LINE_SEPARATOR = "------------------------------------------------------------------------------------------------------------------------------------";
	
	private WebDriver driver = null;
	private List<BaseTestCase> caseList = new ArrayList<BaseTestCase>(0); 
	private Map<String, String> caseMessages = new HashMap<String, String>(0); 
	private Map<String, Boolean> caseResponseStatus = new HashMap<String, Boolean>(0); 
	private int caseExecuted = 0;
	private int caseFailed = 0;
	private boolean traceRunOnLogger = true;
	private long startTime = 0L;
	private long endTime = 0L;
	private List<UserCaseResult> parallelResults = null;
	private int parallelCounter = 0;
	private int parallelProcesses = 0;
	private int parallelLimit = 0;
	private long parallelStart = 0L;
	private long parallelEnd = 0L;
	private WebDriverParallelFactory factory = null;

	public TestEngine() {
		super();
	}

	public TestEngine(WebDriver driver) {
		super();
		this.driver=driver;
	}
	
	public TestEngine(WebDriver driver, boolean traceRunOnLogger) {
		super();
		this.driver = driver;
		this.traceRunOnLogger = traceRunOnLogger;
	}

	public void addCase(BaseTestCase testCase) {
		if (testCase!=null && !caseList.contains(testCase)) {
			caseList.add(testCase);
		}
	}

	public boolean isTraceRunOnLogger() {
		return traceRunOnLogger;
	}

	public void setTraceRunOnLogger(boolean traceRunOnLogger) {
		this.traceRunOnLogger = traceRunOnLogger;
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public void setWebDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void clearCaseList() {
		caseList.clear();
	}
	
	public int getCaseNumber() {
		return caseList.size();
	}
	
	protected final void info(String message) {
		if (this.traceRunOnLogger) {
			logger.info(message);
		}
	}
	
	protected final void error(String message, Throwable exception) {
		if (this.traceRunOnLogger) {
			logger.error(message, exception);
		}
	}

	public void addCaseByClassName(String className) throws FrameworkException {
		try {
			BaseTestCase testCase = (BaseTestCase)(Class.forName(className).newInstance());
			if (testCase!=null && !caseList.contains(testCase)) {
				caseList.add(testCase);
			}
		} catch (Throwable e) {
			error("Error loading class : " + className, e);
			throw new FrameworkException("Error loading class : " + className, e);
		}
	}

	public void addCaseByPackageName(String packageName) throws FrameworkException {
		try {
			Reflections reflections = new Reflections(packageName);
			Set<Class<? extends BaseTestCase>> classes = reflections.getSubTypesOf(BaseTestCase.class);
			for(Class<? extends BaseTestCase> classMask: classes) {
				BaseTestCase testCase = classMask.newInstance();
				if (testCase!=null && !caseList.contains(testCase)) {
					caseList.add(testCase);
				}
			}
		} catch (Throwable e) {
			error("Error loading package : " + packageName, e);
			throw new FrameworkException("Error loading package : " + packageName, e);
		}
	}
	
	public void addCasesByXMLDirectory(String directory) throws FrameworkException {
		try {
			FilenameFilter filter = new PatternFilenameFilter(".*\\.xml");
			String[] xmlFiles = new File(directory).list(filter);
			for(String xmlFile: xmlFiles) {
				XMLTestGroup testGroup = SeleniumUtilities.loadXMLTestFramework(new File(xmlFile));
				for(XMLTestCase testCase: testGroup.getTestCases()) {
					try {
						if (testGroup.getImplementationClassFullName()==null) {
							BaseTestCase xmlTestCase = new XMLGroupedTestCase(testGroup.getGroupName(), testCase);
							if (xmlTestCase!=null && !caseList.contains(xmlTestCase)) {
								caseList.add(xmlTestCase);
							}
						}
						else {
							Constructor<?> constructor = Class.forName(testGroup.getImplementationClassFullName()).getConstructor(String.class, XMLTestCase.class);
							BaseTestCase xmlTestCase = (XMLGroupedTestCase)constructor.newInstance(testGroup.getGroupName(), testCase);
							if (xmlTestCase!=null && !caseList.contains(xmlTestCase)) {
								caseList.add(xmlTestCase);
							}
							
						}
					} catch (Throwable e) {
						error("Error creating test Case : " + testCase, e);
						throw new FrameworkException("Error creating test Case : " + testCase, e);
					}
				}
			}
		} catch (Throwable e) {
			error("Error loading from XML Directory : " + directory, e);
			throw new FrameworkException("Error loading from XML Directory : " + directory, e);
		}
	}
	
	public void clearTestEngineStatus() {
		this.caseExecuted = 0;
		this.caseFailed = 0;
		this.startTime = 0L;
		this.endTime = 0L;
		this.caseMessages.clear();
		this.caseResponseStatus.clear();
		this.parallelResults=null;
	}
	
	private TestCaseResult executeParallelTestCase(WebDriver driver, BaseTestCase testCase) throws Throwable {
		TestCaseResult result = new TestCaseResult(testCase.getCaseUID(), testCase.getCaseName());
		testCase.resetCounters();
		testCase.startTimeCounter(TIMER_TYPE.TEST_CASE);
		if (testCase.isConnectionRequired()) {
			if(!testCase.isSecureConnection()) {
				testCase.startTimeCounter(TIMER_TYPE.RENDERING);
				driver.get(testCase.getConnectionURL());
				testCase.stopTimeCounter(TIMER_TYPE.RENDERING);
			}
			else {
				
				testCase.startTimeCounter(TIMER_TYPE.SECURITY);
				testCase.startTimeCounter(TIMER_TYPE.RENDERING);
				if (!testCase.handleSecureConnection(driver)) {
					testCase.stopTimeCounter(TIMER_TYPE.SECURITY);
					testCase.stopTimeCounter(TIMER_TYPE.TEST_CASE);
					throw new FrameworkException("Unable to connect to " + testCase.getConnectionURL() + " : Authentication failed ... ");
				}
				testCase.stopTimeCounter(TIMER_TYPE.RENDERING);
				testCase.stopTimeCounter(TIMER_TYPE.SECURITY);
			}
		}
		testCase.automatedTest(driver);
		testCase.stopTimeCounter(TIMER_TYPE.TEST_CASE);
		result.setMessage("[SUCCESS]: Test Case '"+testCase.getCaseName()+"' executed correctly");
		result.setSuccess(true);
		result.setRenderingElapsedTime(testCase.getRenderingElapsedTime());
		result.setTestActionElapsedTime(testCase.getTestActionElapsedTime());
		result.setSecurityAccessElapsedTime(testCase.getSecurityAccessElapsedTime());
		result.setTestCaseElapsedTime(testCase.getTestCaseElapsedTime());
		return result;
	}
	
	private synchronized int nextParallelCounter() {
		return ++parallelCounter;
	}
	
	@Override
	public UserCaseResult call() throws Exception {
		int ticket = nextParallelCounter();
		UserCaseResult userResult  = new UserCaseResult(ticket, "User" + ticket);
		long startTime = System.currentTimeMillis();
		int caseExecuted = 0;
		int caseFailed = 0;
		WebDriverSelector driverSelector = null;
		try {
			driverSelector = factory.nextWebDriver();
			WebDriver driver = driverSelector.getWebDriver();
			userResult.setDriverName(driver!=null ? driver.getClass().getName() : null);
			for(BaseTestCase testCase: caseList) {
				caseExecuted++;
				if (this.traceRunOnLogger)
					info("Executing test case [UID:"+testCase.getCaseUID()+"] name : " + testCase.getCaseName());
				try {
					userResult.addTestCase(this.executeParallelTestCase(driver, testCase));
				} catch (Throwable e) {
					caseFailed++;
					String message = e.getMessage();
					TestCaseResult result = new TestCaseResult(testCase.getCaseUID(), testCase.getCaseName());
					result.setRenderingElapsedTime(testCase.getRenderingElapsedTime());
					result.setTestActionElapsedTime(testCase.getTestActionElapsedTime());
					result.setSecurityAccessElapsedTime(testCase.getSecurityAccessElapsedTime());
					result.setTestCaseElapsedTime(testCase.getTestCaseElapsedTime());
					result.setMessage("[FAIL]: Test Case '"+testCase.getCaseName()+"' failed due to: "+ (message!=null ? message.replace('\n', ' '):message));
					result.setSuccess(false);
					userResult.addTestCase(result);
				}
			}
		} catch (Throwable e) {
			error("Unable to Complete the Tread due to : ", e);
			String message = e.getMessage();
			userResult.setErrorMessage("Unable to Complete the Tread due to : " +  (message!=null ? message.replace('\n', ' '):message));
		}
		finally {
			if (driverSelector!=null)
				driverSelector.stopWebDriver();
		}
		long endTime = System.currentTimeMillis();
		userResult.setElapsedTime(endTime-startTime);
		userResult.setCaseExecuted(caseExecuted);
		userResult.setCaseFailed(caseFailed);
		return userResult;
	}

	public void parallel(WebDriverParallelFactory factory, int numberOfUsers, int limitOfUsers) throws Throwable {
		this.parallelCounter = 0;
		this.parallelProcesses = numberOfUsers;
		this.parallelLimit = limitOfUsers;
		this.parallelEnd=0L;
		this.factory = factory;
		this.parallelStart = System.currentTimeMillis();
		this.parallelResults = new ArrayList<UserCaseResult>(numberOfUsers);
		ExecutorService executor = Executors.newFixedThreadPool(limitOfUsers);
	    CompletionService<UserCaseResult> compService = new ExecutorCompletionService<UserCaseResult>(executor);		
		for(int i=0;i<numberOfUsers;i++) {
			compService.submit(this);
		}
		for(int i=0;i<numberOfUsers;i++) {
			Future<UserCaseResult> result = compService.take();
			this.parallelResults.add(result.get());
		}
		executor.shutdown();
		this.parallelEnd = System.currentTimeMillis();
		executor = null;
		compService = null;
		factory = null;
		Collections.sort(this.parallelResults);
	}

	private void executeTestCase(BaseTestCase testCase) throws Throwable {
		testCase.resetCounters();
		testCase.startTimeCounter(TIMER_TYPE.TEST_CASE);
		if(!testCase.isSecureConnection()) {
			testCase.startTimeCounter(TIMER_TYPE.SECURITY);
			if (!testCase.handleSecureConnection(driver)) {
				testCase.stopTimeCounter(TIMER_TYPE.SECURITY);
				testCase.stopTimeCounter(TIMER_TYPE.TEST_CASE);
				throw new FrameworkException("Unable to connect to " + testCase.getConnectionURL() + " authentication Failed ... ");
			}
			testCase.stopTimeCounter(TIMER_TYPE.SECURITY);
		}
		if (testCase.isConnectionRequired()) {
			testCase.startTimeCounter(TIMER_TYPE.RENDERING);
			driver.get(testCase.getConnectionURL());
			testCase.stopTimeCounter(TIMER_TYPE.RENDERING);
		}
		testCase.startTimeCounter(TIMER_TYPE.TEST_ACTION);
		testCase.automatedTest(this.driver);
		testCase.stopTimeCounter(TIMER_TYPE.TEST_ACTION);
		testCase.stopTimeCounter(TIMER_TYPE.TEST_CASE);
		info("Executed test case [UID:"+testCase.getCaseUID()+"] name : " + testCase.getCaseName());
		caseMessages.put(testCase.getCaseUID(), "[SUCCESS]: Test Case '"+testCase.getCaseName()+"' executed correctly");
		caseResponseStatus.put(testCase.getCaseUID(), true);		
	}
	
	public void run() throws Throwable {
		this.clearTestEngineStatus();
		startTime = System.currentTimeMillis();
		for(BaseTestCase testCase: caseList) {
			caseExecuted++;
			if (this.traceRunOnLogger)
				info("Executing test case [UID:"+testCase.getCaseUID()+"] name : " + testCase.getCaseName());
			try {
				this.executeTestCase(testCase);
			} catch (Throwable e) {
				caseFailed++;
				String message = e.getMessage();
				caseMessages.put(testCase.getCaseUID(), "[FAIL]: Test Case '"+testCase.getCaseName()+"' failed due to: "+ (message!=null ? message.replace('\n', ' '):message));
				caseResponseStatus.put(testCase.getCaseUID(), false);
				if(testCase.isExceptionTransversable())
					throw e;
				else
					error("Failure of test case : " + testCase.getCaseName(), e);
			}
		}
		endTime = System.currentTimeMillis();
	}

	public int getCaseExecuted() {
		return caseExecuted;
	}

	public int getCaseSecceded() {
		return caseExecuted-caseFailed;
	}
	
	public void report(PrintStream ps) {
		if(parallelResults==null) {
			if (caseExecuted==0)
				return;
			int skipped = 0;
			ps.println(REPORT_LINE_SEPARATOR);
			ps.println("Test Engine Report - web driver used : " + this.driver.getClass().getName());
			ps.println(REPORT_LINE_SEPARATOR);
			for(int i=0; i<this.getCaseNumber();i++) {
				BaseTestCase testCase = caseList.get(i);
				String message = caseMessages.get(testCase.getCaseUID());
				String timingString = ", elapsed : " + testCase.getTestCaseElapsedTime() + " ms, security : " + testCase.getSecurityAccessElapsedTime() + " ms, "
						+ "rendering : " + testCase.getRenderingElapsedTime() + " ms, test : " + testCase.getTestActionElapsedTime()+" ms";
				if (message!=null) {
					ps.println("Case " + (i+1) + " - " + message + timingString);
				}
				else {
					ps.println("Case " + (i+1) + " - [SKIPPED]: Test Case '"+testCase.getCaseName()+"' skipped in last execution" + timingString);
					skipped++;
				}
			}
			ps.println(REPORT_LINE_SEPARATOR);
			ps.println("Total Cases :  "+this.getCaseNumber()+", Executed : " + this.caseExecuted + ", Skipped : " + skipped + ", Success : " + this.getCaseSecceded() + ", Failed : " + caseFailed + ", Elapsed Time : " + (endTime-startTime) + " ms");
			ps.println(REPORT_LINE_SEPARATOR);
		}
		else {
			for(UserCaseResult result:parallelResults) {
				ps.println(REPORT_LINE_SEPARATOR);
				ps.println("Test Engine Report PARALLEL (processName : '"+result.getProcessName()+"') - web driver used : " + result.getDriverName());
				ps.println("Succeded : '"+result.isSuccess()+"  - Error Message : " + result.getErrorMessage());
				ps.println(REPORT_LINE_SEPARATOR);
				int skipped = 0;
				if (result.getCaseExecuted()>0) {
					int i=0;
					for(TestCaseResult testCase: result.getTestCases()) {
						String message = testCase.getMessage();
						String timingString = ", elapsed : " + testCase.getTestCaseElapsedTime() + " ms, security : " + testCase.getSecurityAccessElapsedTime() + " ms, "
								+ "rendering : " + testCase.getRenderingElapsedTime() + " ms, test : " + testCase.getTestActionElapsedTime()+" ms";
						if (message!=null) {
							ps.println("Case " + (i+1) + " - " + message + timingString);
						}
						else {
							ps.println("Case " + (i+1) + " - [SKIPPED]: Test Case '"+testCase.getCaseName()+"' skipped in last execution" + timingString);
							skipped++;
						}
						i++;
					}
				}
				ps.println(REPORT_LINE_SEPARATOR);
				ps.println("Total Cases :  "+this.getCaseNumber()+", Executed : " + result.getCaseExecuted() + ", Skipped : " + skipped + ", Success : " + (result.getCaseExecuted()-result.getCaseFailed()) + ", Failed : " + result.getCaseFailed() + ", Elapsed Time : " + result.getElapsedTime() + " ms");
				ps.println(REPORT_LINE_SEPARATOR);
			}
		}
	}

	public int getCaseFailed() {
		return caseFailed;
	}

	public String jsonReport() {
		if(parallelResults==null) {
			if (caseExecuted==0)
				return this.getCasesJSON("", false, this.driver.getClass().getName(), this.getCaseNumber(), this.caseExecuted, 0, this.caseFailed, this.getCaseSecceded(), (endTime-startTime), "[]", false, null);
			int skipped = 0;
			String cases = "";
			for(int i=0; i<this.getCaseNumber();i++) {
				BaseTestCase testCase = caseList.get(i);
				String message = caseMessages.get(testCase.getCaseUID());
				if (message!=null) {
					cases += (i>0 ? ", " : "") + this.getSingleCaseJSON(testCase.getCaseName(), caseResponseStatus.get(testCase.getCaseUID()), false, testCase.getTestCaseElapsedTime(), 
							testCase.getRenderingElapsedTime(),  testCase.getTestActionElapsedTime(), testCase.getSecurityAccessElapsedTime(), caseMessages.get(testCase.getCaseUID()));
				}
				else {
					cases += (i>0 ? ", " : "") + this.getSingleCaseJSON(testCase.getCaseName(), caseResponseStatus.get(testCase.getCaseUID()), true, testCase.getTestCaseElapsedTime(), 
																		testCase.getRenderingElapsedTime(),  testCase.getTestActionElapsedTime(), testCase.getSecurityAccessElapsedTime(), "[SKIPPED]: Test Case '"+testCase.getCaseName()+"' skipped in last execution");
					skipped++;
				}
			}
			return this.getCasesJSON("", false, this.driver.getClass().getName(), this.getCaseNumber(), this.caseExecuted, skipped, this.caseFailed, this.getCaseSecceded(), (endTime-startTime), cases, true, null);
		}
		else {
			String parallelJSON = "{";
			parallelJSON +=  "\"parallelResult\": true, ";
			parallelJSON +=  "\"numberOfUsers\": "+this.parallelProcesses+", ";
			parallelJSON +=  "\"limitPerSession\": "+this.parallelLimit+", ";
			parallelJSON +=  "\"totalUsersExecuted\": "+this.parallelCounter+", ";
			parallelJSON +=  "\"totalElapsedTime\": "+(this.parallelEnd-this.parallelStart)+", ";
			parallelJSON +=  "\"results\": [";
			int j=0;
			for(UserCaseResult result:parallelResults) {
				if (result.getCaseExecuted()>0) {
					int i=0;
					String cases = "";
					for(TestCaseResult testCase: result.getTestCases()) {
						cases += (i>0 ? ", " : "") + this.getSingleCaseJSON(testCase.getCaseName(), testCase.isSuccess(), testCase.isSkipped(), testCase.getTestCaseElapsedTime(), 
								testCase.getRenderingElapsedTime(),  testCase.getTestActionElapsedTime(), testCase.getSecurityAccessElapsedTime(), testCase.getMessage());
						i++;
					}
					parallelJSON += (j>0 ? ", " : "") + this.getCasesJSON(result.getProcessName(), true, result.getDriverName(), this.getCaseNumber(), result.getCaseExecuted(), this.getCaseNumber() - result.getCaseExecuted(), result.getCaseFailed(), result.getCaseExecuted()-result.getCaseFailed(), result.getElapsedTime(), cases, result.isSuccess(), result.getErrorMessage());
					j++;
				}
			}
			parallelJSON +=  "]";
			
			parallelJSON +=  "}";
			return parallelJSON;
		}
	}

	private final String getCasesJSON(String processName, boolean parallel, String driverName, int caseNumber, int caseExecuted, int caseSkipped,
									  int caseFailed, int caseSucceded, long elapsedTime, String caseSummaryJSon, boolean succeded, String errorMessage) {
		return "{"
				+ "\"processName\": \"" + processName+"\", "
				+ "\"parallel\": \"" + parallel+"\", "
				+ "\"succeded\": " + succeded + ", "
				+ "\"errorMessage\": \"" + (errorMessage!=null ? errorMessage : "")+"\", "
				+ "\"driver\": \"" + driverName+"\", "
				+ "\"cases\": " + caseNumber+", "
				+ "\"executed\": " + caseExecuted+", "
				+ "\"skipped\": " + caseSkipped +", "
				+ "\"failed\": " + caseFailed+", "
				+ "\"success\": " + caseSucceded+", "
				+ "\"elapsed\": " + elapsedTime + ", "
				+ "\"casesResponse\": ["+caseSummaryJSon+"]}";

	}

	private final String getSingleCaseJSON(String caseName, boolean success, boolean skipped, long totalElapseTime, long renderElapseTime, long testElapseTime, long securityElapseTime, String message) {
		return "{"
				+"\"caseName\" : \"" + caseName + "\", "
				+"\"success\" : " + success + ", "
				+"\"skipped\" : "+skipped+", "
				+"\"totalElapsed\" : " + totalElapseTime + ", "
				+"\"renderingElapsed\" : " + renderElapseTime + ", "
				+"\"actionsElapsed\" : " + testElapseTime + ","
				+"\"securityElapsed\" : " + securityElapseTime + ", "
				+"\"message\" : \""+message+"\""
				+ "}";
		
	}

}
