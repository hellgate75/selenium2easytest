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

public class AssertionUtilities {

	public static final <T> void assertArrayEquals(T[] expected, T[] actual) throws ArrayComparisonFailure, AssertionError {
		Assert.assertArrayEquals(expected, actual);
	}

	public static final <T> void assertArrayEquals(String message, T[] expected, T[] actual) throws ArrayComparisonFailure, AssertionError {
		Assert.assertArrayEquals(message, expected, actual);
	}

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

	public static final <T> void assertEquals(T expected, T actual) throws ArrayComparisonFailure, AssertionError {
		Assert.assertEquals(expected, actual);
	}

	
	public static final <T> void assertEquals(String message, T expected, T actual) throws AssertionError {
		Assert.assertEquals(message, expected, actual);
	}

	public static final <T> void assertNotEquals(T expected, T actual) throws AssertionError {
		Assert.assertNotEquals(expected, actual);
	}

	public static final <T> void assertNotEquals(String message, T expected, T actual) throws AssertionError {
		Assert.assertNotEquals(message, expected, actual);
	}

	public static final <T> void assertNull(T actual) throws AssertionError {
		Assert.assertNull(actual);
	}

	public static final <T> void assertNull(String message, T actual) throws AssertionError {
		Assert.assertNull(message, actual);
	}

	public static final <T> void assertNotNull(T actual) throws AssertionError {
		Assert.assertNotNull(actual);
	}

	public static final <T> void assertNotNull(String message, T actual) throws AssertionError {
		Assert.assertNotNull(message, actual);
	}

	public static final <T> void assertSame(T expected, T actual) throws AssertionError {
		Assert.assertSame(expected, actual);
	}

	public static final <T> void assertSame(String message, T expected, T actual) throws AssertionError {
		Assert.assertSame(message, expected, actual);
	}

	public static final <T> void assertNotSame(T expected, T actual) throws AssertionError {
		Assert.assertNotSame(expected, actual);
	}

	public static final <T> void assertNotSame(String message, T expected, T actual) throws AssertionError {
		Assert.assertNotSame(message, expected, actual);
	}

	public static final void assertFalse(boolean actual) throws AssertionError {
		Assert.assertFalse(actual);
	}

	public static final void assertFalse(String message, boolean actual) throws AssertionError {
		Assert.assertFalse(message, actual);
	}
	
	public static final void assertTrue(boolean actual) throws AssertionError {
		Assert.assertTrue(actual);
	}

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
	
	public static final <T> void assertThat(AssertionThatMatcherType type, T expected, T actual) throws AssertionError {
		Matcher<? super T> matcher = getThatMatcher(null, type, actual);
		if (matcher!=null) {
			Assert.assertThat(actual, matcher);
		}
	}

	public static final <T> void assertThat(String message, AssertionThatMatcherType type, T expected, T actual) throws AssertionError {
		Matcher<? super T> matcher =  getThatMatcher(message, type, actual);
		if (matcher!=null) {
			Assert.assertThat(message, actual, matcher);
		}
	}
	
}
