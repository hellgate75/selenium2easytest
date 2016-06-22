package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

public class XMLTestDOMAssertion {

	@XmlAttribute(name="type", required=false)
	@XmlValue
	private AssertionsType type;

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

	public AssertionsType getType() {
		return type;
	}

	public void setType(AssertionsType type) {
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
}
