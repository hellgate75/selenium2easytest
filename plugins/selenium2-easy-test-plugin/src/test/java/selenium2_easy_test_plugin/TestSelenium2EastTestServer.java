package selenium2_easy_test_plugin;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;


public class TestSelenium2EastTestServer {
	private static final String osPath = System.getProperty("os.name").toLowerCase().indexOf("win")>=0 ? "win" : "others";
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
	@Test
	public void testPluginAsAnInitialScenarioMustBeCreated() throws Exception {
		File pom = new File( "src/test/resources/"+osPath+"/selenium-server-test-pom.xml" );
		assertNotNull( pom );
		assertTrue( pom.exists() );
        ExecuteSeleniumServerPlugin executeSeleniumServerPlugin = (ExecuteSeleniumServerPlugin) rule.lookupMojo("execute", pom );
        assertNotNull( executeSeleniumServerPlugin );
    }

	@Test
	public void testInitAsFirstScenarioBeExecutedForPropertiesFile() throws Exception {
		File pom = new File( "src/test/resources/"+osPath+"/selenium-server-test-pom.xml" );
		assertNotNull( pom );
		assertTrue( pom.exists() );
        ExecuteSeleniumServerPlugin executeSeleniumServerPlugin = (ExecuteSeleniumServerPlugin) rule.lookupMojo("execute", pom );
        assertNotNull( executeSeleniumServerPlugin );
        executeSeleniumServerPlugin.execute();
    }

	@Test
	public void testInitAsSecondScenarioBeExecutedForClasses() throws Exception {
		File pom = new File( "src/test/resources/"+osPath+"/selenium-server-classes-pom.xml" );
		assertNotNull( pom );
		assertTrue( pom.exists() );
        ExecuteSeleniumServerPlugin executeSeleniumServerPlugin = (ExecuteSeleniumServerPlugin) rule.lookupMojo("execute", pom );
        assertNotNull( executeSeleniumServerPlugin );
        executeSeleniumServerPlugin.execute();
    }

	@Test
	public void testInitAsThirdScenarioBeExecutedForPackages() throws Exception {
		File pom = new File( "src/test/resources/"+osPath+"/selenium-server-package-pom.xml" );
		assertNotNull( pom );
		assertTrue( pom.exists() );
        ExecuteSeleniumServerPlugin executeSeleniumServerPlugin = (ExecuteSeleniumServerPlugin) rule.lookupMojo("execute", pom );
        assertNotNull( executeSeleniumServerPlugin );
        executeSeleniumServerPlugin.execute();
    }

	@Test
	public void testInitAsUlltimateScenarioBeExecutedForXMLPath() throws Exception {
		File pom = new File( "src/test/resources/"+osPath+"/selenium-server-xmlpath-pom.xml" );
		assertNotNull( pom );
		assertTrue( pom.exists() );
        ExecuteSeleniumServerPlugin executeSeleniumServerPlugin = (ExecuteSeleniumServerPlugin) rule.lookupMojo("execute", pom );
        assertNotNull( executeSeleniumServerPlugin );
        executeSeleniumServerPlugin.execute();
    }
}
