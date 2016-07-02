package com.selenium2.easy.test.server.xml;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.selenium2.easy.test.server.cases.TestEngine;
import com.selenium2.easy.test.server.cases.XMLGroupedTestCase;

/**
 * JAXB Class wrapper for the XMLTestCase and it provides the test features used by the {@link TestEngine}.
 * <br/>It is used by the TestGroup in the TestEngine during the Test Cases execution.
 * 
 * @see TestEngine
 * @see XMLGroupedTestCase
 * @see XMLTestGroup
 * @see XMLTestURL
 * @see XMLTestCase
 * @see XMLTestCaseAction
 * @see XMLTestAssertion
 * @see XMLTestDOMAssertion
 * 
 * @author Fabrizio Torelli
 * 
 */
@XmlRootElement(name = "case")
public class XMLTestCase {
	private String name;
	
	private  Boolean useUrl = Boolean.FALSE;
	
	private XMLTestURL connectionURL;
	
	private Boolean retrowException=Boolean.FALSE;
	
	private List<XMLTestCase> childrenCases;

	private Boolean secureAccess = Boolean.FALSE;

	private Boolean inheritEnvironment = Boolean.FALSE;

	private Map<String, String> securityInfo;
	
	private List<XMLTestCaseAction> testCaseActions;

	private List<XMLTestAssertion> testCaseAssertions;

	private List<XMLTestDOMAssertion> testCaseDOMAssertions;

	private String templateClass;

	/**
	 * Retrieves the list of DOM Assertions related to the Test Case (see: {@link XMLTestDOMAssertion})
	 * @return The {@link XMLTestDOMAssertion} list or null
	 */
	public List<XMLTestDOMAssertion> getTestCaseDOMAssertions() {
		return testCaseDOMAssertions;
	}

	/**
	 * Sets the list of DOM Assertions related to the Test Case (see: {@link XMLTestDOMAssertion})
	 * @param testCaseDOMAssertions The {@link XMLTestDOMAssertion} list (Default: null)
	 */
	@XmlElement(name="domAssertion", required=false)
	public void setTestCaseDOMAssertions(
			List<XMLTestDOMAssertion> testCaseDOMAssertions) {
		this.testCaseDOMAssertions = testCaseDOMAssertions;
	}

	/**
	 * Retrieves the Test Case name
	 * @return The Test Case name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Test Case name
	 * @param name The Test Case name
	 */
	@XmlAttribute(required=true)
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the URL object used by the Test Case to change the page location (see: {@link XMLTestURL})
	 * @return The {@link XMLTestURL}
	 */
	public XMLTestURL getConnectionURL() {
		return connectionURL;
	}

	/**
	 * Sets the URL object used by the Test Case to change the page location (see: {@link XMLTestURL})
	 * @param connectionURL The {@link XMLTestURL}
	 */
	@XmlElement(name="url", type=XMLTestURL.class,required=false)
	public void setConnectionURL(XMLTestURL connectionURL) {
		this.connectionURL = connectionURL;
	}

	/**
	 * Retrieves the children Test Cases objects executed after the Test Case execution
	 * @return The children Test Case list
	 */
	public List<XMLTestCase> getChildrenCases() {
		return childrenCases;
	}

	/**
	 * Sets the children Test Cases objects executed after the Test Case execution
	 * @param childrenCases The children Test Case list
	 */
	@XmlElement(name="childCase", type=XMLTestCase.class, required=false)
	public void setChildrenCases(List<XMLTestCase> childrenCases) {
		this.childrenCases = childrenCases;
	}

	/**
	 * Retrieves the flag of use the URL to change browser location
	 * @return The change of URL request flag
	 */
	public Boolean getUseUrl() {
		return useUrl;
	}

	/**
	 * Sets the flag of use the URL to change browser location
	 * @param useUrl The change of URL request flag
	 */
	@XmlAttribute(required=false)
	public void setUseUrl(Boolean useUrl) {
		this.useUrl = useUrl;
	}

	/**
	 * Retrieves the flag of use Secure Access to login the URL
	 * @return The secure access request flag
	 */
	public Boolean getSecureAccess() {
		return secureAccess;
	}

	/**
	 * Sets the flag of use Secure Access to login the URL
	 * @param secureAccess The secure access request flag
	 */
	@XmlAttribute(required=true)
	public void setSecureAccess(Boolean secureAccess) {
		this.secureAccess = secureAccess;
	}

	/**
	 * Retrieves the The Secure Access information
	 * <br/>In the current implementation is defined a set of operations on the form to login 
	 * as a list of number suffix attributes ({prefix} indicates a domain of 1..N number as are the login repeats sequentially) :
	 * <br/>
	 * <ul>
	 * <br/><li><b>{prefix}-name</b> Necessary to execute the action. It is printed in the log</li>
	 * <br/><li><b>{prefix}-url</b> Allow to change the URL before invoke the form actions</li>
	 * <br/><li><b>{prefix}-userField</b> The name or id of the user name field</li>
	 * <br/><li><b>{prefix}-userName</b> The value of the user name field</li>
	 * <br/><li><b>{prefix}-userFieldById</b> (0) Search user name field element by the Name attribute or (1) Search user name element by the Id  attribute</li>
	 * <br/><li><b>{prefix}-password</b> Field The name or id of the password field</li>
	 * <br/><li><b>{prefix}-password</b> The value of the password field</li>
	 * <br/><li><b>{prefix}-passwordFieldById</b> (0) Search password field element by the Name attribute or (1) Search password element by the Id  attribute</li>
	 * <br/><li><b>{prefix}-customFields</b> A custom field matcher and value list (1..N values) in the following format "{field1Name};{field1Value};{field1ById};....{fieldNName};{fieldNValue};{fieldNById}"</li>
	 * <br/><li><b>{prefix}-submitField</b> Element name or id to be submitted</li>
	 * <br/><li><b>{prefix}-submitFieldById</b> (0) Search submit field element by the Name attribute or (1) Search submit element by the Id  attribute</li>
	 * <br/><li><b>{prefix}-waitTimeoutMillis</b> Long value that represent the thread timeout after the submit if it is desired a next action.</li>
	 * </ul>
	 * @return The Secure Access information
	 */
	public Map<String, String> getSecurityInfo() {
		return securityInfo;
	}

	/**
	 * Retrieves the The Secure Access information
	 * <br/>In the current implementation is defined a set of operations on the form to login 
	 * as a list of number suffix attributes ({prefix} indicates a domain of 1..N number as are the login repeats sequentially) :
	 * <br/>
	 * <ul>
	 * <br/><li><b>{prefix}-name</b> Necessary to execute the action. It is printed in the log (e.g.: "Login to the main page.." or "Secondary security level", ....)</li>
	 * <br/><li><b>{prefix}-url</b> Allow to change the URL before invoke the form actions</li>
	 * <br/><li><b>{prefix}-userField</b> The name or id of the user name field</li>
	 * <br/><li><b>{prefix}-userName</b> The value of the user name field</li>
	 * <br/><li><b>{prefix}-userFieldById</b> (0) Search user name field element by the Name attribute or (1) Search user name element by the Id  attribute</li>
	 * <br/><li><b>{prefix}-password</b> Field The name or id of the password field</li>
	 * <br/><li><b>{prefix}-password</b> The value of the password field</li>
	 * <br/><li><b>{prefix}-passwordFieldById</b> (0) Search password field element by the Name attribute or (1) Search password element by the Id  attribute</li>
	 * <br/><li><b>{prefix}-customFields</b> A custom field matcher and value list (1..N values) in the following format "{field1Name};{field1Value};{field1ById};....{fieldNName};{fieldNValue};{fieldNById}"</li>
	 * <br/><li><b>{prefix}-submitField</b> Element name or id to be submitted</li>
	 * <br/><li><b>{prefix}-submitFieldById</b> (0) Search submit field element by the Name attribute or (1) Search submit element by the Id  attribute</li>
	 * <br/><li><b>{prefix}-waitTimeoutMillis</b> Long value that represent the thread timeout after the submit if it is desired a next action.</li>
	 * </ul>
	 * @param securityInfo The Secure Access information
	 */
	@XmlElement(name="security", required=false)
	public void setSecurityInfo(Map<String, String> securityInfo) {
		this.securityInfo = securityInfo;
	}

	/**
	 * Retrieves the list of Test Case Actions used by the Test Case to perform the Selenium2 operations (see: {@link XMLTestCaseAction})
	 * @return The {@link XMLTestCaseAction} list or null
	 */
	public List<XMLTestCaseAction> getTestCaseActions() {
		return testCaseActions;
	}

	/**
	 * Sets the list of Test Case Actions used by the Test Case to perform the Selenium2 operations (see: {@link XMLTestCaseAction})
	 * @param testCaseActions The {@link XMLTestCaseAction} list (Default: null)
	 */
	@XmlElement(name="action", required=false)
	public void setTestCaseActions(List<XMLTestCaseAction> testCaseActions) {
		this.testCaseActions = testCaseActions;
	}

	/**
	 * Retrieves the flag of re-throw the exception and stops the Test Case on the first exception occurs during the Actions execution
	 * @return The re-throw exception flag
	 */
	public Boolean getRetrowException() {
		return retrowException;
	}

	/**
	 * Sets the flag of re-throw the exception and stops the Test Case on the first exception occurs during the Actions execution
	 * @param retrowException The re-throw exception flag
	 */
	@XmlAttribute(required=false)
	public void setRetrowException(Boolean retrowException) {
		this.retrowException = retrowException;
	}

	/**
	 * Retrieves the list of Test Case Assertions used by the Test Case to verify on the Post Operations execution (see: {@link XMLTestAssertion})
	 * @return The {@link XMLTestAssertion} list or null
	 */
	public List<XMLTestAssertion> getTestCaseAssertions() {
		return testCaseAssertions;
	}

	/**
	 * Sets the list of Test Case Assertions used by the Test Case to verify on the Post Operations execution (see: {@link XMLTestAssertion})
	 * @param testCaseAssertions The {@link XMLTestAssertion} list or (Default: null)
	 */
	@XmlElement(name="assertion", required=false)
	public void setTestCaseAssertions(List<XMLTestAssertion> testCaseAssertions) {
		this.testCaseAssertions = testCaseAssertions;
	}

	/**
	 * Retrieves the flag of inherit the Test Case variable environment
	 * @return The environment variable inherits flag
	 */
	public Boolean getInheritEnvironment() {
		return inheritEnvironment;
	}

	/**
	 * Retrieves the flag of inherit the Test Case variable environment
	 * @param inheritEnvironment The environment variable inherits flag
	 */
	@XmlAttribute(required=false)
	public void setInheritEnvironment(Boolean inheritEnvironment) {
		this.inheritEnvironment = inheritEnvironment;
	}
	/**
	 * Retrieves the Test Case full java path name of a derived class (an extended or inherited one) by the {@link XMLGroupedTestCase}.
	 * <br/>It is used to create the test case instance within this Test Case configuration.
	 * @return The full java path name of the case class
	 */
	public String getTemplateClass() {
		return templateClass;
	}

	/**
	 * Sets the Test Case full java path name of a derived class (an extended or inherited one) by the {@link XMLGroupedTestCase}.
	 * <br/>It is used to create the test case instance within this Test Case configuration.
	 * @param templateClass The full java path name of the case class
	 */
	@XmlAttribute(required=false)
	public void setTemplateClass(String templateClass) {
		this.templateClass = templateClass;
	}

}
