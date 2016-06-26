package com.selenium2.easy.test.server.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.selenium2.easy.test.server.cases.TestEngine;

/**
 * JAXB Class wrapper for require an Assertion feature to the framework.
 * <br/>It is used by a TestCase or an Action in the TestEngine during the TestCase execution.
 * <br/><b>NOTE: The AssertionOperationType elements are not still operating on the 100%!!</b>
 * 
 * @see TestEngine
 * @see AssertionType
 * @see AssertionThatMatcherType
 * @see AssertionOperationType
 * 
 * @author Fabrizio Torelli
 * 
 */
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
	 * Retrieves the list of values to use for the Assertion if needed.
	 * <br/>
	 * <br/>
	 * <br/> There are some special values case and syntax sensible such as:
	 * <br/> <b>Dimension -> $D(w,h)</b>      - Where w is the width and h is the height of the Dimension
	 * - <b>Can be used to match an expression with a size got from a WebElement</b>
	 * <br/> <b>Location  -> $P(x,y)</b>      - Where x and y are the screen coordinates of the Point
	 * - <b>Can be used to match an expression with the location got from a WebElement</b>
	 * <br/> <b>Rectangle -> $R(x,y,w,h)</b>  - Where x and y are the screen coordinates of the Top right vertex Point and w is the width and h is the height of the Dimension of the Rectangle
	 * - <b>Can be used to match an expression with the size got from a WebElement</b>
	 * <br/>
	 * @return The list of String values
	 */
	public List<String> getValues() {
		return values;
	}

	/**
	 * @param values
	 */
	@XmlElement(name="value", required=false)
	public void setValues(List<String> values) {
		if (this.values==null) {
			this.values = values;
		}
		else {
			values.addAll(values);
		}
	}

	/**
	 * @return
	 */
	public String getAssertionTitle() {
		return assertionTitle;
	}

	/**
	 * @return
	 */
	public String getValue() {
		return values!=null && values.size()==1 ? values.get(0) : null;
	}

	/**
	 * @param value
	 */
	@XmlTransient
	public void setValue(String value) {
		if (this.values==null) {
			this.values=new ArrayList<String>(0);
		}
		this.values.add(value);
	}

	/**
	 * @param assertionTitle
	 */
	@XmlAttribute(name="title", required=true)
	public void setAssertionTitle(String assertionTitle) {
		this.assertionTitle = assertionTitle;
	}

	/**
	 * @return
	 */
	public String getTextFile() {
		return textFile;
	}

	/**
	 * @param textFile
	 */
	@XmlAttribute(name="file", required=false)
	public void setTextFile(String textFile) {
		this.textFile = textFile;
	}

	/**
	 * @return
	 */
	public String getUseResult() {
		return useResult;
	}

	/**
	 * @param useResult
	 */
	@XmlAttribute(name="useResult", required=false)
	public void setUseResult(String useResult) {
		this.useResult = useResult;
	}

	/**
	 * @return
	 */
	public AssertionOperationType getOperationType() {
		return operationType;
	}

	/**
	 * @return
	 */
	public String getUseMatcherResult() {
		return useMatcherResult;
	}

	/**
	 * @param useMatcherResult
	 */
	@XmlAttribute(name="useMatcherResult", required=false)
	public void setUseMatcherResult(String useMatcherResult) {
		this.useMatcherResult = useMatcherResult;
	}

	/**
	 * @param operationType
	 */
	@XmlAttribute(name="operation", required=false)
	public void setOperationType(AssertionOperationType operationType) {
		this.operationType = operationType;
	}

	/**
	 * @return
	 */
	public Boolean getUseValue() {
		return useValue;
	}

	/**
	 * @param useValue
	 */
	@XmlAttribute(name="useValues", required=false)
	public void setUseValue(Boolean useValue) {
		this.useValue = useValue;
	}

	/**
	 * @return
	 */
	public Boolean getUseTextFile() {
		return useTextFile;
	}

	/**
	 * @param useTextFile
	 */
	@XmlAttribute(name="useFile", required=false)
	public void setUseTextFile(Boolean useTextFile) {
		this.useTextFile = useTextFile;
	}

	/**
	 * @return
	 */
	public AssertionThatMatcherType getThatMatcherType() {
		return thatMatcherType;
	}

	/**
	 * @param thatMatcherType
	 */
	@XmlAttribute(name="thatMatcher", required=false)
	public void setThatMatcherType(AssertionThatMatcherType thatMatcherType) {
		this.thatMatcherType = thatMatcherType;
	}

	/**
	 * @return
	 */
	public Long getAssertionTimeoutInSeconds() {
		return assertionTimeoutInSeconds;
	}

	/**
	 * @param assertionTimeoutInSeconds
	 */
	@XmlAttribute(name="timeout", required=true)
	public void setAssertionTimeoutInSeconds(Long assertionTimeoutInSeconds) {
		this.assertionTimeoutInSeconds = assertionTimeoutInSeconds;
	}

}
