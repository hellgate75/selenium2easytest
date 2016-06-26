package com.selenium2.easy.test.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium2.easy.test.server.automated.SeleniumAutomatedServer;
import com.selenium2.easy.test.server.automated.WebDriverSelector;

/**
 * Main Class that provides a command line execution feature
 * <br/>Expected parameters:
 * <br/>
 * <ul>
 * <br><li><b>--configFile <file></b> It recover the information of the custom position of the configuration file </li>
 * </ul>
 * @author Fabrizio Torelli
 * @see SeleniumAutomatedServer
 */
public class Main {
	static {
		if (System.getProperty("log4j.configurationFile")==null)
			System.setProperty("log4j.configurationFile", "log4j2.xml");
		WebDriverSelector.isInUnitTest = true;
	}
	private static Logger logger = LoggerFactory.getLogger("com.selenium2.easy.test.server");

	/**
	 * Main class default execution method
	 * @param args arguments passed by the java runtime environment
	 * @throws Throwable Any exception raised in the program
	 */
	public static void main(String[] args) throws Throwable{
		logger.info("****************************************************************");
		logger.info("Selenium 2 Automated Test Server Bootstrap in progress");
		logger.info("****************************************************************");
		String configFile = "selenium2.properies";
		for(int i=0; i < args.length; i++) {
			String arg = args[i];
			if (arg.equalsIgnoreCase("--configFile")) {
				try {
					configFile = args[i+1];
				} catch (Throwable e) {
				}
			}
		}
		logger.info("Configuration : ");
		logger.info("config : " + configFile);
		logger.info("****************************************************************");
		
		SeleniumAutomatedServer seleniumServer = new SeleniumAutomatedServer();
		
		try {
			if (configFile.toLowerCase().indexOf(".xml")<0)
				seleniumServer.readConfig(configFile);
			else
				seleniumServer.readConfigXml(configFile);
			logger.info("Selenium 2 Automated Test Server Bootstrap started server");
			logger.info("****************************************************************");
			seleniumServer.startTests();
		} finally {
		}
		logger.info("****************************************************************");
		logger.info("Selenium 2 Automated Test Server Bootstrap exit");
		logger.info("****************************************************************");
	}

}
