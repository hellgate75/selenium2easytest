package com.selenium2.easy.test.server.unireest.connector.cases;

import org.openqa.selenium.WebDriver;

import com.selenium2.easy.test.server.cases.BaseTestCase;
import com.selenium2.easy.test.server.cases.api.IUniRestElement;

/**
 * @author Fabrizio Torelli
 *
 */
public abstract class UniRestTestCase extends BaseTestCase implements IUniRestElement {

	/**
	 * @param caseName
	 * @param caseURL
	 * @param openUrl
	 * @param retrowExcpetion
	 */
	public UniRestTestCase(String caseName, String caseURL, boolean openUrl,
			boolean retrowExcpetion) {
		super(caseName, caseURL, openUrl, retrowExcpetion);
	}
	
	/**
	 */
	public UniRestTestCase() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.api.IUniRestElement#connectServiceURL()
	 */
	@Override
	public boolean connectServiceURL() {
		// TODO Implements UniRestConnector connection
		return false;
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.BaseTestCase#handleSecureConnection(org.openqa.selenium.WebDriver)
	 */
	@Override
	public boolean handleSecureConnection(WebDriver driver) {
		// TODO Implements UniRestConnector security access
		return super.handleSecureConnection(driver);
	}

}
