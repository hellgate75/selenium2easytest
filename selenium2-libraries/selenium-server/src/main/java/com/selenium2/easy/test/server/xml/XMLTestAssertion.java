package com.selenium2.easy.test.server.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class XMLTestAssertion {

	@XmlAttribute(name="type", required=false)
	@XmlValue
	private AssertionType type;

	@XmlAttribute(name="thatMatcher", required=false)
	@XmlValue
	private AssertionThatMatcherType thatMatcherType;

	@XmlAttribute(name="title", required=true)
	private String assertionTitle;
	
	@XmlAttribute(name="useResult", required=false)
	private String useResult;
	
	@XmlAttribute(name="useMatcherResult", required=false)
	private String useMatcherResult;

	@XmlAttribute(name="operation", required=false)
	@XmlValue
	private AssertionOperationType operationType;

	@XmlAttribute(name="useValues", required=false)
	private Boolean useValue = Boolean.FALSE;
	
	@XmlAttribute(name="values", required=false)
	private List<String> values;
	
	@XmlAttribute(name="value", required=false)
	private String value;

	@XmlAttribute(name="useFile", required=false)
	private Boolean useTextFile = Boolean.FALSE;
	
	@XmlAttribute(name="file", required=false)
	private String textFile;
	
	public AssertionType getType() {
		return type;
	}

	public void setType(AssertionType type) {
		this.type = type;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public String getAssertionTitle() {
		return assertionTitle;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public String getUseMatcherResult() {
		return useMatcherResult;
	}

	public void setUseMatcherResult(String useMatcherResult) {
		this.useMatcherResult = useMatcherResult;
	}

	public void setOperationType(AssertionOperationType operationType) {
		this.operationType = operationType;
	}

	public Boolean getUseValue() {
		return useValue;
	}

	public void setUseValue(Boolean useValue) {
		this.useValue = useValue;
	}

	public Boolean getUseTextFile() {
		return useTextFile;
	}

	public void setUseTextFile(Boolean useTextFile) {
		this.useTextFile = useTextFile;
	}

	public AssertionThatMatcherType getThatMatcherType() {
		return thatMatcherType;
	}

	public void setThatMatcherType(AssertionThatMatcherType thatMatcherType) {
		this.thatMatcherType = thatMatcherType;
	}

}
