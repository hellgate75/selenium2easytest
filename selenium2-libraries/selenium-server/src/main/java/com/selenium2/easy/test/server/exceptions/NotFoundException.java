package com.selenium2.easy.test.server.exceptions;

/**
 * @author Fabrizio Torelli
 * Exception that occurs when any framework external resource or any needed or mandatory WebElement 
 * is not found and communicate the framework the unavailability of a resource necessary to continue
 * the current action. When the impact is on the framework operative state itself it is used the 
 * Framework exception that is caught in an higher level.
 */
public class NotFoundException extends Exception {

	/**
	 * Exception Serial Version Unique Identifier
	 */
	private static final long serialVersionUID = 8866962255414376192L;

	/**
	 * Default public Exception constructor
	 */
	public NotFoundException() {
		super();
	}

	/**
	 * Message and original clause reporter public Exception constructor
	 * @param message Message to report
	 * @param cause Exception to report
	 */
	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Message reporter public Exception constructor
	 * @param message Message to report
	 */
	public NotFoundException(String message) {
		super(message);
	}

	/**
	 * Original clause reporter public Exception constructor
	 * @param cause Exception to report
	 */
	public NotFoundException(Throwable cause) {
		super(cause);
	}

}
