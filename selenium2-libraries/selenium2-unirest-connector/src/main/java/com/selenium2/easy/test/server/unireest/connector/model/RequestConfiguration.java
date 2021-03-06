/**
 * 
 */
package com.selenium2.easy.test.server.unireest.connector.model;

import java.util.Map;

import com.selenium2.easy.test.server.unireest.connector.UniRestConnector;
import com.selenium2.easy.test.server.xml.WebMethod;
import com.selenium2.easy.test.server.xml.WebResponse;

/**
 * Generic request configuration type that introduce the features used to configure the Request in the {@link UniRestConnector}
 * @see UniRestConnector
 * @author Fabrizio Torelli
 *
 */
public interface RequestConfiguration {
	
	/**
	 * Retrieves the request headers map used to configure the Request in the {@link UniRestConnector}
	 * @return The request headers map
	 */
	Map<String, String> getHeaders();
	
	/**
	 * Retrieves the request headers parameters map definition
	 * @return The request headers parameters definition status
	 */
	boolean hasHeaders();
	
	/**
	 * Retrieves the query parameters map used to configure the Request in the {@link UniRestConnector}
	 * @return The query parameters map
	 */
	Map<String, Object> getQueryStringMap();

	/**
	 * Retrieves the query parameters parameters map definition
	 * @return The query parameters parameters definition status
	 */
	boolean hasQueryStringParameters();

	/**
	 * Retrieves the route parameters map used to configure the Request in the {@link UniRestConnector}
	 * <br/>These are used to configure the request for composed URLs as : /myservice/myrest/{parm1}/{param2}
	 * <br/>Where param1 and param2 are stored in the map
	 * @return The route parameters map
	 */
	Map<String, String> getRouteParams();
	
	/**
	 * Retrieves the route parameters map definition
	 * @return The route parameters definition status
	 */
	boolean hasRouteParams();
	
	/**
	 * Retrieves the body Object as call body structure used to communicate with the service
	 * @return The body Object
	 */
	Object getBody();
	
	/**
	 * Retrieves the {link WebMethod} to call the service
	 * @return The {link WebMethod} to call the service
	 */
	WebMethod getWebMethod();
	
	/**
	 * Retrieves the {link WebResponse} to parse the call answer
	 * @return The {link WebResponse} to parse the call answer
	 */
	WebResponse getResponseType();
}
