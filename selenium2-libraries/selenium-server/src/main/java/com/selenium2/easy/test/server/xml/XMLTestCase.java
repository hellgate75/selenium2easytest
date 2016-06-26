package com.selenium2.easy.test.server.xml;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.selenium2.easy.test.server.cases.TestEngine;

/**
 * JAXB Class wrapper for the XMLTestCase and it provides the test features used by the {@link TestEngine}.
 * <br/>It is used by the TestGroup in the TestEngine during the Test Cases execution.
 * 
 * @see TestEngine
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

	/**
	 * @return
	 */
	public List<XMLTestDOMAssertion> getTestCaseDOMAssertions() {
		return testCaseDOMAssertions;
	}

	/**
	 * @param testCaseDOMAssertions
	 */
	@XmlElement(name="domAssertion", required=false)
	public void setTestCaseDOMAssertions(
			List<XMLTestDOMAssertion> testCaseDOMAssertions) {
		this.testCaseDOMAssertions = testCaseDOMAssertions;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	@XmlAttribute(required=true)
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public XMLTestURL getConnectionURL() {
		return connectionURL;
	}

	/**
	 * @param connectionURL
	 */
	@XmlElement(name="url", type=XMLTestURL.class,required=false)
	public void setConnectionURL(XMLTestURL connectionURL) {
		this.connectionURL = connectionURL;
	}

	/**
	 * @return
	 */
	public List<XMLTestCase> getChildrenCases() {
		return childrenCases;
	}

	/**
	 * @param childrenCases
	 */
	@XmlElement(name="childCase", type=XMLTestCase.class, required=false)
	public void setChildrenCases(List<XMLTestCase> childrenCases) {
		this.childrenCases = childrenCases;
	}

	/**
	 * @return
	 */
	public Boolean getUseUrl() {
		return useUrl;
	}

	/**
	 * @param useUrl
	 */
	@XmlAttribute(required=false)
	public void setUseUrl(Boolean useUrl) {
		this.useUrl = useUrl;
	}

	/**
	 * @return
	 */
	public Boolean getSecureAccess() {
		return secureAccess;
	}

	/**
	 * @param secureAccess
	 */
	@XmlAttribute(required=true)
	public void setSecureAccess(Boolean secureAccess) {
		this.secureAccess = secureAccess;
	}

	/**
	 * @return
	 */
	public Map<String, String> getSecurityInfo() {
		return securityInfo;
	}

	/**
	 * @param securityInfo
	 */
	@XmlElement(name="security", required=false)
	public void setSecurityInfo(Map<String, String> securityInfo) {
		this.securityInfo = securityInfo;
	}

	/**
	 * @return
	 */
	public List<XMLTestCaseAction> getTestCaseActions() {
		return testCaseActions;
	}

	/**
	 * @param testCaseActions
	 */
	@XmlElement(name="action", required=false)
	public void setTestCaseActions(List<XMLTestCaseAction> testCaseActions) {
		this.testCaseActions = testCaseActions;
	}

	/**
	 * @return
	 */
	public Boolean getRetrowException() {
		return retrowException;
	}

	/**
	 * @param retrowException
	 */
	@XmlAttribute(required=false)
	public void setRetrowException(Boolean retrowException) {
		this.retrowException = retrowException;
	}

	/**
	 * @return
	 */
	public List<XMLTestAssertion> getTestCaseAssertions() {
		return testCaseAssertions;
	}

	/**
	 * @param testCaseAssertions
	 */
	@XmlElement(name="assertion", required=false)
	public void setTestCaseAssertions(List<XMLTestAssertion> testCaseAssertions) {
		this.testCaseAssertions = testCaseAssertions;
	}

	/**
	 * @return
	 */
	public Boolean getInheritEnvironment() {
		return inheritEnvironment;
	}

	/**
	 * @param inheritEnvironment
	 */
	@XmlAttribute(required=false)
	public void setInheritEnvironment(Boolean inheritEnvironment) {
		this.inheritEnvironment = inheritEnvironment;
	}
	
}
