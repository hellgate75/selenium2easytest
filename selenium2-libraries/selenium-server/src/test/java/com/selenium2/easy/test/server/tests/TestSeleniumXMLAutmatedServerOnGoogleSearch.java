package com.selenium2.easy.test.server.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;

import com.selenium2.easy.test.server.automated.SeleniumAutomatedServer;
import com.selenium2.easy.test.server.automated.WebDriverSelector;
import com.selenium2.easy.test.server.xml.sample.GenerateSampleXMLCaseGroup;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSeleniumXMLAutmatedServerOnGoogleSearch {
	static {
		WebDriverSelector.isInUnitTest = true;
		new File("target/reports/easytest").mkdirs();
	}

	private static SeleniumAutomatedServer automatedServer = new SeleniumAutomatedServer();
	private String configFileChrome = "src/test/resources/easytest/GoogleAutomatedEasyTest.properties";
	private String configFileIntenetExplore = "src/test/resources/easytest/IExplorerAutomatedEasyTest.properties";
	private String configFileFirefox = "src/test/resources/easytest/FirefoxAutomatedEasyTest.properties";
	private static final boolean isWindows = System.getProperty("os.name").toLowerCase().indexOf("win")>=0;
	
	@BeforeClass
	public static final void initTests() {
		GenerateSampleXMLCaseGroup.clearReportDirectory();
		GenerateSampleXMLCaseGroup.createDefaultTestGroup();
	}

	@AfterClass
	public static final void disposeTests() {
		GenerateSampleXMLCaseGroup.clearTestGroupDirectory();
	}
	
	
	@Test(timeout=25000)
	public void run0GoogleTestOnIExplorer() throws Throwable {
		if (isWindows) {
			automatedServer.readConfig(configFileIntenetExplore);
			automatedServer.startTests();
		}
		else {
		}
	}

	@Test(timeout=525000)
	public void run1GoogleTestOnChrome() throws Throwable {
		automatedServer.readConfig(configFileChrome);
		automatedServer.startTests();
	}
	

	@Test(timeout=25000)
	public void run2GoogleTestOnFirefox() throws Throwable {
		automatedServer.readConfig(configFileFirefox);
		automatedServer.startTests();
	}
	
}
