package com.selenium2.easy.test.server.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "operation")
public class XMLTestOperation {

	private OperationType operationType;
	
	private String resultAs;
	
	private String useResult;
	
	private List<String> valueList;

	private Boolean targetInSource=Boolean.FALSE;

	private XMLWebElement targetElement;

	private XMLWebElement sourceElement;

	public OperationType getOperationType() {
		return operationType;
	}

	@XmlAttribute(name="type", required=false)
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public String getResultAs() {
		return resultAs;
	}

	@XmlAttribute(name="resultAs", required=false)
	public void setResultAs(String resultAs) {
		this.resultAs = resultAs;
	}

	public String getUseResult() {
		return useResult;
	}

	@XmlAttribute(name="useResult", required=false)
	public void setUseResult(String useResult) {
		this.useResult = useResult;
	}

	public List<String> getValueList() {
		return valueList;
	}

	@XmlElement(name="value", required=true)
	public void setValueList(List<String> valueList) {
		this.valueList = valueList;
	}

	public XMLWebElement getTargetElement() {
		return targetElement;
	}

	@XmlElement(name="target", type=XMLWebElement.class, required=false)
	public void setTargetElement(XMLWebElement targetElement) {
		this.targetElement = targetElement;
	}

	public XMLWebElement getSourceElement() {
		return sourceElement;
	}

	@XmlElement(name="source", type=XMLWebElement.class, required=false)
	public void setSourceElement(XMLWebElement sourceElement) {
		this.sourceElement = sourceElement;
	}

	public Boolean getTargetInSource() {
		return targetInSource;
	}

	@XmlAttribute(name="targetInSource", required=false)
	public void setTargetInSource(Boolean targetInSource) {
		this.targetInSource = targetInSource;
	}

}
