/**
 * 
 */
package com.selenium2.easy.test.server.unireest.connector.api;

import java.util.Map;

import com.selenium2.easy.test.server.unireest.connector.UniRestConnector;

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
}
