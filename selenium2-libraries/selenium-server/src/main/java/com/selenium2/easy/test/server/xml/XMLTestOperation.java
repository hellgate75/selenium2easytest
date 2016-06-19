package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

public class XMLTestOperation {

	@XmlAttribute(name="type", required=false)
	@XmlValue
	private OperationType operationType;


	@XmlElement(name="element", type=XMLWebElement.class, required=true)
	private XMLWebElement snapshootElement;
}
