package com.selenium2.easy.test.server.automated.multithread;

import com.selenium2.easy.test.server.automated.WebDriverSelector;
import com.selenium2.easy.test.server.exceptions.FrameworkException;

public interface WebDriverParallelFactory {
	WebDriverSelector nextWebDriver() throws FrameworkException;
}
