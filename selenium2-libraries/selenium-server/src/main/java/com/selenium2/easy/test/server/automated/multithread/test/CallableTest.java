package com.selenium2.easy.test.server.automated.multithread.test;

import java.util.concurrent.Callable;

public class CallableTest implements Callable<TestReult> {

	public CallableTest() {
	}

	@Override
	public TestReult call() throws Exception {
		TestReult result = new TestReult();
		Thread.sleep(1000);
		result.end();
		return result;
	}

}
