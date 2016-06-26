package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.selenium2.easy.test.server.cases.TestEngine;

/**
 * JAXB Class wrapper for the XMLTestDOMAssertion and it provides the assertion (for DOM Elements) to the {@link XMLTestCase} used by the {@link TestEngine}.
 * <br/>It is used by a TestCase in the TestEngine during the TestCase execution.
 * 
 * @see TestEngine
 * @see XMLTestURL
 * @see XMLTestCase
 * @see XMLTakeSnpshoot
 * @see XMLTestAssertion
 * @see XMLTestOperation
 * 
 * @author Fabrizio Torelli
 * 
 */
public class XMLTestDOMAssertion {

	private AssertionType type;

	private AssertionThatMatcherType thatMatcherType;

	private String assertionTitle;

	private Long assertionTimeoutInSeconds=0L;

	private XMLWebElement assertionElement;

	private XMLWebElement matcherElement;
	
	private String attributeSource;

	private String attributeMatcher;
	
	private XMLTakeSnpshoot assertionSnapshoot;

	/**
	 * Retrieves the Assertion type to use evaluating the expressions (see: {@link AssertionType})
	 * @return The {@link AssertionType} to use
	 */
	public AssertionType getType() {
		return type;
	}

	/**
	 * Sets the Assertion type to use evaluating the expressions (see: {@link AssertionType})
	 * @param type The {@link AssertionType} to use
	 */
	@XmlAttribute(name="type", required=false)
	public void setType(AssertionType type) {
		this.type = type;
	}

	/**
	 * Retrieves the Web Element selector containing information to query for one or more elements (see: {@link XMLWebElement})
	 * @return The {@link XMLWebElement} to use
	 */
	public XMLWebElement getAssertionElement() {
		return assertionElement;
	}

	/**
	 * Sets the Web Element selector containing information to query for one or more elements (see: {@link XMLWebElement})
	 * @param assertionElement The {@link XMLWebElement} to use
	 */
	@XmlElement(name="element", type=XMLWebElement.class, required=true)
	public void setAssertionElement(XMLWebElement assertionElement) {
		this.assertionElement = assertionElement;
	}

	/**
	 * Retrieves the matcher Web Element selector containing information to query for one or more elements (see: {@link XMLWebElement})
	 * @return The {@link XMLWebElement} to use
	 */
	public XMLWebElement getMatcherElement() {
		return matcherElement;
	}

	/**
	 * Sets the matcher Web Element selector containing information to query for one or more elements (see: {@link XMLWebElement})
	 * @param matcherElement The {@link XMLWebElement} to use
	 */
	@XmlElement(name="matchElement", type=XMLWebElement.class, required=false)
	public void setMatcherElement(XMLWebElement matcherElement) {
		this.matcherElement = matcherElement;
	}


	/**
	 * Retrieves source element attribute used to retrieve the value for the assertion
	 * @return The element attribute name
	 */
	public String getAttributeSource() {
		return attributeSource;
	}

	/**
	 * Sets source element attribute used to retrieve the value for the assertion
	 * @param attributeSource The element attribute name
	 */
	@XmlAttribute(name="attributeSource", required=false)
	public void setAttributeSource(String attributeSource) {
		this.attributeSource = attributeSource;
	}

	/**
s	 */
	public String getAttributeMatcher() {
		return attributeMatcher;
	}

	/**
	 * Sets matcher element attribute used to retrieve the value for the assertion
	 * @param matcherAttribute The element attribute name
	 */
	@XmlAttribute(name="attributeMatcher", required=false)
	public void setAttributeMatcher(String matcherAttribute) {
		this.attributeMatcher = matcherAttribute;
	}

	/**
	 * Retrieves the assertion description in case of failure
	 * @return the description of the assertion
	 */
	public String getAssertionTitle() {
		return assertionTitle;
	}

	/**
	 * Retrieves the assertion description in case of failure
	 * @param assertionTitle the description of the assertion
	 */
	@XmlAttribute(name="title", required=true)
	public void setAssertionTitle(String assertionTitle) {
		this.assertionTitle = assertionTitle;
	}

	/**
	 * Retrieves the matcher type for the 'That' assertion if needed (see: {@link AssertionThatMatcherType})
	 * @return The {@link AssertionThatMatcherType} that clause specific matcher
	 */
	public AssertionThatMatcherType getThatMatcherType() {
		return thatMatcherType;
	}

	/**
	 * Sets the matcher type for the 'That' assertion if needed (see: {@link AssertionThatMatcherType})
	 * @param thatMatcherType The {@link AssertionThatMatcherType} that clause specific matcher
	 */
	@XmlAttribute(name="thatMatcher", required=false)
	public void setThatMatcherType(AssertionThatMatcherType thatMatcherType) {
		this.thatMatcherType = thatMatcherType;
	}

	/**
	 * Retrieves the timeout for waiting the page ready after a reload
	 * @return The timeout value in seconds
	 */
	public Long getAssertionTimeoutInSeconds() {
		return assertionTimeoutInSeconds;
	}

	/**
	 * Sets the timeout for waiting the page ready after a reload
	 * @param assertionTimeoutInSeconds The timeout value in seconds
	 */
	@XmlAttribute(name="timeout", required=true)
	public void setAssertionTimeoutInSeconds(Long assertionTimeoutInSeconds) {
		this.assertionTimeoutInSeconds = assertionTimeoutInSeconds;
	}

	/**
	 * Retrieves the Take Snapshot Selenium2 based event used by the Test Case after the Assertion execution (see: {@link XMLTakeSnpshoot})
	 * @return The {@link XMLTakeSnpshoot}
	 */
	public XMLTakeSnpshoot getAssertionSnapshoot() {
		return assertionSnapshoot;
	}

	/**
	 * Sets the Take Snapshot Selenium2 based event used by the Test Case after the Assertion execution (see: {@link XMLTakeSnpshoot})
	 * @param actionSnapshoot The {@link XMLTakeSnpshoot}
	 */
	@XmlElement(name="snapshoot", type=XMLTakeSnpshoot.class, required=false)
	public void setAssertionSnapshoot(XMLTakeSnpshoot assertionSnapshoot) {
		this.assertionSnapshoot = assertionSnapshoot;
	}

}
