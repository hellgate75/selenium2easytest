package com.selenium2.easy.test.server.automated;


public class WebDriverFactory {
	private static WebDriverFactory instance = null;

	public static enum SELECTOR_TYPE {IE_SELECTOR, REMOTE_SELECTOR, IE_INTERNAL_SELECTOR, CHROME_SELECTOR, CHROME_INTERNAL_SELECTOR,
									  OPERA_SELECTOR, HTML_UNIT_SELECTOR, EVENT_FIRING_SELECTOR}; 
	
	protected WebDriverFactory() {
		super();
	}
	
	public WebDriverSelector getDriverSelector(SELECTOR_TYPE selector, Object... parameters) {
		return new WebDriverSelector(selector, parameters);
	}
	
	public static WebDriverFactory getInstance() {
		if (instance == null)
			instance = new WebDriverFactory();
		return instance;
	}

}
