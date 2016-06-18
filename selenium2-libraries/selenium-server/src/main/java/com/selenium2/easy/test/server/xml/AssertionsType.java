package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum AssertionsType {
	EQUALS, TRUE, FALSE, ARRAY_EQUALS, NULL, NOT_NULL
}
