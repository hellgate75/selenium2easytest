package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlEnum;

/**
	 * Enumeration that describes the different kinds of Operations available in the framework
	 * <br/>
	 * The members are :
	 * <br/><b>CLEAR_VALUE</b> - The operation used to clear the value of a WebElement
	 * <br/><b>CLICK_ACTION</b> - The operation used to click a WebElement
	 * <br/><b>FIND_ONE</b> - The operation used to retrieve one WebElement in the page
	 * <br/><b>FIND_MANY</b> - The operation used to retrieve one or more WebElements in the page
	 * <br/><b>FIND_ONE_WITHIN</b> - The operation used to retrieve one WebElement within another WebElemnt
	 * <br/><b>FIND_MANY_WITHIN</b> - The operation used to retrieve one or more WebElements within another WebElemnt
	 * <br/><b>GET_ATTRIBUTE</b> - The operation used to retrieve and save an environment variable with an attribute of a WebElement
	 * <br/><b>GET_CSS</b> - The operation used to retrieve and save an environment variable with the CSS classes of a WebElement
	 * <br/><b>GET_LOCATION</b> - The operation used to retrieve and save an environment variable with the location of a WebElement
	 * <br/><b>GET_PAGE_SOURCE</b> - The operation used to retrieve the page source code
	 * <br/><b>GET_PAGE_TITLE</b> - The operation used to retrieve the page title
	 * <br/><b>GET_RECT</b> - The operation used to retrieve and save an environment variable with the rectangle containing a WebElement
	 * <br/><b>GET_SIZE</b> - The operation used to retrieve and save an environment variable with the size of a WebElement
	 * <br/><b>GET_TAG</b> - The operation used to retrieve and save an environment variable with the HTML TAG of a WebElement
	 * <br/><b>IS_DISPLAYED</b> - The operation used to retrieve and save an environment variable with the UI display status of a WebElement
	 * <br/><b>IS_ENABLED</b> - The operation used to retrieve and save an environment variable with the enabled/disabled status of a WebElement
	 * <br/><b>IS_SELECTED</b> - The operation used to retrieve and save an environment variable with the selection status of a WebElement
	 * <br/><b>SET_VALUE</b> - The operation used to set the value of a WebElement
	 * <br/><b>SUBMIT_ACTION</b> - The operation used to submit a WebElement
	 * <br/><b>TAKE_SCREENSHOT_FROM</b> - The operation used to take a screenshot of a WebElement
	 * <br/><b>RETRIEVE_MAPPED_VALUE</b> - The operation used to retrieve an element from the mapped result variables
	 * <br/>
 * @author Fabrizio Torelli
 *
 */
@XmlEnum
public enum OperationType {
	/**
	 * The operation used to clear the value of a WebElement
	 */
	CLEAR_VALUE, 
	/**
	 * The operation used to click a WebElement
	 */
	CLICK_ACTION, 
	/**
	 * The operation used to retrieve one WebElement in the page
	 */
	FIND_ONE, 
	/**
	 * The operation used to retrieve one or more WebElements in the page
	 */
	FIND_MANY, 
	/**
	 * The operation used to retrieve one WebElement within another WebElemnt
	 */
	FIND_ONE_WITHIN, 
	/**
	 * The operation used to retrieve one or more WebElements within another WebElemnt
	 */
	FIND_MANY_WITHIN, 
	/**
	 * The operation used to retrieve and save an environment variable with an attribute of a WebElement
	 */
	GET_ATTRIBUTE, 
	/**
	 * The operation used to retrieve and save an environment variable with the CSS classes of a WebElement
	 */
	GET_CSS, 
	/**
	 * The operation used to retrieve and save an environment variable with the location of a WebElement
	 */
	GET_LOCATION,
	/**
	 * The operation used to retrieve the page source code
	 */
	GET_PAGE_SOURCE, 
	/**
	 * The operation used to retrieve the page title
	 */
	GET_PAGE_TITLE, 
	/**
	 * The operation used to retrieve and save an environment variable with the rectangle containing a WebElement
	 */
	GET_RECT, 
	/**
	 * The operation used to retrieve and save an environment variable with the size of a WebElement
	 */
	GET_SIZE, 
	/**
	 * The operation used to retrieve and save an environment variable with the HTML TAG of a WebElement
	 */
	GET_TAG, 
	/**
	 * The operation used to retrieve and save an environment variable with the UI display status of a WebElement
	 */
	IS_DISPLAYED, 
	/**
	 * The operation used to retrieve and save an environment variable with the enabled/disabled status of a WebElement
	 */
	IS_ENABLED, 
	/**
	 * The operation used to retrieve and save an environment variable with the selection status of a WebElement
	 */
	IS_SELECTED,
	/**
	 * The operation used to set the value of a WebElement
	 */
	SET_VALUE, 
	/**
	 * The operation used to submit a WebElement
	 */
	SUBMIT_ACTION, 
	/**
	 * The operation used to take a screenshot of a WebElement
	 */
	TAKE_SCREENSHOT_FROM,
	/**
	 * The operation used to take a value from the mapped result variables
	 */
	RETRIEVE_MAPPED_VALUE
}
