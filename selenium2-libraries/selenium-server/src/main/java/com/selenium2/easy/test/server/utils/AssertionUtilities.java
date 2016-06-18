package com.selenium2.easy.test.server.utils;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.internal.ArrayComparisonFailure;

public class AssertionUtilities {

	public static final <T> void assertArrayEquals(T[] expected, T[] actual) throws ArrayComparisonFailure, AssertionError {
		Assert.assertArrayEquals(expected, actual);
	}

	public static final <T> void assertArrayEquals(String message, T[] expected, T[] actual) throws ArrayComparisonFailure, AssertionError {
		Assert.assertArrayEquals(message, expected, actual);
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
	
	public static final <T> void assertThat(T actual, Matcher<? super T> matcher) throws AssertionError {
		Assert.assertThat(actual, matcher);
	}

	public static final <T> void assertThat(String message, T actual, Matcher<? super T> matcher) throws AssertionError {
		Assert.assertThat(message, actual, matcher);
	}
	
}
