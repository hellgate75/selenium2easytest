/**
 * 
 */
package com.selenium2.easy.test.server.unireest.connector.cases;

import org.openqa.selenium.WebDriver;

import com.selenium2.easy.test.server.cases.XMLGroupedTestCase;
import com.selenium2.easy.test.server.utils.FrameworkUtilities;
import com.selenium2.easy.test.server.xml.WebMethod;
import com.selenium2.easy.test.server.xml.WebResponse;
import com.selenium2.easy.test.server.xml.XMLTestCase;

/**
 * XML Load Service Oriented Test Case, It do not use the WebDriver and the Selenium2 utilities to 
 * connect to the related service URL, it can be loaded in the XML Path load mechanism and execute the
 * loaded test case operations and assertions.
 * @author Fabrizio Torelli
 *
 */
public class XMLGroupedUniRestTestCase extends UniRestTestCase {
	
	/**
	 * Test Engine's uUsed Constructor for the {@link XMLGroupedTestCase} derived classes
	 * @param groupName The name of the current group
	 * @param testCase The Used test case
	 * @throws NullPointerException When a null Test Case XML object is used s parameter for the constructor
	 * @throws RuntimeException When any Test Case XML object data recovery exception occurs
	 */
	public XMLGroupedUniRestTestCase(String groupName, XMLTestCase testCase)
			throws NullPointerException, RuntimeException {
		super(groupName, testCase);
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.unireest.connector.cases.UniRestTestCase#getWebMethodType()
	 */
	@Override
	public WebMethod getWebMethodType() {
		return this.testCase!=null && this.testCase.getConnectionURL()!=null ? this.testCase.getConnectionURL().getWebMethod() : null;
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.unireest.connector.cases.UniRestTestCase#getWebResponseType()
	 */
	@Override
	public WebResponse getWebResponseType() {
		return this.testCase!=null && this.testCase.getConnectionURL()!=null ? this.testCase.getConnectionURL().getExpectedResponse() : null;
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.unireest.connector.cases.UniRestTestCase#automatedTest(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void automatedTest(WebDriver driver) throws Throwable {
		this.setCaseResults(FrameworkUtilities.executeXMLCase(this, driver, super.testCase, this.getCaseResults()));
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.TestCase#isWebDriverDriven()
	 */
	@Override
	public boolean isWebDriverDriven() {
		return false;
	}
	
}
