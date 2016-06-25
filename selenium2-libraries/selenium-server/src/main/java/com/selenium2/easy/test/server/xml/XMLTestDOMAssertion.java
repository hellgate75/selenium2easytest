package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class XMLTestDOMAssertion {

	private AssertionType type;

	private AssertionThatMatcherType thatMatcherType;

	private String assertionTitle;

	private Long assertionTimeoutInSeconds=0L;

	private XMLWebElement assertionElement;

	private XMLWebElement matcherElement;
	
	private SearchType matchType;

	private String attributeSource;

	private String attributeMatcher;

	public AssertionType getType() {
		return type;
	}

	@XmlAttribute(name="type", required=false)
	public void setType(AssertionType type) {
		this.type = type;
	}

	public XMLWebElement getAssertionElement() {
		return assertionElement;
	}

	@XmlElement(name="element", type=XMLWebElement.class, required=true)
	public void setAssertionElement(XMLWebElement assertionElement) {
		this.assertionElement = assertionElement;
	}

	public XMLWebElement getMatcherElement() {
		return matcherElement;
	}

	@XmlElement(name="matchElement", type=XMLWebElement.class, required=false)
	public void setMatcherElement(XMLWebElement matcherElement) {
		this.matcherElement = matcherElement;
	}

	public SearchType getMatchType() {
		return matchType;
	}

	@XmlAttribute(name="matchElement", required=false)
	public void setMatchType(SearchType matchType) {
		this.matchType = matchType;
	}

	public String getAttributeSource() {
		return attributeSource;
	}

	@XmlAttribute(name="attributeSource", required=false)
	public void setAttributeSource(String attributeSource) {
		this.attributeSource = attributeSource;
	}

	public String getAttributeMatcher() {
		return attributeMatcher;
	}

	@XmlAttribute(name="attributeMatcher", required=false)
	public void setAttributeMatcher(String attributeMatcher) {
		this.attributeMatcher = attributeMatcher;
	}

	public String getAssertionTitle() {
		return assertionTitle;
	}

	@XmlAttribute(name="title", required=true)
	public void setAssertionTitle(String assertionTitle) {
		this.assertionTitle = assertionTitle;
	}

	public AssertionThatMatcherType getThatMatcherType() {
		return thatMatcherType;
	}

	@XmlAttribute(name="thatMatcher", required=false)
	public void setThatMatcherType(AssertionThatMatcherType thatMatcherType) {
		this.thatMatcherType = thatMatcherType;
	}

	public Long getAssertionTimeoutInSeconds() {
		return assertionTimeoutInSeconds;
	}

	@XmlAttribute(name="timeout", required=true)
	public void setAssertionTimeoutInSeconds(Long assertionTimeoutInSeconds) {
		this.assertionTimeoutInSeconds = assertionTimeoutInSeconds;
	}

}
