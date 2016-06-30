package com.selenium2.easy.test.server.cases.unirest;

import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.xml.XMLTestURL;

/**
 * Interface that identify the nature of an UNIRestConnector for accessing the service
 * within different protocols GET, POST, PUT, DELETE and handling the SSL Handshake 
 * for the secure connection. Those Kind of Implementing test cases are usually used 
 * to connect and retrieve the service rensponse.
 * 
 * @author Fabrizio Torelli
 *
 */
public interface IUniRestElement {
	/**
	 * UNIRestConnector Test Case self URL connector 
	 * @return The connection status
	 * @throws When any exception occurs during the URL connection or to gather the answer in the required format
	 */
	boolean connectServiceURL() throws ActionException;
	
	/**
	 * UNIRestConnector Test Case self URL connector used by any child to use the Test Case authentication constraints
	 * @param url - The URL to load
	 * @return The connection status
	 * @throws When any exception occurs during the URL connection or to gather the answer in the required format
	 */
	boolean connectServiceURL(XMLTestURL url) throws ActionException;
}
