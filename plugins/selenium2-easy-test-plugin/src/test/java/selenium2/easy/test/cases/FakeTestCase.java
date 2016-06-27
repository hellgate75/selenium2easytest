package selenium2.easy.test.cases;

import org.openqa.selenium.WebDriver;

import com.selenium2.easy.test.server.cases.BaseTestCase;

public class FakeTestCase extends BaseTestCase {

	public FakeTestCase() {
		super("FakeTestCase", "", false, false);
	}

	@Override
	public void automatedTest(WebDriver driver) throws Throwable {
		this.getLogger().info("***** FakeTestCase executed!!");
	}

}
