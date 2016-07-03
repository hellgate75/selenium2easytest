package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Enumeration that describes the different kinds of Assertion's Operations available in the framework
 * <br/>
 * The members are :
 * <br/><b>IS_DISPLAYED</b> - The operation used to retrieve and save an environment variable with the UI display status of a WebElement
 * <br/><b>IS_ENABLED</b> - The operation used to retrieve and save an environment variable with the enabled/disabled status of a WebElement
 * <br/><b>IS_SELECTED</b> - The operation used to retrieve and save an environment variable with the selection status of a WebElement
 * <br/><b>GET_ATTRIBUTE</b> - The operation used to retrieve and save an environment variable with an attribute of a WebElement
 * <br/><b>GET_CSS</b> - The operation used to retrieve and save an environment variable with the CSS classes of a WebElement
 * <br/><b>GET_LOCATION</b> - The operation used to retrieve and save an environment variable with the location of a WebElement
 * <br/><b>GET_PAGE_SOURCE</b> - The operation used to retrieve the page source code
 * <br/><b>GET_PAGE_TITLE</b> - The operation used to retrieve the page title
 * <br/><b>RETRIEVE_MAPPED_VALUE</b> - The operation used to retrieve an element from the mapped result variables
 * <br/>
 * @author Fabrizio Torelli
 *
 */
@XmlEnum
public enum AssertionOperationType {
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
	 * The operation used to take a value from the mapped result variables
	 */
	RETRIEVE_MAPPED_VALUE
}
