package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.selenium2.easy.test.server.cases.TestEngine;

/**
 * JAXB Class wrapper for require a Screenshot feature to the framework.
 * <br/>It is used to define a Screenshot event in the TestEngine.
 * 
 * @see TestEngine
 * @see XMLWebElement
 * 
 * @author Fabrizio Torelli
 * 
 */
@XmlRootElement(name = "snapshoot")
public class XMLTakeSnpshoot {
	
	private String folder=".";

	private String fileName;

	private String fileExtension="png";

	private XMLWebElement snapshootElement;

	/**
	 * Retrieves the full path content directory where to save the file
	 * @return The directory path string
	 */
	public String getFolder() {
		return folder;
	}

	/**
	 * Sets the full path content directory where to save the file
	 * @param folder The directory path string
	 */
	@XmlAttribute(name="directory", required=false)
	public void setFolder(String folder) {
		this.folder = folder;
	}

	/**
	 * Retrieves the file name excluded the extension
	 * @return The file name string
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name excluded the extension
	 * @param fileName The file name string
	 */
	@XmlAttribute(name="name", required=true)
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Retrieves the file extension
	 * @return The file extension string
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * Sets the file extension
	 * @param fileExtension The file extension string
	 */
	@XmlAttribute(name="extension", required=false)
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	/**
	 * Retrieves the WebElement to take the snapshot
	 * @return The Web element affected by the snapshot
	 */
	@XmlElement(name="element", type=XMLWebElement.class, required=false)
	public XMLWebElement getSnapshootElement() {
		return snapshootElement;
	}

	/**
	 * Sets the WebElement to take the snapshot
	 * @param snapshootElement The Web element affected by the snapshot
	 */
	public void setSnapshootElement(XMLWebElement snapshootElement) {
		this.snapshootElement = snapshootElement;
	}
	
}
