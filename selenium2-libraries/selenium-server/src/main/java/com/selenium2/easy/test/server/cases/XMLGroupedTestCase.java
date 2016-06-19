/**
 * 
 */
package com.selenium2.easy.test.server.cases;

import org.openqa.selenium.WebDriver;

import com.selenium2.easy.test.server.utils.XMLTestCaseUtilities;
import com.selenium2.easy.test.server.xml.XMLTestAssertion;
import com.selenium2.easy.test.server.xml.XMLTestCase;
import com.selenium2.easy.test.server.xml.XMLTestCaseAction;

/**
 * XML Configuration Test Case Execution Artifact.
 * @author Fabrizio Torelli
 *
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
		super(testCase.getName(), testCase.getConnectionURL(), testCase.isUseUrl(), testCase.isRetrowException());
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
		return testCase.isSecureAccess();
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.BaseTestCase#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new XMLGroupedTestCase(this.groupName, this.testCase);
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.TestCase#automatedTest(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void automatedTest(WebDriver driver) throws Throwable {
		this.startTimeCounter(TIMER_TYPE.TEST_ACTION);
		for(XMLTestCaseAction action: this.testCase.getTestCaseActions()) {
			XMLTestCaseUtilities.doAction(driver, action);
		}
		for(XMLTestAssertion assertion: this.testCase.getTestCaseAssertions()) {
			XMLTestCaseUtilities.doAssertion(driver, assertion);
		}
		this.stopTimeCounter(TIMER_TYPE.TEST_ACTION);
	}

}
