package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XMLTestCaseAction {
	
	@XmlAttribute(required=false)
	private String changeURL;

	@XmlAttribute(required=false)
	private String connectionUrl;

}
