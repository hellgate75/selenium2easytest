package com.selenium2.easy.test.server.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="caseGroup")
public class XMLTestGroup {

	@XmlAttribute(name="name", required=true)
	private String groupName;

	@XmlAttribute(name="version", required=true)
	private String groupVersion;

	@XmlAttribute(name="testClass", required=false)
	private String implementationClassFullName;

	@XmlAttribute(name="allowDependencies", required=false)
	private Boolean allowDependencies = Boolean.FALSE;

	@XmlAttribute(name="cases", required=true)
	@XmlList
	private List<XMLTestCase> testCases = new ArrayList<XMLTestCase>(0);
	
	@XmlElement(name="snapshoot", type=XMLTakeSnpshoot.class, required=false)
	private XMLTakeSnpshoot groupSnapshoot;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupVersion() {
		return groupVersion;
	}

	public void setGroupVersion(String groupVersion) {
		this.groupVersion = groupVersion;
	}

	public String getImplementationClassFullName() {
		return implementationClassFullName;
	}

	public void setImplementationClassFullName(String implementationClassFullName) {
		this.implementationClassFullName = implementationClassFullName;
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

	public XMLTakeSnpshoot getGroupSnapshoot() {
		return groupSnapshoot;
	}

	public void setGroupSnapshoot(XMLTakeSnpshoot groupSnapshoot) {
		this.groupSnapshoot = groupSnapshoot;
	}
	
}
