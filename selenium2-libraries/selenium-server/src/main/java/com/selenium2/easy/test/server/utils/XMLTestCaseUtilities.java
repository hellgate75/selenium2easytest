package com.selenium2.easy.test.server.utils;

import java.io.File;
import java.sql.Types;
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

import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.xml.OperationType;
import com.selenium2.easy.test.server.xml.XMLTestAssertion;
import com.selenium2.easy.test.server.xml.XMLTestCaseAction;
import com.selenium2.easy.test.server.xml.XMLTestOperation;
import com.selenium2.easy.test.server.xml.XMLWebElement;

public class XMLTestCaseUtilities {
	
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
	    
	    if (str.equals("TRUE") || str.equals("FALSE")) {
	    	return str.equals("TRUE");
	    }
	    return str;
	}

	@SuppressWarnings("unchecked")
	public static final Map<String, Object> doAction(WebDriver driver, XMLTestCaseAction action) throws ActionException {
		Map<String, Object> results = new HashMap<String, Object>(0);
		/*
		 * TODO Implement the XML Test Case Action feature
		 */
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

	public static final void doAssertion(WebDriver driver, XMLTestAssertion assertion, Map<String, Object> caseResults) {
		/*
		 * TODO Implement the XML Test Assertion feature
		 */
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
