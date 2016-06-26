package com.selenium2.easy.test.server.automated.multithread;

import com.selenium2.easy.test.server.automated.WebDriverSelector;
import com.selenium2.easy.test.server.exceptions.FrameworkException;

/**
 * Parallel multi-users execution and multi-drive selection Factory Interface
 * @author Fabrizio Torelli
 * @see WebDriverSelector
 */
public interface WebDriverParallelFactory {
	/**
	 * Retrieve the next driver used to run the tests
	 * @return WebDriver
	 * @throws FrameworkException When any framework exception occurs
	 */
	WebDriverSelector nextWebDriver() throws FrameworkException;
}
