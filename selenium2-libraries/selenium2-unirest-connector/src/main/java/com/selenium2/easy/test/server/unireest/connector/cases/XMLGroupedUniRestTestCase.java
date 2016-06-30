/**
 * 
 */
package com.selenium2.easy.test.server.unireest.connector.cases;

import org.openqa.selenium.WebDriver;

import com.selenium2.easy.test.server.cases.XMLGroupedTestCase;
import com.selenium2.easy.test.server.cases.unirest.IUniRestElement;
import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.xml.XMLTestCase;
import com.selenium2.easy.test.server.xml.XMLTestURL;

/**
 * @author Fabrizio Torelli
 *
 */
public class XMLGroupedUniRestTestCase extends XMLGroupedTestCase implements IUniRestElement {

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
	 * @see com.selenium2.easy.test.server.cases.unirest.IUniRestElement#connectServiceURL()
	 */
	@Override
	public boolean connectServiceURL() throws ActionException {
		// TODO Implements UniRestConnector connection
		return false;
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.unirest.IUniRestElement#connectServiceURL(com.selenium2.easy.test.server.xml.XMLTestURL)
	 */
	@Override
	public boolean connectServiceURL(XMLTestURL url) throws ActionException {
		// TODO Implements UniRestConnector connection
		return false;
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.BaseTestCase#handleSecureConnection(org.openqa.selenium.WebDriver)
	 */
	@Override
	public boolean handleSecureConnection(WebDriver driver) {
		return this.connectServiceURL();
	}

}
