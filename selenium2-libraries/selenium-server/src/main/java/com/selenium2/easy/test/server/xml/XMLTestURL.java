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

	private Map<String, String> hostQueryString;

	public String getProtocol() {
		return protocol;
	}

	@XmlAttribute(name="protocol", required=false)
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHostName() {
		return hostName;
	}

	@XmlAttribute(name="hostname", required=true)
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostPort() {
		return hostPort;
	}

	@XmlAttribute(name="port", required=false)
	public void setHostPort(String hostPort) {
		this.hostPort = hostPort;
	}

	public String getHostUser() {
		return hostUser;
	}

	@XmlAttribute(name="user", required=false)
	public void setHostUser(String hostUser) {
		this.hostUser = hostUser;
	}

	public String getHostPassword() {
		return hostPassword;
	}

	@XmlAttribute(name="password", required=false)
	public void setHostPassword(String hostPassword) {
		this.hostPassword = hostPassword;
	}

	public String getHostPath() {
		return hostPath;
	}

	@XmlAttribute(name="path", required=false)
	public void setHostPath(String hostPath) {
		this.hostPath = hostPath;
	}

	public Map<String, String> getHostQueryString() {
		return hostQueryString;
	}

	@XmlElement(name="query", required=false)
	public void setHostQueryString(Map<String, String> hostQueryString) {
		this.hostQueryString = hostQueryString;
	}
	
	@XmlTransient
	public String getFormattedURL() {
		return protocol.toLowerCase() + "://" + (protocol.equalsIgnoreCase("file") ? "/":"") +
				(hostUser!=null || hostPassword!=null ? ((hostUser!=null ? hostUser : "") + ":" + (hostPassword!=null ? hostPassword : "") + "@") :"") + 
				hostName + (hostPort!=null?":" + hostPort:"") + (hostPath!=null ? "/" + hostPath : "") + 
				(hostQueryString!=null ? "?" + SeleniumUtilities.mapToQueryString(hostQueryString) : "");
	}
}
