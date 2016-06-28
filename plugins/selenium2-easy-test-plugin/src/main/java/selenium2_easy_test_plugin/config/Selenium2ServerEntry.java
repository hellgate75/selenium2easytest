package selenium2_easy_test_plugin.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.maven.plugins.annotations.Parameter;

import com.selenium2.easy.test.server.automated.SeleniumServerConstants;
import com.selenium2.easy.test.server.automated.WebDriverFactory.SELECTOR_TYPE;

/**
 * Selenium2 Automated Server Framework Test Suite Configuration MOJO element
 * @author Fabrizio Torelli
 *
 */
public class Selenium2ServerEntry {
	@Parameter(required=true)
	private SELECTOR_TYPE selector;
	
	@Parameter(required=false)
	private SELECTOR_TYPE subSelector;
	
	@Parameter(required=false)
	private String subSelectorDriver;

	@Parameter(required=false)
	private String driverCapabilities;

	@Parameter(required=false)
	private String driverService;

	@Parameter(required=false)
	private String driverCommander;

	@Parameter(required=false)
	private List<String> testCaseClasses = new ArrayList<String>(0);

	@Parameter(required=false)
	private String testCasePackage;

	@Parameter(required=false)
	private String testCaseXMLDirectory;

	@Parameter(required=false)
	private Boolean testCaseLoggingActive = Boolean.FALSE;

	@Parameter(required=false)
	private Boolean textReportActive = Boolean.FALSE;

	@Parameter(required=false)
	private Boolean JSONReportActive = Boolean.FALSE;

	@Parameter(required=false)
	private String textReportFile;

	@Parameter(required=false)
	private String JSONReportFile;

	@Parameter(required=false)
	private Boolean runParallel = Boolean.FALSE;

	@Parameter(required=false)
	private Integer parallelInstances = 5;

	@Parameter(required=false)
	private Integer parallelLimit = 5;

	/**
	 * Retrieves the Primary WebDriver Selector (Identify the Selenium2 WebSriver Selector)
	 * @return The Primary WebDriver Selector
	 */
	public SELECTOR_TYPE getSelector() {
		return selector;
	}

	/**
	 * Sets the Primary WebDriver Selector (Identify the Selenium2 WebSriver Selector)
	 * @param selector The Primary WebDriver Selector
	 */
	public void setSelector(SELECTOR_TYPE selector) {
		this.selector = selector;
	}

	/**
	 * Retrieves the Slave WebDriver Selector (Identify the Selenium2 WebSriver Selector)
	 * @return The Slave WebDriver Selector
	 */
	public SELECTOR_TYPE getSubSelector() {
		return subSelector;
	}

	/**
	 * Sets the Slave WebDriver Selector (Identify the Selenium2 WebSriver Selector)
	 * @param subSelector The Slave WebDriver Selector
	 */
	public void setSubSelector(SELECTOR_TYPE subSelector) {
		this.subSelector = subSelector;
	}

	/**
	 * Retrieves the Slave Driver full class path
	 * @return The Slave Driver full class path
	 */
	public String getSubSelectorDriver() {
		return subSelectorDriver;
	}

	/**
	 * Sets the Slave Driver full class path
	 * @param subSelectorDriver The Slave Driver full class path
	 */
	public void setSubSelectorDriver(String subSelectorDriver) {
		this.subSelectorDriver = subSelectorDriver;
	}

	/**
	 * Retrieves the Primary Driver Capabilities full class path
	 * @return The Primary Driver Capabilities
	 */
	public String getDriverCapabilities() {
		return driverCapabilities;
	}

	/**
	 * Sets the Primary Driver Capabilities full class path
	 * @param driverCapabilities The Primary Driver Capabilities
	 */
	public void setDriverCapabilities(String driverCapabilities) {
		this.driverCapabilities = driverCapabilities;
	}

	/**
	 * Retrieves the Primary Driver Service full class path
	 * @return The Primary Driver Service
	 */
	public String getDriverService() {
		return driverService;
	}

	/**
	 * Sets the Primary Driver Service full class path
	 * @param driverService The Primary Driver Service
	 */
	public void setDriverService(String driverService) {
		this.driverService = driverService;
	}

	/**
	 * Retrieves the Primary Driver Commander full class path
	 * @return The Primary Driver Commander
	 */
	public String getDriverCommander() {
		return driverCommander;
	}

	/**
	 * Sets the Primary Driver Commander full class path
	 * @param driverCommander The Primary Driver Commander 
	 */
	public void setDriverCommander(String driverCommander) {
		this.driverCommander = driverCommander;
	}

	/**
	 * Retrieves the Test Case Classes list
	 * @return The Test Case Classes list
	 */
	public List<String> getTestCaseClasses() {
		return testCaseClasses;
	}

	/**
	 * Sets the Test Case Classes list
	 * @param testCaseClasses The Test Case Classes list
	 */
	public void setTestCaseClasses(List<String> testCaseClasses) {
		this.testCaseClasses = testCaseClasses;
	}

	/**
	 * Retrieves the Test Case Classes package name
	 * @return The Test Case Classes package name
	 */
	public String getTestCasePackage() {
		return testCasePackage;
	}

	/**
	 * Sets the Test Case Classes package name
	 * @param testCasePackage The Test Case Classes package name
	 */
	public void setTestCasePackage(String testCasePackage) {
		this.testCasePackage = testCasePackage;
	}

	/**
	 * Retrieves the Test Cases XML files directory full OS path
	 * @return The Test Cases XML files directory full OS path
	 */
	public String getTestCaseXMLDirectory() {
		return testCaseXMLDirectory;
	}

	/**
	 * Sets the Test Cases XML files directory full OS path
	 * @param testCaseXMLDirectory The Test Cases XML files directory full OS path
	 */
	public void setTestCaseXMLDirectory(String testCaseXMLDirectory) {
		this.testCaseXMLDirectory = testCaseXMLDirectory;
	}

	/**
	 * Retrieves the Console/Log file activity status
	 * @return The Console/Log file activity status
	 */
	public Boolean getTestCaseLoggingActive() {
		return testCaseLoggingActive;
	}

	/**
	 * Sets the Console/Log file activity status
	 * @param testCaseLoggingActive The Console/Log file activity status
	 */
	public void setTestCaseLoggingActive(Boolean testCaseLoggingActive) {
		this.testCaseLoggingActive = testCaseLoggingActive;
	}

	/**
	 * Retrieves the Text Format Report file enabled/disabled status
	 * @return The Text Format Report file enabled/disabled status
	 */
	public Boolean getTextReportActive() {
		return textReportActive;
	}

	/**
	 * Sets the Text Format Report file enabled/disabled status
	 * @param textReportActive The Text Format Report file enabled/disabled status
	 */
	public void setTextReportActive(Boolean textReportActive) {
		this.textReportActive = textReportActive;
	}

	/**
	 * Retrieves the JSON Format Report file enabled/disabled status
	 * @return The JSON Format Report file enabled/disabled status
	 */
	public Boolean getJSONReportActive() {
		return JSONReportActive;
	}

	/**
	 * Sets the JSON Format Report file enabled/disabled status
	 * @param jSONReportActive The JSON Format Report file enabled/disabled status
	 */
	public void setJSONReportActive(Boolean jSONReportActive) {
		JSONReportActive = jSONReportActive;
	}

	/**
	 * Retrieves the Text Format Report file path string
	 * @return The Text Format Report file path string
	 */
	public String getTextReportFile() {
		return textReportFile;
	}

	/**
	 * Sets the Text Format Report file path string
	 * @param textReportFile The Text Format Report file path string
	 */
	public void setTextReportFile(String textReportFile) {
		this.textReportFile = textReportFile;
	}

	/**
	 * Retrieves the JSON Format Report file path string
	 * @return The JSON Format Report file path string
	 */
	public String getJSONReportFile() {
		return JSONReportFile;
	}

	/**
	 * Sets the JSON Format Report file path string
	 * @param jSONReportFile The JSON Format Report file path string
	 */
	public void setJSONReportFile(String jSONReportFile) {
		JSONReportFile = jSONReportFile;
	}

	/**
	 * Retrieves the enabled/disabled status for the parallel testing
	 * @return The enabled/disabled status for the parallel testing
	 */
	public Boolean getRunParallel() {
		return runParallel;
	}

	/**
	 * Sets the enabled/disabled status for the parallel testing
	 * @param runParallel The enabled/disabled status for the parallel testing
	 */
	public void setRunParallel(Boolean runParallel) {
		this.runParallel = runParallel;
	}

	/**
	 * Retrieves the number of parallel Test Suites
	 * @return The number of parallel Test Suites
	 */
	public Integer getParallelInstances() {
		return parallelInstances;
	}

	/**
	 * Sets the number of parallel Test Suites
	 * @param parallelInstances The number of parallel Test Suites
	 */
	public void setParallelInstances(Integer parallelInstances) {
		this.parallelInstances = parallelInstances;
	}

	/**
	 * Retrieves the number maximum concurrent Test Suites
	 * @return The number of maximum concurrent Test Suites
	 */
	public Integer getParallelLimit() {
		return parallelLimit;
	}

	/**
	 * Sets the number maximum concurrent Test Suites
	 * @param parallelLimit The number of maximum concurrent Test Suites
	 */
	public void setParallelLimit(Integer parallelLimit) {
		this.parallelLimit = parallelLimit;
	}

	/**
	 * Retrieve the Properties file related to the {@link Selenium2ServerEntry}
	 * @return Properties file
	 */
	public Properties getMergedProperties() {
		Properties properties = new Properties();
		properties.put(SeleniumServerConstants.driverSelector, ""+this.selector);
		if (this.driverService!=null) {
			properties.put(SeleniumServerConstants.driverService, this.driverService);
		}
		if (this.driverCommander!=null) {
			properties.put(SeleniumServerConstants.driverCommander, this.driverCommander);
		}
		if (this.driverCapabilities!=null) {
			properties.put(SeleniumServerConstants.driverCapabilities, this.driverCapabilities);
		}
		if (this.subSelector!=null) {
			properties.put(SeleniumServerConstants.driverSubSelector, ""+this.subSelector);
		}
		if (this.subSelectorDriver!=null) {
			properties.put(SeleniumServerConstants.driverSubDriver, this.subSelectorDriver);
		}
		if (this.testCasePackage!=null) {
			properties.put(SeleniumServerConstants.testCasePackages, this.testCasePackage);
		}
		if (this.testCaseXMLDirectory!=null) {
			properties.put(SeleniumServerConstants.testXmlDirectory, this.testCaseXMLDirectory);
		}
		properties.put(SeleniumServerConstants.loggingActive, ""+this.testCaseLoggingActive.booleanValue());
		properties.put(SeleniumServerConstants.reportActive, ""+this.textReportActive.booleanValue());
		properties.put(SeleniumServerConstants.reportJSONActive, ""+this.JSONReportActive.booleanValue());
		properties.put(SeleniumServerConstants.runParallel, ""+this.runParallel.booleanValue());
		if(this.textReportActive && this.textReportFile!=null) {
			properties.put(SeleniumServerConstants.outputReport, this.textReportFile);
		}
		if(this.JSONReportActive && this.JSONReportFile!=null) {
			properties.put(SeleniumServerConstants.outputJSon, this.JSONReportFile);
		}
		if(this.runParallel) {
			properties.put(SeleniumServerConstants.parallelInstances, ""+this.parallelInstances);
			properties.put(SeleniumServerConstants.parallelLimit, ""+this.parallelLimit);
		}
		if(this.testCaseClasses.size()>0) {
			String outClassesList = "";
			for(String testClass: this.testCaseClasses) {
				outClassesList += (outClassesList.length()>0 ? "," : "") + testClass;
			}
			properties.put(SeleniumServerConstants.testCaseClasses, outClassesList);
		}
		return properties;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Selenium2ServerEntry [selector=" + selector + ", subSelector="
				+ subSelector + ", subSelectorDriver=" + subSelectorDriver
				+ ", driverCapabilities=" + driverCapabilities
				+ ", driverService=" + driverService + ", driverCommander="
				+ driverCommander + ", testCaseClasses=" + Arrays.toString(testCaseClasses.toArray())
				+ ", testCasePackage=" + testCasePackage
				+ ", testCaseXMLDirectory=" + testCaseXMLDirectory
				+ ", testCaseLoggingActive=" + testCaseLoggingActive
				+ ", textReportActive=" + textReportActive
				+ ", JSONReportActive=" + JSONReportActive
				+ ", textReportFile=" + textReportFile + ", JSONReportFile="
				+ JSONReportFile + ", runParallel=" + runParallel
				+ ", parallelInstances=" + parallelInstances
				+ ", parallelLimit=" + parallelLimit + "]";
	}
	
}
