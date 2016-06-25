package com.selenium2.easy.test.server.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "action")
public class XMLTestCaseAction {
	
	private Boolean useURL = Boolean.FALSE;
	
	private XMLTestURL connectionURL;
	
	private XMLTakeSnpshoot caseSnapshoot;
	
	private List<XMLTestOperation> testOperations;

	private List<XMLTestAssertion> testAssertions;

	public Boolean getUseURL() {
		return useURL;
	}

	@XmlAttribute(required=true)
	public void setUseURL(Boolean changeURL) {
		this.useURL = changeURL;
	}

	public XMLTestURL getConnectionUrl() {
		return connectionURL;
	}

	@XmlElement(name="url", type=XMLTestURL.class,required=false)
	public void setConnectionUrl(XMLTestURL connectionUrl) {
		this.connectionURL = connectionUrl;
	}

	public XMLTakeSnpshoot getCaseSnapshoot() {
		return caseSnapshoot;
	}

	@XmlElement(name="snapshoot", type=XMLTakeSnpshoot.class, required=false)
	public void setCaseSnapshoot(XMLTakeSnpshoot caseSnapshoot) {
		this.caseSnapshoot = caseSnapshoot;
	}

	public List<XMLTestOperation> getTestOperations() {
		return testOperations;
	}

	@XmlElement(name="operation", required=true)
	public void setTestOperations(List<XMLTestOperation> testOperations) {
		this.testOperations = testOperations;
	}

	public List<XMLTestAssertion> getTestAssertions() {
		return testAssertions;
	}

	@XmlElement(name="assertion", required=true)
	public void setTestAssertions(List<XMLTestAssertion> testAssertions) {
		this.testAssertions = testAssertions;
	}

}
