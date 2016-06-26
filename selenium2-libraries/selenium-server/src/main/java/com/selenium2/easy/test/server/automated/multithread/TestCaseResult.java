package com.selenium2.easy.test.server.automated.multithread;

/**
 * Test results Bean containing statistic and messages information for a single test case.
 * @author Fabrizio Torelli
 * @See BaseTestCase
 */
public class TestCaseResult {

	private String caseUID = null;
	private String caseName = null;
	private long securityAccessElapsedTime = 0L;
	private long testCaseElapsedTime = 0L;
	private long testActionElapsedTime = 0L;
	private long renderingElapsedTime = 0L;
	private String message = "";
	private boolean success = false;
	private boolean skipped = false;
	/**
	 * Public full constructor of the Bean
	 * @param caseUID Unique Identifier of the Test Case
	 * @param caseName Name of the Test Case
	 * @param securityAccessElapsedTime Total time to login for the Test Case
	 * @param testCaseElapsedTime Total time to run the Test Case
	 * @param testActionElapsedTime Total time to execute the tests for the Test Case
	 * @param renderingElapsedTime Total time to render the page for the Test Case
	 * @param message Message related to the Test Case execution
	 * @param success Success flag related to the Test Case execution
	 * @param skipped Skip flag related to the Test Case execution
	 */
	public TestCaseResult(String caseUID, String caseName, long securityAccessElapsedTime,
			long testCaseElapsedTime, long testActionElapsedTime,
			long renderingElapsedTime, String message, boolean success,
			boolean skipped) {
		super();
		this.securityAccessElapsedTime = securityAccessElapsedTime;
		this.testCaseElapsedTime = testCaseElapsedTime;
		this.testActionElapsedTime = testActionElapsedTime;
		this.renderingElapsedTime = renderingElapsedTime;
		this.message = message;
		this.success = success;
		this.skipped = skipped;
		this.caseUID=caseUID;
		this.caseName = caseName;
	}
	/**
	 * Public short constructor of the Bean
	 * @param caseUID Unique Identifier of the Test Case
	 * @param caseName Name of the Test Case
	 */
	public TestCaseResult(String caseUID, String caseName) {
		super();
		this.caseUID=caseUID;
		this.caseName = caseName;
	}
	/**
	 * Retrieve the Test Case's Unique Identifier
	 * @return The Test Case's Unique Identifier
	 */
	public String getCaseUID() {
		return caseUID;
	}
	/**
	 * Retrieve the Test Case's Name
	 * @return Test Case's Name
	 */
	public String getCaseName() {
		return caseName;
	}
	/**
	 * Retrieve the Test Case's URL Access elapsed time in milliseconds
	 * @return Test Case's URL Access elapsed time in milliseconds
	 */
	public long getSecurityAccessElapsedTime() {
		return securityAccessElapsedTime;
	}
	/**
	 * Retrieve the Test Case's Total elapsed time in milliseconds
	 * @return Test Case's Total elapsed time in milliseconds
	 */
	public long getTestCaseElapsedTime() {
		return testCaseElapsedTime;
	}
	/**
	 * Retrieve the Test Case's test code execution elapsed time in milliseconds
	 * @return Test Case's test code execution elapsed time in milliseconds
	 */
	public long getTestActionElapsedTime() {
		return testActionElapsedTime;
	}
	/**
	 * Retrieve the Test Case's page rendering elapsed time in milliseconds
	 * @return Test Case's page rendering elapsed time in milliseconds
	 */
	public long getRenderingElapsedTime() {
		return renderingElapsedTime;
	}
	/**
	 * Retrieve the Test Case's execution message
	 * @return Test Case's execution message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * Retrieve the Test Case's success state
	 * @return Test Case's success state
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * Retrieve the Test Case's skipped state
	 * @return Test Case's skipped state
	 */
	public boolean isSkipped() {
		return skipped;
	}
	/**
	 * (non-Javadoc)
	 * @param securityAccessElapsedTime Test Case's URL access elapsed time
	 */
	public void setSecurityAccessElapsedTime(long securityAccessElapsedTime) {
		if (securityAccessElapsedTime<0)
			securityAccessElapsedTime = 0;
		this.securityAccessElapsedTime = securityAccessElapsedTime;
	}
	/**
	 * (non-Javadoc)
	 * @param testCaseElapsedTime Test Case's total elapsed time
	 */
	public void setTestCaseElapsedTime(long testCaseElapsedTime) {
		if (testCaseElapsedTime<0)
			testCaseElapsedTime = 0;
		this.testCaseElapsedTime = testCaseElapsedTime;
	}
	/**
	 * (non-Javadoc)
	 * @param testActionElapsedTime Test Case's test code execution elapsed time
	 */
	public void setTestActionElapsedTime(long testActionElapsedTime) {
		if (testActionElapsedTime<0)
			testActionElapsedTime = 0;
		this.testActionElapsedTime = testActionElapsedTime;
	}
	/**
	 * (non-Javadoc)
	 * @param renderingElapsedTime Test Case's page rendering elapsed time
	 */
	public void setRenderingElapsedTime(long renderingElapsedTime) {
		if (renderingElapsedTime<0)
			renderingElapsedTime = 0;
		this.renderingElapsedTime = renderingElapsedTime;
	}
	/**
	 * (non-Javadoc)
	 * @param message Test Case's execution message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * (non-Javadoc)
	 * @param success Test Case's success status
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * (non-Javadoc)
	 * @param skipped Test Case's skipped status
	 */
	public void setSkipped(boolean skipped) {
		this.skipped = skipped;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TestCaseResult [caseUID=" + caseUID + ", caseName=" + caseName
				+ ", securityAccessElapsedTime=" + securityAccessElapsedTime
				+ ", testCaseElapsedTime=" + testCaseElapsedTime
				+ ", testActionElapsedTime=" + testActionElapsedTime
				+ ", renderingElapsedTime=" + renderingElapsedTime
				+ ", message=" + message + ", success=" + success
				+ ", skipped=" + skipped + "]";
	}

}
