package com.selenium2.easy.test.server.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium2.easy.test.server.automated.WebDriverSelector;
import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.exceptions.FrameworkException;
import com.selenium2.easy.test.server.exceptions.NotFoundException;
import com.selenium2.easy.test.server.xml.SearchType;
import com.selenium2.easy.test.server.xml.XMLTestGroup;

/**
 * Selenium2 operations helper class that provides Selenium2 static operations and framework friendly
 * features such as the load or save of the JAXB XML configuration or the mapping of a query string, 
 * and so on ...
 * @author Fabrizio Torelli
 * @see BROWSER_TYPE
 * @see SeleniumHelper
 * @see WebDriver
 * @see WebElement
 * @see XMLTestGroup
 */
public class SeleniumUtilities {
	static {
		if (System.getProperty("log4j.configurationFile")==null)
			System.setProperty("log4j.configurationFile", "log4j2.xml");
	}
	private static Logger logger = LoggerFactory.getLogger("com.selenium2.easy.test.server");
	private static final SeleniumHelper seleniumHelper = new SeleniumHelper();

	/**
	 * Enumeration that describes the different kinds of Browser types available in the framework
	 * <br/>
	 * The members are :
	 * <br/><b>IE</b> - The Internet Explorer browser descriptor
	 * <br/><b>CROME</b> - The Google Chrome browser descriptor
	 * <br/><b>FIREFOX</b> - The Mozilla Firefox browser descriptor
	 * <br/><b>OPERA</b> - The Opera browser descriptor
	 * <br/><b>HTML_UNIT</b> - The HTML Unit browser descriptor
	 * <br/><b>REMOTE</b> - The Remote browser descriptor
	 * <br/><b>EVENT_FIRING</b> - The Event firing sub browser descriptor 
	 * <br/>
	 * @author Fabrizio Torelli
	 *
	 */
	public static enum BROWSER_TYPE {
		/**
		 * The Internet Explorer browser descriptor
		 */
		IE, 
		/**
		 * The Google Chrome browser descriptor
		 */
		CROME, 
		/**
		 * The Mozilla Firefox browser descriptor
		 */
		FIREFOX, 
		/**
		 * The Opera browser descriptor
		 */
		OPERA, 
		/**
		 * The HTML Unit browser descriptor
		 */
		HTML_UNIT, 
		/**
		 * The Remote browser descriptor
		 */
		REMOTE, 
		/**
		 * The Event firing sub browser descriptor
		 */
		EVENT_FIRING};
	
	
	/**
	 * This method retrieves a WebDriver according to the {@link BROWSER_TYPE} enumeration without using any
	 * further WebDriver configuration parameter
	 * @param type Is the {@link BROWSER_TYPE} selector of the {@link WebDriver}.
	 * @return The {@link WebDriver} discovered or null
	 */
	public static final WebDriver getBrowserDriver(BROWSER_TYPE type) {
		if (type!=BROWSER_TYPE.REMOTE && type!=BROWSER_TYPE.EVENT_FIRING)
			return getBrowserDriver(type, null, null, null, null);
		return null;
	}
	
	/**
	 * This method retrieves a WebDriver according to the {@link BROWSER_TYPE} enumeration using some
	 * further WebDriver configuration parameters
	 * @param type Is the {@link BROWSER_TYPE} selector of the {@link WebDriver}.
	 * @param firingDriver Related triggering {@link WebDriver}, whom is responsible be the behalf with the User Interface
	 * @param exec The {@link CommandExecutor} used in some different kind of {@link WebDriver}s.
	 * @param remoteURL The remote URL to be opened by the {@link WebDriver} after the creation of the driver
	 * @param capabilities Capabilities full path class to be used with the REMOTE {@link WebDriver} (and it configures the driver for the execution platform).
	 * @return The {@link WebDriver} discovered or null
	 */
	public static final WebDriver getBrowserDriver(BROWSER_TYPE type, WebDriver firingDriver, CommandExecutor exec, URL remoteURL, Capabilities capabilities) {
		WebDriver webDriver = null;
		switch(type) {
			case IE:
				webDriver = new InternetExplorerDriver();
				break;
			case FIREFOX:
				webDriver = new FirefoxDriver();
				break;
			case OPERA:
				webDriver = new OperaDriver();
				break;
			case HTML_UNIT:
				webDriver = new HtmlUnitDriver();
				break;
			case REMOTE:
				if (remoteURL==null)
					webDriver = new RemoteWebDriver(exec, capabilities);
				else
					webDriver = new RemoteWebDriver(remoteURL, capabilities);
				break;
			case EVENT_FIRING:
				webDriver = new EventFiringWebDriver(firingDriver);
				break;
			default:
				webDriver = new ChromeDriver();
		}
		return webDriver;
	}

	/**
	 * This method close and finalize a {@link WebDriver}. After the use of this feature the 
	 * {@link WebDriver} must be recreated or reinitialized.
	 * @param webDriver {@link WebDriver} to close and finalize
	 */
	public static final void closeBrowserDriver(WebDriver webDriver) {
		if (webDriver!=null) {
			webDriver.close();
			webDriver.quit();
		}
	}

	/**
	 * This method creates an image full screen's screenshot and save it in a specified file
	 * @param webDriver {@link WebDriver} used to take the screenshot.
	 * @param screenshotFileName The full path's filename to save the screenshot in
	 * @throws FrameworkException When any exception occurs during the {@link WebDriver} operation
	 */
	public static final void saveScreenShot(WebDriver webDriver, String screenshotFileName) throws FrameworkException{
		try {
			seleniumHelper.saveScreenshot(screenshotFileName, webDriver);
		} catch (Throwable e) {
		    logger.error("error Taking the scrinshot in file ='" + screenshotFileName + "'", e);
			throw new FrameworkException("Unable to take scrinshot ans save it to file : " + screenshotFileName + " - exception : " ,e);
		}
	}

	/**
	 * This method submits a specified WebElement if it has the submit operation available
	 * @param elem {@link WebElement} referrer of the action
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 */
	public static final void submitActionElement(WebElement elem) throws ActionException {
		try {
			elem.submit();
		} catch (Throwable e) {
		    logger.error("Error running 'submit' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'submit' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method clicks a specified WebElement if it has the click operation available
	 * @param elem {@link WebElement} referrer of the action
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 */
	public static final void clickActionElement(WebElement elem) throws ActionException {
		try {
			elem.click();
		} catch (Throwable e) {
		    logger.error("Error running 'click' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'click' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method sets a specified value in the WebElement if it has related attribute
	 * @param elem {@link WebElement} referrer of the action
	 * @param value String value used in the operation
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 */
	public static final void setValueToElement(WebElement elem, String value) throws ActionException {
		try {
			elem.sendKeys(value);
		} catch (Throwable e) {
		    logger.error("Error running 'setValue' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'setValue' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method retrieves the selection property of a specified WebElement
	 * @param elem {@link WebElement} referrer of the action
	 * @return The requested status
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 */
	public static final boolean isSelectedTheElement(WebElement elem) throws ActionException {
		try {
			return elem.isSelected();
		} catch (Throwable e) {
		    logger.error("Error running 'isSelected' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'isSelected' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method retrieves the enabled/disable property of a specified WebElement
	 * @param elem {@link WebElement} referrer of the action
	 * @return The requested status
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 */
	public static final boolean isEnabledTheElement(WebElement elem) throws ActionException {
		try {
			return elem.isEnabled();
		} catch (Throwable e) {
		    logger.error("Error running 'isEnabled' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'isEnabled' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method retrieves on the UI one WebElement according to the search clause in the 
	 * hierarchy of a specified element
	 * @param elem {@link WebElement} referrer of the action
	 * @param by The {@link By} clause to find out the element
	 * @return The found {@link WebElement}
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 * @throws NotFoundException When the search criteria do not match any element
	 */
	public static final WebElement findOneInTheElement(WebElement elem, By clause) throws ActionException, NotFoundException {
		try {
			return elem.findElement(clause);
			 
		} catch (NoSuchElementException e) {
		    logger.error("Error running 'findElement' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new NotFoundException("Unable to apply 'findElement' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		} catch (Throwable e) {
		    logger.error("Error running 'findElement' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'findElement' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method retrieves on the UI one WebElement according to the search clause in the
	 * current page
	 * @param webDriver {@link WebDriver} used to search the element
	 * @param by The {@link By} clause to find out the element
	 * @return The found {@link WebElement} or null
	 * @throws ActionException When any exception occurs during the {@link WebDriver} operation
	 * @throws NotFoundException When the search criteria do not match any element
	 */
	public static final WebElement findOneInThePage(WebDriver driver, By clause) throws ActionException, NotFoundException {
		try {
			return driver.findElement(clause);
		} catch (NoSuchElementException e) {
		    logger.error("Error running 'findElement' action on the page", e);
			throw new NotFoundException("Unable to apply 'findElement' action on the page due to the exception : ", e);
		} catch (Throwable e) {
		    logger.error("Error running 'findElement' action on the page", e);
			throw new ActionException("Unable to apply 'findElement' action on the page due to the exception : ", e);
		}
	}
	
	/**
	 * This method retrieves on the UI one or more WebElements according to the search clause in the 
	 * hierarchy of a specified element
	 * @param elem {@link WebElement} referrer of the action
	 * @param by The {@link By} clause to find out the elements
	 * @return The found {@link WebElement} list or an empty one
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 * @throws NotFoundException When the search criteria do not match any element
	 */
	public static final List<WebElement> findManyInTheElement(WebElement elem, By clause) throws ActionException, NotFoundException {
		try {
			return elem.findElements(clause);
		} catch (NoSuchElementException e) {
		    logger.error("Error running 'findElements' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new NotFoundException("Unable to apply 'findElements' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		} catch (Throwable e) {
		    logger.error("Error running 'findElements' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'findElements' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method retrieves on the UI one or more WebElements according to the search clause in the
	 * current page
	 * @param webDriver {@link WebDriver} used to search the elements
	 * @param by The {@link By} clause to find out the elements
	 * @return The found {@link WebElement} list or an empty one
	 * @throws ActionException When any exception occurs during the {@link WebDriver} operation
	 * @throws NotFoundException When the search criteria do not match any element
	 */
	public static final List<WebElement> findManyInThePage(WebDriver driver, By clause) throws ActionException, NotFoundException {
		try {
			return driver.findElements(clause);
		} catch (NoSuchElementException e) {
		    logger.error("Error running 'findElements' action on the page", e);
			throw new NotFoundException("Unable to apply 'findElements' action on the page due to the exception : ", e);
		} catch (Throwable e) {
		    logger.error("Error running 'findElements' action on the page", e);
			throw new ActionException("Unable to apply 'findElements' action on the page due to the exception : ", e);
		}
	}
	
	/**
<	 * This method retrieves the status if a specified WebElement is shown on the UI
	 * @param elem {@link WebElement} referrer of the action
	 * @return The requested status
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 */
	public static final boolean isDisplayedTheElement(WebElement elem) throws ActionException {
		try {
			return elem.isDisplayed();
		} catch (Throwable e) {
		    logger.error("Error running 'isDisplayed' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'isDisplayed' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method creates an image WebElement relative screenshot returns an output object to be saved
	 * in a further time
	 * @param elem {@link WebElement} referrer of the action
	 * @param type The type of output requested for the screenshot
	 * @throws FrameworkException When any exception occurs during the {@link WebElement} operation
	 */
	public static final <T> T takeAScreenshotFromTheElement(WebElement elem, OutputType<T> type) throws ActionException {
		try {
			return elem.getScreenshotAs(type);
		} catch (Throwable e) {
		    logger.error("Error running 'getScreenshotAs' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'getScreenshotAs' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method retrieves page source code
	 * @param webDriver {@link WebDriver} used to retrieve the page source code
	 * @return The page source code string
	 * @throws ActionException When any exception occurs during the {@link WebDriver} operation
	 */
	public static final String getPageSource(WebDriver driver) throws ActionException {
		try {
			return driver.getPageSource();
		} catch (Throwable e) {
		    logger.error("Error running 'getPageSource' action  due to", e);
			throw new ActionException("Unable to apply 'getPageSource' action  due to", e);
		}
	}
	
	/**
	 * This method retrieves page title
	 * @param webDriver {@link WebDriver} used to retrieve the page title
	 * @return The title string
	 * @throws ActionException When any exception occurs during the {@link WebDriver} operation
	 */
	public static final String getPageTitle(WebDriver driver) throws ActionException {
		try {
			return driver.getTitle();
		} catch (Throwable e) {
		    logger.error("Error running 'getPageSource' action due to", e);
			throw new ActionException("Unable to apply 'getPageSource' action due to", e);
		}
	}
	
	/**
	 * This method retrieve the value of an attribute related to a specified WebElement if it has that attribute
	 * @param elem {@link WebElement} referrer of the action
	 * @param attributeName String value used in the operation
	 * @return The attribute value or null
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 */
	public static final String getAttributeFromElement(WebElement elem, String attributeName) throws ActionException {
		try {
			return elem.getAttribute(attributeName);
		} catch (Throwable e) {
		    logger.error("Error running 'getAttribute' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'getAttribute' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method retrieve the value of a property related to a specified WebElement if it has that property
	 * @param elem {@link WebElement} referrer of the action
	 * @param propertyName String value used in the operation
	 * @return The property value or null
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 */
	public static final String getCssValueFromElement(WebElement elem, String propertyName) throws ActionException {
		try {
			return elem.getCssValue(propertyName);
		} catch (Throwable e) {
		    logger.error("Error running 'getCssValue' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'getCssValue' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method retrieve a rectangle bounding a specified WebElement if it has been shown on the UI
	 * @param elem {@link WebElement} referrer of the action
	 * @return The rectangle related to the {@link WebElement}
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 */
	public static final Rectangle getRectFromElement(WebElement elem) throws ActionException {
		try {
			return elem.getRect();
		} catch (Throwable e) {
		    logger.error("Error running 'getRect' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'getRect' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method retrieve a point indicating the upper right position of a specified WebElement if it has been shown on the UI
	 * @param elem {@link WebElement} referrer of the action
	 * @return The point related to the {@link WebElement} location on the UI
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 */
	public static final Point getLocationFromElement(WebElement elem) throws ActionException {
		try {
			return elem.getLocation();
		} catch (Throwable e) {
		    logger.error("Error running 'getLocation' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'getLocation' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method retrieve a dimension related to a specified WebElement if it has been shown on the UI
	 * @param elem {@link WebElement} referrer of the action
	 * @return The dimension of related {@link WebElement}
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 */
	public static final Dimension getSizeFromElement(WebElement elem) throws ActionException {
		try {
			return elem.getSize();
		} catch (Throwable e) {
		    logger.error("Error running 'getSize' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'getSize' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method retrieve tag name of a specified WebElement
	 * @param elem {@link WebElement} referrer of the action
	 * @return The tag name of the {@link WebElement}
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 */
	public static final String getTagNameFromElement(WebElement elem) throws ActionException {
		try {
			return elem.getTagName();
		} catch (Throwable e) {
		    logger.error("Error running 'getTagName' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'getTagName' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	/**
	 * This method clear the value of a specified WebElement, if it has the value property
	 * @param elem {@link WebElement} referrer of the action
	 * @throws ActionException When any exception occurs during the {@link WebElement} operation
	 */
	public static final void clearValueToElement(WebElement elem) throws ActionException {
		try {
			elem.clear();
		} catch (Throwable e) {
		    logger.error("Error running 'clearValue' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'clearValue' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	
	/**
	 * This method provides the feature to load a {@link XMLTestGroup} object parsing an
	 * input XML file
	 * @param xmlFilePath Full file path locating the XML source
	 * @return The found and parsed {@link XMLTestGroup} or null
	 */
	public static final XMLTestGroup loadXMLTestFramework(File xmlFilePath) {
	  try {

		JAXBContext jaxbContext = JAXBContext.newInstance(XMLTestGroup.class);
		Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();

		// output pretty printed
		//jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
		File schema = SeleniumUtilities.recoverFileInJar("com/selenium2/easy/test/server/xml/schema/XMLTestGroupSchema.xsd");
		jaxbMarshaller.setSchema(sf.newSchema(schema));
		return (XMLTestGroup) jaxbMarshaller.unmarshal(xmlFilePath);

      } catch (JAXBException e) {
	    logger.error("Error loading xml test case framework configuration : " + xmlFilePath, e);
      } catch (Throwable e) {
	    logger.error("Error loading xml test case framework configuration : " + xmlFilePath, e);
      }
	  return null;
	}

	/**
	 * This method provides the feature to save a {@link XMLTestGroup} in a target XML file 
	 * parsing the object content in the XML format
	 * @return The found and parsed {@link XMLTestGroup} or null
	 * @param root The {@link XMLTestGroup} to save in the XML file
	 * @param xmlFilePath Full file path locating the XML target
	 * @return The execution status
	 */
	public static final boolean saveXMLTestFramework(XMLTestGroup root, File xmlFilePath) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(XMLTestGroup.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			jaxbMarshaller.marshal(root, xmlFilePath);
			
			return true;
		} catch (JAXBException e) {
			logger.error("Error saving xml test case framework configuration : " + xmlFilePath, e);
		} catch (Throwable e) {
			logger.error("Error saving xml test case framework configuration : " + xmlFilePath, e);
		}
		return false;
	}
	
	/**
	 * This method maps the query parameters in a queryString for a web GET call
	 * @param map Map containing the query string parameters
	 * @return The parsed query string
	 */
	public static final String mapToQueryString(Map<String, String> map) {
		String retValue = "";
		if (map!=null) {
			for(String key : map.keySet()) {
				retValue += (retValue.length()>0 ? "&" : "") + key + "=" + map.get(key);
			}
		}
		return retValue;
	}
	
	/**
	 * This method create a {@link By} clause used to search WebElements on the UI
	 * @param map Map containing the query string parameters
	 * @return The parsed query string
	 * @param type The {@link SearchType} criteria enumeration clause
	 * @param searchText The text used to match the given criteria
	 * @return The {@link By} clause to find out one or more elements
	 */
	public static final By getBy(SearchType type, String searchText) {
		switch(type) {
			case NAME:
				return By.name(searchText);
			case ID:
				return By.id(searchText);
			case CLASS_NAME:
				return By.className(searchText);
			case XPATH:
				return By.xpath(searchText);
			case TAG:
				return By.tagName(searchText);
			case EXACT_LINK:
				return By.linkText(searchText);
			case PARTIAL_LINK:
				return By.partialLinkText(searchText);
			default:
				return By.cssSelector(searchText);
			
		}
	}
	/**
	 * This method provides a waitFor feature of the WebDriver since a page location change 
	 * has performed an the page context is not yet loaded with a specified check interval in seconds
	 * @param driver {@link WebDriver} to be put in listening for the page load
	 * @param timeout Numeric value representing seconds to stand by before check again the page is loaded
	 */
	public static final void waitForLoad(WebDriver driver, long timeout) {
	    new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver wd) {
		          return ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete");
		        }
		      }
	    );
	}
	private static long copyFromZipFile(String zipFilePath, String relativeFilePath, FileOutputStream os) {
        BufferedInputStream bis = null;
        ZipFile zipFile = null;
        long bytes = 0;
	    try {
	        zipFile = new ZipFile(zipFilePath);
	        Enumeration<? extends ZipEntry> e = zipFile.entries();
	        while (e.hasMoreElements()) {
	            ZipEntry entry = (ZipEntry) e.nextElement();
	            // if the entry is not directory and matches relative file then extract it
	            if (!entry.isDirectory() && entry.getName().equals(relativeFilePath)) {
	                bis = new BufferedInputStream(
	                        zipFile.getInputStream(entry));
	                 bytes = IOUtils.copyLarge(bis, os);
	            } else {
	                continue;
	            }
	        }
	    } catch (Throwable e) {
			logger.error("Error extracting from zip '"+zipFilePath+"' resource : " + relativeFilePath + " caused by : ", e);
	    }
	    finally {
	    	if (bis!=null) {
	    		try {
	    			bis.close();
	    	    } catch (Throwable e) {
	    	    }
	    	}
	    	if (zipFile!=null) {
	    		try {
	    			zipFile.close();
	    	    } catch (Throwable e) {
	    	    }
	    	}
	    }
	    return bytes;
	}
	/**
	 * Extract in a temporary file a resource from the current jar
	 * @param fileJarPath java class full path replacing the dot with the slash
	 * @return Temporary File
	 */
	public static synchronized File recoverFileInJar(String fileJarPath) {
		String jarPath = "";
		try {
			URL url = WebDriverSelector.class.getProtectionDomain().getCodeSource().getLocation();
			jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
		} catch (Throwable e1) {
		}
		File file = null;
		String extension = "tmp";
		if (fileJarPath.indexOf(".")>=0) {
			extension = fileJarPath.substring(fileJarPath.lastIndexOf(".")+1);
		}
		FileOutputStream output = null;
		try {
			file = File.createTempFile("tempfile", "."+extension);
			output = new FileOutputStream(file);
			long bytes = copyFromZipFile(jarPath, fileJarPath, output);
			file.deleteOnExit();
			if (bytes==0L) {
				file = null;
			}
		} catch (Throwable ex) {
			file = null;
			logger.error("Error loading resource : " + fileJarPath + " caused by : ", ex);
		}
		finally {
			if (output!=null) {
				try {
					output.close();
				} catch (Throwable e) {
				}
			}
		}
		return file;
	}
	/**
	 * Class containing the helper for the the Screenshot features
	 * @author Fabrizio Torelli
	 * @see WebDriver
	 * @see TakesScreenshot
	 * @see FileUtils
	 */
	private static class SeleniumHelper {
		  
	    /**
	     * The Method provides the page's screenshot feature
	     * @param screenshotFileName Full path file name to save in the screenshot
	     * @param driver {@link WebDriver} to be take the page's screenshot
	     * @throws IOException When the file saving operation fails due to an IO issue
	     */
	    public void saveScreenshot(String screenshotFileName,  WebDriver driver) throws IOException {
	      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	      FileUtils.copyFile(screenshot, new File(screenshotFileName));
	      logger.debug("Taken screenshot to file : " + screenshotFileName);
	    }
	  }
}
