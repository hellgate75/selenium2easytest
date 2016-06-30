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
	
	private XMLTakeSnpshoot assertionSnapshoot;
	
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
	 * Retrieves the list of values to be used for the Assertion if needed.
	 * <br/>
	 * <br/> There are some special values case and syntax sensible such as:
	 * <br/> <b>Dimension -> $D(w,h)</b>      - Where w is the width and h is the height of the Dimension
	 * - <b>Can be used to match an expression with a size got from a WebElement</b>
	 * <br/> <b>Location  -> $P(x,y)</b>      - Where x and y are the screen coordinates of the Point
	 * - <b>Can be used to match an expression with the location got from a WebElement</b>
	 * <br/> <b>Rectangle -> $R(x,y,w,h)</b>  - Where x and y are the screen coordinates of the Top right vertex Point and w is the width and h is the height of the Dimension of the Rectangle
	 * - <b>Can be used to match an expression with the size got from a WebElement</b>
	 * <br/> <b>Object -> $O(fullClassName)</b>  - Where fullClassName is the full package class name of the object with the default constructor to get the new instance
	 * - <b>Can be used to match an expression with some object referring to the original ones</b>
	 * <br/> <b>From Map -> $M(variable)</b>  - Where variable is the variable name of the object previously saved in the results map
	 * - <b>Can be used to match an expression with some object referring to the original ones</b>
	 * <br/>
	 * @return The list of String values
	 */
	public List<String> getValues() {
		return values;
	}

	/**
	 * Sets the list of values to be used for the Assertion if needed.
	 * <br/>
	 * <br/> There are some special values case and syntax sensible such as:
	 * <br/> <b>Dimension -> $D(w,h)</b>      - Where w is the width and h is the height of the Dimension
	 * - <b>Can be used to match an expression with a size got from a WebElement</b>
	 * <br/> <b>Location  -> $P(x,y)</b>      - Where x and y are the screen coordinates of the Point
	 * - <b>Can be used to match an expression with the location got from a WebElement</b>
	 * <br/> <b>Rectangle -> $R(x,y,w,h)</b>  - Where x and y are the screen coordinates of the Top right vertex Point and w is the width and h is the height of the Dimension of the Rectangle
	 * - <b>Can be used to match an expression with the size got from a WebElement</b>
	 * <br/> <b>Object -> $O(fullClassName)</b>  - Where fullClassName is the full package class name of the object with the default constructor to get the new instance
	 * - <b>Can be used to match an expression with some object referring to the original ones</b>
	 * <br/> <b>From Map -> $M(variable)</b>  - Where variable is the variable name of the object previously saved in the results map
	 * - <b>Can be used to match an expression with some object referring to the original ones</b>
	 * <br/>
	 * @param values The list of String values
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
	 * Retrieves the assertion description in case of failure
	 * @return the description of the assertion
	 */
	public String getAssertionTitle() {
		return assertionTitle;
	}

	/**
	 * Retrieves the assertion description in case of failure
	 * @param assertionTitle the description of the assertion
	 */
	@XmlAttribute(name="title", required=true)
	public void setAssertionTitle(String assertionTitle) {
		this.assertionTitle = assertionTitle;
	}

	/**
	 * Retrieves the value string to be used for the Assertion if needed.
	 * <br/>
	 * <br/> There are some special values case and syntax sensible such as:
	 * <br/> <b>Dimension -> $D(w,h)</b>      - Where w is the width and h is the height of the Dimension
	 * - <b>Can be used to match an expression with a size got from a WebElement</b>
	 * <br/> <b>Location  -> $P(x,y)</b>      - Where x and y are the screen coordinates of the Point
	 * - <b>Can be used to match an expression with the location got from a WebElement</b>
	 * <br/> <b>Rectangle -> $R(x,y,w,h)</b>  - Where x and y are the screen coordinates of the Top right vertex Point and w is the width and h is the height of the Dimension of the Rectangle
	 * - <b>Can be used to match an expression with the size got from a WebElement</b>
	 * <br/> <b>Object -> $O(fullClassName)</b>  - Where fullClassName is the full package class name of the object with the default constructor to get the new instance
	 * - <b>Can be used to match an expression with some object referring to the original ones</b>
	 * <br/> <b>From Map -> $M(variable)</b>  - Where variable is the variable name of the object previously saved in the results map
	 * - <b>Can be used to match an expression with some object referring to the original ones</b>
	 * <br/>
	 * @return The string value
	 */
	public String getValue() {
		return values!=null && values.size()==1 ? values.get(0) : null;
	}

	/**
	 * Sets the value string to be used for the Assertion if needed.
	 * <br/>
	 * <br/> There are some special values case and syntax sensible such as:
	 * <br/> <b>Dimension -> $D(w,h)</b>      - Where w is the width and h is the height of the Dimension
	 * - <b>Can be used to match an expression with a size got from a WebElement</b>
	 * <br/> <b>Location  -> $P(x,y)</b>      - Where x and y are the screen coordinates of the Point
	 * - <b>Can be used to match an expression with the location got from a WebElement</b>
	 * <br/> <b>Rectangle -> $R(x,y,w,h)</b>  - Where x and y are the screen coordinates of the Top right vertex Point and w is the width and h is the height of the Dimension of the Rectangle
	 * - <b>Can be used to match an expression with the size got from a WebElement</b>
	 * <br/> <b>Object -> $O(fullClassName)</b>  - Where fullClassName is the full package class name of the object with the default constructor to get the new instance
	 * - <b>Can be used to match an expression with some object referring to the original ones</b>
	 * <br/> <b>From Map -> $M(variable)</b>  - Where variable is the variable name of the object previously saved in the results map
	 * - <b>Can be used to match an expression with some object referring to the original ones</b>
	 * <br/>
	 * @param value The string value
	 */
	@XmlTransient
	public void setValue(String value) {
		if (this.values==null) {
			this.values=new ArrayList<String>(0);
		}
		this.values.add(value);
	}

	/**
	 * Retrieves the text file full path to be used as value in the assertion
	 * @return The file path
	 */
	public String getTextFile() {
		return textFile;
	}

	/**
	 * Sets the text file full path to be used as value in the assertion
	 * @param textFile The file path
	 */
	@XmlAttribute(name="file", required=false)
	public void setTextFile(String textFile) {
		this.textFile = textFile;
	}

	/**
	 * Retrieves The result variable name to be used as value
	 * @return The variable name
	 */
	public String getUseResult() {
		return useResult;
	}

	/**
	 * Sets the result variable name to be used as value
	 * @param useResult The variable name
	 */
	@XmlAttribute(name="useResult", required=false)
	public void setUseResult(String useResult) {
		this.useResult = useResult;
	}

	/**
	 * Retrieves the operation type to be used during the assertion execution ({@link AssertionOperationType})
	 * @return useResult The {@link AssertionOperationType} used by the {@link TestEngine}
	 */
	public AssertionOperationType getOperationType() {
		return operationType;
	}

	/**
	 * Sets the operation type to be used during the assertion execution ({@link AssertionOperationType})
	 * @param operationType useResult The {@link AssertionOperationType} used by the {@link TestEngine}
	 */
	@XmlAttribute(name="operation", required=false)
	public void setOperationType(AssertionOperationType operationType) {
		this.operationType = operationType;
	}

	/**
	 * Retrieves the environment result variable as matcher of the assertion
	 * @return The variable name
	 */
	public String getUseMatcherResult() {
		return useMatcherResult;
	}

	/**
	 * Sets the environment result variable as matcher of the assertion
	 * @param useMatcherResult The variable name
	 */
	@XmlAttribute(name="useMatcherResult", required=false)
	public void setUseMatcherResult(String useMatcherResult) {
		this.useMatcherResult = useMatcherResult;
	}

	/**
	 * Retrieves the flag to allow to use the values
	 * @return Flag to allow the use of values
	 */
	public Boolean getUseValue() {
		return useValue;
	}

	/**
	 * Sets the flag to allow to use the values
	 * @param useValue Flag to allow the use of values
	 */
	@XmlAttribute(name="useValues", required=false)
	public void setUseValue(Boolean useValue) {
		this.useValue = useValue;
	}

	/**
	 * Retrieves the flag that allow to use the text file full path to use as matcher value
	 * @return Flag that allow to use the text file path
	 */
	public Boolean getUseTextFile() {
		return useTextFile;
	}

	/**
	 * Sets the flag that allow to use the text file full path to use as matcher value
	 * @param useTextFile flag that allow to use the text file path
	 */
	@XmlAttribute(name="useFile", required=false)
	public void setUseTextFile(Boolean useTextFile) {
		this.useTextFile = useTextFile;
	}

	/** 
	 * Retrieves the matcher type for the 'That' assertion if needed (see: {@link AssertionThatMatcherType})
	 * @return The {@link AssertionThatMatcherType} that clause specific matcher
	 */
	public AssertionThatMatcherType getThatMatcherType() {
		return thatMatcherType;
	}

	/**
	 * Sets the matcher type for the 'That' assertion if needed (see: {@link AssertionThatMatcherType})
	 * @param thatMatcherType The {@link AssertionThatMatcherType} that clause specific matcher
	 */
	@XmlAttribute(name="thatMatcher", required=false)
	public void setThatMatcherType(AssertionThatMatcherType thatMatcherType) {
		this.thatMatcherType = thatMatcherType;
	}

	/**
	 * Retrieves the timeout for waiting the page ready after a reload
	 * @return The timeout value in seconds
	 */
	public Long getAssertionTimeoutInSeconds() {
		return assertionTimeoutInSeconds;
	}

	/**
	 * Sets the timeout for waiting the page ready after a reload
	 * @param assertionTimeoutInSeconds The timeout value in seconds
	 */
	@XmlAttribute(name="timeout", required=true)
	public void setAssertionTimeoutInSeconds(Long assertionTimeoutInSeconds) {
		this.assertionTimeoutInSeconds = assertionTimeoutInSeconds;
	}

	/**
	 * Retrieves the Take Snapshot Selenium2 based event used by the Test Case after the Assertion execution (see: {@link XMLTakeSnpshoot})
	 * @return The {@link XMLTakeSnpshoot}
	 */
	public XMLTakeSnpshoot getAssertionSnapshoot() {
		return assertionSnapshoot;
	}

	/**
	 * Sets the Take Snapshot Selenium2 based event used by the Test Case after the Assertion execution (see: {@link XMLTakeSnpshoot})
	 * @param actionSnapshoot The {@link XMLTakeSnpshoot}
	 */
	@XmlElement(name="snapshoot", type=XMLTakeSnpshoot.class, required=false)
	public void setAssertionSnapshoot(XMLTakeSnpshoot assertionSnapshoot) {
		this.assertionSnapshoot = assertionSnapshoot;
	}

}
