package com.selenium2.easy.test.server.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.selenium2.easy.test.server.cases.TestEngine;

/**
 * JAXB Class wrapper for the XMLTestOperation and it provides the Selenium2 Operations to the {@link XMLTestCaseAction} used by the {@link TestEngine}.
 * <br/>It is used by a TestCaseAction in the TestEngine during the TestCase execution.
 * 
 * @see TestEngine
 * @see XMLTestCase
 * @see OperationType
 * @see XMLWebElement
 * 
 * @author Fabrizio Torelli
 * 
 */
@XmlRootElement(name = "operation")
public class XMLTestOperation {

	private OperationType operationType;
	
	private String resultAs;
	
	private String useResult;
	
	private List<String> valueList;

	private Boolean targetInSource=Boolean.FALSE;

	private XMLWebElement targetElement;

	private XMLWebElement sourceElement;

	/**
	 * Retrieves the OperationTypes used by the Test Case to perform the Selenium2 operation (see: {@link OperationType})
	 * @return The {@link OperationType}
	 */
	public OperationType getOperationType() {
		return operationType;
	}

	/**
	 * Sets the OperationTypes used by the Test Case to perform the Selenium2 operation (see: {@link OperationType})
	 * @param operationType The {@link OperationType}
	 */
	@XmlAttribute(name="type", required=false)
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	/**
	 * Retrieves the variable name used to save, in the environment, the operation value if needed
	 * @return The environment variable name
	 */
	public String getResultAs() {
		return resultAs;
	}

	/**
	 * Sets the variable name used to save in the environment the operation value if needed
	 * @param resultAs The environment variable name
	 */
	@XmlAttribute(name="resultAs", required=false)
	public void setResultAs(String resultAs) {
		this.resultAs = resultAs;
	}

	/**
	 * Retrieves the variable name used to retrieve in the environment the operation value if needed
	 * @return The environment variable name
	 */
	public String getUseResult() {
		return useResult;
	}

	/**
	 * Sets the variable name used to retrieve in the environment the operation value if needed
	 * @param useResult The environment variable name
	 */
	@XmlAttribute(name="useResult", required=false)
	public void setUseResult(String useResult) {
		this.useResult = useResult;
	}

	/**
	 * Retrieves the value list if needed
	 * <br/>
	 * <br/> There are some special values case and syntax sensible for any element in the list such as:
	 * <br/> <b>Dimension -> $D(w,h)</b>      - Where w is the width and h is the height of the Dimension
	 * - <b>Can be used to match an expression with a size got from a WebElement</b>
	 * <br/> <b>Location  -> $P(x,y)</b>      - Where x and y are the screen coordinates of the Point
	 * - <b>Can be used to match an expression with the location got from a WebElement</b>
	 * <br/> <b>Rectangle -> $R(x,y,w,h)</b>  - Where x and y are the screen coordinates of the Top right vertex Point and w is the width and h is the height of the Dimension of the Rectangle
	 * - <b>Can be used to match an expression with the size got from a WebElement</b>
	 * <br/>
	 * @return The value list
	 */
	public List<String> getValueList() {
		return valueList;
	}

	/**
	 * Sets the value list if needed
	 * <br/>
	 * <br/> There are some special values case and syntax sensible for any element in the list such as:
	 * <br/> <b>Dimension -> $D(w,h)</b>      - Where w is the width and h is the height of the Dimension
	 * - <b>Can be used to match an expression with a size got from a WebElement</b>
	 * <br/> <b>Location  -> $P(x,y)</b>      - Where x and y are the screen coordinates of the Point
	 * - <b>Can be used to match an expression with the location got from a WebElement</b>
	 * <br/> <b>Rectangle -> $R(x,y,w,h)</b>  - Where x and y are the screen coordinates of the Top right vertex Point and w is the width and h is the height of the Dimension of the Rectangle
	 * - <b>Can be used to match an expression with the size got from a WebElement</b>
	 * <br/>
	 * @param valueList The value list
	 */
	@XmlElement(name="value", required=true)
	public void setValueList(List<String> valueList) {
		this.valueList = valueList;
	}

	/**
	 * Retrieves the matcher Web Element selector containing information to query for one or more elements (see: {@link XMLWebElement})
	 * @return The {@link XMLWebElement} to use
	 */
	public XMLWebElement getTargetElement() {
		return targetElement;
	}

	/**
	 * Sets the matcher Web Element selector containing information to query for one or more elements (see: {@link XMLWebElement})
	 * @param targetElement The {@link XMLWebElement} to use
	 */
	@XmlElement(name="target", type=XMLWebElement.class, required=false)
	public void setTargetElement(XMLWebElement targetElement) {
		this.targetElement = targetElement;
	}

	/**
	 * Retrieves the source Web Element selector containing information to query for one or more elements (see: {@link XMLWebElement})
	 * @return The {@link XMLWebElement} to use
	 */
	public XMLWebElement getSourceElement() {
		return sourceElement;
	}

	/**
	 * Sets the source Web Element selector containing information to query for one or more elements (see: {@link XMLWebElement})
	 * @param sourceElement The {@link XMLWebElement} to use
	 */
	@XmlElement(name="source", type=XMLWebElement.class, required=false)
	public void setSourceElement(XMLWebElement sourceElement) {
		this.sourceElement = sourceElement;
	}

	/**
	 * Retrieves flag that allows to recover the marcher element using the source one
	 * @return The flag that allows to recover from the source element
	 */
	public Boolean getTargetInSource() {
		return targetInSource;
	}

	/**
	 * Sets flag that allows to recover the marcher element using the source one
	 * @param targetInSource The flag that allows to recover from the source element
	 */
	@XmlAttribute(name="targetInSource", required=false)
	public void setTargetInSource(Boolean targetInSource) {
		this.targetInSource = targetInSource;
	}

}
