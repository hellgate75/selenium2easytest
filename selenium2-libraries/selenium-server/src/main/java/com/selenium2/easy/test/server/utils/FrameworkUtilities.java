/**
 * 
 */
package com.selenium2.easy.test.server.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium2.easy.test.server.automated.WebDriverSelector;
import com.selenium2.easy.test.server.cases.TestCase;
import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.xml.AssertionOperationType;
import com.selenium2.easy.test.server.xml.AssertionThatMatcherType;
import com.selenium2.easy.test.server.xml.AssertionType;
import com.selenium2.easy.test.server.xml.XMLTestAssertion;
import com.selenium2.easy.test.server.xml.XMLTestCase;
import com.selenium2.easy.test.server.xml.XMLTestCaseAction;
import com.selenium2.easy.test.server.xml.XMLTestDOMAssertion;
import com.selenium2.easy.test.server.xml.XMLWebElement;

/**
 * Framework base utilities to provide element parse, validation and resource load functions
 * @author Fabrizio Torelli
 *
 */
public class FrameworkUtilities {

	private static Logger logger = LoggerFactory.getLogger("com.selenium2.easy.test.server");

	
	private FrameworkUtilities() {
		super();
	}

	/*
	 * File ZIP/JAR entry copier utility that copy the entry data in a specific output stream
	 */
	private static final long copyFromZipFile(String zipFilePath, String relativeFilePath, FileOutputStream os) {
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
			SeleniumUtilities.logger.error("Error extracting from zip '"+zipFilePath+"' resource : " + relativeFilePath + " caused by : ", e);
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
	 * Extracts in a temporary file a resource from the current jar
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
			SeleniumUtilities.logger.error("Error loading resource : " + fileJarPath + " caused by : ", ex);
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
	 * Extracts from results the value of a variable
	 * <br/>
	 * <br/> There are some special values case and syntax sensible for any element in the list such as:
	 * <br/> <b>Dimension -> $D(w,h)</b>      - Where w is the width and h is the height of the Dimension
	 * - <b>Can be used to match an expression with a size got from a WebElement</b>
	 * <br/> <b>Location  -> $P(x,y)</b>      - Where x and y are the screen coordinates of the Point
	 * - <b>Can be used to match an expression with the location got from a WebElement</b>
	 * <br/> <b>Rectangle -> $R(x,y,w,h)</b>  - Where x and y are the screen coordinates of the Top right vertex Point and w is the width and h is the height of the Dimension of the Rectangle
	 * - <b>Can be used to match an expression with the size got from a WebElement</b>
	 * <br/> <b>Object -> $O(fullClassName)</b>  - Where fullClassName is the full package class name of the object with the default constructor to get the new instance
	 * - <b>Can be used to match an expression with some object referring to the original ones</b>
	 * <br/> <b>From Map -> $M(variable)</b>  - Where variable is the variable name of the object previously saved in the results map
	 * - <b>Can be used to match an expression with some object referring to the original ones</b>
	 * <br/> <b>From Text File -> $F(textFileFullPath)</b>  - Where text file pull path is the file name including the path of the information to be loaded from the file system
	 * - <b>Can be used to match an expression with some string file data referring to the original values</b>
	 * <br/>
	 * @param str The variable name 
	 * @param results The results map to search the variable in
	 * @return The result parsed Object
	 */
	public static Object pack( final String str, Map<String, Object> results )
	{
	    if ( str == null || str.length() > 18 )
	        return str;
	    if ( str.isEmpty() )
	        return ""; //ensure that interned empty string literal is returned
	    final char firstChar = str.charAt( 0 );
	    if ( str.length() == 1 ) //one char string is converted to Character
	        return firstChar;
	    //all wrapper classes valueOf methods support leading zeroes, but conversion back will not restore them
	    final boolean startDigit1to9 = firstChar >= '1' && firstChar <= '9';
	    if ( startDigit1to9 )
	    {
	        try
	        { //Integers have extended caching support, prefer them
	            return Integer.valueOf( str );    
	        }
	        catch ( NumberFormatException ex )
	        {
	            try
	            { //fallback to Longs if possible
	                return Long.valueOf( str );    
	            }
	            catch ( NumberFormatException ex2 )
	            {                       
	                try
	                {
	                    //convert to double and check if we can convert value back to the exactly original string
	                    final Double val = Double.valueOf( str );
	                    final String reverted = val.toString();
	                    if ( reverted.equals( str ) )
	                        return val;
	                }
	                catch ( NumberFormatException ex3 )
	                {
		                try
		                {
	                	//convert to float and check if we can convert value back to the exactly original string
	                    final Float val = Float.valueOf( str );
	                    final String reverted = val.toString();
	                    if ( reverted.equals( str ) )
	                        return val;
		                }
		                catch ( NumberFormatException ignored )
		                {
		                	
		                }
	                }
	            }
	        }
	    }
	    //add support for other known data types here. Replacement objects must be as compact as possible.
	    //Special type: character followed by several digits.
	    final boolean secondDigit1to9 = str.charAt( 1 ) >= '1' && str.charAt( 1 ) <= '9';
	    if ( secondDigit1to9 )
	    {
	        try
	        {
	            return new FrameworkUtilities.CharAndNumber( firstChar, Long.valueOf( str.substring( 1 ) ) );
	        }
	        catch ( NumberFormatException ignored )
	        {
	        }
	    }
	    
	    if (str.toUpperCase().trim().startsWith("$R(") && str.trim().endsWith(")")) {
	    	String value=str.toUpperCase().trim().substring(3, str.trim().length()-1);
	    	String[] tokens = value.split(",");
	    	if (tokens.length>=4) {
	    		try {
					int x = Integer.parseInt(tokens[0].trim());
					int y = Integer.parseInt(tokens[1].trim());
					int w = Integer.parseInt(tokens[2].trim());
					int h = Integer.parseInt(tokens[3].trim());
					return new Rectangle(x, y, h, w);
				} catch (Throwable e) {
				}
	    	}
	    }
	    if (str.toUpperCase().trim().startsWith("$D(") && str.trim().endsWith(")")) {
	    	String value=str.toUpperCase().trim().substring(3, str.trim().length()-1);
	    	String[] tokens = value.split(",");
	    	if (tokens.length>=2) {
	    		try {
					int w = Integer.parseInt(tokens[0].trim());
					int h = Integer.parseInt(tokens[1].trim());
					return new Dimension(w, h);
				} catch (Throwable e) {
				}
	    	}
	    }
	    if (str.toUpperCase().trim().startsWith("$P(") && str.trim().endsWith(")")) {
	    	String value=str.toUpperCase().trim().substring(3, str.trim().length()-1);
	    	String[] tokens = value.split(",");
	    	if (tokens.length>=2) {
	    		try {
					int x = Integer.parseInt(tokens[0].trim());
					int y = Integer.parseInt(tokens[1].trim());
					return new Point(x, y);
				} catch (Throwable e) {
				}
	    		
	    	}
	    }
	    if (str.toUpperCase().trim().startsWith("$O(") && str.trim().endsWith(")")) {
	    	String value=str.toUpperCase().trim().substring(3, str.trim().length()-1);
	    	if (value.length()>0) {
	    		try {
					return Class.forName(value).newInstance();
				} catch (Throwable e) {
				}
	    		
	    	}
	    }
	    if (str.toUpperCase().trim().startsWith("$M(") && str.trim().endsWith(")")) {
	    	String value=str.toUpperCase().trim().substring(3, str.trim().length()-1);
	    	if (value.length()>0 && results!=null && results.size()>0) {
	    		try {
					return results.get(value);
				} catch (Throwable e) {
				}
	    		
	    	}
	    }
	    
	    if (str.toUpperCase().trim().startsWith("$F(") && str.trim().endsWith(")")) {
	    	String value=str.toUpperCase().trim().substring(3, str.trim().length()-1);
	    	if (value.length()>0 && results!=null && results.size()>0) {
	    		try {
					return loadTextFile(value);
				} catch (Throwable e) {
				}
	    		
	    	}
	    }
	    
	    if (str.equals("TRUE") || str.equals("FALSE")) {
	    	return str.equals("TRUE");
	    }
	    return str;
	}

	/**
	 * Recovers the UI web elements from {@link XMLWebElement} search information.
	 * @param driver Driver to be used to search for elements
	 * @param element The {@link XMLWebElement} containing the UI element filter information
	 * @return The list of {@link WebElement} found on the UI
	 */
	public static List<WebElement> getElementByXML(WebDriver driver, XMLWebElement element) {
		List<WebElement> returnList = new ArrayList<WebElement>(0);
		if (element!=null) {
			By clause = element.getByClause();
			if (clause!=null && element.getSearchText()!=null && element.getSearchText().trim().length()>0) {
				if (element.getMultipleMatches()) {
					try {
						List<WebElement> elements = SeleniumUtilities.findManyInThePage(driver, clause);
						if (elements!=null && elements.size()>0) {
							returnList.addAll(elements);
						}
					} catch (Throwable e) {
					}
				}
				else {
					try {
						WebElement foundElement = SeleniumUtilities.findOneInThePage(driver, clause);
						if (foundElement!=null) {
							returnList.add(foundElement);
						}
					} catch (Throwable e) {
					}
				}
			}
		}
		return returnList;
	}

	/**
	 * Recovers the UI web elements from {@link XMLWebElement} search information contained in a list of parent UI {@link WebElement}.
	 * @param parents The list of parent {@link WebElement}
	 * @param element The {@link XMLWebElement} containing the UI element filter information
	 * @return The list of {@link WebElement} found on the UI
	 */
	public static List<WebElement> getElementByXML(List<WebElement> parents, XMLWebElement element) {
		List<WebElement> returnList = new ArrayList<WebElement>(0);
		if (element!=null && parents!=null) {
			for (WebElement parent: parents) {
				returnList.addAll(getElementByXML(parent, element));
			}
		}
		return returnList;
	}

	/**
	 * Recovers the UI web elements from {@link XMLWebElement} search information contained in a single parent UI {@link WebElement}.
	 * @param parents The parent {@link WebElement}
	 * @param element The {@link XMLWebElement} containing the UI element filter information
	 * @return The list of {@link WebElement} found on the UI
	 */
	public static List<WebElement> getElementByXML(WebElement parent, XMLWebElement element) {
		List<WebElement> returnList = new ArrayList<WebElement>(0);
		if (element!=null && parent!=null) {
			By clause = element.getByClause();
			if (clause!=null && element.getSearchText()!=null && element.getSearchText().trim().length()>0) {
				if (element.getMultipleMatches()) {
					try {
						List<WebElement> elements = SeleniumUtilities.findManyInTheElement(parent, clause);
						if (elements!=null && elements.size()>0) {
							returnList.addAll(elements);
						}
					} catch (Throwable e) {
					}
				}
				else {
					try {
						WebElement foundElement = SeleniumUtilities.findOneInTheElement(parent, clause);
						if (foundElement!=null) {
							returnList.add(foundElement);
						}
					} catch (Throwable e) {
					}
				}
			}
		}
		return returnList;
	}

	/**
	 * Loads the content of a text file and returns it as a string.
	 * @param filePath The full file path on the file system
	 * @return The string containing the file text
	 */
	public static final String loadTextFile(String filePath) {
		String fileContent = "";
		BufferedReader bufferedReader=null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)) ));
			while(bufferedReader.ready()) {
				fileContent += (fileContent.length()>0 ? "\n" : "") + bufferedReader.readLine();
			}
		} catch (IOException e) {
			logger.error("Unable to read file : " + filePath, e);
		} catch (Throwable e) {
			logger.error("Unable to read file : " + filePath, e);
		}
		finally {
			if (bufferedReader!=null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
				}
			}
		}
		return fileContent;
	}

	/**
	 * Extracts a value from a UI {@link WebElement} according to a specific operation defined in the {@link AssertionOperationType} criteria
	 * @param element The UI {@link WebElement} to be used to extract the assertion value
	 * @param type The {@link AssertionOperationType} extraction criteria
	 * @param value The value used to validate the {@link AssertionOperationType} extraction criteria if needed
	 * @return The extracted value or null
	 * @throws ActionException When any exception occurs during the assertion operation value extraction
	 */
	public  static final Object extractValue(WebElement element, AssertionOperationType type, Object value, Map<String, Object> caseResults) throws ActionException {
		switch(type) {
			case IS_DISPLAYED:
				return SeleniumUtilities.isDisplayedTheElement(element);
			case IS_ENABLED:
				return SeleniumUtilities.isEnabledTheElement(element);
			case IS_SELECTED:
				return SeleniumUtilities.isSelectedTheElement(element);
			case GET_ATTRIBUTE:
				return SeleniumUtilities.getAttributeFromElement(element, (String)value);
			case GET_CSS:
				return SeleniumUtilities.getCssValueFromElement(element, (String)value);
			case GET_LOCATION:
				return SeleniumUtilities.getLocationFromElement(element);
			case RETRIEVE_MAPPED_VALUE:
				return caseResults.get(""+value);
			default:
		}
		return null;
	}

	/**
	 * Extracts a list of values from a list of UI {@link WebElement} according to a specific operation defined in the {@link AssertionOperationType} criteria
	 * @param elements The UI {@link WebElement} list used to extract the assertion values
	 * @param type The {@link AssertionOperationType} extraction criteria
	 * @param value The value used to validate the {@link AssertionOperationType} extraction criteria if needed
	 * @return The extracted list of values or null
	 * @throws ActionException When any exception occurs during the assertion operation value extraction
	 */
	public static final List<Object> extractValues(List<WebElement> elements, AssertionOperationType type, Object value, Map<String, Object> caseResults) throws ActionException {
		if(elements!=null) {
			List<Object> listOfValues = new ArrayList<Object>(elements.size());
			for(WebElement element: elements) {
				listOfValues.add(extractValue(element, type, listOfValues, caseResults));
			}
			return listOfValues;
		}
		return null;
	}

	/**
	 * It runs the Assertion Clause. It allows the Framework to run an assertion according to the {@link AssertionType} the current and the expected value objects
	 * @param type The {@link AssertionType} defining the assertion criteria
	 * @param description The Assertion error description
	 * @param thatMatherType The {@link AssertionThatMatcherType} used only in the {@link AssertionType#THAT} assertion type criteria
	 * @param expected The expected value object
	 * @param actual The current value object
	 */
	public static final void assertValues(AssertionType type, String description, AssertionThatMatcherType thatMatherType, Object expected, Object actual) {
		switch(type) {
			case STARTS_WITH:
				if (description!=null) {
					AssertionUtilities.assertStartsWith(description, expected, actual);
				}
				else {
					AssertionUtilities.assertStartsWith(expected, actual);
				}
			break;
			case STARTS_WITH_IGNORE_CASE:
				if (description!=null) {
					AssertionUtilities.assertStartsIgnoreCaseWith(description, expected, actual);
				}
				else {
					AssertionUtilities.assertStartsIgnoreCaseWith(expected, actual);
				}
			break;
			case ENDS_WITH:
				if (description!=null) {
					AssertionUtilities.assertEndsWith(description, expected, actual);
				}
				else {
					AssertionUtilities.assertEndsWith(expected, actual);
				}
			break;
			case ENDS_WITH_IGNORE_CASE:
				if (description!=null) {
					AssertionUtilities.assertEndsIgnoreCaseWith(description, (Object[])expected, (Object[])actual);
				}
				else {
					AssertionUtilities.assertEndsIgnoreCaseWith((Object[])expected, (Object[])actual);
				}
			break;
			case CONTAINS:
				if (description!=null) {
					AssertionUtilities.assertContains(description, expected, actual);
				}
				else {
					AssertionUtilities.assertContains(expected, actual);
				}
			break;
			case CONTAINS_IGNORE_CASE:
				if (description!=null) {
					AssertionUtilities.assertContainsIgnoreCase(description, expected, actual);
				}
				else {
					AssertionUtilities.assertContainsIgnoreCase(expected, actual);
				}
			break;
			case ARRAY_EQUALS:
				if (description!=null) {
					AssertionUtilities.assertArrayEquals(description, (Object[])expected, (Object[])actual);
				}
				else {
					AssertionUtilities.assertArrayEquals((Object[])expected, (Object[])actual);
				}
				break;
			case EQUALS:
				System.out.println("Equals -> \nexpected : "+expected+"\ncurrent : "+actual);
				if (description!=null) {
					AssertionUtilities.assertEquals(description, expected, actual);
				}
				else {
					AssertionUtilities.assertEquals(expected, actual);
				}
				break;
			case NOT_EQUALS:
				if (description!=null) {
					AssertionUtilities.assertNotEquals(description, expected, actual);
				}
				else {
					AssertionUtilities.assertNotEquals(expected, actual);
				}
				break;
			case FALSE:
				if (description!=null) {
					AssertionUtilities.assertFalse(description, (Boolean)actual);
				}
				else {
					AssertionUtilities.assertFalse((Boolean)actual);
				}
				break;
			case TRUE:
				if (description!=null) {
					AssertionUtilities.assertTrue(description, (Boolean)actual);
				}
				else {
					AssertionUtilities.assertTrue((Boolean)actual);
				}
				break;
			case NULL:
				if (description!=null) {
					AssertionUtilities.assertNull(description, actual);
				}
				else {
					AssertionUtilities.assertNull(actual);
				}
				break;
			case NOT_NULL:
				if (description!=null) {
					AssertionUtilities.assertNotNull(description, actual);
				}
				else {
					AssertionUtilities.assertNotNull(actual);
				}
				break;
			case SAME:
				if (description!=null) {
					AssertionUtilities.assertSame(description, expected, actual);
				}
				else {
					AssertionUtilities.assertSame(expected, actual);
				}
				break;
			case NOT_SAME:
				if (description!=null) {
					AssertionUtilities.assertNotSame(description, expected, actual);
				}
				else {
					AssertionUtilities.assertNotSame(expected, actual);
				}
				break;
			case THAT:
				if (description!=null) {
					AssertionUtilities.assertThat(description, thatMatherType, expected, actual);
				}
				else {
					AssertionUtilities.assertThat(thatMatherType, expected, actual);
				}
				break;
			default:
		}
	}

	/**
	 * Executes The Test Case Operations and Assertions and returns the Map according to the inherit clause
	 * @param executionThread Test case in action
	 * @param driver WebDriver used to run the Test Cases
	 * @param testCase The {@link XMLTestCase} to execute
	 * @param previousReultsMap The previous variables's result map
	 * @return The variables's result map image after the Test Case execution
	 * @throws ActionException When any exception occurs during the {@link TestCase} execution.
	 */
	public static synchronized Map<String, Object> executeXMLCase(TestCase executionThread, WebDriver driver, XMLTestCase testCase, Map<String, Object> previousReultsMap) throws ActionException {
		Map<String, Object> resultsMap = new HashMap<String, Object>(0);
		if (testCase.getTestCaseActions()!=null) {
			for(XMLTestCaseAction action: testCase.getTestCaseActions()) {
				Map<String, Object> temporaryMap = XMLTestCaseUtilities.doAction(driver, executionThread, action);
				if (temporaryMap.size()>0) {
					resultsMap.putAll(temporaryMap);
				}
			}
		}
		if (testCase.getInheritEnvironment())
			previousReultsMap.putAll(resultsMap);
		if (testCase.getTestCaseAssertions()!=null) {
			for(XMLTestAssertion assertion: testCase.getTestCaseAssertions()) {
				long timeout = assertion.getAssertionTimeoutInSeconds();
				if (timeout>0 && driver!=null) {
					SeleniumUtilities.waitForLoad(driver, timeout);
				}
				XMLTestCaseUtilities.doAssertion(driver, assertion, testCase.getInheritEnvironment()? previousReultsMap : resultsMap);
			}
		}
		if (testCase.getTestCaseDOMAssertions()!=null) {
			for(XMLTestDOMAssertion assertion: testCase.getTestCaseDOMAssertions()) {
				long timeout = assertion.getAssertionTimeoutInSeconds();
				if (timeout>0 && driver!=null) {
					SeleniumUtilities.waitForLoad(driver, timeout);
				}
				XMLTestCaseUtilities.doAssertion(driver, assertion, testCase.getInheritEnvironment()? previousReultsMap : resultsMap);
			}
		}
		if (testCase.getChildrenCases()!=null) {
			for(XMLTestCase childCase: testCase.getChildrenCases()) {
				resultsMap = executeXMLCase(executionThread, driver, childCase, testCase.getInheritEnvironment()? previousReultsMap : resultsMap);
			}
		}
		if (!testCase.getInheritEnvironment())
			previousReultsMap.putAll(resultsMap);
		return previousReultsMap;
	}
	
	static class CharAndNumber
	{
	    public final char m_symbol;
	    public final long m_number;
	
	    public CharAndNumber( char symbol, long number ) {
	        this.m_symbol = symbol;
	        this.m_number = number;
	    }
	    
	    public String toString()
	    {
	        return Character.toString( m_symbol ) + Long.toString( m_number );
	    }
	    
	}


}
