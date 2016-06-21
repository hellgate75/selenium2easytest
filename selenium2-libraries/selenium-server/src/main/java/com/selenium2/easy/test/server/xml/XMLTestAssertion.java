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
	
	@XmlAttribute(name="useResult", required=false)
	private String useResult;

	@XmlElement(name="element", type=XMLWebElement.class, required=true)
	private XMLWebElement assertionElement;

	@XmlElement(name="matchElement", type=XMLWebElement.class, required=false)
	private XMLWebElement matcherElement;
	
	@XmlAttribute(name="match", required=false)
	@XmlValue
	private SearchType matchTpe;

	@XmlAttribute(name="operation", required=false)
	@XmlValue
	private AssertionOperationType operationType;
	
	@XmlAttribute(name="value", required=false)
	private String value;

	@XmlAttribute(name="file", required=false)
	private String textFile;
	
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

	public String getAssertionTitle() {
		return assertionTitle;
	}

	public void setAssertionTitle(String assertionTitle) {
		this.assertionTitle = assertionTitle;
	}

	public String getTextFile() {
		return textFile;
	}

	public void setTextFile(String textFile) {
		this.textFile = textFile;
	}

	public String getUseResult() {
		return useResult;
	}

	public void setUseResult(String useResult) {
		this.useResult = useResult;
	}

	public AssertionOperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(AssertionOperationType operationType) {
		this.operationType = operationType;
	}

}
