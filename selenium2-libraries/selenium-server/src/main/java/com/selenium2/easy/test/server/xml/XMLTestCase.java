package com.selenium2.easy.test.server.xml;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlValue;

import com.selenium2.easy.test.server.automated.WebDriverFactory.SELECTOR_TYPE;

public class XMLTestCase {
	@XmlAttribute(required=true)
	private String name;
	
	@XmlAttribute(required=false)
	private boolean monitorStartTime=Boolean.FALSE;

	@XmlAttribute(required=false)
	private boolean monitorEndTime=Boolean.FALSE;

	@XmlAttribute(required=false)
	private boolean monitorTestElapseTime=Boolean.FALSE;
	
	@XmlAttribute(required=false)
	private boolean monitorSecurityAccessTime=Boolean.FALSE;
	
	@XmlAttribute(required=false)
	private String connectionURL;
	
	@XmlAttribute(required=false)
	private boolean retrowException=Boolean.FALSE;
	
	@XmlAttribute(name="children", required=false)
	@XmlList
	private List<XMLTestCase> childrenCases;
	
	@XmlAttribute(required=true)
	private boolean useUrl;

	@XmlAttribute(required=true)
	private boolean secureAccess;

	@XmlAttribute(required=false)
	@XmlValue
	private SELECTOR_TYPE webDriverSelector;

	@XmlAttribute(name="security", required=false)
	private Map<String, String> securityInfo;
	
	@XmlAttribute(name="actions", required=true)
	@XmlList
	private List<XMLTestCaseAction> testCaseActions;

	@XmlAttribute(name="assertions", required=true)
	@XmlList
	private List<XMLTestAssertion> testCaseAssertions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMonitorStartTime() {
		return monitorStartTime;
	}

	public void setMonitorStartTime(boolean monitorStartTime) {
		this.monitorStartTime = monitorStartTime;
	}

	public boolean isMonitorEndTime() {
		return monitorEndTime;
	}

	public void setMonitorEndTime(boolean monitorEndTime) {
		this.monitorEndTime = monitorEndTime;
	}

	public boolean isMonitorTestElapseTime() {
		return monitorTestElapseTime;
	}

	public void setMonitorTestElapseTime(boolean monitorTestElapseTime) {
		this.monitorTestElapseTime = monitorTestElapseTime;
	}

	public boolean isMonitorSecurityAccessTime() {
		return monitorSecurityAccessTime;
	}

	public void setMonitorSecurityAccessTime(boolean monitorSecurityAccessTime) {
		this.monitorSecurityAccessTime = monitorSecurityAccessTime;
	}

	public String getConnectionURL() {
		return connectionURL;
	}

	public void setConnectionURL(String connectionURL) {
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
