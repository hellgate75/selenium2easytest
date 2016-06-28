package selenium2.easy.test.cases;

import org.openqa.selenium.WebDriver;

import com.selenium2.easy.test.server.cases.BaseTestCase;

/**
 * Fake Test case used to test the Selenium2 Automated Server Framework MOJO Plug-in
 * @see BaseTestCase
 * @author Fabrizio Torelli
 *
 */
public class FakeTestCase extends BaseTestCase {

	/**
	 * Default Test Case Constructor
	 */
	public FakeTestCase() {
		super("FakeTestCase", "", false, false);
	}

	/* (non-Javadoc)
	 * @see com.selenium2.easy.test.server.cases.TestCase#automatedTest(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void automatedTest(WebDriver driver) throws Throwable {
		this.getLogger().info("***** FakeTestCase executed!!");
	}

}
