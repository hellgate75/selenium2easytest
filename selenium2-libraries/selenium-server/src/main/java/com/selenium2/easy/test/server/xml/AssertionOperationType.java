package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum AssertionOperationType {
	IS_DISPLAYED, IS_ENABLED, IS_SELECTED, GET_ATTRIBUTE, GET_CSS, GET_LOCATION,
	GET_PAGE_SOURCE, GET_PAGE_TITLE
}
