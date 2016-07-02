package com.selenium2.easy.test.server.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.selenium2.easy.test.server.cases.TestEngine;
import com.selenium2.easy.test.server.cases.XMLGroupedTestCase;

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

	private String templateClass;

	private Boolean allowDependencies = Boolean.FALSE;

	private List<XMLTestCase> testCases = new ArrayList<XMLTestCase>(0);
	
	private XMLTakeSnpshoot groupSnapshoot;

	/**
	 * Retrieves the group name value
	 * @return The group name value
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Sets the group name value
	 * @param groupName The group name value
	 */
	@XmlAttribute(name="name", required=true)
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * Retrieves the group version value
	 * @return The group version value
	 */
	@XmlAttribute(name="version", required=true)
	public String getGroupVersion() {
		return groupVersion;
	}

	/**
	 * Sets the group version value
	 * @param groupVersion The group version value
	 */
	public void setGroupVersion(String groupVersion) {
		this.groupVersion = groupVersion;
	}
	/**
	 * Retrieves the Test Case full java path name of a derived class (an extended or inherited one) by the {@link XMLGroupedTestCase}.
	 * <br/>It is used to create all the test case instances that has not a template within the specific Test Case configurations.
	 * @return The full java path name of the case class
	 */
	public String getTemplateClass() {
		return templateClass;
	}

	/**
	 * Sets the Test Case full java path name of a derived class (an extended or inherited one) by the {@link XMLGroupedTestCase}.
	 * <br/>It is used to create all the test case instances that has not a template within the specific Test Case configurations.
	 * @param templateClass The full java path name of the case class
	 */
	@XmlAttribute(required=false)
	public void setTemplateClass(String templateClass) {
		this.templateClass = templateClass;
	}

	/**
	 * Retrieves the flag that allow the use of framework dependencies (Not used because actually this is not a plug-in project)
	 * @return The flag that allow the use of framework dependencies
	 */
	public Boolean getAllowDependencies() {
		return allowDependencies;
	}

	/**
	 * Sets the flag that allow the use of framework dependencies (Not used because actually this is not a plug-in project)
	 * @param allowDependencies The flag that allow the use of framework dependencies
	 */
	@XmlAttribute(name="allowDependencies", required=false)
	public void setAllowDependencies(Boolean allowDependencies) {
		this.allowDependencies = allowDependencies;
	}

	/**
	 * Retrieves the list of Test Cases to execute (see: {@link XMLTestCase})
	 * @return The {@link XMLTestCase}
	 */
	public List<XMLTestCase> getTestCases() {
		return testCases;
	}

	/**
	 * Sets the list of Test Cases to execute (see: {@link XMLTestCase})
	 * @param testCases The {@link XMLTestCase}
	 */
	@XmlElement(name="case", type=XMLTestCase.class, required=true)
	public void setTestCases(List<XMLTestCase> testCases) {
		this.testCases = testCases;
	}

	/**
	 * Retrieves the Take Snapshot Selenium2 based event used by the Test Group after the whole Test Cases execution (see: {@link XMLTakeSnpshoot})
	 * @return The {@link XMLTakeSnpshoot}
	 */
	public XMLTakeSnpshoot getGroupSnapshoot() {
		//TODO: Implements the TestEngine Snapshot execution code taking as example the XMLTestCaseUtilities public methods
		return groupSnapshoot;
	}

	/**
	 * Sets the Take Snapshot Selenium2 based event used by the Test Group after the whole Test Cases execution (see: {@link XMLTakeSnpshoot})
	 * @param groupSnapshoot The {@link XMLTakeSnpshoot}
	 */
	@XmlElement(name="snapshoot", type=XMLTakeSnpshoot.class, required=false)
	public void setGroupSnapshoot(XMLTakeSnpshoot groupSnapshoot) {
		this.groupSnapshoot = groupSnapshoot;
	}
}
