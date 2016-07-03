package com.selenium2.easy.test.server.unireest.connector;

import static com.mashape.unirest.http.Unirest.delete;
import static com.mashape.unirest.http.Unirest.get;
import static com.mashape.unirest.http.Unirest.post;
import static com.mashape.unirest.http.Unirest.put;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.request.HttpRequest;
import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.unireest.connector.cases.UniRestTestCase;
import com.selenium2.easy.test.server.unireest.connector.cases.XMLGroupedUniRestTestCase;
import com.selenium2.easy.test.server.unireest.connector.model.BasicConfiguration;
import com.selenium2.easy.test.server.unireest.connector.model.RequestConfiguration;
import com.selenium2.easy.test.server.unireest.connector.model.SecureConfiguration;
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
//					case OBJECT:
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

	/**
	 * Retrieves the URL Response Object according to the URL, the attributes map, the {@link WebMethod} and the {@link WebResponse}
	 * @param configurationMap The map of attributes used for the Headers, the QueryString, the Routing parameters and to retrieve the Body Object.
	 * @param url the URL to lookup
	 * @param method The {@link WebMethod} used to lookup the URL
	 * @param responseType The {@link WebResponse} used to parse the {@link HttpResponse}
	 * @param useSecureConnection The Use secure connection flag
	 * @return The Response Object
	 * @throws ActionException When any exception occurs during the URL lookup or the Response parse
	 */
	@SuppressWarnings("unchecked")
	public final synchronized Object retrieveUrlResponse(Map<String, String> configurationMap, String url, WebMethod method, WebResponse responseType, boolean useSecureConnection) throws ActionException {
		//TODO: Implement the RequestConfiguration filling and the performaURLAction call!!
		HttpResponse<?> response =  null;
		if (useSecureConnection) {
			SecureConfiguration configuration = new SecureConfiguration();
			//TODO Implement the Header, query string, route parameters and Body object retrieve from the configurationMap
			configuration.setWebMethod(method);
			configuration.setResponseType(responseType);
			response =  performaURLAction(url, configuration);
		}
		else {
			BasicConfiguration configuration = new BasicConfiguration();
			//TODO Implement the Header, query string, route parameters and Body object retrieve from the configurationMap
			configuration.setWebMethod(method);
			configuration.setResponseType(responseType);
			response =  performaURLAction(url, configuration);
		}
		if (response!=null) {
			switch (responseType) {
				case BINARY:
					HttpResponse<InputStream> binaryResponse = (HttpResponse<InputStream>)response;
					int status0 = binaryResponse.getStatus();
					return getObjectFromStream(binaryResponse.getRawBody());
				case JSON:
					HttpResponse<JsonNode> jsonResponse = (HttpResponse<JsonNode>)response;
					int status1 = jsonResponse.getStatus();
					return getObjectFromStream(jsonResponse.getRawBody());
				case STRING:
					HttpResponse<JsonNode> stringResponse = (HttpResponse<JsonNode>)response;
					int status2 = stringResponse.getStatus();
					return getObjectFromStream(stringResponse.getRawBody());
				case OBJECT:
					//TODO To be implemented
				default:
					return null;
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
