package selenium2_easy_test_plugin.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.maven.plugins.annotations.Parameter;

import com.selenium2.easy.test.server.automated.SeleniumServerConstants;
import com.selenium2.easy.test.server.automated.WebDriverFactory.SELECTOR_TYPE;

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

	public SELECTOR_TYPE getSelector() {
		return selector;
	}

	public void setSelector(SELECTOR_TYPE selector) {
		this.selector = selector;
	}

	public SELECTOR_TYPE getSubSelector() {
		return subSelector;
	}

	public void setSubSelector(SELECTOR_TYPE subSelector) {
		this.subSelector = subSelector;
	}

	public String getSubSelectorDriver() {
		return subSelectorDriver;
	}

	public void setSubSelectorDriver(String subSelectorDriver) {
		this.subSelectorDriver = subSelectorDriver;
	}

	public String getDriverCapabilities() {
		return driverCapabilities;
	}

	public void setDriverCapabilities(String driverCapabilities) {
		this.driverCapabilities = driverCapabilities;
	}

	public String getDriverService() {
		return driverService;
	}

	public void setDriverService(String driverService) {
		this.driverService = driverService;
	}

	public String getDriverCommander() {
		return driverCommander;
	}

	public void setDriverCommander(String driverCommander) {
		this.driverCommander = driverCommander;
	}

	public List<String> getTestCaseClasses() {
		return testCaseClasses;
	}

	public void setTestCaseClasses(List<String> testCaseClasses) {
		this.testCaseClasses = testCaseClasses;
	}

	public String getTestCasePackage() {
		return testCasePackage;
	}

	public void setTestCasePackage(String testCasePackage) {
		this.testCasePackage = testCasePackage;
	}

	public String getTestCaseXMLDirectory() {
		return testCaseXMLDirectory;
	}

	public void setTestCaseXMLDirectory(String testCaseXMLDirectory) {
		this.testCaseXMLDirectory = testCaseXMLDirectory;
	}

	public Boolean getTestCaseLoggingActive() {
		return testCaseLoggingActive;
	}

	public void setTestCaseLoggingActive(Boolean testCaseLoggingActive) {
		this.testCaseLoggingActive = testCaseLoggingActive;
	}

	public Boolean getTextReportActive() {
		return textReportActive;
	}

	public void setTextReportActive(Boolean textReportActive) {
		this.textReportActive = textReportActive;
	}

	public Boolean getJSONReportActive() {
		return JSONReportActive;
	}

	public void setJSONReportActive(Boolean jSONReportActive) {
		JSONReportActive = jSONReportActive;
	}

	public String getTextReportFile() {
		return textReportFile;
	}

	public void setTextReportFile(String textReportFile) {
		this.textReportFile = textReportFile;
	}

	public String getJSONReportFile() {
		return JSONReportFile;
	}

	public void setJSONReportFile(String jSONReportFile) {
		JSONReportFile = jSONReportFile;
	}

	public Boolean getRunParallel() {
		return runParallel;
	}

	public void setRunParallel(Boolean runParallel) {
		this.runParallel = runParallel;
	}

	public Integer getParallelInstances() {
		return parallelInstances;
	}

	public void setParallelInstances(Integer parallelInstances) {
		this.parallelInstances = parallelInstances;
	}

	public Integer getParallelLimit() {
		return parallelLimit;
	}

	public void setParallelLimit(Integer parallelLimit) {
		this.parallelLimit = parallelLimit;
	}

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
