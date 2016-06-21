package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum AssertionOperationType {
	IS_DISPLAYED, IS_ENABLED, IS_SELECTED, TAKE_SCREENSHOT_FROM
}
