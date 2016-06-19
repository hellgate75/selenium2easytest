package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum AssertionsType {
	EQUALS, NOT_EQUALS, TRUE, FALSE, ARRAY_EQUALS, ARRAY_NOT_EQUALS, NULL, NOT_NULL, SAME, NOT_SAME, THAT, NOT_THAT
}
