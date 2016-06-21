package com.selenium2.easy.test.server.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.exceptions.FrameworkException;
import com.selenium2.easy.test.server.exceptions.NotFoundException;
import com.selenium2.easy.test.server.xml.XMLTestGroup;

public class SeleniumUtilities {
	static {
		if (System.getProperty("log4j.configurationFile")==null)
			System.setProperty("log4j.configurationFile", "log4j2.xml");
	}
	private static Logger logger = LoggerFactory.getLogger("com.selenium2.easy.test.server");
	private static final SeleniumHelper seleniumHelper = new SeleniumHelper();

	public static enum BROWSER_TYPE {IE, CROME, FIREFOX, OPERA, HTML_UNIT, REMOTE, EVENT_FIRING};
	
	
	public static final WebDriver getBrowserDriver(BROWSER_TYPE type) {
		if (type!=BROWSER_TYPE.REMOTE && type!=BROWSER_TYPE.EVENT_FIRING)
			return getBrowserDriver(type, null, null, null, null);
		return null;
	}
	
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

	public static final void closeBrowserDriver(WebDriver webDriver) {
		if (webDriver!=null) {
			webDriver.close();
			webDriver.quit();
		}
	}

	public static final void saveScreenShot(WebDriver webDriver, String screenshotFileName) throws FrameworkException{
		try {
			seleniumHelper.saveScreenshot(screenshotFileName, webDriver);
		} catch (Throwable e) {
		    logger.error("error Taking the scrinshot in file ='" + screenshotFileName + "'", e);
			throw new FrameworkException("Unable to take scrinshot ans save it to file : " + screenshotFileName + " - exception : " ,e);
		}
	}

	public static final void submitActionElement(WebElement elem) throws ActionException {
		try {
			elem.submit();
		} catch (Throwable e) {
		    logger.error("Error running 'submit' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'submit' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final void clickActionElement(WebElement elem) throws ActionException {
		try {
			elem.click();
		} catch (Throwable e) {
		    logger.error("Error running 'click' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'click' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final void setValueToElement(WebElement elem, String value) throws ActionException {
		try {
			elem.sendKeys(value);
		} catch (Throwable e) {
		    logger.error("Error running 'setValue' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'setValue' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final boolean isSelectedTheElement(WebElement elem) throws ActionException {
		try {
			return elem.isSelected();
		} catch (Throwable e) {
		    logger.error("Error running 'isSelected' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'isSelected' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final boolean isEnabledTheElement(WebElement elem) throws ActionException {
		try {
			return elem.isEnabled();
		} catch (Throwable e) {
		    logger.error("Error running 'isEnabled' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'isEnabled' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final WebElement findOneInTheElement(WebElement elem, By clause) throws ActionException {
		try {
			return elem.findElement(clause);
		} catch (Throwable e) {
		    logger.error("Error running 'findElement' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'findElement' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final WebElement findOneInThePage(WebDriver driver, By clause) throws ActionException {
		try {
			return driver.findElement(clause);
		} catch (Throwable e) {
		    logger.error("Error running 'findElement' action on the page", e);
			throw new ActionException("Unable to apply 'findElement' action on the page due to the exception : ", e);
		}
	}
	
	public static final List<WebElement> findManyInTheElement(WebElement elem, By clause) throws ActionException {
		try {
			return elem.findElements(clause);
		} catch (Throwable e) {
		    logger.error("Error running 'findElements' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'findElements' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final List<WebElement> findManyInThePage(WebDriver driver, By clause) throws ActionException {
		try {
			return driver.findElements(clause);
		} catch (Throwable e) {
		    logger.error("Error running 'findElements' action on the page", e);
			throw new ActionException("Unable to apply 'findElements' action on the page due to the exception : ", e);
		}
	}
	
	public static final boolean isDisplayedTheElement(WebElement elem) throws ActionException {
		try {
			return elem.isDisplayed();
		} catch (Throwable e) {
		    logger.error("Error running 'isDisplayed' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'isDisplayed' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final <T> T takeAScreenshotFromTheElement(WebElement elem, OutputType<T> type) throws ActionException {
		try {
			return elem.getScreenshotAs(type);
		} catch (Throwable e) {
		    logger.error("Error running 'getScreenshotAs' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'getScreenshotAs' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final String getPageSource(WebDriver driver) throws ActionException {
		try {
			return driver.getPageSource();
		} catch (Throwable e) {
		    logger.error("Error running 'getPageSource' action  due to", e);
			throw new ActionException("Unable to apply 'getPageSource' action  due to", e);
		}
	}
	
	public static final String getPageTitle(WebDriver driver) throws ActionException {
		try {
			return driver.getTitle();
		} catch (Throwable e) {
		    logger.error("Error running 'getPageSource' action due to", e);
			throw new ActionException("Unable to apply 'getPageSource' action due to", e);
		}
	}
	
	public static final String getAttributeFromElement(WebElement elem, String attributeName) throws ActionException {
		try {
			return elem.getAttribute(attributeName);
		} catch (Throwable e) {
		    logger.error("Error running 'getAttribute' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'getAttribute' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final String getCssValueFromElement(WebElement elem, String propertyName) throws ActionException {
		try {
			return elem.getCssValue(propertyName);
		} catch (Throwable e) {
		    logger.error("Error running 'getCssValue' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'getCssValue' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final Rectangle getRectFromElement(WebElement elem) throws ActionException {
		try {
			return elem.getRect();
		} catch (Throwable e) {
		    logger.error("Error running 'getRect' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'getRect' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final Point getLocationFromElement(WebElement elem) throws ActionException {
		try {
			return elem.getLocation();
		} catch (Throwable e) {
		    logger.error("Error running 'getLocation' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'getLocation' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final Dimension getSizeFromElement(WebElement elem) throws ActionException {
		try {
			return elem.getSize();
		} catch (Throwable e) {
		    logger.error("Error running 'getSize' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'getSize' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final String getTagNameFromElement(WebElement elem) throws ActionException {
		try {
			return elem.getTagName();
		} catch (Throwable e) {
		    logger.error("Error running 'getTagName' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'getTagName' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final void clearValueToElement(WebElement elem) throws ActionException {
		try {
			elem.clear();
		} catch (Throwable e) {
		    logger.error("Error running 'clearValue' action on the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new ActionException("Unable to apply 'clearValue' action to element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final WebElement findWithinElement(WebElement elem, By clause) throws NotFoundException, FrameworkException {
		try {
			WebElement subElem = elem.findElement(clause);
			if (subElem==null)
				throw new NotFoundException("Unable to locate element by clause='"+clause+"' on the user interface into the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'");
			return subElem;
		} catch (Throwable e) {
		    logger.error("Error finding element by clause : " + clause +" within the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new FrameworkException("Unable to find by clause '"+clause+"' into the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final List<WebElement> findAllWithinElement(WebElement elem, By clause) throws NotFoundException, FrameworkException {
		try {
			List<WebElement> subElems = elem.findElements(clause);
			if (subElems==null || subElems.size()==0)
				throw new NotFoundException("Unable to locate element by clause='"+clause+"' on the user interface into the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'");
			return subElems;
		} catch (Throwable e) {
		    logger.error("Error finding elements by clause : " + clause +" within the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "'", e);
			throw new FrameworkException("Unable to find by clause '"+clause+"' into the element by id='" + (elem!=null ? elem.getAttribute("id") : null) + "' exception : ", e);
		}
	}
	
	public static final XMLTestGroup loadXMLTestFramework(File xmlFilePath) {
	  try {

		JAXBContext jaxbContext = JAXBContext.newInstance(XMLTestGroup.class);
		Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		return (XMLTestGroup) jaxbMarshaller.unmarshal(xmlFilePath);

      } catch (JAXBException e) {
	    logger.error("Error loading xml test case framework configuration : " + xmlFilePath, e);
      } catch (Throwable e) {
	    logger.error("Error loading xml test case framework configuration : " + xmlFilePath, e);
      }
	  return null;
	}

	public static final boolean loadXMLTestFramework(XMLTestGroup root, File xmlFilePath) {
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
	
	public static final String mapToQueryString(Map<String, String> map) {
		String retValue = "";
		if (map!=null) {
			for(String key : map.keySet()) {
				retValue += (retValue.length()>0 ? "&" : "") + key + "=" + map.get(key);
			}
		}
		return retValue;
	}
	
	private static class SeleniumHelper {
		  
	    public void saveScreenshot(String screenshotFileName,  WebDriver driver) throws IOException {
	      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	      FileUtils.copyFile(screenshot, new File(screenshotFileName));
	      logger.debug("Taken screenshot to file : " + screenshotFileName);
	    }
	  }
}
