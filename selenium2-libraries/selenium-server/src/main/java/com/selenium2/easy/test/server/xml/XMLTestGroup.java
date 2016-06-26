package com.selenium2.easy.test.server.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.selenium2.easy.test.server.cases.TestEngine;

/**
 * JAXB Class wrapper for the TestCase container and the main features used in the framework.
 * <br/>It is used to define a Test Suite, grouping more test cases.
 * 
 * @see TestEngine
 * @see XMLTestURL
 * @see XMLTakeSnpshoot
 * 
 * @author Fabrizio Torelli
 * 
 */
@XmlRootElement(name="caseGroup")
public class XMLTestGroup {

	private String groupName;

	private String groupVersion;

	private String implementationClassFullName;

	private Boolean allowDependencies = Boolean.FALSE;

	private List<XMLTestCase> testCases = new ArrayList<XMLTestCase>(0);
	
	private XMLTakeSnpshoot groupSnapshoot;

	public String getGroupName() {
		return groupName;
	}

	@XmlAttribute(name="name", required=true)
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@XmlAttribute(name="version", required=true)
	public String getGroupVersion() {
		return groupVersion;
	}

	public void setGroupVersion(String groupVersion) {
		this.groupVersion = groupVersion;
	}

	public String getImplementationClassFullName() {
		return implementationClassFullName;
	}

	@XmlAttribute(name="implmentation", required=false)
	public void setImplementationClassFullName(String implementationClassFullName) {
		this.implementationClassFullName = implementationClassFullName;
	}

	public Boolean getAllowDependencies() {
		return allowDependencies;
	}

	@XmlAttribute(name="allowDependencies", required=false)
	public void setAllowDependencies(Boolean allowDependencies) {
		this.allowDependencies = allowDependencies;
	}

	public List<XMLTestCase> getTestCases() {
		return testCases;
	}

	@XmlElement(name="case", type=XMLTestCase.class, required=true)
	public void setTestCases(List<XMLTestCase> testCases) {
		this.testCases = testCases;
	}

	public XMLTakeSnpshoot getGroupSnapshoot() {
		return groupSnapshoot;
	}

	@XmlElement(name="snapshoot", type=XMLTakeSnpshoot.class, required=false)
	public void setGroupSnapshoot(XMLTakeSnpshoot groupSnapshoot) {
		this.groupSnapshoot = groupSnapshoot;
	}


}
