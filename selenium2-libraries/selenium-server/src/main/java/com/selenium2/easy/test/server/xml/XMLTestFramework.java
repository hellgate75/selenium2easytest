package com.selenium2.easy.test.server.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="testFramework")
public class XMLTestFramework {

	@XmlAttribute(name="name", required=true)
	private String frameworkName;

	@XmlAttribute(name="version", required=true)
	private String frameworkVersion;

	@XmlAttribute(name="allowDependencies", required=false)
	private Boolean allowDependencies = Boolean.FALSE;

	@XmlElementRef(name="cases", required=true)
	private List<XMLTestCase> testCases = new ArrayList<XMLTestCase>(0);

	public String getFrameworkName() {
		return frameworkName;
	}

	public void setFrameworkName(String frameworkName) {
		this.frameworkName = frameworkName;
	}

	public String getFrameworkVersion() {
		return frameworkVersion;
	}

	public void setFrameworkVersion(String frameworkVersion) {
		this.frameworkVersion = frameworkVersion;
	}

	public Boolean getAllowDependencies() {
		return allowDependencies;
	}

	public void setAllowDependencies(Boolean allowDependencies) {
		this.allowDependencies = allowDependencies;
	}

	public List<XMLTestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<XMLTestCase> testCases) {
		this.testCases = testCases;
	}

	
}
