package com.selenium2.easy.test.server.automated.multithread.test;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestSemaphore {
	ExecutorService executor = Executors.newFixedThreadPool(10);
    CompletionService<TestReult> compService = new ExecutorCompletionService<TestReult>(executor);
	public TestSemaphore() {
		for(int i=0;i<30;i++) {
			CallableTest task = new CallableTest();
			compService.submit(task);
		}
	}
	
	public void run() throws InterruptedException, ExecutionException {
		for(int i=0;i<30;i++) {
			Future<TestReult> result = compService.take();
			System.out.println(result.get());
		}
		executor.shutdown();
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new TestSemaphore().run();
	}
}
