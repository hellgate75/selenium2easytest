package com.selenium2.easy.test.server.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

public class XMLTestOperation {

	@XmlAttribute(name="type", required=false)
	@XmlValue
	private OperationType operationType;
	
	@XmlAttribute(name="resultAs", required=false)
	private String resultAs;
	
	@XmlAttribute(name="useResult", required=false)
	private String useResult;
	
	@XmlAttribute(name="values", required=true)
	private List<String> valueList;

	@XmlAttribute(name="targetInSource", required=false)
	private Boolean targetInSource=Boolean.FALSE;

	@XmlElement(name="target", type=XMLWebElement.class, required=false)
	private XMLWebElement targetElement;

	@XmlElement(name="source", type=XMLWebElement.class, required=false)
	private XMLWebElement sourceElement;

	@XmlAttribute(name="operations", required=true)
	private List<XMLTestOperation> operationList;

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public String getResultAs() {
		return resultAs;
	}

	public void setResultAs(String resultAs) {
		this.resultAs = resultAs;
	}

	public String getUseResult() {
		return useResult;
	}

	public void setUseResult(String useResult) {
		this.useResult = useResult;
	}

	public List<String> getValueList() {
		return valueList;
	}

	public void setValueList(List<String> valueList) {
		this.valueList = valueList;
	}

	public XMLWebElement getTargetElement() {
		return targetElement;
	}

	public void setTargetElement(XMLWebElement targetElement) {
		this.targetElement = targetElement;
	}

	public XMLWebElement getSourceElement() {
		return sourceElement;
	}

	public void setSourceElement(XMLWebElement sourceElement) {
		this.sourceElement = sourceElement;
	}

	public List<XMLTestOperation> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<XMLTestOperation> operationList) {
		this.operationList = operationList;
	}

	public Boolean getTargetInSource() {
		return targetInSource;
	}

	public void setTargetInSource(Boolean targetInSource) {
		this.targetInSource = targetInSource;
	}

}
