package com.selenium2.easy.test.server.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium2.easy.test.server.cases.TestCase;
import com.selenium2.easy.test.server.cases.unirest.IUniRestElement;
import com.selenium2.easy.test.server.exceptions.ActionException;
import com.selenium2.easy.test.server.xml.OperationType;
import com.selenium2.easy.test.server.xml.XMLTakeSnpshoot;
import com.selenium2.easy.test.server.xml.XMLTestAssertion;
import com.selenium2.easy.test.server.xml.XMLTestCaseAction;
import com.selenium2.easy.test.server.xml.XMLTestDOMAssertion;
import com.selenium2.easy.test.server.xml.XMLTestOperation;

/**
 * Class providing the base features for the XMLTestCase execution such as the operation 
 * and assertions execution methods.
 * @author Fabrizio Torelli
 *
 */
public class XMLTestCaseUtilities {
	private static Logger logger = LoggerFactory.getLogger("com.selenium2.easy.test.server");

	/**
	 * The methods provides an XMLTestCase action's operations execution and the relevant operation's
	 * assertions execution.
	 * @param driver The {@link WebDriver} used to execute the action operations and operation's assertions
	 * @param action The {@link TestCase} to owner of the test action
	 * @param action The {@link XMLTestCaseAction} to have been executed
	 * @return The map of the environment variables discovered or created during in the operations.
	 * @throws ActionException When any exception occurs during the {@link WebDriver} and located {@link WebElement} operation
	 */
	@SuppressWarnings("unchecked")
	public static final Map<String, Object> doAction(WebDriver driver, TestCase theCase, XMLTestCaseAction action) throws ActionException {
		Map<String, Object> results = new HashMap<String, Object>(0);
		if (action.getUseURL()) {
			if (theCase!=null && IUniRestElement.class.isAssignableFrom(theCase.getClass())) {
				if (! ((IUniRestElement)theCase).connectServiceURL() ) {
					throw new ActionException("Unable to connect to the service URL : " + (theCase!=null ? theCase.getConnectionURL() : null));
				}
			}
			else {
				driver.get(action.getConnectionUrl().getFormattedURL());
			}

		}
		/*
		 * Before we run the operations, collect the results and that we run the assertions.
		 */
		if (action.getTestOperations()!=null) {
			for(XMLTestOperation operation: action.getTestOperations()) {
				OperationType type = operation.getOperationType();
				List<WebElement> source = FrameworkUtilities.getElementByXML(driver, operation.getSourceElement());
				List<WebElement> target = operation.getTargetInSource() ? FrameworkUtilities.getElementByXML(source, operation.getSourceElement()) : FrameworkUtilities.getElementByXML(driver, operation.getTargetElement());
				List<WebElement> paramList = new ArrayList<WebElement>(0);
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
						operationValues.add(FrameworkUtilities.pack(aValue,results));
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
				case FIND_ONE_WITHIN:
					if (operation.getResultAs()!=null && operation.getResultAs().trim().length()>0 && target.size()>0) {
						results.put(operation.getResultAs().trim(), target.get(0));
					}
					break;
				case FIND_MANY_WITHIN:
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
				case RETRIEVE_MAPPED_VALUE:
					if (operationValues!=null && operationValues.size()>0 && operation.getResultAs()!=null && operation.getResultAs().trim().length()>0) {
						if (operationValues.size()==1) {
							Object value = operationValues.get(0);
							if (value!=null) {
								results.put(operation.getResultAs(), value);
							}
						}
						else {
							List<Object> values = new ArrayList<Object>(0);
							for(Object value: operationValues) {
								if (value!=null) {
									values.add(value);
								}
							}
							if (values.size()>1) {
								results.put(operation.getResultAs(), values);
							}
							else if (values.size()>0) {
								results.put(operation.getResultAs(), values.get(0));
							}
						}
					}
					break;
				default:
				}
			}
		}
		/* 
		 * At the end we run the operation scoped assertions
		 * enclosed to the locally scoped results related to the specific action
		 * response of the execution of the operations.
		 * */
		if (action.getTestAssertions()!=null) {
			for(XMLTestAssertion assertion: action.getTestAssertions()) {
				long timeout = assertion.getAssertionTimeoutInSeconds();
				if (timeout>0 && driver!=null) {
					SeleniumUtilities.waitForLoad(driver, timeout);
				}
				doAssertion(driver, assertion, results);
			}
		}
		XMLTakeSnpshoot takeSnapShot = action.getCaseSnapshoot();
		if (takeSnapShot!=null) {
			long counter=0L;
			List<WebElement> snapshootElements = FrameworkUtilities.getElementByXML(driver, takeSnapShot.getSnapshootElement());
			for(WebElement element: snapshootElements) {
				SeleniumUtilities.takeAScreenshotFromTheElement(element, OutputType.FILE).renameTo(new File(takeSnapShot.getFolder() + File.separator + takeSnapShot.getFileName() + "_" + counter + "." + takeSnapShot.getFileExtension()));
				counter++;
			}
		}
		return results;
	}

	/**
	 * The methods provides an XMLTestCase assertion execution. This kind of assertion compare WebElement values, attributes or previous
	 * discovered environment variables' content.
	 * @param driver The {@link WebDriver} used to execute the assertion
	 * @param assertion {@link XMLTestAssertion} to have been executed
	 * @param caseResults The map of the environment variables to have been used during the assertion execution
	 * @throws ActionException When occurs any exception during the Assertion executing
	 */
	@SuppressWarnings("unchecked")
	public static final void doAssertion(WebDriver driver, XMLTestAssertion assertion, Map<String, Object> caseResults) throws ActionException {
		/*
		 * TODO Implement the XML Test Assertion feature
		 */
		List<WebElement> paramList =  new ArrayList<WebElement>(0);
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
		List<WebElement> paramMatcherList = new ArrayList<WebElement>(0);
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
		Object assertionValue=assertion.getUseValue() ? FrameworkUtilities.pack(assertion.getValue(), caseResults) : null;
		String textFile = assertion.getUseTextFile() ? FrameworkUtilities.loadTextFile(assertion.getTextFile()) : null;
		List<Object> assertionValues = null;
		if (assertionValue==null && assertion.getUseValue() && assertion.getValues()!=null) {
			assertionValues = new ArrayList<Object>(0);
			for(String value: assertion.getValues()) {
				assertionValues.add(FrameworkUtilities.pack(value, caseResults));
			}
		}
		Object expected = null;
		if (assertion.getOperationType()!=null) {
			try {
				switch (assertion.getOperationType()) {
				case GET_PAGE_SOURCE:
					if (driver!=null) {
						expected = SeleniumUtilities.getPageSource(driver);
					}
					break;
				case GET_PAGE_TITLE:
					if (driver!=null) {
						expected = SeleniumUtilities.getPageTitle(driver);
					}
					break;
				default:
					break;
				}
			} catch (ActionException e) {
				logger.error("Unable acquire assertion expected : " + assertion.getOperationType() , e);
			}
		}
		if (expected==null) {
			if (textFile != null) {
				expected = textFile;
			} else if (paramMatcherList.size()>0) {
				expected = FrameworkUtilities.extractValues(paramMatcherList,
						assertion.getOperationType(), paramMatcherValue, caseResults);
				if (expected==null && paramMatcherValue!=null){
					expected = paramMatcherValue;
				}
			} else if (paramMatcherElement != null) {
				expected = FrameworkUtilities.extractValue(paramMatcherElement,
						assertion.getOperationType(), paramMatcherValue, caseResults);
				if (expected==null && paramMatcherValue!=null){
					expected = paramMatcherValue;
				}
			} else if (paramMatcherValues != null) {
				expected = paramMatcherValues;
			} else if (paramMatcherValue != null) {
				expected = paramMatcherValue;
			}
			
			if  (expected==null) {
				if(textFile!=null && assertion.getUseTextFile()) {
					expected = textFile;
				}
				else if (assertionValue!=null && assertion.getUseValue() && !assertion.getUseTextFile()) {
					expected = assertionValue;
				}
			}
		}
		Object current = null;
		try {
			if (paramList.size()>0) {
				current = FrameworkUtilities.extractValues(paramList,
						assertion.getOperationType(), assertionValue, caseResults);
			} else if (paramElement != null) {
				current = FrameworkUtilities.extractValue(paramElement,
						assertion.getOperationType(), assertionValue, caseResults);
			} else if (paramValues != null) {
				current = paramValues;
			} else if (assertionValue!=null && assertion.getUseValue() && assertion.getUseTextFile()) {
				current = assertionValue;
			} else if (assertionValues!=null && assertionValues.size()>0) {
				current = assertionValues;
			}
			else if(assertionValue!=null) {
				current = assertionValue;
			} else {
				current = paramValue;
			}
		} catch (Exception e) {
			logger.error("Unable aquire assertion expected : " + assertion.getOperationType() , e);
		}
		FrameworkUtilities.assertValues(assertion.getType(), assertion.getAssertionTitle(), assertion.getThatMatcherType(),expected, current);
		XMLTakeSnpshoot takeSnapShot = assertion.getAssertionSnapshoot();
		if (takeSnapShot!=null) {
			long counter=0L;
			List<WebElement> snapshootElements = FrameworkUtilities.getElementByXML(driver, takeSnapShot.getSnapshootElement());
			for(WebElement element: snapshootElements) {
				SeleniumUtilities.takeAScreenshotFromTheElement(element, OutputType.FILE).renameTo(new File(takeSnapShot.getFolder() + File.separator + takeSnapShot.getFileName() + "_" + counter + "." + takeSnapShot.getFileExtension()));
				counter++;
			}
		}
	}

	/**
	 * The methods provides an XMLTestCase DOM assertion execution. This kind of assertion compare two or more WebElement attributes.
	 * @param driver The {@link WebDriver} used to execute the assertion
	 * @param assertion {@link XMLTestDOMAssertion} to have been executed
	 * @param caseResults The map of the environment variables to have been used during the assertion execution
	 * @throws ActionException When occurs any exception during the Assertion executing
	 */
	public static final void doAssertion(WebDriver driver, XMLTestDOMAssertion assertion, Map<String, Object> caseResults) throws ActionException {
		/*
		 * TODO Implement the XML Test DoM Assertion feature
		 */
		List<WebElement> sourceList = FrameworkUtilities.getElementByXML(driver, assertion.getAssertionElement());
		List<WebElement> targetList = FrameworkUtilities.getElementByXML(driver, assertion.getMatcherElement());
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
			FrameworkUtilities.assertValues(assertion.getType(), assertion.getAssertionTitle(), assertion.getThatMatcherType(), expected.get(i), current.get(i));
		}
		XMLTakeSnpshoot takeSnapShot = assertion.getAssertionSnapshoot();
		if (takeSnapShot!=null) {
			long counter=0L;
			List<WebElement> snapshootElements = FrameworkUtilities.getElementByXML(driver, takeSnapShot.getSnapshootElement());
			for(WebElement element: snapshootElements) {
				SeleniumUtilities.takeAScreenshotFromTheElement(element, OutputType.FILE).renameTo(new File(takeSnapShot.getFolder() + File.separator + takeSnapShot.getFileName() + "_" + counter + "." + takeSnapShot.getFileExtension()));
				counter++;
			}
		}
	}
}
