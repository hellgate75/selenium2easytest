package com.selenium2.easy.test.server.automated;

import com.selenium2.easy.test.server.xml.XMLTestGroup;

/**
 * Constants class used to merge the configuration file.
 * @author Fabrizio Torelli
 *
 */
public class SeleniumServerConstants {

	/**
	 * This attribute has the information about the enumeration distinguish the different WebDriver(s)
	 * <br/>See ${@link WebDriverFactory.SELECTOR_TYPE}
	 */
	public static final String driverSelector = "com.service.restfy.selenium.server.driverSelector";
	/**
	 * This attribute has the information about the enumeration distinguish the different WebDriver(s)
	 * <br/>See ${@link WebDriverFactory.SELECTOR_TYPE}
	 */
	public static final String driverSubSelector = "com.service.restfy.selenium.server.driverSubSelector";
	/**
	 * This attribute has the information about full Class path that is used to run the sub-driver
	 */
	public static final String driverSubDriver = "com.service.restfy.selenium.server.driverSubDriverClass";
	/**
	 * This attribute has the information about Selenium2 DesiredCapabilities to be loaded as Class full path
	 */
	public static final String driverCapabilities = "com.service.restfy.selenium.server.driverCapabilities";
	/**
	 * This attribute has the information about Selenium2 DriverService to be loaded as Class full path
	 */
	public static final String driverService = "com.service.restfy.selenium.server.driverService";
	/**
	 * This attribute has the information about Selenium2 DriverCommander to be loaded as Class full path
	 */
	public static final String driverCommander = "com.service.restfy.selenium.server.driverCommander";
	/**
	 * This attribute has the information about the TestCase class list comma-separated string (each token must be the full Class path of the TestCase extension class)
	 */
	public static final String testCaseClasses = "com.service.restfy.selenium.server.testCaseClasses";
	/**
	 * This attribute has the information about the package list comma-separated string (each token must be the literal package path containing the desired TestCase extension classes)
	 */
	public static final String testCasePackages = "com.service.restfy.selenium.server.testCasePackages";
	/**
	 * This attribute has the information about the system path containing the test cases xml definition files
	 * <br/>See: {@link XMLTestGroup} for the JAXB implementation
	 */
	public static final String testXmlDirectory = "com.service.restfy.selenium.server.xmlDirectory";
	/**
	 * This attribute has the information about the enabled/disabled state of the logging activities
	 */
	public static final String loggingActive = "com.service.restfy.selenium.server.loggingActive";
	/**
	 * This attribute has the information about the enabled/disabled state of the reporting service
	 */
	public static final String reportActive = "com.service.restfy.selenium.server.reportActive";
	/**
	 * This attribute has the information about the enabled/disabled state of the JSON reporting service
	 */
	public static final String reportJSONActive = "com.service.restfy.selenium.server.reportJSONActive";
	/**
	 * This attribute has the information about the JSon report output system file path
	 */
	public static final String outputJSon = "com.service.restfy.selenium.server.outputJSon";
	/**
	 * This attribute has the information about the log output system file path
	 */
	public static final String outputReport = "com.service.restfy.selenium.server.outputLog";
	/**
	 * This attribute has the information about the enabled/disabled state of the parallel TesCases's execution
	 */
	public static final String runParallel = "com.service.restfy.selenium.server.runParallel";
	/**
	 * This attribute has the information about the the TesCases's execution wanted parallel processes
	 */
	public static final String parallelInstances = "com.service.restfy.selenium.server.parallelInstances";
	/**
	 * This attribute has the information about the the TesCases's execution maximum parallel processes fetching
	 */
	public static final String parallelLimit = "com.service.restfy.selenium.server.parallelLimit";

}
