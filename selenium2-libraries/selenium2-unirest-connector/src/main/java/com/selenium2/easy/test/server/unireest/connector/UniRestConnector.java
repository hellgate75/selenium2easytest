package com.selenium2.easy.test.server.unireest.connector;

import static com.mashape.unirest.http.Unirest.*;

import com.mashape.unirest.request.GetRequest;
import com.selenium2.easy.test.server.unireest.connector.model.RequestConfiguration;

public class UniRestConnector {
	private static UniRestConnector instance = null;
	
	private UniRestConnector() {
		super();
	}
	
	public final synchronized void performaURLAction(String url, RequestConfiguration config) {
		GetRequest request = get(url);
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
		//TODO: Implements the data type and the selector kind (PUT, POST, GET, DELETE)
	}

	public static final UniRestConnector getInstance() {
		if (instance == null) {
			instance = new UniRestConnector();
		}
		return instance;
	}
}
