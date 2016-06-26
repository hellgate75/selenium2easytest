package com.selenium2.easy.test.server.automated;


/**
 * Class retain the business logic to load the WebDriverSelector
 * @author Fabrizio Torelli
 * @see WebDriverSelector
 */
public class WebDriverFactory {
	private static WebDriverFactory instance = null;

	/**
	 * Enumeration that describes the different kinds of WebBrowser available in the framework
	 * <br/>
	 * The members are :
	 * <br/><b>IE_SELECTOR</b> - The generic Internet Explore WebDriver selector
	 * <br/><b>REMOTE_SELECTOR</b> - The generic Remote WebDriver selector
	 * <br/><b>IE_INTERNAL_SELECTOR</b> - The Windows 32-bit provided Internet Explore WebDriver selector (included by RemoteService)
	 * <br/><b>FIREFOX_SELECTOR</b> - The generic Mozilla Firefox WebDriver selector
	 * <br/><b>CHROME_SELECTOR</b> - The generic Google Chrome WebDriver selector
	 * <br/><b>CHROME_INTERNAL_SELECTOR</b> - The Windows 32-bit provided Google Chrome WebDriver selector (included by RemoteService)
	 * <br/><b>OPERA_SELECTOR</b> - The generic Opera WebDriver selector
	 * <br/><b>HTML_UNIT_SELECTOR</b> - The Unit Test WebDriver selector
	 * <br/><b>EVENT_FIRING_SELECTOR</b> - The Event firing WebDriver selector to be associated to a Browser's relevant one
	 * <br/>
	 * @author Fabrizio Torelli
	 *
	 */
	public static enum SELECTOR_TYPE {
		/**
		 * The generic Internet Explore WebDriver selector
		 */
		IE_SELECTOR, 
		/**
		 * The generic Remote WebDriver selector
		 */
		REMOTE_SELECTOR, 
		/**
		 * The Windows 32-bit provided Internet Explore WebDriver selector (included by RemoteService)
		 */
		IE_INTERNAL_SELECTOR, 
		/**
		 * The generic Mozilla Firefox WebDriver selector
		 */
		FIREFOX_SELECTOR, 
		/**
		 * The generic Google Chrome WebDriver selector
		 */
		CHROME_SELECTOR, 
		/**
		 * The Windows 32-bit provided Google Chrome WebDriver selector (included by RemoteService)
		 */
		CHROME_INTERNAL_SELECTOR,
		/**
		 * The generic Opera WebDriver selector
		 */
		OPERA_SELECTOR, 
		/**
		 * The Unit Test WebDriver selector
		 */
		HTML_UNIT_SELECTOR, 
		/**
		 * The Event firing WebDriver selector to be associated to a Browser's relevant one
		 */
		EVENT_FIRING_SELECTOR}; 
	
	private WebDriverFactory() {
		super();
	}
	
	/**
	 * Retrieve the WebDriverSelector according to the selector ({@link WebDriverFactory.SELECTOR_TYPE}}) type and 
	 * the parameters used to create the WebDriver instance. 
	 * @param selector - Selector type enumeration
	 * @param parameters
	 * @return
	 */
	public WebDriverSelector getDriverSelector(SELECTOR_TYPE selector, Object... parameters) {
		return new WebDriverSelector(selector, parameters);
	}
	
	/**
	 * Singleton class retrieve method
	 * @return The singleton instance
	 */
	public static WebDriverFactory getInstance() {
		if (instance == null)
			instance = new WebDriverFactory();
		return instance;
	}

}
