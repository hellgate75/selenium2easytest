package com.selenium2.easy.test.server.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class XMLTestCaseAction {
	
	@XmlAttribute(required=true)
	private boolean useUrl;
	
	@XmlElement(name="url", type=XMLTestURL.class,required=false)
	private XMLTestURL connectionURL;
	
	@XmlElement(name="snapshoot", type=XMLTakeSnpshoot.class, required=false)
	private XMLTakeSnpshoot caseSnapshoot;
	
	@XmlAttribute(name="operations", required=true)
	private List<XMLTestOperation> testOperations;

	@XmlAttribute(name="assertions", required=true)
	private List<XMLTestAssertion> testAssertions;

	public boolean isChangeURL() {
		return useUrl;
	}

	public void setChangeURL(boolean changeURL) {
		this.useUrl = changeURL;
	}

	public XMLTestURL getConnectionUrl() {
		return connectionURL;
	}

	public void setConnectionUrl(XMLTestURL connectionUrl) {
		this.connectionURL = connectionUrl;
	}

	public XMLTakeSnpshoot getCaseSnapshoot() {
		return caseSnapshoot;
	}

	public void setCaseSnapshoot(XMLTakeSnpshoot caseSnapshoot) {
		this.caseSnapshoot = caseSnapshoot;
	}

	public List<XMLTestOperation> getTestOperations() {
		return testOperations;
	}

	public void setTestOperations(List<XMLTestOperation> testOperations) {
		this.testOperations = testOperations;
	}

	public List<XMLTestAssertion> getTestAssertions() {
		return testAssertions;
	}

	public void setTestAssertions(List<XMLTestAssertion> testAssertions) {
		this.testAssertions = testAssertions;
	}

}
