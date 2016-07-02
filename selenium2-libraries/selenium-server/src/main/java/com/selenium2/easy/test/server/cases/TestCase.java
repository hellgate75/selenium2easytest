package com.selenium2.easy.test.server.cases;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.WebDriver;

public abstract class TestCase {
	private String uid = null;
	/**
	 * Test Case Result variables Map
	 */
	protected Map<String, Object> caseResults = new HashMap<String, Object>(0);
	
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
	 * Return the WebDriver request flag. When in a TestSuite all the elements are not WebSriver driven,
	 * <br/>In that case the WebDriver instance desn't happen and the Selenium2 features are disabled. Probably we have a
	 * different kind of test suite (such as Service HTTP request driven or simply a non-interactive UI Test Suite)
	 * @return The WebDriver request flag
	 */
	public boolean isWebDriverDriven() {
		return true;
	}

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
		try {
			String elements = "";
			for(Field field: getClass().getDeclaredFields()) {
				if(Modifier.isPrivate(field.getModifiers())&&!Modifier.isStatic(field.getModifiers())) {
					try {
						elements += (elements.length()>0 ? "," : "") + field.getName();
						elements += "=" + field.get(this);
					} catch (IllegalArgumentException e) {
					} catch (IllegalAccessException e) {
					} catch (Throwable e) {
					}
				}
			}
			if (elements.length()>0) {
				return this.getClass().getSimpleName() + " [" + elements + "]";
			}
		} catch (Throwable e) {
		}
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

	/**
	 * Retrieves the case results variables Map
	 * @return The results variable map
	 */
	public Map<String, Object> getCaseResults() {
		return caseResults;
	}

	/**
	 * Sets the case results variables Map
	 * @param caseResults The results variable map
	 */
	public void setCaseResults(Map<String, Object> caseResults) {
		this.caseResults = caseResults;
	}

	/**
	 * Add a variable value to the case results variables Map
	 * @param variableName The variable name
	 * @param value The variable value
	 */
	public void addCaseResult(String variableName, Object value) {
		this.caseResults.put(variableName, value);
	}

	/**
	 * Removes a variable value from the case results variables Map
	 * @param variableName The variable name
	 */
	public void removeCaseResult(String variableName) {
		this.caseResults.remove(variableName);
	}

	/**
	 * Retrieves a variable value from the case results variables Map
	 * @param variableName The variable name
	 * @return The variable value or null
	 */
	public Object getCaseResult(String variableName) {
		return this.caseResults.get(variableName);
	}

	/**
	 * Checks the existence of a variable into the case results variables Map
	 * @param variableName The variable name
	 * @return The existence status of the variable
	 */
	public boolean hasCaseResult(String variableName) {
		return this.caseResults.containsKey(variableName);
	}

	
}
