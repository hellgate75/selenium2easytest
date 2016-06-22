package com.selenium2.easy.test.server.xml;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import com.selenium2.easy.test.server.automated.WebDriverFactory.SELECTOR_TYPE;

@XmlRootElement(name = "case")
public class XMLTestCase {
	@XmlAttribute(required=true)
	private String name;
	
	@XmlAttribute(required=true)
	private boolean useUrl;
	
	@XmlElement(name="url", type=XMLTestURL.class,required=false)
	private XMLTestURL connectionURL;
	
	@XmlAttribute(required=false)
	private boolean retrowException=Boolean.FALSE;
	
	@XmlElement(name="children", type=XMLTestCase.class, required=false)
	private List<XMLTestCase> childrenCases;

	@XmlAttribute(required=true)
	private boolean secureAccess;

	@XmlAttribute(required=false)
	@XmlValue
	private SELECTOR_TYPE webDriverSelector;

	@XmlAttribute(name="security", required=false)
	private Map<String, String> securityInfo;
	
	@XmlAttribute(name="actions", required=false)
	private List<XMLTestCaseAction> testCaseActions;

	@XmlAttribute(name="assertions", required=false)
	private List<XMLTestAssertion> testCaseAssertions;

	@XmlAttribute(name="domAssertions", required=false)
	private List<XMLTestDOMAssertion> testCaseDOMAssertions;

	public List<XMLTestDOMAssertion> getTestCaseDOMAssertions() {
		return testCaseDOMAssertions;
	}

	public void setTestCaseDOMAssertions(
			List<XMLTestDOMAssertion> testCaseDOMAssertions) {
		this.testCaseDOMAssertions = testCaseDOMAssertions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public XMLTestURL getConnectionURL() {
		return connectionURL;
	}

	public void setConnectionURL(XMLTestURL connectionURL) {
		this.connectionURL = connectionURL;
	}

	public List<XMLTestCase> getChildrenCases() {
		return childrenCases;
	}

	public void setChildrenCases(List<XMLTestCase> childrenCases) {
		this.childrenCases = childrenCases;
	}

	public boolean isUseUrl() {
		return useUrl;
	}

	public void setUseUrl(boolean useUrl) {
		this.useUrl = useUrl;
	}

	public boolean isSecureAccess() {
		return secureAccess;
	}

	public void setSecureAccess(boolean secureAccess) {
		this.secureAccess = secureAccess;
	}

	public SELECTOR_TYPE getWebDriverSelector() {
		return webDriverSelector;
	}

	public void setWebDriverSelector(SELECTOR_TYPE webDriverSelector) {
		this.webDriverSelector = webDriverSelector;
	}

	public Map<String, String> getSecurityInfo() {
		return securityInfo;
	}

	public void setSecurityInfo(Map<String, String> securityInfo) {
		this.securityInfo = securityInfo;
	}

	public List<XMLTestCaseAction> getTestCaseActions() {
		return testCaseActions;
	}

	public void setTestCaseActions(List<XMLTestCaseAction> testCaseActions) {
		this.testCaseActions = testCaseActions;
	}

	public boolean isRetrowException() {
		return retrowException;
	}

	public void setRetrowException(boolean retrowException) {
		this.retrowException = retrowException;
	}

	public List<XMLTestAssertion> getTestCaseAssertions() {
		return testCaseAssertions;
	}

	public void setTestCaseAssertions(List<XMLTestAssertion> testCaseAssertions) {
		this.testCaseAssertions = testCaseAssertions;
	}
	
}
