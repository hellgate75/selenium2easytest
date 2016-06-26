package com.selenium2.easy.test.server.automated.multithread.test;

import java.text.SimpleDateFormat;

/**
 * Test result timing collector class.
 * @author Fabrizio Torelli
 *
 */
public class TestReult {

	private long start = System.currentTimeMillis();
	private long end = 0;
	/**
	 * Public constructor
	 */
	public TestReult() {
	}
	
	/**
	 * Stops the timer
	 */
	public void end() {
		end = System.currentTimeMillis();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("HH:MM:SS.MS");
		return "TestReult [start=" +  format.format(start) + ", end=" + format.format(end) + ", elapsed(ms)="+(end-start)+"]";
	}
}
