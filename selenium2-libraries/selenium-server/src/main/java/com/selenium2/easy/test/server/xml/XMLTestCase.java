package com.selenium2.easy.test.server.xml;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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

	public List<XMLTestDOMAssertion> getTestCaseDOMAssertions() {
		return testCaseDOMAssertions;
	}

	@XmlElement(name="domAssertion", required=false)
	public void setTestCaseDOMAssertions(
			List<XMLTestDOMAssertion> testCaseDOMAssertions) {
		this.testCaseDOMAssertions = testCaseDOMAssertions;
	}

	public String getName() {
		return name;
	}

	@XmlAttribute(required=true)
	public void setName(String name) {
		this.name = name;
	}

	public XMLTestURL getConnectionURL() {
		return connectionURL;
	}

	@XmlElement(name="url", type=XMLTestURL.class,required=false)
	public void setConnectionURL(XMLTestURL connectionURL) {
		this.connectionURL = connectionURL;
	}

	public List<XMLTestCase> getChildrenCases() {
		return childrenCases;
	}

	@XmlElement(name="childCase", type=XMLTestCase.class, required=false)
	public void setChildrenCases(List<XMLTestCase> childrenCases) {
		this.childrenCases = childrenCases;
	}

	public Boolean getUseUrl() {
		return useUrl;
	}

	@XmlAttribute(required=false)
	public void setUseUrl(Boolean useUrl) {
		this.useUrl = useUrl;
	}

	public Boolean getSecureAccess() {
		return secureAccess;
	}

	@XmlAttribute(required=true)
	public void setSecureAccess(Boolean secureAccess) {
		this.secureAccess = secureAccess;
	}

	public Map<String, String> getSecurityInfo() {
		return securityInfo;
	}

	@XmlElement(name="security", required=false)
	public void setSecurityInfo(Map<String, String> securityInfo) {
		this.securityInfo = securityInfo;
	}

	public List<XMLTestCaseAction> getTestCaseActions() {
		return testCaseActions;
	}

	@XmlElement(name="action", required=false)
	public void setTestCaseActions(List<XMLTestCaseAction> testCaseActions) {
		this.testCaseActions = testCaseActions;
	}

	public Boolean getRetrowException() {
		return retrowException;
	}

	@XmlAttribute(required=false)
	public void setRetrowException(Boolean retrowException) {
		this.retrowException = retrowException;
	}

	public List<XMLTestAssertion> getTestCaseAssertions() {
		return testCaseAssertions;
	}

	@XmlElement(name="assertion", required=false)
	public void setTestCaseAssertions(List<XMLTestAssertion> testCaseAssertions) {
		this.testCaseAssertions = testCaseAssertions;
	}

	public Boolean getInheritEnvironment() {
		return inheritEnvironment;
	}

	@XmlAttribute(required=false)
	public void setInheritEnvironment(Boolean inheritEnvironment) {
		this.inheritEnvironment = inheritEnvironment;
	}
	
}
