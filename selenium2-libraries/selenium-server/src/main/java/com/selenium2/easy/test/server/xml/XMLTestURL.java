package com.selenium2.easy.test.server.xml;

import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.selenium2.easy.test.server.cases.TestEngine;
import com.selenium2.easy.test.server.utils.SeleniumUtilities;

/**
 * JAXB Class wrapper for the XMLTestURL and it provides the Information about the URL to change to the {@link XMLTestGroup}, the {@link XMLTestCase} and to the {@link XMLTestCaseAction} used by the {@link TestEngine}.
 * <br/>It is used by different JAXB wrappers in the TestEngine during the TestCase execution.
 * 
 * @see TestEngine
 * @see XMLTestGroup
 * @see XMLTestCase
 * @see XMLTestCaseAction
 * 
 * @author Fabrizio Torelli
 * 
 */
@XmlRootElement(name = "url")
public class XMLTestURL {

	private String protocol = "http";

	private String hostName;

	private String hostPort;

	private String hostUser;

	private String hostPassword;

	private String hostPath;

	private Boolean serviceURL = Boolean.FALSE;

	private WebMethod webMethod;

	private Map<String, String> hostQueryString;

	private WebResponse expectedResponse;

	/**
	 * Retrieves the protocol string value (e.g.: http, https, ftp, sftp, an so on ....)
	 * @return The protocol string value
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * Sets the protocol string value (e.g.: http, https, ftp, sftp, an so on ....)
	 * @param protocol The protocol string value
	 */
	@XmlAttribute(name="protocol", required=false)
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * Retrieves the host name string value (e.g.: www.google.ie, 10.34.22.345, and so on ....)
	 * @return The host name string value
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * Sets the host name string value (e.g.: www.google.ie, 10.34.22.345, and so on ....)
	 * @param hostName The host name string value
	 */
	@XmlAttribute(name="hostname", required=true)
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * Retrieves the numeric port string value (e.g.: 80, 8080, 43, and so on ....)
	 * @return The numeric port string value
	 */
	public String getHostPort() {
		return hostPort;
	}

	/**
	 * Sets the numeric port string value (e.g.: 80, 8080, 43, and so on ....)
	 * @param hostPort The numeric port string value
	 */
	@XmlAttribute(name="port", required=false)
	public void setHostPort(String hostPort) {
		this.hostPort = hostPort;
	}

	/**
	 * Retrieves the user name string value (e.g.: fabtor, ui1234, and so on ....)
	 * @return The user name string value
	 */
	public String getHostUser() {
		return hostUser;
	}

	/**
	 * Sets the user name string value (e.g.: fabtor, ui1234, and so on ....)
	 * @param hostUser The user name string value
	 */
	@XmlAttribute(name="user", required=false)
	public void setHostUser(String hostUser) {
		this.hostUser = hostUser;
	}

	/**
	 * Retrieves the user password string value
	 * @return The user password string value
	 */
	public String getHostPassword() {
		return hostPassword;
	}

	/**
	 * Sets the user password string value
	 * @param hostPassword The user password string value
	 */
	@XmlAttribute(name="password", required=false)
	public void setHostPassword(String hostPassword) {
		this.hostPassword = hostPassword;
	}

	/**
	 * Retrieves the site path string value (e.g.; /users/login, /bills/12343, and so on ...)
	 * @return The site path string value
	 */
	public String getHostPath() {
		return hostPath;
	}

	/**
	 * Sets the site path string value (e.g.; /users/login, /bills/12343, and so on ...)
	 * @param hostPath The site path string value
	 */
	@XmlAttribute(name="path", required=false)
	public void setHostPath(String hostPath) {
		this.hostPath = hostPath;
	}

	/**
	 * Retrieves the map of query entries (e.g.; [('q', 'selenium 2')], and so on ...)
	 * @return The map of query entries
	 */
	public Map<String, String> getHostQueryString() {
		return hostQueryString;
	}

	/**
	 * Sets the map of query entries (e.g.; [('q', 'selenium 2')], and so on ...)
	 * @param hostQueryString The map of query entries
	 */
	@XmlElement(name="query", required=false)
	public void setHostQueryString(Map<String, String> hostQueryString) {
		this.hostQueryString = hostQueryString;
	}

	/**
	 * Retrieves the service nature for the URL, it is used almost for direct service call bypassing the Selenium2 WebDriver
	 * @return The service nature for the URL
	 */
	public Boolean getServiceURL() {
		return serviceURL;
	}

	/**
	 * Sets the service nature for the URL, it is used almost for direct service call bypassing the Selenium2 WebDriver
	 * @param serviceURL The service nature for the URL
	 */
	public void setServiceURL(Boolean serviceURL) {
		this.serviceURL = serviceURL;
	}

	/**
	 * Retrieves the {@link WebMethod} used for the call, it is used almost for direct service call bypassing the Selenium2 WebDriver
	 * @return The {@link WebMethod} used for the call
	 */
	public WebMethod getWebMethod() {
		return webMethod;
	}

	/**
	 * Sets the {@link WebMethod} used for the call, it is used almost for direct service call bypassing the Selenium2 WebDriver
	 * @param webMethod The {@link WebMethod} used for the call
	 */
	public void setWebMethod(WebMethod webMethod) {
		this.webMethod = webMethod;
	}

	/**
	 * Retrieves the {@link WebResponse} used to parse the call response, it is used almost for direct service call bypassing the Selenium2 WebDriver
	 * @return The {@link WebResponse} used to parse the call response
	 */
	public WebResponse getExpectedResponse() {
		return expectedResponse;
	}

	/**
	 * Sets the {@link WebResponse} used to parse the call response, it is used almost for direct service call bypassing the Selenium2 WebDriver
	 * @param expectedResponse The {@link WebResponse} used to parse the call response
	 */
	public void setExpectedResponse(WebResponse expectedResponse) {
		this.expectedResponse = expectedResponse;
	}

	/**
	 * Retrieves the parsed formatted URL string (e.g.; 'http://www.google.com/?q=selenium 2&r=12], and so on ...)
	 * @return The formatted URL string
	 */
	@XmlTransient
	public String getFormattedURL() {
		return protocol.toLowerCase() + "://" + (protocol.equalsIgnoreCase("file") ? "/":"") +
				(hostUser!=null || hostPassword!=null ? ((hostUser!=null ? hostUser : "") + ":" + (hostPassword!=null ? hostPassword : "") + "@") :"") + 
				hostName + (hostPort!=null?":" + hostPort:"") + (hostPath!=null ? "/" + hostPath : "") + 
				(hostQueryString!=null ? "?" + SeleniumUtilities.mapToQueryString(hostQueryString) : "");
	}
}
