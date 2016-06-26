package com.selenium2.easy.test.server.xml.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import  org.junit.Assert;

import com.selenium2.easy.test.server.automated.SeleniumServerConstants;
import com.selenium2.easy.test.server.automated.WebDriverFactory.SELECTOR_TYPE;
import com.selenium2.easy.test.server.utils.SeleniumUtilities;
import com.selenium2.easy.test.server.xml.AssertionOperationType;
import com.selenium2.easy.test.server.xml.AssertionType;
import com.selenium2.easy.test.server.xml.OperationType;
import com.selenium2.easy.test.server.xml.SearchType;
import com.selenium2.easy.test.server.xml.XMLTestAssertion;
import com.selenium2.easy.test.server.xml.XMLTestCase;
import com.selenium2.easy.test.server.xml.XMLTestCaseAction;
import com.selenium2.easy.test.server.xml.XMLTestGroup;
import com.selenium2.easy.test.server.xml.XMLTestOperation;
import com.selenium2.easy.test.server.xml.XMLTestURL;
import com.selenium2.easy.test.server.xml.XMLWebElement;

/**
 * @author Fabrizio Torelli
 *
 */
public class GenerateSampleXMLCaseGroup {
	private static final String googleSearchText = "Selenium2";

	/**
	 * Clear the Test Group Source Directory
	 */
	public static synchronized final void clearTestGroupDirectory() {
		File directory = new File("src/test/resources/easytest");
		if (directory.exists()) {
			if (directory.isDirectory()) {
				for(String f: directory.list()) {
					new File(f).delete();
				}
			}
			directory.delete();
		}
	}
	/**
	 * Clear the Test Group Report Directory
	 */
	public static synchronized final void clearReportDirectory() {
		File directory = new File("target/reports/easytest");
		if (directory.exists()) {
			if (directory.isDirectory()) {
				for(String f: directory.list()) {
					new File(f).delete();
				}
			}
			directory.delete();
		}
	}
	
	/**
	 * Create the Test UNIT TEST's Case Group XML File and the relevant 
	 * configuration test suite files for Mozilla Firefox, Google Chrome 
	 * and (in Windows OSs) Internet Explorer
	 */
	public static synchronized final void createDefaultTestGroup() {
		XMLTestGroup group = new XMLTestGroup();
		group.setGroupName("Google Sample Test");
		group.setGroupVersion("1.0");
		List<XMLTestCase> cases = new ArrayList<XMLTestCase>(0);
		XMLTestCase titleCase = new XMLTestCase();
		titleCase.setName("Test Google Page load title");
		titleCase.setUseUrl(true);
		XMLTestURL url = new XMLTestURL();
		url.setHostName("www.google.com");
		titleCase.setConnectionURL(url);
		
		List<XMLTestCaseAction> googleTitleActions = new ArrayList<XMLTestCaseAction>(0);
		XMLTestCaseAction googleLoadAction = new XMLTestCaseAction();
		List<XMLTestOperation> loadOperations = new ArrayList<XMLTestOperation>(0);
		XMLTestOperation loadGoogleTitle = new XMLTestOperation();
		loadGoogleTitle.setOperationType(OperationType.GET_PAGE_TITLE);
		loadGoogleTitle.setResultAs("pageTitle");
		loadOperations.add(loadGoogleTitle);
		googleLoadAction.setTestOperations(loadOperations);
		googleTitleActions.add(googleLoadAction);

		XMLTestAssertion titleAssertion = new XMLTestAssertion();
		titleAssertion.setType(AssertionType.EQUALS);
		titleAssertion.setAssertionTitle("The page title should equal Google at the start of the test.");
		titleAssertion.setValue("Google");
		titleAssertion.setUseResult("pageTitle");
		List<XMLTestAssertion> titleAssertions = new ArrayList<XMLTestAssertion>(0);
		titleAssertions.add(titleAssertion);
		XMLTestCaseAction googleTitleAction = new XMLTestCaseAction();
		googleTitleAction.setTestAssertions(titleAssertions);
		googleTitleActions.add(googleTitleAction);
		
		titleCase.setTestCaseActions(googleTitleActions);
		cases.add(titleCase);

		XMLTestCase googleSearchCase = new XMLTestCase();
		googleSearchCase.setUseUrl(false);
		googleSearchCase.setInheritEnvironment(true);
		googleSearchCase.setName("Test Google Page searh for a text");
		List<XMLTestOperation> searchCaseOperations = new ArrayList<XMLTestOperation>(0);
		List<XMLTestCaseAction> searchCaseActions = new ArrayList<XMLTestCaseAction>(0);
		XMLTestCaseAction googleSearchAction = new XMLTestCaseAction();
		
		XMLTestOperation searchForGoogleQueryField = new XMLTestOperation();
		searchForGoogleQueryField.setOperationType(OperationType.FIND_ONE);
		searchForGoogleQueryField.setResultAs("searchField");
		XMLWebElement webElementSerchField = new XMLWebElement();
		webElementSerchField.setBy(SearchType.NAME);
		webElementSerchField.setMultipleMatches(false);
		webElementSerchField.setSearchText("q");
		searchForGoogleQueryField.setSourceElement(webElementSerchField);
		searchCaseOperations.add(searchForGoogleQueryField);

		XMLTestOperation setGoogleSearchText = new XMLTestOperation();
		setGoogleSearchText.setOperationType(OperationType.SET_VALUE);
		setGoogleSearchText.setUseResult("searchField");
		List<String> valueList= new ArrayList<String>(0);
		valueList.add(googleSearchText);
		setGoogleSearchText.setValueList(valueList);
		searchCaseOperations.add(setGoogleSearchText);

		XMLTestOperation submitGoogleSearchText = new XMLTestOperation();
		submitGoogleSearchText.setOperationType(OperationType.SUBMIT_ACTION);
		submitGoogleSearchText.setUseResult("searchField");
		submitGoogleSearchText.setValueList(valueList);
		searchCaseOperations.add(submitGoogleSearchText);
		
		searchCaseOperations.add(loadGoogleTitle);
		
		googleSearchAction.setTestOperations(searchCaseOperations);

		XMLTestAssertion submitAssertion = new XMLTestAssertion();
		submitAssertion.setType(AssertionType.STARTS_WITH_IGNORE_CASE);
		submitAssertion.setAssertionTitle("The page title should start with the search string after the search.");
		submitAssertion.setOperationType(AssertionOperationType.GET_PAGE_TITLE);
		submitAssertion.setValue(googleSearchText);
		submitAssertion.setUseResult("pageTitle");
		List<XMLTestAssertion> submitAssertions = new ArrayList<XMLTestAssertion>(0);
		submitAssertions.add(submitAssertion);
		googleSearchAction.setTestAssertions(submitAssertions);
		
		searchCaseActions.add(googleSearchAction);
		googleSearchCase.setTestCaseActions(searchCaseActions);
		cases.add(googleSearchCase);
		
		group.setTestCases(cases);
		clearTestGroupDirectory();
		File directory = new File("src/test/resources/easytest");
		directory.mkdirs();
		//Try to save the xml file
		SeleniumUtilities.saveXMLTestFramework(group, new File("src/test/resources/easytest/GoogleSeleniumTestCaseGroup.xml"));
		createRunConfigs();
	}
	
	private static synchronized void createRunConfigs() {
		boolean isWindows = System.getProperty("os.name").toLowerCase().indexOf("win")>=0;
		Properties configFile = new Properties();
		configFile.put(SeleniumServerConstants.driverSelector, isWindows ? SELECTOR_TYPE.CHROME_INTERNAL_SELECTOR.toString() : SELECTOR_TYPE.CHROME_SELECTOR.toString());
		configFile.put(SeleniumServerConstants.outputJSon, "target/reports/easytest/reportEasyChromeTest.json");
		configFile.put(SeleniumServerConstants.outputReport, "target/reports/easytest/reportEasyChromeTest.txt");
		configFile.put(SeleniumServerConstants.testXmlDirectory, "src/test/resources/easytest");
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileOutputStream("src/test/resources/easytest/GoogleAutomatedEasyTest.properties"));
			configFile.store(writer, "Google Chrome Selenium2 Google Test Group");
			writer.close();
			FileInputStream fis = new FileInputStream("src/test/resources/easytest/GoogleAutomatedEasyTest.properties");
			configFile.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		configFile.put(SeleniumServerConstants.outputJSon, "target/reports/easytest/reportEasyFireforTest.json");
		configFile.put(SeleniumServerConstants.outputReport, "target/reports/easytest/reportEasyFireforTest.txt");
		configFile.put(SeleniumServerConstants.driverSelector, SELECTOR_TYPE.FIREFOX_SELECTOR.toString());
		try {
			writer = new PrintWriter(new FileOutputStream("src/test/resources/easytest/FirefoxAutomatedEasyTest.properties"));
			configFile.store(writer, "Mozilla Firefox Selenium2 Google Test Group");
			writer.close();
			FileInputStream fis = new FileInputStream("src/test/resources/easytest/FirefoxAutomatedEasyTest.properties");
			configFile.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		if (isWindows) {
			configFile.put(SeleniumServerConstants.outputJSon, "target/reports/easytest/reportEasyIExplorerTest.json");
			configFile.put(SeleniumServerConstants.outputReport, "target/reports/easytest/reportEasyIExplorerTest.txt");
			configFile.put(SeleniumServerConstants.driverSelector, SELECTOR_TYPE.IE_INTERNAL_SELECTOR.toString());
			try {
				writer = new PrintWriter(new FileOutputStream("src/test/resources/easytest/IExplorerAutomatedEasyTest.properties"));
				configFile.store(writer, "Microsoft Intenet Explorer Selenium2 Google Test Group");
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
}
	
	/**
	 * The main test for the creation of Unit test related TestGroup files and related local test
	 * @param args
	 */
	public static void main(String[] args) {
		//Create Default Test Group for the Google Selenium2 lookup
		createDefaultTestGroup();
		//Try to read and verify the xml file
		XMLTestGroup loadedGroup = SeleniumUtilities.loadXMLTestFramework(new File("src/test/resources/easytest/GoogleSeleniumTestCaseGroup.xml"));
		Assert.assertNotNull(loadedGroup);
		Assert.assertNotNull(loadedGroup.getTestCases());
		Assert.assertEquals(2, loadedGroup.getTestCases().size());
		System.out.println("UNIT TEST Group Cases summary");
		System.out.println("-----------------------------");
		System.out.println("Group name : " + loadedGroup.getGroupName());
		System.out.println("Group version : " + loadedGroup.getGroupVersion());
		System.out.println("Group has # of cases : " + loadedGroup.getTestCases().size());
	}

}
