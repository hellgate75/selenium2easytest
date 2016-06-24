package com.selenium2.easy.test.server.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.xml.AssertionOperationType;
import com.selenium2.easy.test.server.xml.AssertionThatMatcherType;
import com.selenium2.easy.test.server.xml.AssertionType;
import com.selenium2.easy.test.server.xml.OperationType;
import com.selenium2.easy.test.server.xml.XMLTestAssertion;
import com.selenium2.easy.test.server.xml.XMLTestCaseAction;
import com.selenium2.easy.test.server.xml.XMLTestDOMAssertion;
import com.selenium2.easy.test.server.xml.XMLTestOperation;
import com.selenium2.easy.test.server.xml.XMLWebElement;

public class XMLTestCaseUtilities {
	private static Logger logger = LoggerFactory.getLogger("com.selenium2.easy.test.server");
	
	private static List<WebElement> getElementByXML(WebDriver driver, XMLWebElement element) {
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
	
	private static List<WebElement> getElementByXML(List<WebElement> parents, XMLWebElement element) {
		List<WebElement> returnList = new ArrayList<WebElement>(0);
		if (element!=null && parents!=null) {
			for (WebElement parent: parents) {
				returnList.addAll(getElementByXML(parent, element));
			}
		}
		return returnList;
	}

	private static List<WebElement> getElementByXML(WebElement parent, XMLWebElement element) {
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

	
	private static Object pack( final String str )
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
	            return new CharAndNumber( firstChar, Long.valueOf( str.substring( 1 ) ) );
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
	    
	    if (str.equals("TRUE") || str.equals("FALSE")) {
	    	return str.equals("TRUE");
	    }
	    return str;
	}

	@SuppressWarnings("unchecked")
	public static final Map<String, Object> doAction(WebDriver driver, XMLTestCaseAction action) throws ActionException {
		Map<String, Object> results = new HashMap<String, Object>(0);
		if (action.isChangeURL()) {
			driver.get(action.getConnectionUrl().getFormattedURL());
		}
		/*
		 * Before we run the operations, collect the results and that we run the assertions.
		 */
		for(XMLTestOperation operation: action.getTestOperations()) {
			OperationType type = operation.getOperationType();
			List<WebElement> source = getElementByXML(driver, operation.getSourceElement());
			List<WebElement> target = operation.getTargetInSource() ? getElementByXML(source, operation.getSourceElement()) : getElementByXML(driver, operation.getTargetElement());
			List<WebElement> paramList = null;
			WebElement paramElement = null;
			Object paramValue = null;
			List<? extends Object> paramValues = null;
			if (operation.getUseResult()!=null && operation.getUseResult().trim().length()>0) {
				Object aValue = results.get(operation.getUseResult().trim());
				if (aValue!=null) {
					if (WebElement.class.isAssignableFrom(aValue.getClass())) {
						paramElement = (WebElement)aValue;
					}
					else if (List.class.isAssignableFrom(aValue.getClass())) {
						try {
							paramList = (List<WebElement>) aValue;
						} catch (Exception e) {
							paramValues = (List<? extends Object>)aValue;
						}
					}
					else {
						paramValue = aValue;
					}
				}
			}
			List<Object> operationValues = new ArrayList<Object>(0);
			if (operation.getValueList()!=null) {
				for(String aValue:operation.getValueList()) {
					if (aValue==null)
						aValue="";
					operationValues.add(pack(aValue));
				}
			}
			
			switch(type) {
				case CLEAR_VALUE:
					for(WebElement element: source) {
						SeleniumUtilities.clearValueToElement(element);
					}
					for(WebElement element: target) {
						SeleniumUtilities.clearValueToElement(element);
					}
					break;
				case FIND_MANY:
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0 && source.size()>0) {
						results.put(operation.getResultAs().trim(), source);
					}
					break;
				case FIND_WITHIN:
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0 && target.size()>0) {
						results.put(operation.getResultAs().trim(), target);
					}
					break;
				case FIND_ONE:
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0 && source.size()>0) {
						results.put(operation.getResultAs().trim(), source.get(0));
					}
					break;
				case CLICK_ACTION:
					for(WebElement element: source) {
						SeleniumUtilities.clickActionElement(element);
					}
					for(WebElement element: target) {
						SeleniumUtilities.clickActionElement(element);
					}
					for(WebElement element: paramList) {
						SeleniumUtilities.clickActionElement(element);
					}
					if(paramElement!=null) {
						SeleniumUtilities.clickActionElement(paramElement);
					}
					break;
				case GET_ATTRIBUTE:
					
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0) {
						List<String> valueList = new ArrayList<String>(0);
						if (paramValue!=null) {
							for(WebElement element: source) {
								String attribute = SeleniumUtilities.getAttributeFromElement(element, (String)paramValue);
								if (attribute!=null) {
									valueList.add(attribute);
								}
							}
						}
						if (paramValues!=null && paramValues.size()>0) {
							for(Object aValue: paramValues) {
								for(WebElement element: source) {
									String attribute = SeleniumUtilities.getAttributeFromElement(element, (String)aValue);
									if (attribute!=null) {
										valueList.add(attribute);
									}
								}
							}
						}
						if (operationValues!=null && operationValues.size()>0) {
							for(Object aValue: operationValues) {
								for(WebElement element: source) {
									String attribute = SeleniumUtilities.getAttributeFromElement(element, (String)aValue);
									if (attribute!=null) {
										valueList.add(attribute);
									}
								}
							}
						}
						
						if (valueList.size()>0) {
							results.put(operation.getResultAs().trim(), valueList);
						}
						
					}
					break;
				case GET_CSS:
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0) {
						List<String> valueList = new ArrayList<String>(0);
						if (paramValue!=null) {
							for(WebElement element: source) {
								String css = SeleniumUtilities.getCssValueFromElement(element, (String)paramValue);
								if (css!=null) {
									valueList.add(css);
								}
							}
						}
						if (paramValues!=null && paramValues.size()>0) {
							for(Object aValue: paramValues) {
								for(WebElement element: source) {
									String css = SeleniumUtilities.getCssValueFromElement(element, (String)aValue);
									if (css!=null) {
										valueList.add(css);
									}
								}
							}
						}
						if (operationValues!=null && operationValues.size()>0) {
							for(Object aValue: operationValues) {
								for(WebElement element: source) {
									String css = SeleniumUtilities.getCssValueFromElement(element, (String)aValue);
									if (css!=null) {
										valueList.add(css);
									}
								}
							}
						}
						
						if (valueList.size()>0) {
							results.put(operation.getResultAs().trim(), valueList);
						}
						
					}
					break;
				case GET_LOCATION:
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0) {
						List<Object> valueList = new ArrayList<Object>(0);
							for(WebElement element: source) {
								Point location = SeleniumUtilities.getLocationFromElement(element);
								if (location!=null) {
									valueList.add(location);
								}
							}
						if (valueList.size()>0) {
							results.put(operation.getResultAs().trim(), valueList);
						}
						
					}
					break;
				case GET_PAGE_SOURCE:
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0) {
						results.put(operation.getResultAs().trim(), SeleniumUtilities.getPageSource(driver));
					}
					break;
				case GET_PAGE_TITLE:
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0) {
						results.put(operation.getResultAs().trim(), SeleniumUtilities.getPageTitle(driver));
					}
					break;
				case GET_RECT:
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0) {
						List<Object> valueList = new ArrayList<Object>(0);
							for(WebElement element: source) {
								Rectangle rect = SeleniumUtilities.getRectFromElement(element);
								if (rect!=null) {
									valueList.add(rect);
								}
							}
						if (valueList.size()>0) {
							results.put(operation.getResultAs().trim(), valueList);
						}
					}
					break;
				case GET_SIZE:
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0) {
						List<Object> valueList = new ArrayList<Object>(0);
							for(WebElement element: source) {
								Dimension size = SeleniumUtilities.getSizeFromElement(element);
								if (size!=null) {
									valueList.add(size);
								}
							}
						if (valueList.size()>0) {
							results.put(operation.getResultAs().trim(), valueList);
						}
					}
					break;
				case GET_TAG:
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0) {
						List<String> valueList = new ArrayList<String>(0);
						for(WebElement element: source) {
							String tag = SeleniumUtilities.getTagNameFromElement(element);
							if (tag!=null) {
								valueList.add(tag);
							}
						}
						if (valueList.size()>0) {
							results.put(operation.getResultAs().trim(), valueList);
						}
						
					}
					break;
				case IS_DISPLAYED:
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0) {
						List<Boolean> valueList = new ArrayList<Boolean>(0);
						for(WebElement element: source) {
								valueList.add(SeleniumUtilities.isDisplayedTheElement(element));
						}
						if (valueList.size()>0) {
							results.put(operation.getResultAs().trim(), valueList);
						}
						
					}
					break;
				case IS_ENABLED:
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0) {
						List<Boolean> valueList = new ArrayList<Boolean>(0);
						for(WebElement element: source) {
								valueList.add(SeleniumUtilities.isEnabledTheElement(element));
						}
						if (valueList.size()>0) {
							results.put(operation.getResultAs().trim(), valueList);
						}
						
					}
					break;
				case IS_SELECTED:
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0) {
						List<Boolean> valueList = new ArrayList<Boolean>(0);
						for(WebElement element: source) {
								valueList.add(SeleniumUtilities.isSelectedTheElement(element));
						}
						if (valueList.size()>0) {
							results.put(operation.getResultAs().trim(), valueList);
						}
						
					}
					break;
				case SET_VALUE:
					if (operationValues.size()>0) {
						for(WebElement element: source) {
							SeleniumUtilities.setValueToElement(element, (String)operationValues.get(operationValues.size()-1));
						}
						for(WebElement element: target) {
							SeleniumUtilities.setValueToElement(element, (String)operationValues.get(operationValues.size()-1));
						}
						for(WebElement element: paramList) {
							SeleniumUtilities.setValueToElement(element, (String)operationValues.get(operationValues.size()-1));
						}
						if(paramElement!=null) {
							SeleniumUtilities.setValueToElement(paramElement, (String)operationValues.get(operationValues.size()-1));
						}
					}
					break;
				case SUBMIT_ACTION:
					for(WebElement element: source) {
						SeleniumUtilities.submitActionElement(element);
					}
					for(WebElement element: target) {
						SeleniumUtilities.submitActionElement(element);
					}
					for(WebElement element: paramList) {
						SeleniumUtilities.submitActionElement(element);
					}
					if(paramElement!=null) {
						SeleniumUtilities.submitActionElement(paramElement);
					}
					break;
				case TAKE_SCREENSHOT_FROM:
					if (operationValues.size()>0) {
						for(WebElement element: source) {
							SeleniumUtilities.takeAScreenshotFromTheElement(element, OutputType.FILE).renameTo(new File(operationValues.get(0).toString()));
						}
						for(WebElement element: target) {
							SeleniumUtilities.takeAScreenshotFromTheElement(element, OutputType.FILE).renameTo(new File(operationValues.get(0).toString()));
						}
						for(WebElement element: paramList) {
							SeleniumUtilities.takeAScreenshotFromTheElement(element, OutputType.FILE).renameTo(new File(operationValues.get(0).toString()));
						}
						if(paramElement!=null) {
							SeleniumUtilities.takeAScreenshotFromTheElement(paramElement, OutputType.FILE).renameTo(new File(operationValues.get(0).toString()));
						}
					}
					break;
				default:
			}
		}
		/* 
		 * At the end we run the operation scoped assertions
		 * enclosed to the locally scoped results related to the specific action
		 * response of the execution of the operations.
		 * */
		for(XMLTestAssertion assertion: action.getTestAssertions()) {
			doAssertion(driver, assertion, results);
		}
		return results;
	}

	private static final String loadTextFile(String filePath) {
		String fileContenct = "";
		BufferedReader bufferedReader=null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)) ));
			while(bufferedReader.ready()) {
				fileContenct += bufferedReader.readLine();
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
		return fileContenct;
	}
	
	private static final Object extractValue(WebElement element, AssertionOperationType type, Object value) throws ActionException {
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
			default:
		}
		return null;
	}

	private static final List<Object> extractValues(List<WebElement> elements, AssertionOperationType type, Object value) throws ActionException {
		if(elements!=null) {
			List<Object> listOfValues = new ArrayList<Object>(elements.size());
			for(WebElement element: elements) {
				listOfValues.add(extractValue(element, type, listOfValues));
			}
			return listOfValues;
		}
		return null;
	}

	private static final void assertValues(AssertionType type, String description, AssertionThatMatcherType thatMatherType, Object expected, Object actual) {
		switch(type) {
			case ARRAY_EQUALS:
				if (description!=null) {
					AssertionUtilities.assertArrayEquals(description, (Object[])expected, (Object[])actual);
				}
				else {
					AssertionUtilities.assertArrayEquals((Object[])expected, (Object[])actual);
				}
				break;
			case EQUALS:
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
	
	@SuppressWarnings("unchecked")
	public static final void doAssertion(WebDriver driver, XMLTestAssertion assertion, Map<String, Object> caseResults) {
		/*
		 * TODO Implement the XML Test Assertion feature
		 */
		List<WebElement> paramList = null;
		WebElement paramElement = null;
		Object paramValue = null;
		List<? extends Object> paramValues = null;
		if (assertion.getUseResult()!=null && assertion.getUseResult().trim().length()>0) {
			Object aValue = caseResults.get(assertion.getUseResult().trim());
			if (aValue!=null) {
				if (WebElement.class.isAssignableFrom(aValue.getClass())) {
					paramElement = (WebElement)aValue;
				}
				else if (List.class.isAssignableFrom(aValue.getClass())) {
					try {
						paramList = (List<WebElement>) aValue;
					} catch (Exception e) {
						paramValues = (List<? extends Object>)aValue;
					}
				}
				else {
					paramValue = aValue;
				}
			}
		}
		List<WebElement> paramMatcherList = null;
		WebElement paramMatcherElement = null;
		Object paramMatcherValue = null;
		List<? extends Object> paramMatcherValues = null;
		if (assertion.getUseMatcherResult()!=null && assertion.getUseMatcherResult().trim().length()>0) {
			Object aValue = caseResults.get(assertion.getUseMatcherResult().trim());
			if (aValue!=null) {
				if (WebElement.class.isAssignableFrom(aValue.getClass())) {
					paramMatcherElement = (WebElement)aValue;
				}
				else if (List.class.isAssignableFrom(aValue.getClass())) {
					try {
						paramMatcherList = (List<WebElement>) aValue;
					} catch (Exception e) {
						paramMatcherValues = (List<? extends Object>)aValue;
					}
				}
				else {
					paramMatcherValue = aValue;
				}
			}
		}
		Object assertionValue=assertion.getUseValue() ? pack(assertion.getValue()) : null;
		String textFile = assertion.getUseTextFile() ? loadTextFile(assertion.getTextFile()) : null;
		List<Object> assertionValues = null;
		if (assertion.getUseValue() && assertion.getValues()!=null) {
			assertionValues = new ArrayList<Object>(0);
			for(String value: assertion.getValues()) {
				assertionValues.add(pack(value));
			}
		}
		Object expected = null;
		if (assertion.getOperationType()!=null) {
			try {
				switch (assertion.getOperationType()) {
					case GET_PAGE_SOURCE:
						expected = SeleniumUtilities.getPageSource(driver);
						break;
					case GET_PAGE_TITLE:
						expected = SeleniumUtilities.getPageTitle(driver);
						break;
					default:
						if (assertionValues!=null) {
							expected = assertionValues;
						}  else if (textFile != null) {
							expected = textFile;
						} else {
							expected = assertionValue;
						}
						break;
				}
			} catch (ActionException e) {
				logger.error("Unable aquire assertion expected : " + assertion.getOperationType() , e);
			}
		}
		Object current = null;
		try {
			if (paramList != null) {
				current = extractValues(paramList,
						assertion.getOperationType(), assertionValue);
			} else if (paramMatcherList != null) {
				current = extractValues(paramMatcherList,
						assertion.getOperationType(), assertionValue);
			} else if (paramElement != null) {
				current = extractValue(paramElement,
						assertion.getOperationType(), assertionValue);
			} else if (paramMatcherElement != null) {
				current = extractValue(paramMatcherElement,
						assertion.getOperationType(), assertionValue);
			} else if (paramValues != null) {
				current = paramValues;
			} else if (paramMatcherValues != null) {
				current = paramMatcherValues;
			} else if (paramMatcherValue != null) {
				current = paramMatcherValue;
			} else {
				current = paramValue;
			}
		} catch (Exception e) {
			logger.error("Unable aquire assertion expected : " + assertion.getOperationType() , e);
		}
		assertValues(assertion.getType(),assertion.getAssertionTitle(), assertion.getThatMatcherType(),expected, current);
	}

	public static final void doAssertion(WebDriver driver, XMLTestDOMAssertion assertion, Map<String, Object> caseResults) {
		/*
		 * TODO Implement the XML Test DoM Assertion feature
		 */
		List<WebElement> sourceList = getElementByXML(driver, assertion.getAssertionElement());
		List<WebElement> targetList = getElementByXML(driver, assertion.getMatcherElement());
		String sourceValue = assertion.getAttributeSource();
		String matcherValue = assertion.getAttributeMatcher();
		if (matcherValue==null) {
			matcherValue = sourceValue;
		}
		List<String> current = new ArrayList<String>(0);
		List<String> expected = new ArrayList<String>(0);
		if (sourceList!=null && sourceValue!=null) {
			for(WebElement element: sourceList) {
				try {
					String value = SeleniumUtilities.getAttributeFromElement(
							element, sourceValue);
					if (value!=null)
						current.add(value);
				} catch (Exception e) {
				}
			}
		}
		if (targetList!=null && matcherValue!=null) {
			for(WebElement element: targetList) {
				try {
					String value = SeleniumUtilities.getAttributeFromElement(
							element, matcherValue);
					if (value!=null)
						expected.add(value);
				} catch (Exception e) {
				}
			}
		}
		for(int i=0;i<current.size();i++) {
			if (i>=expected.size()) {
				break;
			}
			assertValues(assertion.getType(), assertion.getAssertionTitle(), assertion.getThatMatcherType(), expected.get(i), current.get(i));
		}
	}

	private static class CharAndNumber
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
