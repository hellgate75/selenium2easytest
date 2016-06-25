package com.selenium2.easy.test.server.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "assertion")
public class XMLTestAssertion {

	private AssertionType type;

	private AssertionThatMatcherType thatMatcherType;

	private String assertionTitle;

	private Long assertionTimeoutInSeconds=0L;
	
	private String useResult;
	
	private String useMatcherResult;

	private AssertionOperationType operationType;

	private Boolean useValue = Boolean.FALSE;
	
	private List<String> values;

	private Boolean useTextFile = Boolean.FALSE;
	
	private String textFile;
	
	public AssertionType getType() {
		return type;
	}

	@XmlAttribute(name="type", required=false)
	public void setType(AssertionType type) {
		this.type = type;
	}

	public List<String> getValues() {
		return values;
	}

	@XmlElement(name="value", required=false)
	public void setValues(List<String> values) {
		if (this.values==null) {
			this.values = values;
		}
		else {
			values.addAll(values);
		}
	}

	public String getAssertionTitle() {
		return assertionTitle;
	}

	public String getValue() {
		return values!=null && values.size()==1 ? values.get(0) : null;
	}

	@XmlTransient
	public void setValue(String value) {
		if (this.values==null) {
			this.values=new ArrayList<String>(0);
		}
		this.values.add(value);
	}

	@XmlAttribute(name="title", required=true)
	public void setAssertionTitle(String assertionTitle) {
		this.assertionTitle = assertionTitle;
	}

	public String getTextFile() {
		return textFile;
	}

	@XmlAttribute(name="file", required=false)
	public void setTextFile(String textFile) {
		this.textFile = textFile;
	}

	public String getUseResult() {
		return useResult;
	}

	@XmlAttribute(name="useResult", required=false)
	public void setUseResult(String useResult) {
		this.useResult = useResult;
	}

	public AssertionOperationType getOperationType() {
		return operationType;
	}

	public String getUseMatcherResult() {
		return useMatcherResult;
	}

	@XmlAttribute(name="useMatcherResult", required=false)
	public void setUseMatcherResult(String useMatcherResult) {
		this.useMatcherResult = useMatcherResult;
	}

	@XmlAttribute(name="operation", required=false)
	public void setOperationType(AssertionOperationType operationType) {
		this.operationType = operationType;
	}

	public Boolean getUseValue() {
		return useValue;
	}

	@XmlAttribute(name="useValues", required=false)
	public void setUseValue(Boolean useValue) {
		this.useValue = useValue;
	}

	public Boolean getUseTextFile() {
		return useTextFile;
	}

	@XmlAttribute(name="useFile", required=false)
	public void setUseTextFile(Boolean useTextFile) {
		this.useTextFile = useTextFile;
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
