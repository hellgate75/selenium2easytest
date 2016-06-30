package com.selenium2.easy.test.server.unireest.connector.cases;

import org.openqa.selenium.WebDriver;

import com.selenium2.easy.test.server.cases.BaseTestCase;
import com.selenium2.easy.test.server.cases.TestEngine;
import com.selenium2.easy.test.server.cases.unirest.IUniRestElement;
import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.unireest.connector.UniRestConnector;
import com.selenium2.easy.test.server.xml.XMLTestCase;
import com.selenium2.easy.test.server.xml.XMLTestURL;

/**
 * Basic Service Oriented Test Case, It do not use the WebDriver and the Selenium2 utilities to 
 * connect to the related service URL, it can be extended as class and the test controls and the
 * same implementation can be loaded in the XML Path load mechanism
 * @see UniRestConnector
 * @see WebDriver
 * @author Fabrizio Torelli
 *
 */
public abstract class UniRestTestCase extends BaseTestCase implements IUniRestElement {
	private XMLTestCase testCase;
	private String groupName;
	
	/**
	 * Constructor used to be installed by loadXMLPathFiles {@link TestEngine} configuration option
	 * @param caseName 
	 * @param testCase {@link XMLTestCase} used to configure the test operations
	 */
	public UniRestTestCase(String groupName, XMLTestCase testCase) {
		super(testCase.getName(), testCase.getConnectionURL().getFormattedURL(), testCase.getUseUrl(), testCase.getRetrowException());
		this.testCase = testCase;
		this.groupName = groupName;
	}

	/**
	 * Constructor used to be installed by loadByClassNames or loadByPackage {@link TestEngine} configuration options
	 */
	public UniRestTestCase() {
		super();
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
		// TODO Implements UniRestConnector connection with an URL
		return false;
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.BaseTestCase#handleSecureConnection(org.openqa.selenium.WebDriver)
	 */
	@Override
	public boolean handleSecureConnection(WebDriver driver) {
		try {
			return this.connectServiceURL();
		} catch (ActionException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retrieves the current {@link XMLTestCase} Configuration, when used in the loadXMLPathFiles mode
	 * @return The current {@link XMLTestCase} Configuration
	 */
	public XMLTestCase getTestCase() {
		return testCase;
	}

	/**
	 * Retrieves the current {@link XMLTestCase} Configuration, when used in the loadXMLPathFiles mode
	 * @param testCase The current {@link XMLTestCase} Configuration
	 */
	public void setTestCase(XMLTestCase testCase) {
		this.testCase = testCase;
	}

	/**
	 * Retrieves the Group Name, when used in the loadXMLPathFiles mode
	 * @return The Group name
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Sets the Group Name, when used in the loadXMLPathFiles mode
	 * @param groupName The Group name
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
