package com.selenium2.easy.test.server.unireest.connector.cases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver.When;

import com.selenium2.easy.test.server.cases.BaseTestCase;
import com.selenium2.easy.test.server.cases.TestCase;
import com.selenium2.easy.test.server.cases.TestEngine;
import com.selenium2.easy.test.server.cases.unirest.IUniRestElement;
import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.unireest.connector.UniRestConnector;
import com.selenium2.easy.test.server.utils.FrameworkUtilities;
import com.selenium2.easy.test.server.xml.WebMethod;
import com.selenium2.easy.test.server.xml.WebResponse;
import com.selenium2.easy.test.server.xml.XMLTestCase;
import com.selenium2.easy.test.server.xml.XMLTestURL;

/**
 * Basic Service Oriented Test Case, It do not use the WebDriver and the Selenium2 utilities to 
 * connect to the related service URL, it can be extended as class and the test controls and the
 * same implementation can be loaded in the XML Path load mechanism
 * @see UniRestConnector
 * @see WebDriver
 * @author Fabrizio Torelli
 *
 */
public abstract class UniRestTestCase extends BaseTestCase implements IUniRestElement {
	public static final String RESPONSE_VARIABLE_NAME="ServiceResponse";
	protected XMLTestCase testCase;
	protected String groupName;

	/**
	 * Constructor used to be installed by loadXMLPathFiles {@link TestEngine} configuration option
	 * @param caseName 
	 * @param testCase {@link XMLTestCase} used to configure the test operations
	 */
	public UniRestTestCase(String groupName, XMLTestCase testCase) {
		super(testCase.getName(), testCase.getConnectionURL().getFormattedURL(), testCase.getUseUrl(), testCase.getRetrowException());
		this.testCase = testCase;
		this.groupName = groupName;
		super.openUrl = testCase.getUseUrl();
		super.caseURL = testCase!=null && testCase.getConnectionURL()!=null ? testCase.getConnectionURL().getFormattedURL() : null;
		super.inheritEnvironment = testCase.getInheritEnvironment();
		super.retrowExcpetion = testCase.getRetrowException();
		super.caseName = testCase.getName();
	}

	/**
	 * Constructor used to be installed by loadByClassNames or loadByPackage {@link TestEngine} configuration options
	 */
	public UniRestTestCase() {
		super();
	}

	/**
	 * Retrieves the {@link WebMethod} used during the URL lookup
	 * @return The {@link WebMethod} used during the URL lookup
	 */
	public abstract WebMethod getWebMethodType();

	/**
	 * Retrieves the {@link WebResponse} used to parse the URL lookup result
	 * @return The {@link WebResponse} used to parse the URL lookup result
	 */
	public abstract WebResponse getWebResponseType();


	/**
	 * UNIRestConnector Test Case self URL connector 
	 * @return The connection status
	 * @throws When any exception occurs during the URL connection or to gather the answer in the required format
	 */
	@Override
	public boolean connectServiceURL() throws ActionException {
		if (this.getConnectionURL()!=null && this.getWebMethodType()!=null && this.getWebResponseType()!=null) {
			try {
				Object response = UniRestConnector.getInstance().retrieveUrlResponse(this.getSecurityInfo(), this.getConnectionURL(), this.getWebMethodType(), this.getWebResponseType(), this.isSecureConnection());
				if (response!=null) {
					this.getLogger().debug("Object Response : " + response);
					this.addCaseResult(RESPONSE_VARIABLE_NAME, response);
				}
				else {
					this.getLogger().warn("The response for the URL '" + this.getConnectionURL() + "' has not returned any response!!");
				}
				return true;
			} catch (AssertionError e) {
				if (this.retrowExcpetion) {
					this.getLogger().error("URL connection failed - Stopping the Test Case : " + this.getCaseName() + " caused by : ", e);
					throw e;
				}
				else {
					this.getLogger().warn("The response for the URL '" + this.getConnectionURL() + "' has failed!! Contiune due the exceptio retrow policies");
				}
			} catch (Throwable e) {
				if (this.retrowExcpetion) {
					this.getLogger().error("URL connection failed - Stopping the Test Case : " + this.getCaseName() + " caused by : ", e);
				}
				else {
					this.getLogger().warn("The request for the URL '" + this.getConnectionURL() + "' has failed!! Contiune due the exception retrow policies");
					
				}
			}
			return !this.retrowExcpetion;
		}
		else {
			this.getLogger().warn("No URL found for the Test Case : " + this.getCaseName());
			this.getLogger().error("Stopping the Test Case : " + this.getCaseName());
		}
		return false;
	}

	/**
	 * UNIRestConnector Test Case self URL connector used by any child to use the Test Case authentication constraints
	 * @param url - The URL to load
	 * @return The connection status
	 * @throws When any exception occurs during the URL connection or to gather the answer in the required format
	 */
	@Override
	public boolean connectServiceURL(XMLTestURL url) throws ActionException {
		if (url!=null) {
			try {
				Object response = UniRestConnector.getInstance().retrieveUrlResponse(this.getSecurityInfo(), url.getFormattedURL(), url.getWebMethod(), url.getExpectedResponse(), this.isSecureConnection());
				if (response!=null) {
					this.getLogger().debug("Object Response : " + response);
					this.addCaseResult(RESPONSE_VARIABLE_NAME, response);
				}
				else {
					this.getLogger().warn("The response for the URL '" + this.getConnectionURL() + "' has not returned any response!!");
				}
				return true;
			} catch (AssertionError e) {
				if (this.retrowExcpetion) {
					this.getLogger().error("URL connection failed - Stopping the Test Case : " + this.getCaseName() + " caused by : ", e);
					throw e;
				}
				else {
					this.getLogger().warn("The request for the URL '" + this.getConnectionURL() + "' has failed!! Contiune due the exceptio retrow policies");
				}
			} catch (Throwable e) {
				if (this.retrowExcpetion) {
					this.getLogger().error("URL connection failed - Stopping the Test Case : " + this.getCaseName() + " caused by : ", e);
				}
				else {
					this.getLogger().warn("The response for the URL '" + this.getConnectionURL() + "' has failed!! Contiune due the exceptio retrow policies");
					
				}
			}
			return !this.retrowExcpetion;
		}
		else {
			this.getLogger().warn("No URL found for the Test Case : " + this.getCaseName());
			this.getLogger().error("Stopping the Test Case : " + this.getCaseName());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.BaseTestCase#handleSecureConnection(org.openqa.selenium.WebDriver)
	 */
	@Override
	public boolean handleSecureConnection(WebDriver driver) {
		try {
			return this.connectServiceURL();
		} catch (ActionException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retrieves the current {@link XMLTestCase} Configuration, when used in the loadXMLPathFiles mode
	 * @return The current {@link XMLTestCase} Configuration
	 */
	public XMLTestCase getTestCase() {
		return testCase;
	}

	/**
	 * Retrieves the current {@link XMLTestCase} Configuration, when used in the loadXMLPathFiles mode
	 * @param testCase The current {@link XMLTestCase} Configuration
	 */
	public void setTestCase(XMLTestCase testCase) {
		this.testCase = testCase;
	}

	/**
	 * Retrieves the Group Name, when used in the loadXMLPathFiles mode
	 * @return The Group name
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Sets the Group Name, when used in the loadXMLPathFiles mode
	 * @param groupName The Group name
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.TestCase#automatedTest(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void automatedTest(WebDriver driver) throws Throwable {
		if (this.testCase!=null) {
			this.setCaseResults(FrameworkUtilities.executeXMLCase((TestCase)this, driver, this.testCase, this.getCaseResults()));
		}
		else {
			throw new ActionException("The test case implementation needs to define the custom test actions");
		}

	}

}
