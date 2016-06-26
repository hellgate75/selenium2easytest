package com.selenium2.easy.test.server.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.selenium2.easy.test.server.cases.TestEngine;

/**
 * JAXB Class wrapper for the TestCaseAction and it provides the test operations and assertion to the {@link XMLTestCase} used by the {@link TestEngine}.
 * <br/>It is used by a TestCase in the TestEngine during the TestCase execution.
 * 
 * @see TestEngine
 * @see XMLTestURL
 * @see XMLTestCase
 * @see XMLTakeSnpshoot
 * @see XMLTestAssertion
 * @see XMLTestOperation
 * 
 * @author Fabrizio Torelli
 * 
 */
@XmlRootElement(name = "action")
public class XMLTestCaseAction {
	
	private Boolean useURL = Boolean.FALSE;
	
	private XMLTestURL connectionURL;
	
	private XMLTakeSnpshoot caseSnapshoot;
	
	private List<XMLTestOperation> testOperations;

	private List<XMLTestAssertion> testAssertions;

	/**
	 * Retrieves the flag of use the URL to change browser location
	 * @return The change of URL request flag
	 */
	public Boolean getUseURL() {
		return useURL;
	}

	/**
	 * Sets the flag of use the URL to change browser location
	 * @param changeURL The change of URL request flag
	 */
	@XmlAttribute(required=true)
	public void setUseURL(Boolean changeURL) {
		this.useURL = changeURL;
	}

	/**
	 * Retrieves the URL object used by the Test Case to change the page location (see: {@link XMLTestURL})
	 * @return The {@link XMLTestURL}
	 */
	public XMLTestURL getConnectionUrl() {
		return connectionURL;
	}

	/**
	 * Sets the URL object used by the Test Case to change the page location (see: {@link XMLTestURL})
	 * @param connectionUrl The {@link XMLTestURL}
	 */
	@XmlElement(name="url", type=XMLTestURL.class,required=false)
	public void setConnectionUrl(XMLTestURL connectionUrl) {
		this.connectionURL = connectionUrl;
	}

	/**
	 * Retrieves the Take Snapshot Selenium2 based event used by the Test Case after the Action execution (see: {@link XMLTakeSnpshoot})
	 * @return The {@link XMLTakeSnpshoot}
	 */
	public XMLTakeSnpshoot getCaseSnapshoot() {
		return caseSnapshoot;
	}

	/**
	 * Sets the Take Snapshot Selenium2 based event used by the Test Case after the Action execution (see: {@link XMLTakeSnpshoot})
	 * @param actionSnapshoot The {@link XMLTakeSnpshoot}
	 */
	@XmlElement(name="snapshoot", type=XMLTakeSnpshoot.class, required=false)
	public void setCaseSnapshoot(XMLTakeSnpshoot actionSnapshoot) {
		this.caseSnapshoot = actionSnapshoot;
	}

	/**
	 * Retrieves the list of Operations executed by the Test Case (see: {@link XMLTestOperation})
	 * @return The {@link XMLTestOperation} list or null
	 */
	public List<XMLTestOperation> getTestOperations() {
		return testOperations;
	}

	/**
	 * Sets the list of Operations executed by the Test Case (see: {@link XMLTestOperation})
	 * @param testOperations The {@link XMLTestOperation} list or null
	 */
	@XmlElement(name="operation", required=true)
	public void setTestOperations(List<XMLTestOperation> testOperations) {
		this.testOperations = testOperations;
	}

	/**
	 * Retrieves the list of Assertions executed by the Test Case after the operations using only the domestic environment variables (see: {@link XMLTestAssertion})
	 * @return The {@link XMLTestAssertion} list or null
	 */
	public List<XMLTestAssertion> getTestAssertions() {
		return testAssertions;
	}

	/**
	 * Sets the list of Assertions executed by the Test Case after the operations using only the domestic environment variables (see: {@link XMLTestAssertion})
	 * @param testAssertions The {@link XMLTestAssertion} list or null
	 */
	@XmlElement(name="assertion", required=true)
	public void setTestAssertions(List<XMLTestAssertion> testAssertions) {
		this.testAssertions = testAssertions;
	}

}
