package com.selenium2.easy.test.server.automated.multithread;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

/**
 * Simulated user experience report wrapper. It contains the whole set of the TestCaseResult related
 * to a single user experience. It is possible actually run more users together and collect the performance
 * tests.
 * @author Fabrizio Torelli
 * @see TestCaseResult
 */
public class UserCaseResult implements Comparable<UserCaseResult> {

	private String processName = "";
	private int processId = 0;
	private String driverName = "";
	private String errorMessage = "";
	private boolean success = true;
	private long elapsedTime = 0L;
	private int caseExecuted = 0;
	private int caseFailed = 0;
	private List<TestCaseResult> testCases = new ArrayList<TestCaseResult>(0);

	/**
	 * Public Report wrapper constructor.
	 * @param processId Id of the user process
	 * @param processName Name of the user process
	 */
	public UserCaseResult(int processId, String processName) {
		super();
		this.processName = processName;
		this.processId = processId;
	}
	/**
	 * Retrieve the User process name
	 * @return User process name
	 */
	public String getProcessName() {
		return processName;
	}
	/**
	 * Retrieve the whole set of test cases related to the user process
	 * @return the list of test cases executed by the user process
	 */
	public List<TestCaseResult> getTestCases() {
		return testCases;
	}
	/**
	 * Retrieve the total elapsed time to run the tests in the user process
	 * @return Total elapsed time in milliseconds
	 */
	public long getElapsedTime() {
		return elapsedTime;
	}
	/**
	 * Retrieve the number of tests executed in the user process
	 * @return Number of test cases
	 */
	public int getCaseExecuted() {
		return caseExecuted;
	}
	/**
	 * Retrieve the number of failed tests executed in the user process
	 * @return Failed test cases
	 */
	public int getCaseFailed() {
		return caseFailed;
	}
	/**
	 * Retrieve the error message of executed tests in the user process
	 * @return Error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * Retrieve the WebDriver ({@link WebDriver}) name executed in the user process
	 * @return WebDriver name
	 */
	public String getDriverName() {
		return driverName;
	}
	/**
	 * Retrieve the user process identifier
	 * @return
	 */
	public int getProcessId() {
		return processId;
	}
	/**
	 * (non-Javadoc)
	 * @param driverName WebDriver name
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	/**
	 * (non-Javadoc)
	 * @param errorMessage Error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		this.success = false;
	}
	/**
	 * Retrieve the Success status for all the user process executed test cases
	 * @return User process global success status
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * Add a new test case to the user process's list
	 * @param result
	 */
	public void addTestCase(TestCaseResult result) {
		if (result!=null)
			testCases.add(result);
	}
	/**
	 * (non-Javadoc)
	 * @param elapsedTime total user process test cases execution time in milliseconds
	 */
	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	/**
	 * (non-Javadoc)
	 * @param caseExecuted Number of executed test cases
	 */
	public void setCaseExecuted(int caseExecuted) {
		this.caseExecuted = caseExecuted;
	}
	/**
	 * (non-Javadoc)
	 * @param caseFailed Number of failed test cases
	 */
	public void setCaseFailed(int caseFailed) {
		this.caseFailed = caseFailed;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserCaseResult [processName=" + processName + ", driverName="
				+ driverName + ", errorMessage=" + errorMessage + ", success="
				+ success + ", elapsedTime=" + elapsedTime + ", caseExecuted="
				+ caseExecuted + ", caseFailed=" + caseFailed + ", testCases="
				+ testCases + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(UserCaseResult o) {
		if (o==null)
			return 1;
		return processId- o.getProcessId();
	}

}
