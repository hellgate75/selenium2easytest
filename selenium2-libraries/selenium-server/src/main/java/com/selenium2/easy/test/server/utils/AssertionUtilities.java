package com.selenium2.easy.test.server.utils;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;

import java.awt.List;
import java.util.ArrayList;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.internal.ArrayComparisonFailure;

import com.selenium2.easy.test.server.xml.AssertionThatMatcherType;

/**
 * Class that provides statically many operative function to assert many test scenarios. It is
 * a wrapper to simplify the use of the org.junit.Assert clauses and dome of the complex coding 
 * for the org.hamcrest.CoreMatchers features.
 * @author Fabrizio Torelli
 *
 */
public class AssertionUtilities {

	/**
	 * Assert the equality between template's class arrays, without any assertion answer message
	 * @param expected The expected template's class array
	 * @param actual The current template's class array
	 * @throws ArrayComparisonFailure When any null or malformed data is passed 
	 * @throws AssertionError When the template's class arrays to assert are not equals
	 */
	public static final <T> void assertArrayEquals(T[] expected, T[] actual) throws ArrayComparisonFailure, AssertionError {
		Assert.assertArrayEquals(expected, actual);
	}

	/**
	 * Assert the equality between template's class arrays, with a specific assertion answer message
	 * @param message Message to report in the AssertionException when it has been thrown
	 * @param expected The expected template's class array
	 * @param actual The current template's class array
	 * @throws ArrayComparisonFailure When any null or malformed data is passed 
	 * @throws AssertionError When the template's class arrays to assert are not equals
	 */
	public static final <T> void assertArrayEquals(String message, T[] expected, T[] actual) throws ArrayComparisonFailure, AssertionError {
		Assert.assertArrayEquals(message, expected, actual);
	}

	/**
	 * Assert the string representing the current element starts with string representing the expected one, with a specific assertion answer message
	 * @param message Message to report in the AssertionException when it has been thrown
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertStartsWith(String message, T expected, T actual) throws AssertionError {
		try {
			if (actual==null || expected==null) {
				throw new AssertionError(message);
			}
			else if (actual!=null && ArrayList.class.isAssignableFrom(actual.getClass())) {
				String strExpected = ArrayList.class.isAssignableFrom(expected.getClass()) ? (String)((List)expected).getItem(0):(String)expected;
				String strActual = (String)((List)actual).getItem(0);
				if (!strActual.startsWith(strExpected)) {
					throw new AssertionError(message);
				}
			}
			else {
				if (!((String)actual).startsWith(((String)expected))) {
					throw new AssertionError(message);
				}
				
			}
		} catch (Throwable e) {
			throw new AssertionError(message, e);
		}
	}

	/**
	 * Assert the string representing the current element starts with string representing the expected one, without any specific assertion answer message
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertStartsWith(T expected, T actual) throws AssertionError {
		try {
			if (actual==null || expected==null) {
				throw new AssertionError();
			}
			else if (actual!=null && ArrayList.class.isAssignableFrom(actual.getClass())) {
				String strExpected = ArrayList.class.isAssignableFrom(expected.getClass()) ? (String)((List)expected).getItem(0):(String)expected;
				String strActual = (String)((List)actual).getItem(0);
				if (!strActual.startsWith(strExpected)) {
					throw new AssertionError();
				}
			}
			else {
				if (!((String)actual).startsWith(((String)expected))) {
					throw new AssertionError();
				}
				
			}
		} catch (Throwable e) {
			throw new AssertionError(e);
		}
	}

	/**
	 * Assert the string representing the current element ends with string representing the expected one, with a specific assertion answer message
	 * @param message Message to report in the AssertionException when it has been thrown
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertEndsWith(String message, T expected, T actual) throws AssertionError {
		try {
			if (actual==null || expected==null) {
				throw new AssertionError(message);
			}
			else if (actual!=null && ArrayList.class.isAssignableFrom(actual.getClass())) {
				String strExpected = ArrayList.class.isAssignableFrom(expected.getClass()) ? (String)((List)expected).getItem(0):(String)expected;
				String strActual = (String)((List)actual).getItem(0);
				if (!strActual.endsWith(strExpected)) {
					throw new AssertionError(message);
				}
			}
			else {
				if (!((String)actual).endsWith(((String)expected))) {
					throw new AssertionError(message);
				}
				
			}
		} catch (Throwable e) {
			throw new AssertionError(message, e);
		}
	}

	/**
	 * Assert the string representing the current element ends with string representing the expected one, without any specific assertion answer message
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertEndsWith(T expected, T actual) throws AssertionError {
		try {
			if (actual==null || expected==null) {
				throw new AssertionError();
			}
			else if (actual!=null && ArrayList.class.isAssignableFrom(actual.getClass())) {
				String strExpected = ArrayList.class.isAssignableFrom(expected.getClass()) ? (String)((List)expected).getItem(0):(String)expected;
				String strActual = (String)((List)actual).getItem(0);
				if (!strActual.endsWith(strExpected)) {
					throw new AssertionError();
				}
			}
			else {
				if (!((String)actual).endsWith(((String)expected))) {
					throw new AssertionError();
				}
				
			}
		} catch (Throwable e) {
			throw new AssertionError(e);
		}
	}

	/**
	 * Assert the string representing the current element starts, ignoring the text case, with string representing the expected one, with a specific assertion answer message
	 * @param message Message to report in the AssertionException when it has been thrown
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertStartsIgnoreCaseWith(String message, T expected, T actual) throws AssertionError {
		try {
			if (actual==null || expected==null) {
				throw new AssertionError(message);
			}
			else if (actual!=null && ArrayList.class.isAssignableFrom(actual.getClass())) {
				String strExpected = ArrayList.class.isAssignableFrom(expected.getClass()) ? (String)((List)expected).getItem(0):(String)expected;
				String strActual = (String)((List)actual).getItem(0);
				if (!strActual.toLowerCase().startsWith(strExpected.toLowerCase())) {
					throw new AssertionError(message);
				}
			}
			else {
				if (!((String)actual).toLowerCase().startsWith(((String)expected).toLowerCase())) {
					throw new AssertionError(message);
				}
				
			}
		} catch (Throwable e) {
			throw new AssertionError(message, e);
		}
	}

	/**
	 * Assert the string representing the current element starts, ignoring the text case, with string representing the expected one, without any specific assertion answer message
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertStartsIgnoreCaseWith(T expected, T actual) throws AssertionError {
		try {
			if (actual==null || expected==null) {
				throw new AssertionError();
			}
			else if (actual!=null && ArrayList.class.isAssignableFrom(actual.getClass())) {
				String strExpected = ArrayList.class.isAssignableFrom(expected.getClass()) ? (String)((List)expected).getItem(0):(String)expected;
				String strActual = (String)((List)actual).getItem(0);
				if (!strActual.toLowerCase().startsWith(strExpected.toLowerCase())) {
					throw new AssertionError();
				}
			}
			else {
				if (!((String)actual).toLowerCase().startsWith(((String)expected).toLowerCase())) {
					throw new AssertionError();
				}
				
			}
		} catch (Throwable e) {
			throw new AssertionError(e);
		}
	}

	/**
	 * Assert the string representing the current element ends, ignoring the text case, with string representing the expected one, with a specific assertion answer message
	 * @param message Message to report in the AssertionException when it has been thrown
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertEndsIgnoreCaseWith(String message, T expected, T actual) throws AssertionError {
		try {
			if (actual==null || expected==null) {
				throw new AssertionError(message);
			}
			else if (actual!=null && ArrayList.class.isAssignableFrom(actual.getClass())) {
				String strExpected = ArrayList.class.isAssignableFrom(expected.getClass()) ? (String)((List)expected).getItem(0):(String)expected;
				String strActual = (String)((List)actual).getItem(0);
				if (!strActual.toLowerCase().endsWith(strExpected.toLowerCase())) {
					throw new AssertionError(message);
				}
			}
			else {
				if (!((String)actual).toLowerCase().endsWith(((String)expected).toLowerCase())) {
					throw new AssertionError(message);
				}
				
			}
		} catch (Throwable e) {
			throw new AssertionError(message, e);
		}
	}

	/**
	 * Assert the string representing the current element ends, ignoring the text case, with string representing the expected one, without any specific assertion answer message
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertEndsIgnoreCaseWith(T expected, T actual) throws AssertionError {
		try {
			if (actual==null || expected==null) {
				throw new AssertionError();
			}
			else if (actual!=null && ArrayList.class.isAssignableFrom(actual.getClass())) {
				String strExpected = ArrayList.class.isAssignableFrom(expected.getClass()) ? (String)((List)expected).getItem(0):(String)expected;
				String strActual = (String)((List)actual).getItem(0);
				if (!strActual.toLowerCase().endsWith(strExpected.toLowerCase())) {
					throw new AssertionError();
				}
			}
			else {
				if (!((String)actual).toLowerCase().endsWith(((String)expected).toLowerCase())) {
					throw new AssertionError();
				}
				
			}
		} catch (Throwable e) {
			throw new AssertionError(e);
		}
	}

	/**
	 * Assert the string representing the current element equals the string representing the expected one, without any specific assertion answer message
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertEquals(T expected, T actual) throws ArrayComparisonFailure, AssertionError {
		Assert.assertEquals(expected, actual);
	}

	
	/**
	 * Assert the string representing the current element equals the string representing the expected one, with a specific assertion answer message
	 * @param message Message to report in the AssertionException when it has been thrown
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertEquals(String message, T expected, T actual) throws AssertionError {
		Assert.assertEquals(message, expected, actual);
	}

	/**
	 * Assert the string representing the current element equals the not string representing the expected one, without any specific assertion answer message
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertNotEquals(T expected, T actual) throws AssertionError {
		Assert.assertNotEquals(expected, actual);
	}

	/**
	 * Assert the string representing the current element not equals the string representing the expected one, with a specific assertion answer message
	 * @param message Message to report in the AssertionException when it has been thrown
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertNotEquals(String message, T expected, T actual) throws AssertionError {
		Assert.assertNotEquals(message, expected, actual);
	}

	/**
	 * Assert the current element is null, with a specific assertion answer message
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertNull(T actual) throws AssertionError {
		Assert.assertNull(actual);
	}

	/**
	 * Assert the current element is null, with a specific assertion answer message
	 * @param message Message to report in the AssertionException when it has been thrown
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertNull(String message, T actual) throws AssertionError {
		Assert.assertNull(message, actual);
	}

	/**
	 * Assert the current element is not null, with a specific assertion answer message
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertNotNull(T actual) throws AssertionError {
		Assert.assertNotNull(actual);
	}

	/**
	 * Assert the current element is not null, with a specific assertion answer message
	 * @param message Message to report in the AssertionException when it has been thrown
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertNotNull(String message, T actual) throws AssertionError {
		Assert.assertNotNull(message, actual);
	}

	/**
	 * Assert the current element is the same instance of the expected one
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertSame(T expected, T actual) throws AssertionError {
		Assert.assertSame(expected, actual);
	}

	/**
	 * Assert the current element is the same instance of the expected one, with a specific assertion answer message
	 * @param message Message to report in the AssertionException when it has been thrown
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertSame(String message, T expected, T actual) throws AssertionError {
		Assert.assertSame(message, expected, actual);
	}

	/**
	 * Assert the current element is not the same instance of the expected one
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertNotSame(T expected, T actual) throws AssertionError {
		Assert.assertNotSame(expected, actual);
	}

	/**
	 * Assert the current element is not the same instance of the expected one, with a specific assertion answer message
	 * @param message Message to report in the AssertionException when it has been thrown
	 * @param expected The expected template's class element
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertNotSame(String message, T expected, T actual) throws AssertionError {
		Assert.assertNotSame(message, expected, actual);
	}

	/**
	 * Assert the current element represents a false boolean expression
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final void assertFalse(boolean actual) throws AssertionError {
		Assert.assertFalse(actual);
	}

	/**
	 * Assert the current element represents a false boolean expression, with a specific assertion answer message
	 * @param message Message to report in the AssertionException when it has been thrown
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final void assertFalse(String message, boolean actual) throws AssertionError {
		Assert.assertFalse(message, actual);
	}
	
	/**
	 * Assert the current element represents a true boolean expression
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final void assertTrue(boolean actual) throws AssertionError {
		Assert.assertTrue(actual);
	}

	/**
	 * Assert the current element represents a true boolean expression, with a specific assertion answer message
	 * @param message Message to report in the AssertionException when it has been thrown
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final void assertTrue(String message, boolean actual) throws AssertionError {
		Assert.assertTrue(message, actual);
	}
	
	@SuppressWarnings("unchecked")
	private static <T>  Matcher<? super T> getThatMatcher(String message, AssertionThatMatcherType type, T actual) {
		switch (type) {
		case ANY:
			return any((Class<? super T>)actual.getClass());
		case ANYTHING:
			if (actual==null)
				return anything();
			else
				return anything(message);
		case EQUALS_TO:
			return equalTo(actual);
		case INSTANCE_OF:
			return instanceOf(actual.getClass());
		case IS:
			return is(actual);
		case NOT:
			return not(actual);
		case NULL:
			return nullValue();
		case NOT_NULL:
			return notNullValue();
		default:
			break;
		}
		return null;
	}
	
	/**
	 * Assert the current element and the expected one match the 'that' assertion clause
	 * @param expected The expected template's class element
	 * @param type That clause comparison type enumeration (see {@link AssertionThatMatcherType})
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertThat(AssertionThatMatcherType type, T expected, T actual) throws AssertionError {
		Matcher<? super T> matcher = getThatMatcher(null, type, actual);
		if (matcher!=null) {
			Assert.assertThat(actual, matcher);
		}
	}

	/**
	 * Assert the current element and the expected one match the 'that' assertion clause, with a specific assertion answer message
	 * @param message Message to report in the AssertionException when it has been thrown
	 * @param expected The expected template's class element
	 * @param type That clause comparison type enumeration (see {@link AssertionThatMatcherType})
	 * @param actual The current template's class element
	 * @throws AssertionError When the template's objects to assert do not match the assertion
	 */
	public static final <T> void assertThat(String message, AssertionThatMatcherType type, T expected, T actual) throws AssertionError {
		Matcher<? super T> matcher =  getThatMatcher(message, type, actual);
		if (matcher!=null) {
			Assert.assertThat(message, actual, matcher);
		}
	}
	
}
