package selenium2_easy_test_plugin;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;


/**
 * Selenium2 Automated Test Framework Mojo Plug-in JUnit Integration Tests
 * <br/>Here we check the availability of the main configuration paths:
 * <br/>
 * <ul>
 * <br/><li><b>Property file configuration</b> This feature allows to load a property file with the configuration of the Test Suite</li>
 * <br/><li><b>Classes List dynamic configuration</b> This feature creates a configuration file and a test engine using the class list loading (concurrent with the next two configurations)</li>
 * <br/><li><b>Package dynamic configuration</b> This feature creates a configuration file and a test engine using the package classes loading (concurrent with the previous and next configurations)</li>
 * <br/><li><b>XML files directory dynamic configuration</b> This feature creates a configuration file and a test engine using the XML file test cases saved inside a directory (concurrent with the previous two configurations)</li>
 * </ul>
 * <br/>
 * @author Fabrizio Torelli
 *
 */
public class TestSelenium2EastTestServer {
	private static final String osPath = System.getProperty("os.name").toLowerCase().indexOf("win")>=0 ? "win" : "others";
	/**
	 * The default Mojo Rule
	 * Not used just right now. This will be useful in the 
	 * Sunfire plug-in integration phase.
	 */
	@Rule
    public MojoRule rule = new MojoRule()
    {
      @Override
      protected void before() throws Throwable 
      {
      }

      @Override
      protected void after()
      {
      }
    };
	/**
	 * Here we test the instance available of the plug-in loaded with a sample POM file 
	 * @throws Exception Test execution exceptions
	 */
	@Test
	public void testPluginAsAnInitialScenarioMustBeCreated() throws Exception {
		File pom = new File( "src/test/resources/"+osPath+"/selenium-server-test-pom.xml" );
		assertNotNull( pom );
		assertTrue( pom.exists() );
        ExecuteSeleniumServerPlugin executeSeleniumServerPlugin = (ExecuteSeleniumServerPlugin) rule.lookupMojo("execute", pom );
        assertNotNull( executeSeleniumServerPlugin );
    }

	/**
	 * Here we test the instance available and operative state of the plug-in loaded with a Test Suite properties file's POM file 
	 * @throws Exception Test execution exceptions
	 */
	@Test
	public void testInitAsFirstScenarioBeExecutedForPropertiesFile() throws Exception {
		File pom = new File( "src/test/resources/"+osPath+"/selenium-server-test-pom.xml" );
		assertNotNull( pom );
		assertTrue( pom.exists() );
        ExecuteSeleniumServerPlugin executeSeleniumServerPlugin = (ExecuteSeleniumServerPlugin) rule.lookupMojo("execute", pom );
        assertNotNull( executeSeleniumServerPlugin );
        executeSeleniumServerPlugin.execute();
    }

	/**
	 * Here we test the instance available and operative state of the plug-in loaded with a Classes list's POM file 
	 * @throws Exception Test execution exceptions
	 */
	@Test
	public void testInitAsSecondScenarioBeExecutedForClasses() throws Exception {
		File pom = new File( "src/test/resources/"+osPath+"/selenium-server-classes-pom.xml" );
		assertNotNull( pom );
		assertTrue( pom.exists() );
        ExecuteSeleniumServerPlugin executeSeleniumServerPlugin = (ExecuteSeleniumServerPlugin) rule.lookupMojo("execute", pom );
        assertNotNull( executeSeleniumServerPlugin );
        executeSeleniumServerPlugin.execute();
    }

	/**
	 * Here we test the instance available and operative state of the plug-in loaded with a Package location's POM file 
	 * @throws Exception Test execution exceptions
	 */
	@Test
	public void testInitAsThirdScenarioBeExecutedForPackages() throws Exception {
		File pom = new File( "src/test/resources/"+osPath+"/selenium-server-package-pom.xml" );
		assertNotNull( pom );
		assertTrue( pom.exists() );
        ExecuteSeleniumServerPlugin executeSeleniumServerPlugin = (ExecuteSeleniumServerPlugin) rule.lookupMojo("execute", pom );
        assertNotNull( executeSeleniumServerPlugin );
        executeSeleniumServerPlugin.execute();
    }

	/**
	 * Here we test the instance available and operative state of the plug-in loaded with an XML files directory's POM file
	 * @throws Exception Test execution exceptions
	 */
	@Test
	public void testInitAsUlltimateScenarioBeExecutedForXMLPath() throws Exception {
		File pom = new File( "src/test/resources/"+osPath+"/selenium-server-xmlpath-pom.xml" );
		assertNotNull( pom );
		assertTrue( pom.exists() );
        ExecuteSeleniumServerPlugin executeSeleniumServerPlugin = (ExecuteSeleniumServerPlugin) rule.lookupMojo("execute", pom );
        assertNotNull( executeSeleniumServerPlugin );
        executeSeleniumServerPlugin.execute();
    }
	
	/**
	 * Here we test the instance available and operative state of the plug-in loaded with an XML files directory's POM file
	 * @throws Exception Test execution exceptions
	 */
	@Test
	public void testInitAsUlltimateUnirestScenarioBeExecutedForXMLPath() throws Exception {
		File pom = new File( "src/test/resources/unirest/selenium-server-unirest-xmlpath-pom.xml" );
		assertNotNull( pom );
		assertTrue( pom.exists() );
        ExecuteSeleniumServerPlugin executeSeleniumServerPlugin = (ExecuteSeleniumServerPlugin) rule.lookupMojo("execute", pom );
        assertNotNull( executeSeleniumServerPlugin );
        executeSeleniumServerPlugin.execute();
    }
}
