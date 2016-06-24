package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

public class XMLTestDOMAssertion {

	@XmlAttribute(name="type", required=false)
	@XmlValue
	private AssertionType type;

	@XmlAttribute(name="thatMatcher", required=false)
	@XmlValue
	private AssertionThatMatcherType thatMatcherType;

	@XmlAttribute(name="title", required=true)
	private String assertionTitle;

	@XmlElement(name="element", type=XMLWebElement.class, required=true)
	private XMLWebElement assertionElement;

	@XmlElement(name="matchElement", type=XMLWebElement.class, required=false)
	private XMLWebElement matcherElement;
	
	@XmlAttribute(name="matchElement", required=false)
	@XmlValue
	private SearchType matchType;

	@XmlAttribute(name="attributeSource", required=false)
	private String attributeSource;

	@XmlAttribute(name="attributeMatcher", required=false)
	private String attributeMatcher;

	public AssertionType getType() {
		return type;
	}

	public void setType(AssertionType type) {
		this.type = type;
	}

	public XMLWebElement getAssertionElement() {
		return assertionElement;
	}

	public void setAssertionElement(XMLWebElement assertionElement) {
		this.assertionElement = assertionElement;
	}

	public XMLWebElement getMatcherElement() {
		return matcherElement;
	}

	public void setMatcherElement(XMLWebElement matcherElement) {
		this.matcherElement = matcherElement;
	}

	public SearchType getMatchType() {
		return matchType;
	}

	public void setMatchType(SearchType matchType) {
		this.matchType = matchType;
	}

	public String getAttributeSource() {
		return attributeSource;
	}

	public void setAttributeSource(String attributeSource) {
		this.attributeSource = attributeSource;
	}

	public String getAttributeMatcher() {
		return attributeMatcher;
	}

	public void setAttributeMatcher(String attributeMatcher) {
		this.attributeMatcher = attributeMatcher;
	}

	public String getAssertionTitle() {
		return assertionTitle;
	}

	public void setAssertionTitle(String assertionTitle) {
		this.assertionTitle = assertionTitle;
	}

	public AssertionThatMatcherType getThatMatcherType() {
		return thatMatcherType;
	}

	public void setThatMatcherType(AssertionThatMatcherType thatMatcherType) {
		this.thatMatcherType = thatMatcherType;
	}
	
}
