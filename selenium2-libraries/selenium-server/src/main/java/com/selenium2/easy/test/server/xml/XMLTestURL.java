package com.selenium2.easy.test.server.xml;

import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import com.selenium2.easy.test.server.utils.SeleniumUtilities;

public class XMLTestURL {

	@XmlAttribute(name="protocol", required=false)
	private String protocol = "http;";

	@XmlAttribute(name="hostname", required=true)
	private String hostName;

	@XmlAttribute(name="port", required=false)
	private String hostPort;

	@XmlAttribute(name="user", required=false)
	private String hostUser;

	@XmlAttribute(name="password", required=false)
	private String hostPassword;

	@XmlAttribute(name="path", required=false)
	private String hostPath;

	@XmlAttribute(name="query", required=false)
	private Map<String, String> hostQueryString;

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostPort() {
		return hostPort;
	}

	public void setHostPort(String hostPort) {
		this.hostPort = hostPort;
	}

	public String getHostUser() {
		return hostUser;
	}

	public void setHostUser(String hostUser) {
		this.hostUser = hostUser;
	}

	public String getHostPassword() {
		return hostPassword;
	}

	public void setHostPassword(String hostPassword) {
		this.hostPassword = hostPassword;
	}

	public String getHostPath() {
		return hostPath;
	}

	public void setHostPath(String hostPath) {
		this.hostPath = hostPath;
	}

	public Map<String, String> getHostQueryString() {
		return hostQueryString;
	}

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
