package com.selenium2.easy.test.server.unireest.connector;

import static com.mashape.unirest.http.Unirest.*;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.unireest.connector.model.RequestConfiguration;
import com.selenium2.easy.test.server.unireest.connector.model.SecureConfiguration;
import com.selenium2.easy.test.server.xml.WebMethod;

public class UniRestConnector {
	private static UniRestConnector instance = null;
	
	private UniRestConnector() {
		super();
	}
	
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

	public static final UniRestConnector getInstance() {
		if (instance == null) {
			instance = new UniRestConnector();
		}
		return instance;
	}
}
