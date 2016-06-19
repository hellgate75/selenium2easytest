package com.selenium2.easy.test.server.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;

public class XMLTestCaseAction {
	
	@XmlAttribute(required=false)
	private Boolean changeURL=Boolean.FALSE;

	@XmlAttribute(required=false)
	private String connectionUrl;
	
	@XmlElement(name="snapshoot", type=XMLTakeSnpshoot.class, required=false)
	private XMLTakeSnpshoot caseSnapshoot;
	
	@XmlAttribute(name="operations", required=true)
	@XmlList
	private List<XMLTestOperation> testOperations;

	@XmlAttribute(name="assertions", required=true)
	@XmlList
	private List<XMLTestAssertion> testAssertions;

	public Boolean getChangeURL() {
		return changeURL;
	}

	public void setChangeURL(Boolean changeURL) {
		this.changeURL = changeURL;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
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
