package com.selenium2.easy.test.server.automated.multithread.test;

import java.text.SimpleDateFormat;

public class TestReult {

	private long start = System.currentTimeMillis();
	private long end = 0;
	public TestReult() {
	}
	
	public void end() {
		end = System.currentTimeMillis();
	}

	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("HH:MM:SS.MS");
		return "TestReult [start=" +  format.format(start) + ", end=" + format.format(end) + ", elapsed(ms)="+(end-start)+"]";
	}
	
	

}
