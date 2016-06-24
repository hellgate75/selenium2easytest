package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum AssertionThatMatcherType {
	ANY, ANYTHING, EQUALS_TO,
	INSTANCE_OF, IS, NOT, NOT_NULL, NULL
}
