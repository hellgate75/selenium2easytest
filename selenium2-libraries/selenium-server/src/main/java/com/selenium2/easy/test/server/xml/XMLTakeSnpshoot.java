package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "snapshoot")
public class XMLTakeSnpshoot {
	
	private String folder=".";

	private String fileName;

	private String fileExtension="png";

	private XMLWebElement snapshootElement;

	public String getFolder() {
		return folder;
	}

	@XmlAttribute(name="directory", required=false)
	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getFileName() {
		return fileName;
	}

	@XmlAttribute(name="name", required=true)
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	@XmlAttribute(name="extension", required=false)
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	@XmlElement(name="element", type=XMLWebElement.class, required=false)
	public XMLWebElement getSnapshootElement() {
		return snapshootElement;
	}

	public void setSnapshootElement(XMLWebElement snapshootElement) {
		this.snapshootElement = snapshootElement;
	}
	
}
