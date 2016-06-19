package com.selenium2.easy.test.server.cases;

import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.WebDriver;

public abstract class TestCase {
	private String uid = null;
	
	/**
	 * Default Constructor generating a random UUID for the test case
	 */
	public TestCase() {
		super();
		uid = UUID.randomUUID().toString();
	}

	/**
	 * Recovers the Test Case UUID
	 * @return
	 */
	public final String getCaseUID() {
		return uid;
	}
	
	/**
	 * Recovers the Security Information entries Map
	 * @return the security information map
	 */
	public abstract Map<String, String> getSecurityInfo();
	
	/**
	 * Recovers Case Name. It is used by the TestEngine ({@link TestEngine}) to fill the log information and the execution and performance's report
	 * @return the name of the case
	 */
	public abstract String getCaseName();

	/**
	 * Recovers the URL for connecting the wanted site, service or web-page
	 * @return the full connection URL string
	 */
	public abstract String getConnectionURL();

	/**
	 * Recovers the secure access request status for the test
	 * @return secure access request flag
	 */
	public abstract boolean isSecureConnection();

	/**
	 * Handle the secure access request with the URL specified configuration
	 * before to navigate to the required URL.
	 * @param driver
	 * @return
	 */
	public abstract boolean handleSecureConnection(WebDriver driver);

	/**
	 * Return the connection request flag.
	 * @return connection request flag
	 */
	public abstract boolean isConnectionRequired();

	/**
	 * Return the exception transverse flag.
	 * @return connection request flag
	 */
	public abstract boolean isExceptionTransversable();
	
	/**
	 * Executes the test. Please implement this method with the test actions and assertions.
	 * @param driver Configured WebDriver instance
	 * @throws Throwable Any exception that can occur.
	 */
	public abstract void automatedTest(WebDriver driver) throws Throwable;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return "TestCase [Unique Identifier="+uid+", Case Name=" + getCaseName() + ", Case URL="
				+ getConnectionURL() + ", Connect URL=" + isConnectionRequired() + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestCase other = (TestCase) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	
}
