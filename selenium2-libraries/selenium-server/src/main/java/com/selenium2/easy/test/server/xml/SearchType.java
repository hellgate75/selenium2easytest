package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum SearchType {
	NAME, CLASS_NAME, CSS_SELECTOR, ID, EXACT_LINK, PARTIAL_LINK, TAG, XPATH
}
