package com.selenium2.easy.test.server.automated.multithread.test;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Test User processes execution semaphore. It manages and limits the multi-user test cases execution.
 * @author Fabrizio Torelli
 * @see TestReult
 * @see CallableTest
 * @see ExecutorService
 * @see CompletionService
 */
public class TestSemaphore {
	private ExecutorService executor = Executors.newFixedThreadPool(10);
	private CompletionService<TestReult> compService = new ExecutorCompletionService<TestReult>(executor);
	/**
	 * Public constructor
	 */
	public TestSemaphore() {
		for(int i=0;i<30;i++) {
			CallableTest task = new CallableTest();
			compService.submit(task);
		}
	}
	
	/**
	 * Thread execution monitor
	 * @throws InterruptedException When a occurs a thread unexpected stop
	 * @throws ExecutionException When an exception occurs during the thread execution
	 */
	public void run() throws InterruptedException, ExecutionException {
		for(int i=0;i<30;i++) {
			Future<TestReult> result = compService.take();
			System.out.println(result.get());
		}
		executor.shutdown();
	}
	
	/**
	 * (non-Javadoc)
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new TestSemaphore().run();
	}
}
