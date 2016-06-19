package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class XMLTakeSnpshoot {
	
	@XmlAttribute(name="directory", required=false)
	private String folder=".";

	@XmlAttribute(name="name", required=true)
	private String fileName;

	@XmlAttribute(name="extension", required=false)
	private String fileExtension="png";

	@XmlElement(name="element", type=XMLWebElement.class, required=false)
	private XMLWebElement snapshootElement;

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public XMLWebElement getSnapshootElement() {
		return snapshootElement;
	}

	public void setSnapshootElement(XMLWebElement snapshootElement) {
		this.snapshootElement = snapshootElement;
	}
	
}
