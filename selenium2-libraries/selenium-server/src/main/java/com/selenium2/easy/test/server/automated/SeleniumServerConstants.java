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
	public static final String driverSelector = "com.selenium2.easy.test.server.driverSelector";
	/**
	 * This attribute has the information about the enumeration distinguish the different WebDriver(s)
	 * <br/>See ${@link WebDriverFactory.SELECTOR_TYPE}
	 */
	public static final String driverSubSelector = "com.selenium2.easy.test.server.driverSubSelector";
	/**
	 * This attribute has the information about full Class path that is used to run the sub-driver
	 */
	public static final String driverSubDriver = "com.selenium2.easy.test.server.driverSubDriverClass";
	/**
	 * This attribute has the information about Selenium2 DesiredCapabilities to be loaded as Class full path
	 */
	public static final String driverCapabilities = "com.selenium2.easy.test.server.driverCapabilities";
	/**
	 * This attribute has the information about Selenium2 DriverService to be loaded as Class full path
	 */
	public static final String driverService = "com.selenium2.easy.test.server.driverService";
	/**
	 * This attribute has the information about Selenium2 DriverCommander to be loaded as Class full path
	 */
	public static final String driverCommander = "com.selenium2.easy.test.server.driverCommander";
	/**
	 * This attribute has the information about the TestCase class list comma-separated string (each token must be the full Class path of the TestCase extension class)
	 */
	public static final String testCaseClasses = "com.selenium2.easy.test.server.testCaseClasses";
	/**
	 * This attribute has the information about the package list comma-separated string (each token must be the literal package path containing the desired TestCase extension classes)
	 */
	public static final String testCasePackages = "com.selenium2.easy.test.server.testCasePackages";
	/**
	 * This attribute has the information about the system path containing the test cases xml definition files
	 * <br/>See: {@link XMLTestGroup} for the JAXB implementation
	 */
	public static final String testXmlDirectory = "com.selenium2.easy.test.server.xmlDirectory";
	/**
	 * This attribute has the information about the enabled/disabled state of the logging activities
	 */
	public static final String loggingActive = "com.selenium2.easy.test.server.loggingActive";
	/**
	 * This attribute has the information about the enabled/disabled state of the reporting service
	 */
	public static final String reportActive = "com.selenium2.easy.test.server.reportActive";
	/**
	 * This attribute has the information about the enabled/disabled state of the JSON reporting service
	 */
	public static final String reportJSONActive = "com.selenium2.easy.test.server.reportJSONActive";
	/**
	 * This attribute has the information about the JSon report output system file path
	 */
	public static final String outputJSon = "com.selenium2.easy.test.server.outputJSon";
	/**
	 * This attribute has the information about the log output system file path
	 */
	public static final String outputReport = "com.selenium2.easy.test.server.outputLog";
	/**
	 * This attribute has the information about the enabled/disabled state of the parallel TesCases's execution
	 */
	public static final String runParallel = "com.selenium2.easy.test.server.runParallel";
	/**
	 * This attribute has the information about the the TesCases's execution wanted parallel processes
	 */
	public static final String parallelInstances = "com.selenium2.easy.test.server.parallelInstances";
	/**
	 * This attribute has the information about the the TesCases's execution maximum parallel processes fetching
	 */
	public static final String parallelLimit = "com.selenium2.easy.test.server.parallelLimit";

}
