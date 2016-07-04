package com.selenium2.easy.test.server.unireest.connector;

import static com.mashape.unirest.http.Unirest.delete;
import static com.mashape.unirest.http.Unirest.get;
import static com.mashape.unirest.http.Unirest.post;
import static com.mashape.unirest.http.Unirest.put;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.request.HttpRequest;
import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.unireest.connector.cases.UniRestTestCase;
import com.selenium2.easy.test.server.unireest.connector.cases.XMLGroupedUniRestTestCase;
import com.selenium2.easy.test.server.unireest.connector.model.BasicConfiguration;
import com.selenium2.easy.test.server.unireest.connector.model.RequestConfiguration;
import com.selenium2.easy.test.server.unireest.connector.model.SecureConfiguration;
import com.selenium2.easy.test.server.utils.FrameworkUtilities;
import com.selenium2.easy.test.server.xml.WebMethod;
import com.selenium2.easy.test.server.xml.WebResponse;

/**
 * UniRest Connector class that provides the connection features to the URL using a {@link WebMethod} and collects
 * the result in a specific {@link WebResponse}. It provide the UniRest Test cases features and web methods connectivity.
 * @see WebMethod
 * @see WebResponse
 * @see UniRestTestCase
 * @see XMLGroupedUniRestTestCase
 * @author Fabrizio Torelli
 *
 */
public class UniRestConnector {
	private static UniRestConnector instance = null;
	private static Logger logger = LoggerFactory.getLogger("com.selenium2.easy.test.server");
	
	private UniRestConnector() {
		super();
	}
	
	/**
	 * Retrieves the URL {@link HttpResponse} according to the URL and the {@ RequestConfiguration}
	 * @param url the URL to lookup
	 * @param config The configuration used to parse the response
	 * @return The {@link HttpResponse} coming from the URL lookup
	 * @throws ActionException When any exception occurs during the URL lookup or the Response parse
	 */
	public final synchronized HttpResponse<?> performaURLAction(String url, RequestConfiguration config) throws ActionException {
		WebMethod webMethod = config.getWebMethod();
		if (config!=null && url!=null) {
			if (webMethod!=null) {
				HttpRequest request = null;
				switch (webMethod) {
					case DELETE:
						request = delete(url);
						break;
					case PUT:
						request = put(url);
						break;
					case POST:
						request = post(url);
						break;
					default:
						request = get(url);
				}
				if (config.hasHeaders()) {
					request.headers(config.getHeaders());
				}
				if (config.hasQueryStringParameters()) {
					request.queryString(config.getQueryStringMap());
				}
				if (config.hasRouteParams()) {
					for(String key: config.getRouteParams().keySet()) {
						request.routeParam(key, config.getRouteParams().get(key));
					}
				}
				if (SecureConfiguration.class.isAssignableFrom(config.getClass())) {
					String userName = ((SecureConfiguration)config).getUserName();
					String userPassword = ((SecureConfiguration)config).getPassword();
					if (userName!=null) {
						request.basicAuth(userName, userPassword);
					}
				}
				try {
					switch(config.getResponseType()) {
						case BINARY:
							return request.asBinary();
						case JSON:
							return request.asJson();
					// TODO To Understand how-to get the response object type in an easy way ...
					case OBJECT:
//						return request.asObject();
						default: 
							return request.asString();
					}
				} catch (Throwable e) {
					throw new ActionException("Unable to require the URL : " + url + " with Method:" + webMethod + " with the Answer Type : " + config.getResponseType());
				}
			}
			else {
				throw new ActionException("Unable to require the URL : " + url + " with a null Method ...");
			}
		}
		else {
			throw new ActionException("Unable to require the URL : " + url + " : invalid attributes ...");
		}
	}
	
	private static Object getObjectFromStream(InputStream stream) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedInputStream bis = new BufferedInputStream(stream);
		try {
			long bytes = IOUtils.copyLarge(bis, baos);
			if (bytes>0) {
				return new String(baos.toByteArray(), "UTF-8");
			}
		} catch (IOException e) {
		}
		return null;
	}

	private static Map<String, String> getStringMap(String prefix, Map<String, String> origin) {
		Map<String, String> filteredMap = new HashMap<String, String>(0);
		int actionId = 0;
		while(origin.containsKey("unirest-"+prefix + "-" + (actionId+1)+"-key")) {
			actionId++;
			String mapPrefix = "unirest-"+prefix + "-" + actionId;
			String key  = origin.get(mapPrefix+"-key");
			String value  = origin.get(mapPrefix+"-value");
			logger.debug("acquiring map key pair for section '"+prefix+"' id=" + actionId+" : " + key + "=" + value);
			filteredMap.put(key, value);
		}
		return filteredMap;
	}

	private static Map<String, Object> getObjectMap(String prefix, Map<String, String> origin, Map<String, Object> registry) {
		Map<String, Object> filteredMap = new HashMap<String, Object>(0);
		int actionId = 0;
		while(origin.containsKey("unirest-"+prefix + "-" + (actionId+1)+"-key")) {
			actionId++;
			String mapPrefix = "unirest-"+prefix + "-" + actionId;
			String key  = origin.get(mapPrefix+"-key");
			Object value  = FrameworkUtilities.pack(origin.get(mapPrefix+"-value"), registry);
			logger.debug("acquiring map key pair for section '"+prefix+"' id=" + actionId+" : " + key + "=" + value);
			filteredMap.put(key, value);
		}
		return filteredMap;
	}
	/**
	 * Retrieves the URL Response Object according to the URL, the attributes map, the {@link WebMethod} and the {@link WebResponse}
	 * <br/>
	 * <br/> The configuration map is expected as follows:
	 * <br/>
	 * <br/><b>Header : </b>
	 * <br/>for (1..n) iterations (right now {index}) of :
	 * <br/><b/>unirest-header-{index}-key<br/> - The key of the header map entry.
	 * <br/><b/>unirest-header-{index}-value<br/> - The value of the header map entry.
	 * <br/>
	 * <br/><b>Query Parameters : </b>
	 * <br/>for (1..n) iterations (right now {index}) of :
	 * <br/><b/>unirest-query-{index}-key<br/> - The key of the query parameters map entry.
	 * <br/><b/>unirest-query-{index}-value<br/> - The value of the query parameters map entry.
	 * <br/>
	 * <br/><b>Route Parameters : </b>
	 * <br/>for (1..n) iterations (right now {index}) of :
	 * <br/><b/>unirest-route-{index}-key<br/> - The key of the route parameter map entry used to parse the URL.
	 * <br/><b/>unirest-route-{index}-value<br/> - The value of the route parameter map entry.
	 * <br/>
	 * <br/><b>Base authentication : </b>
	 * <br/><b/>unirest-username<br/> - The key basic service authentication protocol user name.
	 * <br/><b/>unirest-password<br/> - The key basic service authentication protocol password
	 * 
	 * @param configurationMap The map of attributes used for the Headers, the QueryString, the Routing parameters and to retrieve the Body Object.
	 * @param url the URL to lookup
	 * @param method The {@link WebMethod} used to lookup the URL
	 * @param responseType The {@link WebResponse} used to parse the {@link HttpResponse}
	 * @param useSecureConnection The Use secure connection flag
	 * @return The Response Object
	 * @throws ActionException When any exception occurs during the URL lookup or the Response parse
	 */
	@SuppressWarnings("unchecked")
	public final synchronized Object retrieveUrlResponse(Map<String, String> configurationMap, String url, WebMethod method, WebResponse responseType, boolean useSecureConnection, Map<String, Object> registry) throws ActionException {
		//TODO: Implement the RequestConfiguration filling and the performaURLAction call!!
		HttpResponse<?> response =  null;
		if (useSecureConnection) {
			SecureConfiguration configuration = new SecureConfiguration();
			//TODO Implement the Header, query string, route parameters and Body object retrieve from the configurationMap
			configuration.setWebMethod(method);
			configuration.setResponseType(responseType);
			configuration.setHeaders(getStringMap("header", configurationMap));
			configuration.setRouteParams(getStringMap("route", configurationMap));
			configuration.setQueryStringMap(getObjectMap("query", configurationMap, registry));
			configuration.setUserName(configurationMap.get("unirest-username"));
			configuration.setPassword(configurationMap.get("unirest-password"));
			response =  performaURLAction(url, configuration);
			
		}
		else {
			BasicConfiguration configuration = new BasicConfiguration();
			//TODO Implement the Header, query string, route parameters and Body object retrieve from the configurationMap
			configuration.setWebMethod(method);
			configuration.setResponseType(responseType);
			configuration.setHeaders(getStringMap("header", configurationMap));
			configuration.setRouteParams(getStringMap("route", configurationMap));
			configuration.setQueryStringMap(getObjectMap("query", configurationMap, registry));
			response =  performaURLAction(url, configuration);
		}
		/*
		 * HTTP Status codes :
		 * Response Code	HTTP Operation					Response Body 			Contents					Description
		 * 200	        	--								GET, PUT, DELETE		Resource					No error, operation successful.
		 * 201          	Created							POST					Resource that was created	Successful creation of a resource.
		 * 202          	Accepted						POST, PUT, DELETE		N/A							The request was received.
		 * 204          	No Content						GET, PUT, DELETE		N/A							The request was processed successfully, but no response body is needed.
		 * 301          	Moved Permanently				GET						XHTML with link				Resource has moved.
		 * 303          	See Other 						GET						XHTML with link				Redirection.
		 * 304          	Not Modified conditional 		GET						N/A							Resource has not been modified.
		 * 400           	Bad Request						GET, POST, PUT, DELETE	Error Message				Malformed syntax or a bad query.
		 * 401          	Unauthorized					GET, POST, PUT, DELETE	Error Message				Action requires user authentication.
		 * 403          	Forbidden						GET, POST, PUT, DELETE	Error Message				Authentication failure or invalid Application ID.
		 * 404          	Not Found						GET, POST, PUT, DELETE	Error Message				Resource not found.
		 * 405          	Not Allowed						GET, POST, PUT, DELETE	Error Message				Method not allowed on resource.
		 * 406          	Not Acceptable					GET						Error Message				Requested representation not available for the resource.
		 * 408          	Request Timeout					GET, POST				Error Message				Request has timed out.
		 * 409          	Resource Conflict				PUT, PUT, DELETE		Error Message				State of the resource doesn't permit request.
		 * 410          	Gone							GET, PUT				Error Message				The URI used to refer to a resource.
		 * 411          	Length Required					POST, PUT				Error Message				The server needs to know the size of the entity body and it should be specified in the Content Length header.
		 * 412           	Precondition failed				GET						Error Message				Operation not completed because preconditions were not met.
		 * 413          	Request Entity Too Large		POST, PUT				Error Message				The representation was too large for the server to handle.
		 * 414          	Request URI too long			POST, PUT				Error Message				The URI has more than 2k characters.
		 * 415         		Unsupported Type				POST, PUT				Error Message				Representation not supported for the resource.
		 * 416           	Requested Range Not Satisfiable	GET						Error Message				Requested range not satisfiable.
		 * 500          	Server Error					GET, POST, PUT			Error Message				Internal server error.
		 * 501          	Not Implemented					POST, PUT, DELETE		Error Message				Requested HTTP operation not supported.
		 * 502         		Bad Gateway						GET, POST, PUT, DELETE	Error Message				Backend service failure (data store failure).
		 * 505	       		GET	Error Message																	HTTP version not supported.
		 * */
		if (response!=null) {
			switch (responseType) {
				case BINARY:
					HttpResponse<InputStream> binaryResponse = (HttpResponse<InputStream>)response;
					int status0 = binaryResponse.getStatus();
					if (status0 == 200 || status0 == 201 || status0 == 202) {
						return getObjectFromStream(binaryResponse.getRawBody());
					}
					else {
						return "{\"status\":"+status0+", \"message\":\""+binaryResponse.getStatusText()+"\"}";
					}
				case JSON:
					HttpResponse<JsonNode> jsonResponse = (HttpResponse<JsonNode>)response;
					int status1 = jsonResponse.getStatus();
					if (status1 == 200 || status1 == 201 || status1 == 202) {
						return getObjectFromStream(jsonResponse.getRawBody());
					}
					else {
						return "{\"status\":"+status1+", \"message\":\""+jsonResponse.getStatusText()+"\"}";
					}
				case STRING:
					HttpResponse<JsonNode> stringResponse = (HttpResponse<JsonNode>)response;
					int status2 = stringResponse.getStatus();
					if (status2 == 200 || status2 == 201 || status2 == 202) {
						return getObjectFromStream(stringResponse.getRawBody());
					}
					else {
						return "{\"status\":"+status2+", \"message\":\""+stringResponse.getStatusText()+"\"}";
					}
				case OBJECT:
					//TODO To be implemented
				default:
					HttpResponse<JsonNode> stringResponse2 = (HttpResponse<JsonNode>)response;
					int status3 = stringResponse2.getStatus();
					if (status3 == 200 || status3 == 201 || status3 == 202) {
						return getObjectFromStream(stringResponse2.getRawBody());
					}
					else {
						return "{\"status\":"+status3+", \"message\":\""+stringResponse2.getStatusText()+"\"}";
					}
			}
		}
		return null;
	}
	/**
	 * @return
	 */
	public static final UniRestConnector getInstance() {
		if (instance == null) {
			instance = new UniRestConnector();
		}
		return instance;
	}
}
