package selenium2_easy_test_plugin.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugins.annotations.Parameter;

public class Selenium2ServerConfig {
	
	@Parameter(required=false)
	private List<Selenium2ServerEntry> suites = new ArrayList<Selenium2ServerEntry>(0);
	
	
	
	public List<Selenium2ServerEntry> getSuites() {
		return suites;
	}


	public void setSuites(List<Selenium2ServerEntry> suites) {
		this.suites = suites;
	}



	public int size() {
		return suites.size();
	}
}
