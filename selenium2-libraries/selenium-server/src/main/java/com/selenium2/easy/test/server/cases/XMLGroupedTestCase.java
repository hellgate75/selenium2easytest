/**
 * 
 */
package com.selenium2.easy.test.server.cases;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.utils.SeleniumUtilities;
import com.selenium2.easy.test.server.utils.XMLTestCaseUtilities;
import com.selenium2.easy.test.server.xml.XMLTestAssertion;
import com.selenium2.easy.test.server.xml.XMLTestCase;
import com.selenium2.easy.test.server.xml.XMLTestCaseAction;
import com.selenium2.easy.test.server.xml.XMLTestDOMAssertion;

/**
 * XML Configuration Test Case Execution Artifact.
 * @author Fabrizio Torelli
 * @see BaseTestCase
 */
public class XMLGroupedTestCase extends BaseTestCase {
	private String groupName;
	private XMLTestCase testCase;

	/**
	 * Constructor of the XML Grouped 
	 * @param groupName
	 * @param testCase
	 * @throws NullPointerException
	 * @throws RuntimeException
	 */
	public XMLGroupedTestCase(String groupName, XMLTestCase testCase) throws NullPointerException, RuntimeException {
		super(testCase.getName(), testCase.getConnectionURL()!=null ? testCase.getConnectionURL().getFormattedURL() : null, testCase.getUseUrl(), testCase.getRetrowException());
		this.groupName = groupName;
		this.testCase = testCase;
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.BaseTestCase#getCaseName()
	 */
	@Override
	public String getCaseName() {
		return this.groupName + ": " + this.caseName;
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.BaseTestCase#isSecureConnection()
	 */
	@Override
	public boolean isSecureConnection() {
		return testCase.getSecureAccess();
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.BaseTestCase#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new XMLGroupedTestCase(this.groupName, this.testCase);
	}

	private static synchronized Map<String, Object> executeCase(WebDriver driver, XMLTestCase testCase, Map<String, Object> previousReultsMap) throws ActionException {
		Map<String, Object> resultsMap = new HashMap<String, Object>(0);
		if (testCase.getTestCaseActions()!=null) {
			for(XMLTestCaseAction action: testCase.getTestCaseActions()) {
				Map<String, Object> temporaryMap = XMLTestCaseUtilities.doAction(driver, action);
				if (temporaryMap.size()>0) {
					resultsMap.putAll(temporaryMap);
				}
			}
		}
		if (testCase.getInheritEnvironment())
			previousReultsMap.putAll(resultsMap);
		if (testCase.getTestCaseAssertions()!=null) {
			for(XMLTestAssertion assertion: testCase.getTestCaseAssertions()) {
				long timeout = assertion.getAssertionTimeoutInSeconds();
				if (timeout>0) {
					SeleniumUtilities.waitForLoad(driver, timeout);
				}
				XMLTestCaseUtilities.doAssertion(driver, assertion, resultsMap);
			}
		}
		if (testCase.getTestCaseDOMAssertions()!=null) {
			for(XMLTestDOMAssertion assertion: testCase.getTestCaseDOMAssertions()) {
				long timeout = assertion.getAssertionTimeoutInSeconds();
				if (timeout>0) {
					SeleniumUtilities.waitForLoad(driver, timeout);
				}
				XMLTestCaseUtilities.doAssertion(driver, assertion, resultsMap);
			}
		}
		if (testCase.getChildrenCases()!=null) {
			for(XMLTestCase childCase: testCase.getChildrenCases()) {
				resultsMap = executeCase(driver, childCase, resultsMap);
			}
		}
		if (!testCase.getInheritEnvironment())
			previousReultsMap.putAll(resultsMap);
		return previousReultsMap;
	}
	
	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.TestCase#automatedTest(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void automatedTest(WebDriver driver) throws Throwable {
		Map<String, Object> resultsMap = new HashMap<String, Object>(0);
		executeCase(driver, this.testCase, resultsMap);
	}

}
