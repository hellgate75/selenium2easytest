/**
 * 
 */
package com.selenium2.easy.test.server.unireest.connector.model;

import com.selenium2.easy.test.server.unireest.connector.UniRestConnector;

/**
 * Secure configuration that is used to call the {@link UniRestConnector} URL web request in the following
 * HTTP Methods : GET, PUT, DELETE, POST
 * @see BasicConfiguration
 * @see UniRestConnector
 * @see RequestConfiguration
 * @author Fabrizio Torelli
 *
 */
public class SecureConfiguration extends BasicConfiguration {
	private String userName;
	private String password;
	/**
	 * Default constructor
	 */
	public SecureConfiguration() {
		super();
	}
	/**
	 * Retrieves the user name to use as simple access authorization
	 * @return The user name
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * Sets the user name to use as simple access authorization
	 * @param userName The user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * Retrieves the user password to use as simple access authorization
	 * @return The user password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Sets the user password to use as simple access authorization
	 * @param password The user password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
