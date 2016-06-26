package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.openqa.selenium.By;

import com.selenium2.easy.test.server.cases.TestEngine;
import com.selenium2.easy.test.server.utils.SeleniumUtilities;

/**
 * JAXB Class wrapper for the XMLWebElement representing the search criteria to extract one or more 
 * WebElement by the UI and provide these to the TestEngine.
 * <br/>It is used by different JAXB wrappers in the TestEngine during the TestCase execution.
 * 
 * @see TestEngine
 * @see SearchType
 * 
 * @author Fabrizio Torelli
 * 
 */@XmlRootElement(name = "element")
public class XMLWebElement {
	private SearchType by;

	private String searchText;
	
	private Boolean multipleMatches = Boolean.FALSE;

	public SearchType getBy() {
		return by;
	}

	@XmlAttribute(name="by", required=true)
	public void setBy(SearchType by) {
		this.by = by;
	}

	public String getSearchText() {
		return searchText;
	}

	@XmlAttribute(name="query", required=true)
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Boolean getMultipleMatches() {
		return multipleMatches;
	}

	@XmlAttribute(name="multiple", required=false)
	public void setMultipleMatches(Boolean multipleMatches) {
		this.multipleMatches = multipleMatches;
	}
	
	@XmlTransient
	public By getByClause() {
		return SeleniumUtilities.getBy(this.by, this.searchText);
	}
}
