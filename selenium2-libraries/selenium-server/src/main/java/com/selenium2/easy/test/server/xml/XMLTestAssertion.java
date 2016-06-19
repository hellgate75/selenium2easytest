package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

public class XMLTestAssertion {

	@XmlAttribute(name="type", required=false)
	@XmlValue
	private AssertionsType type;

	@XmlAttribute(name="title", required=true)
	private String assertionTitle;

	@XmlElement(name="element", type=XMLWebElement.class, required=true)
	private XMLWebElement assertionElement;

	@XmlElement(name="matchElement", type=XMLWebElement.class, required=false)
	private XMLWebElement matcherElement;
	
	@XmlAttribute(name="match", required=false)
	@XmlValue
	private SearchType matchTpe;

	@XmlAttribute(name="value", required=false)
	private String value;
	
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

	public SearchType getMatchTpe() {
		return matchTpe;
	}

	public void setMatchTpe(SearchType matchTpe) {
		this.matchTpe = matchTpe;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
