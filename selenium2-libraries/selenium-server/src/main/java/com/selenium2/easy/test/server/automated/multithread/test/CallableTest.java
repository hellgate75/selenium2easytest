package com.selenium2.easy.test.server.automated.multithread.test;

import java.util.concurrent.Callable;

/**
 * Multi-threading execution Callable object collecting the execution report information
 * @author Fabrizio Torelli
 * @see TestReult
 * @see Callable
 */
public class CallableTest implements Callable<TestReult> {

	/**
	 * Public constructor
	 */
	public CallableTest() {
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public TestReult call() throws Exception {
		TestReult result = new TestReult();
		Thread.sleep(1000);
		result.end();
		return result;
	}

}
