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
	 * <br/>
 * @author Fabrizio Torelli
 *
 */
@XmlEnum
public enum AssertionOperationType {
	IS_DISPLAYED, IS_ENABLED, IS_SELECTED, GET_ATTRIBUTE, GET_CSS, GET_LOCATION,
	GET_PAGE_SOURCE, GET_PAGE_TITLE
}
