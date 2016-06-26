package com.selenium2.easy.test.server.exceptions;

/**
 * Exception that occurs when any Selenium2 action fails. Such as a setValue on a WebComponent or
 * a Snapshot to be saved on a wrong file path, and so on ...
 * @author Fabrizio Torelli
 *
 */
public class ActionException extends Exception {

	/**
	 * Exception Serial Version Unique Identifier
	 */
	private static final long serialVersionUID = 8963951371409012055L;

	/**
	 * Default public Exception constructor
	 */
	public ActionException() {
		super();
	}

	/**
	 * Message reporter public Exception constructor
	 * @param message Message to report
	 */
	public ActionException(String message) {
		super(message);
	}

	/**
	 * Original clause reporter public Exception constructor
	 * @param cause Exception to report
	 */
	public ActionException(Throwable cause) {
		super(cause);
	}

	/**
	 * Message and original clause reporter public Exception constructor
	 * @param message Message to report
	 * @param cause Exception to report
	 */
	public ActionException(String message, Throwable cause) {
		super(message, cause);
	}

}
