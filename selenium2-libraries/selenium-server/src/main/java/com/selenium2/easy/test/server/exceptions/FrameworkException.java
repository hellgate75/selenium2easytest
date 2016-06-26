package com.selenium2.easy.test.server.exceptions;

/**
  * Exception that occurs when any TestEngine, BeseTestCase extension, the SeleniumAutomatedServer 
  * or any other framework lass operation fails. This exception has used to communicate the framework
  * an anomaly in the process, flow or data design domain.
* @author Fabrizio Torelli
 *
 */
public class FrameworkException extends Exception {

	/**
	 * Exception Serial Version Unique Identifier
	 */
	private static final long serialVersionUID = 8963951371409012055L;

	/**
	 * Default public Exception constructor
	 */
	public FrameworkException() {
		super();
	}

	/**
	 * Message reporter public Exception constructor
	 * @param message Message to report
	 */
	public FrameworkException(String message) {
		super(message);
	}

	/**
	 * Original clause reporter public Exception constructor
	 * @param cause Exception to report
	 */
	public FrameworkException(Throwable cause) {
		super(cause);
	}

	/**
	 * Message and original clause reporter public Exception constructor
	 * @param message Message to report
	 * @param cause Exception to report
	 */
	public FrameworkException(String message, Throwable cause) {
		super(message, cause);
	}

}
