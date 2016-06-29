/**
 * 
 */
package com.selenium2.easy.test.server.unireest.connector.api;

import java.util.HashMap;
import java.util.Map;

import com.selenium2.easy.test.server.unireest.connector.UniRestConnector;

/**
 * Base configuration that is used to call the {@link UniRestConnector} URL web request in the following
 * HTTP Methods : GET, PUT, DELETE, POST
 * @see UniRestConnector
 * @see RequestConfiguration
 * @author Fabrizio Torelli
 *
 */
public class BasicConfiguration implements RequestConfiguration {
	private Map<String, String> headers = new HashMap<String, String>(0);
	private Map<String, Object> queryStringMap = new HashMap<String, Object>(0);
	private Map<String, String> routeParams = new HashMap<String, String>(0);
	/**
	 * Default Constructor
	 */
	public BasicConfiguration() {
		super();
	}
	@Override
	public boolean hasHeaders() {
		return headers.size()>0;
	}
	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.unireest.connector.api.RequestConfiguration#getHeaders()
	 */
	@Override
	public Map<String, String> getHeaders() {
		return headers;
	}
	/**
	 * Sets the request headers map used to configure the Request in the {@link UniRestConnector}
	 * @param headers The request headers map
	 */
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.unireest.connector.api.RequestConfiguration#hasQueryStringParameters()
	 */
	@Override
	public boolean hasQueryStringParameters() {
		return queryStringMap.size()>0;
	}
	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.unireest.connector.api.RequestConfiguration#getQueryStringMap()
	 */
	@Override
	public Map<String, Object> getQueryStringMap() {
		return queryStringMap;
	}
	/**
	 * Sets the query parameters map used to configure the Request in the {@link UniRestConnector}
	 * @param queryStringMap The query parameters map
	 */
	public void setQueryStringMap(Map<String, Object> queryStringMap) {
		this.queryStringMap = queryStringMap;
	}
	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.unireest.connector.api.RequestConfiguration#hasRouteParams()
	 */
	@Override
	public boolean hasRouteParams() {
		return routeParams.size()>0;
	}
	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.unireest.connector.api.RequestConfiguration#getRouteParams()
	 */
	@Override
	public Map<String, String> getRouteParams() {
		return routeParams;
	}
	/**
	 * Sets the route parameters map used to configure the Request in the {@link UniRestConnector}
	 * <br/>These are used to configure the request for composed URLs as : /myservice/myrest/{parm1}/{param2}
	 * <br/>Where param1 and param2 are stored in the map
	 * @param routeParams The route parameters map
	 */
	public void setRouteParams(Map<String, String> routeParams) {
		this.routeParams = routeParams;
	}

}
