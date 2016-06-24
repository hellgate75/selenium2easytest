package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

import org.openqa.selenium.By;

import com.selenium2.easy.test.server.utils.SeleniumUtilities;

public class XMLWebElement {
	@XmlAttribute(name="by", required=true)
	@XmlValue
	private SearchType by;

	@XmlAttribute(name="query", required=true)
	private String searchText;
	
	@XmlAttribute(name="multiple", required=false)
	private Boolean multipleMatches = Boolean.FALSE;

	public SearchType getBy() {
		return by;
	}

	public void setBy(SearchType by) {
		this.by = by;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Boolean getMultipleMatches() {
		return multipleMatches;
	}

	public void setMultipleMatches(Boolean multipleMatches) {
		this.multipleMatches = multipleMatches;
	}
	
	@XmlTransient
	public By getByClause() {
		return SeleniumUtilities.getBy(this.by, this.searchText);
	}
}
