package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum AssertionType {
	EQUALS, NOT_EQUALS, TRUE, FALSE, ARRAY_EQUALS, NULL, NOT_NULL, SAME, NOT_SAME, THAT, STARTS_WITH, STARTS_WITH_IGNORE_CASE, ENDS_WITH, ENDS_WITH_IGNORE_CASE
}
