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

	/**
	 * Retrieves SearchType selector used to identify the attribute or type of search to run on the UI  (see: {@link SearchType})
	 * @return The {@link SearchType}
	 */
	public SearchType getBy() {
		return by;
	}

	/**
	 * Sets SearchType selector used to identify the attribute or type of search to run on the UI  (see: {@link SearchType})
	 * @param by The {@link SearchType}
	 */
	@XmlAttribute(name="by", required=true)
	public void setBy(SearchType by) {
		this.by = by;
	}

	/**
	 * Retrieves the search text used to find one or more Web Elements
	 * @return The search text
	 */
	public String getSearchText() {
		return searchText;
	}

	/**
	 * Retrieves the search text used to find one or more Web Elements
	 * @param searchText The search text
	 */
	@XmlAttribute(name="query", required=true)
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	/**
	 * Retrieves the flag of match multiple elements. Not used because the match is defined in the search criteria
	 * @return The flag of match multiple elements
	 */
	public Boolean getMultipleMatches() {
		return multipleMatches;
	}

	/**
	 * Sets the flag of match multiple elements. Not used because the match is defined in the search criteria
	 * @param multipleMatches The flag of match multiple elements
	 */
	@XmlAttribute(name="multiple", required=false)
	public void setMultipleMatches(Boolean multipleMatches) {
		this.multipleMatches = multipleMatches;
	}
	
	/**
	 * Retrieves the parsed By clause used to match the element (see: {@link By})
	 * @return The parsed By clause
	 */
	@XmlTransient
	public By getByClause() {
		return SeleniumUtilities.getBy(this.by, this.searchText);
	}
}
