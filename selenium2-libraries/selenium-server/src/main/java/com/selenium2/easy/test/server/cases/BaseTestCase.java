package com.selenium2.easy.test.server.cases;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.exceptions.FrameworkException;
import com.selenium2.easy.test.server.exceptions.NotFoundException;
import com.selenium2.easy.test.server.utils.SeleniumUtilities;

/**
 * Base implementation of the Test Case.
 * @author Fabrizio Torelli
 * @see TestCase
 *
 */
public abstract class BaseTestCase extends TestCase implements Cloneable {
	/*
	 * Configuring the log4j slf4j file in the path is it not previously configured
	 * on the application execution process with the default configuration
	 */
	static {
		if (System.getProperty("log4j.configurationFile")==null)
			System.setProperty("log4j.configurationFile", "log4j2.xml");
	}
	/**
	 * This Enumeration retain the information about the time metering metric types.
	 * <br/>Allowed metric types:
	 * <br/>
	 * <ul>
	 * <li><b>TEST_CASE</b> For the timing metrics of the entire test case lasting time (internal use only)</li>
	 * <li><b>SECURITY</b> For the timing metrics of the security access lasting time (internal use only)</li>
	 * <li><b>RENDERING</b> For the timing metrics of the rendering lasting time (internal use only)</li>
	 * <li><b>TEST_ACTION</b> For the timing metrics of the testing actions lasting time (internal use only)</li>
	 * </ul>
	 * @author Fabrizio Torelli
	 *
	 */
	public static enum TIMER_TYPE {TEST_CASE, SECURITY, RENDERING, TEST_ACTION};

	private static Logger logger = LoggerFactory.getLogger("com.selenium2.easy.test.server");
	
	/**
	 * Test Case Name
	 */
	protected String caseName;
	/**
	 * Test Case Connection URL
	 */
	protected String caseURL;
	/**
	 * Test Case URL connection flag
	 */
	protected boolean openUrl;
	/**
	 * Test Case re-throw or manage exceptions flag
	 */
	protected boolean retrowExcpetion;
	
	/* Those fields are private because the security and the time metering are 
	 * straightly part of the framework. It is not desired a re-implementation. 
	 * This can change the metrics or make unstable the process.
	 * */
	
	private Map<String, String> securityInfo = new HashMap<String, String>(0);
	
	private long startTestCase = 0L;

	private long stopTestCase = 0L;

	private long startSecurityAccess = 0L;

	private long stopSecurityAccess = 0L;

	private long startRendering = 0L;

	private long stopRendering = 0L;

	private long startTestAction = 0L;

	private long stopTestAction = 0L;
	
	/**
	 * Constructor of the base test case instance.
	 * It cannot be left as default constructor because in the TestEngine it is 
	 * expected an empty parameter list's constructor
	 * @param caseName The case name associated to the actions
	 * @param caseURL The connection URL if needed
	 * @param openUrl The connection URL flag that allow the URL use
	 * @param retrowExcpetion The exception re-throw flag that allow or not to throw the exceptions and block the tests
	 */
	public BaseTestCase(String caseName, String caseURL, boolean openUrl,
			boolean retrowExcpetion) {
		super();
		this.caseName = caseName;
		this.caseURL = caseURL;
		this.openUrl = openUrl;
		this.retrowExcpetion = retrowExcpetion;
	}
	
	/**
	 * Start measuring a timer according to the timing metrics ({@link TIMER_TYPE})
	 * <br><b>It is used by the Test Engine please do not use it to avoid side effects on the timers</b>
	 * @param the timer to be stopped for the enumeration {@link TIMER_TYPE}
	 * @see TIMER_TYPE
	 */
	public final void startTimeCounter(TIMER_TYPE type) {
		switch(type) {
			case TEST_ACTION:
				startTestAction = System.currentTimeMillis();
				break;
			case RENDERING:
				startRendering = System.currentTimeMillis();
				break;
			case SECURITY:
				startSecurityAccess = System.currentTimeMillis();
				break;
			default:
				startTestCase = System.currentTimeMillis();
				break;
		}
	}
	
	/**
	 * Stop measuring a timer according to the timing metrics ({@link TIMER_TYPE})
	 * <br><b>It is used by the Test Engine please do not use it to avoid side effects on the timers</b>
	 * @param the timer to be stopped for the enumeration {@link TIMER_TYPE}
	 * @see TIMER_TYPE
	 */
	public final void stopTimeCounter(TIMER_TYPE type) {
		switch(type) {
		case TEST_ACTION:
			stopTestAction = System.currentTimeMillis();
			break;
		case RENDERING:
			stopRendering = System.currentTimeMillis();
			break;
		case SECURITY:
			stopSecurityAccess = System.currentTimeMillis();
			break;
		default:
			stopTestCase = System.currentTimeMillis();
			break;
		}
	}
	
	/**
	 * Returns the Security access execution elapsed time when measured or 0
	 * @return the milliseconds used to execute the security execution
	 */
	public final long getSecurityAccessElapsedTime() {
		return stopSecurityAccess>startSecurityAccess ? stopSecurityAccess - startSecurityAccess : 0;
	}
	
	/**
	 * Returns the Test Case full execution elapsed time when measured or 0
	 * @return the milliseconds used to execute the whole test case
	 */
	public final long getTestCaseElapsedTime() {
		return stopTestCase>startTestCase ? stopTestCase - startTestCase : 0;
	}
	
	/**
	 * Returns the Test Case execution elapsed time when measured or 0 if the timer is called to start and stop in 
	 * the automatedTest execution implementation.
	 * <br><b>It is used by the Test Engine please do not use it to avoid side effects on the timers</b>
	 * <br/>
	 * <br/>Anyway if you need to change the timing use the following methods :
	 * <br/>
	 * <br/>At the beginning of the test coding :
	 * <br/>{@link BaseTestCase#startTimeCounter(TIMER_TYPE)} using enumeration key : {@link TIMER_TYPE#TEST_ACTION}
	 * <br/>
	 * <br/>At the end of the test coding :
	 * <br/>{@link BaseTestCase#stopTimeCounter(TIMER_TYPE)} using enumeration key : {@link TIMER_TYPE#TEST_ACTION}
	 * @return the milliseconds used to execute the test
	 */
	public final long getTestActionElapsedTime() {
		return stopTestAction>startTestAction ? stopTestAction - startTestAction : 0;
	}
	
	/**
	 * Returns the Rendering execution elapsed time when measured or 0
	 * @return the milliseconds used to render the page
	 */
	public final long getRenderingElapsedTime() {
		return stopRendering>startRendering ? stopRendering - startRendering : 0;
	}
	
	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.TestCase#isSecureConnection()
	 */
	@Override
	public boolean isSecureConnection() {
		return false;
	}

	/**
	 * Provide the stored security info. This method is useful in case of security
	 * handling implementation
	 * @param securityInfo
	 */
	public final void setSecurityInfo(Map<String, String> securityInfo) {
		this.securityInfo = securityInfo;
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.TestCase#getSecurityInfo()
	 */
	@Override
	public final Map<String, String> getSecurityInfo() {
		return securityInfo;
	}

	/*
	 * This internal feature allow to change the value of a given field according to the 
	 * field name/id and the related to the byName decision's flag
	 */
	private static void setWebElementValue(WebDriver driver, boolean byName, String field, String value) throws NotFoundException, FrameworkException, ActionException {
		WebElement webElement = SeleniumUtilities.findOneInThePage(driver, byName ? By.name(field):By.id(field));
		if (webElement!=null) {
			SeleniumUtilities.setValueToElement(webElement, value);
		}
	}

	/*
	 * This internal feature allow to submit a given field according to the 
	 * field name/id and the related to the byName decision's flag
	 */
	private static void submitWebElement(WebDriver driver, boolean byName, String field) throws NotFoundException, FrameworkException, ActionException {
		WebElement webElement = SeleniumUtilities.findOneInThePage(driver, byName ? By.name(field):By.id(field));
		if (webElement!=null) {
			SeleniumUtilities.submitActionElement(webElement);
		}
	}

	/* 
	 * Form login base action lists implementation if required in the openUrl flag
	 * <br/>It is expected the following information for a multiple case list :
	 * <br/>Security information keys prefix : action{number of case 1..n} as simple {prefix}
	 * <br/>Available keys :
	 * <ul>
	 * <br/><li><b>{prefix}-name</b> Necessary to execute the action. It is printed in the log</li>
	 * <br/><li><b>{prefix}-url</b> Allow to change the URL before invoke the form actions</li>
	 * <br/><li><b>{prefix}-userField</b> The name or id of the user name field</li>
	 * <br/><li><b>{prefix}-userName</b> The value of the user name field</li>
	 * <br/><li><b>{prefix}-userFieldById</b> (0) Search user name field element by the Name attribute or (1) Search user name element by the Id  attribute</li>
	 * <br/><li><b>{prefix}-password</b> Field The name or id of the password field</li>
	 * <br/><li><b>{prefix}-password</b> The value of the password field</li>
	 * <br/><li><b>{prefix}-passwordFieldById</b> (0) Search password field element by the Name attribute or (1) Search password element by the Id  attribute</li>
	 * <br/><li><b>{prefix}-customFields</b> A custom field matcher and value list (1..N values) in the following format "{field1Name};{field1Value};{field1ById};....{fieldNName};{fieldNValue};{fieldNById}"</li>
	 * <br/><li><b>{prefix}-submitField</b> Element name or id to be submitted</li>
	 * <br/><li><b>{prefix}-submitFieldById</b> (0) Search submit field element by the Name attribute or (1) Search submit element by the Id  attribute</li>
	 * <br/><li><b>{prefix}-waitTimeoutMillis</b> Long value that represent the thread timeout after the submit if it is desired a next action.</li>
	 * </ul>
	 * @see com.selenium2.easy.test.server.cases.TestCase#handleSecureConnection(org.openqa.selenium.WebDriver)
	 */
	@Override
	public boolean handleSecureConnection(WebDriver driver) {
		/*
		 * Base implementation
		 * -------------------
		 * I suppose that case by case depending on the complexity of the login actions and web flows
		 * or the security protocols. It should be implemented according to the login form or the protocol. 
		 * For the login form (The current implementation) here an example :
		 * Set the user name field and password and then submit. If there is another for to fill make 
		 * the related actions and then continue up to the login is completed.
		 */
		if (!this.getSecurityInfo().containsKey("action1-name")) {
			/*
			 * Base URL connection if required if no action is available in the 
			 * security information and is required the secure connection anyway.
			 */
			if (this.isConnectionRequired()) {
				driver.get(this.getConnectionURL());
				return true;
			}
		}
		else {
			/*
			 * Form Actions required if required.
			 * In this case it is expected the following information for a multiple case list :
			 * Base prefix : action{number of case 1..n}
			 * Available keys :
			 * {prefix}-name Necessary to execute the action. It is printed in the log
			 * {prefix}-url Allow to change the URL before invoke the form actions
			 * {prefix}-userField The name or id of the user name field
			 * {prefix}-userName The value of the user name field
			 * {prefix}-userFieldById (0) Search user name field element by the Name attribute or (1) Search user name element by the Id  attribute
			 * {prefix}-password Field The name or id of the password field
			 * {prefix}-password The value of the password field
			 * {prefix}-passwordFieldById (0) Search password field element by the Name attribute or (1) Search password element by the Id  attribute
			 * {prefix}-customFields A custom field matcher and value list (1..N values) in the following format "{field1Name};{field1Value};{field1ById};....{fieldNName};{fieldNValue};{fieldNById}"
			 * {prefix}-submitField Element name or id to be submitted
			 * {prefix}-submitFieldById (0) Search submit field element by the Name attribute or (1) Search submit element by the Id  attribute
			 * {prefix}-waitTimeoutMillis Long value that represent the thread timeout after the submit if it is desired a next action.
			 */
			Map<String, String> securityInfos = this.getSecurityInfo();
			int actionId = 0;
			while(securityInfos.containsKey("action"+(actionId+1)+"-name")) {
				actionId++;
				String actionPrefix = "action"+actionId;
				getLogger().info("login action " + actionId+" : " + securityInfos.get(actionPrefix+"-name"));
				getLogger().info("login action " + actionId+" : Attempting login action ....");
				if (securityInfos.containsKey(actionPrefix+"-url")) {
					driver.get(securityInfos.get(actionPrefix+"-url"));
				}
				if (securityInfos.containsKey(actionPrefix+"-userField") && securityInfos.containsKey(actionPrefix+"-userName")) {
					boolean byName="1"!=securityInfos.get(actionPrefix+"-userFieldById") ? true : false;
					try {
						setWebElementValue(driver, byName, securityInfos.get(actionPrefix+"-userField"), securityInfos.get(actionPrefix+"-userName"));
					} catch (NotFoundException e) {
						getLogger().error("During the field user name setting an exception occurred for the login action : " + actionId+" due to", e);
					} catch (FrameworkException e) {
						getLogger().error("During the field user name setting an exception occurred for the login action : " + actionId+" due to", e);
					} catch (Throwable e) {
						getLogger().error("During the field user name setting an exception occurred for the login action : " + actionId+" due to", e);
					}
				}
				if (securityInfos.containsKey(actionPrefix+"-passwordField") && securityInfos.containsKey(actionPrefix+"-password")) {
					boolean byName="1"!=securityInfos.get(actionPrefix+"-passwordFieldById") ? true : false;
					try {
						setWebElementValue(driver, byName, securityInfos.get(actionPrefix+"-passwordField"), securityInfos.get(actionPrefix+"-password"));
					} catch (NotFoundException e) {
						getLogger().error("During the field password setting an exception occurred for the login action : " + actionId+" due to", e);
					} catch (FrameworkException e) {
						getLogger().error("During the field password setting an exception occurred for the login action : " + actionId+" due to", e);
					} catch (Throwable e) {
						getLogger().error("During the field password setting an exception occurred for the login action : " + actionId+" due to", e);
					}
				}
				if (securityInfos.containsKey(actionPrefix+"-customFields")) {
					try {
						String[] fieldCommands = securityInfos.get(actionPrefix+"-customFields").split(";");
						for (int i=0; i<fieldCommands.length; i+=3) {
							String field = fieldCommands[i];
							String value = fieldCommands[i+1];
							boolean byName="1"!= fieldCommands[i+2] ? true : false;
							try {
								setWebElementValue(driver, byName, field, value);
							} catch (NotFoundException e) {
								getLogger().error("During the field " + field + " setting an exception occurred for the login action : " + actionId+" due to", e);
							} catch (FrameworkException e) {
								getLogger().error("During the field " + field + " setting an exception occurred for the login action : " + actionId+" due to", e);
							} catch (Throwable e) {
								getLogger().error("During the field " + field + " setting an exception occurred for the login action : " + actionId+" due to", e);
							}
						}
					} catch (Throwable e) {
						getLogger().error("Failed to parse in the action : " + actionId+" and the custom fields: " + securityInfos.get(actionPrefix+"-customFields") + " due to", e);
					}
				}
				if (securityInfos.containsKey(actionPrefix+"-submitField")) {
					boolean byName="1"!=securityInfos.get(actionPrefix+"-submitFieldById") ? true : false;
					try {
						submitWebElement(driver, byName, securityInfos.get(actionPrefix+"-submitField"));
					} catch (NotFoundException e) {
						getLogger().error("During the element " + securityInfos.containsKey(actionPrefix+"-submitField") + " submit an exception occurred for the login action : " + actionId+" due to", e);
					} catch (FrameworkException e) {
						getLogger().error("During the element " + securityInfos.containsKey(actionPrefix+"-submitField") + " submit an exception occurred for the login action : " + actionId+" due to", e);
					} catch (Throwable e) {
						getLogger().error("During the element " + securityInfos.containsKey(actionPrefix+"-submitField") + " submit an exception occurred for the login action : " + actionId+" due to", e);
					}
					
				}
				if (securityInfos.containsKey(actionPrefix+"-waitTimeoutMillis")) {
					try {
						Thread.sleep(Long.parseLong(securityInfos.get(actionPrefix+"-waitTimeoutMillis")));
					} catch (NumberFormatException e) {
						getLogger().error("During the thread sleep an exception occurred for the login action : " + actionId+" due to", e);
					} catch (InterruptedException e) {
						getLogger().error("During the thread sleep an exception occurred for the login action : " + actionId+" due to", e);
					} catch (Throwable e) {
						getLogger().error("During the thread sleep an exception occurred for the login action : " + actionId+" due to", e);
					}
				}
				getLogger().info("login action " + actionId+" : Completed!!");
			}
			return actionId > 0;
		}
		return false;
	}

	/**
	 * Returns the action logger useful to print in the log some messages
	 * @return The logger instance
	 */
	public final Logger getLogger() {
		return logger;
	}
	
	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.TestCase#getCaseName()
	 */
	@Override
	public String getCaseName() {
		return caseName;
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.TestCase#getConnectionURL()
	 */
	@Override
	public final String getConnectionURL() {
		return caseURL;
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.TestCase#isConnectionRequired()
	 */
	@Override
	public final boolean isConnectionRequired() {
		return openUrl;
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.TestCase#isExceptionTransversable()
	 */
	@Override
	public final boolean isExceptionTransversable() {
		return retrowExcpetion;
	}

	/**
	 * Action that resets the counters it is used by the TestEngine. 
	 * Other uses can make unstable the test case time measuring. 
	 * It is suggested not to use this method during the case development.
	 */
	public final void resetCounters() {
		startTestCase = 0L;
		stopTestCase = 0L;
		startSecurityAccess = 0L;
		stopSecurityAccess = 0L;
		startRendering = 0L;
		stopRendering = 0L;
		startTestAction = 0L;
		stopTestAction = 0L;
	}

	@Override
	/*
	 * Clone feature implementation
	 */
	protected Object clone() throws CloneNotSupportedException {
		try {
			Class<? extends BaseTestCase> clazz = this.getClass();
			Constructor<? extends BaseTestCase> constructor = clazz.getConstructor(String.class, String.class, Boolean.class,
				Boolean.class);
			BaseTestCase testCaseCloned =  constructor.newInstance(this.caseName, this.caseURL, this.openUrl, this.retrowExcpetion);
			return testCaseCloned;
		} catch (NoSuchMethodException e) {
			throw new CloneNotSupportedException("Not suitable constructor in class : " + this.getClass().getName());
		} catch (SecurityException e) {
			throw new CloneNotSupportedException("Security exception accessing in the class : " + this.getClass().getName());
		} catch (Throwable e) {
			throw new CloneNotSupportedException("Unspecified clone exception in class : " + this.getClass().getName() + " message : " + e.getMessage());
		}
	}

}
