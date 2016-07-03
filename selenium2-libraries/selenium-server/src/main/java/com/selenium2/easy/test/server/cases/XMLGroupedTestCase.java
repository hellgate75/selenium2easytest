/**
 * 
 */
package com.selenium2.easy.test.server.cases;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;

import com.selenium2.easy.test.server.utils.FrameworkUtilities;
import com.selenium2.easy.test.server.xml.XMLTestCase;

/**
 * XML Configuration Test Case Execution Artifact.
 * @author Fabrizio Torelli
 * @see BaseTestCase
 */
public class XMLGroupedTestCase extends BaseTestCase {
	private String groupName;
	private XMLTestCase testCase;

	/**
	 * Constructor of the XML Grouped Test Case used by the TestEngine
	 * @param groupName The name of the current group
	 * @param testCase The Used test case
	 * @throws NullPointerException When a null Test Case XML object is used s parameter for the constructor
	 * @throws RuntimeException When any Test Case XML object data recovery exception occurs
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
		try {
			return this.getClass().getConstructor(String.class, XMLTestCase.class).newInstance(this.groupName, this.testCase);
		} catch (InstantiationException e) {
			throw new CloneNotSupportedException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new CloneNotSupportedException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new CloneNotSupportedException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new CloneNotSupportedException(e.getMessage());
		} catch (NoSuchMethodException e) {
			throw new CloneNotSupportedException(e.getMessage());
		} catch (SecurityException e) {
			throw new CloneNotSupportedException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.TestCase#automatedTest(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void automatedTest(WebDriver driver) throws Throwable {
		this.setCaseResults(FrameworkUtilities.executeXMLCase(this, driver, this.testCase, this.getCaseResults()));
	}

}
