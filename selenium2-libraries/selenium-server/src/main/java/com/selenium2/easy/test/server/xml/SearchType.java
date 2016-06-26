package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Enumeration that describes the different kinds of Search Criteria available in the framework
 * <br/>
 * The members are :
 * <br/><b>NAME</b> - The search criteria related to the name of one or more WebElements
 * <br/><b>CLASS_NAME</b> - The search criteria related to the CSS class name of one or more WebElements
 * <br/><b>CSS_SELECTOR</b> - The search criteria related to the CSS expression contained in the CSS class names of one or more WebElements
 * <br/><b>ID</b> - The search criteria related to the Identifier of a WebElement
 * <br/><b>EXACT_LINK</b> - The search criteria related to the HTML exact selector matching one or more WebElements
 * <br/><b>PARTIAL_LINK</b> - The search criteria related to the HTML exact selector matching also partially one or more WebElements
 * <br/><b>TAG</b> - The search criteria related to the TAG name of one or more WebElements 
 * <br/><b>XPATH</b> - The search criteria related an XPATH selector for one or more WebElements
 * <br/>
 * @author Fabrizio Torelli
 *
 */
@XmlEnum
public enum SearchType {
	NAME, CLASS_NAME, CSS_SELECTOR, ID, EXACT_LINK, PARTIAL_LINK, TAG, XPATH
}
