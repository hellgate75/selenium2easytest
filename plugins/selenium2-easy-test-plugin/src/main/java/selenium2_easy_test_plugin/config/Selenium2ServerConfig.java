package selenium2_easy_test_plugin.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * MOJO Container for the Selenium2 Automated Server Framework configurations
 * @see Selenium2ServerEntry
 * @author Fabrizio Torelli
 *
 */
public class Selenium2ServerConfig {
	
	@Parameter(required=false)
	private List<Selenium2ServerEntry> suites = new ArrayList<Selenium2ServerEntry>(0);
	
	
	
	/**
	 * Retrieves the list of {@link Selenium2ServerEntry} that contains the Selenium2 Automated Server Framework Configurations
	 * @return The list of {@link Selenium2ServerEntry}
	 */
	public List<Selenium2ServerEntry> getSuites() {
		return suites;
	}


	/**
	 * Sets the list of {@link Selenium2ServerEntry} that contains the Selenium2 Automated Server Framework Configurations
	 * @param suites The list of {@link Selenium2ServerEntry}
	 */
	public void setSuites(List<Selenium2ServerEntry> suites) {
		this.suites = suites;
	}



	/**
	 * Retrieves the number of Test Suites loaded in the Selenium2 Automated Server Framework MOJO Plug-in
	 * @return number of Test Suites
	 */
	public int size() {
		return suites.size();
	}
}
