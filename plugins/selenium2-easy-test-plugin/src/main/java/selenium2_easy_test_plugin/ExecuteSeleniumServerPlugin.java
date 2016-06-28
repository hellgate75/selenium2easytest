package selenium2_easy_test_plugin;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import selenium2_easy_test_plugin.config.Selenium2ServerConfig;
import selenium2_easy_test_plugin.config.Selenium2ServerEntry;

import com.selenium2.easy.test.server.automated.SeleniumAutomatedServer;
import com.selenium2.easy.test.server.exceptions.FrameworkException;
import com.selenium2.easy.test.server.exceptions.NotFoundException;

/**
 * Goal which disconnect a Selenium2 Automated Test Server.
 */
@Mojo(defaultPhase=LifecyclePhase.TEST, threadSafe=true, name="execute")
public class ExecuteSeleniumServerPlugin extends AbstractMojo {

	@Parameter(defaultValue = "${project}", readonly = true )
	private MavenProject project;

	// ***Not yet useful***
	//	@Parameter( defaultValue = "${session}", readonly = true )
	//	private MavenSession session;
	
	@Parameter(defaultValue = "target/selenium2", readonly = false )
	private String temporaryPath = "target/selenium2";


	/**
	 * List of Selenium2 Configuration Files to be installed.
	 */
	@Parameter(required=false )
	private List<String> configFiles;

	/**
	 * List of Selenium2 Configuration to be created.
	 */
	@Parameter(required=false )
	private Selenium2ServerConfig settings;

	private List<SeleniumAutomatedServer> servers = null;

	public ExecuteSeleniumServerPlugin() {
	}

	private synchronized void cleanFolderOrFile(File file) {
		file.setReadable(true);
		file.setWritable(true);
		if (file.isDirectory()) {
			for(File child: file.listFiles()) {
				cleanFolderOrFile(child);
			}
		}
		else {
			file.delete();
		}
	}
	
	protected synchronized void cleanTemporaryPath(String outDirectoryPath) {
		File directory = new File(outDirectoryPath);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		else if (!directory.isDirectory()) {
			directory.setReadable(true);
			directory.setWritable(true);
			directory.delete();
			directory.mkdirs();
			directory.setReadable(true);
			directory.setWritable(true);
		}
		else {
			for(File file: directory.listFiles()) {
				this.cleanFolderOrFile(file);
				
			}
		}
		
	}

	protected synchronized File initFile(Selenium2ServerEntry entry, String outDirectoryPath) {
		String randomCode = UUID.randomUUID().toString().replace('-', '_');
		File directory = new File(outDirectoryPath);
		if (directory.isDirectory()) {
			String propertiesFile = null;
			PrintStream fileWriteStream = null;
			try {
				propertiesFile = directory.getAbsolutePath() + File.separator
						+ randomCode + ".properties";
				fileWriteStream = new PrintStream(propertiesFile);
				entry.getMergedProperties()
						.store(fileWriteStream,
								"..::°° Generated by the Selenium2 Automated Test Server Plugin °°::..");
				return new File(propertiesFile);
			} catch (Throwable e) {
				getLog().warn("Server Configurations File save failed for Selenium2 Automated Server Plugin on file  : " + propertiesFile + " caused by :", e);
			}
			finally {
				if (fileWriteStream!=null) {
					try {
						fileWriteStream.close();
					} catch (Throwable e2) {
						getLog().warn("Server Configurations File close stream failed for Selenium2 Automated Server Plugin on file : " + propertiesFile + " caused by :", e2);
					}
				}
			}
		}
		return null;
	}

	protected List<SeleniumAutomatedServer> initPlugin() {
		List<SeleniumAutomatedServer> servers = new ArrayList<SeleniumAutomatedServer>(0);
		if ((settings!=null && settings.size()>0) || (configFiles!=null && configFiles.size()>0)) {
			if ((configFiles!=null && configFiles.size()>0)) {
				for(String initFilePath:configFiles) {
					File initFile = new File(initFilePath);
					if (initFile!=null && initFile.exists() && initFile.isFile()) {
						try {
							SeleniumAutomatedServer testServer = new SeleniumAutomatedServer();
							testServer.readConfig(initFile);
							servers.add(testServer);
						} catch (NotFoundException e) {
							getLog().warn("Server Configurations File load failed for Selenium2 Automated Server Plugin on file  : " + initFilePath + " caused by :", e);
						} catch (FrameworkException e) {
							getLog().warn("Server Configurations File load failed for Selenium2 Automated Server Plugin on file  : " + initFilePath + " caused by :", e);
						} catch (Throwable e) {
							getLog().warn("Server Configurations File load failed for Selenium2 Automated Server Plugin on file  : " + initFilePath + " caused by :", e);
						}
					}
					else {
						getLog().warn("Server Configurations File not valid for Selenium2 Automated Server Plugin on file  : " + initFilePath);
					}
				}
			}
			if ((settings!=null && settings.size()>0)) {
				if (!new File(temporaryPath).exists()) {
					new File(temporaryPath).mkdir();
				}
				for(Selenium2ServerEntry initEntry:settings.getSuites()) {
					//TODO: Creare il file nel target e definire il server
						try {
							File initFile = this.initFile(initEntry, temporaryPath);
							if (initFile!=null && initFile.exists() && initFile.isFile()) {
								SeleniumAutomatedServer testServer = new SeleniumAutomatedServer();
								testServer.readConfig(initFile);
								servers.add(testServer);
							}
							else {
								getLog().warn("Server Configurations File not valid for Selenium2 Automated Server Plugin on file  : " + (initFile !=null ? initFile.getAbsolutePath():null));
							}
						} catch (NotFoundException e) {
							getLog().warn("Server Configurations File load failed for Selenium2 Automated Server Plugin on file  : " + initEntry + " (to should write to the directory:"+temporaryPath+") caused by :", e);
						} catch (FrameworkException e) {
							getLog().warn("Server Configurations File load failed for Selenium2 Automated Server Plugin on file  : " + initEntry + " (to should write to the directory:"+temporaryPath+") caused by :", e);
						} catch (Throwable e) {
							getLog().warn("Server Configurations File load failed for Selenium2 Automated Server Plugin on file  : " + initEntry + " (to should write to the directory:"+temporaryPath+") caused by :", e);
						}
				}
			}
		}
		else {
			getLog().error("No Server Configurations found for Selenium2 Automated Server Plugin");
		}
		return servers;
	}
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Selenium2 Automated Server Maven running ....");
		servers = initPlugin();
		if (servers.size()>0) {
			for(SeleniumAutomatedServer server: servers) {
				try {
					if (server != null) {
						server.startTests();
					}
					else {
						getLog().error("Unable to start the Selenium2 Automated Server!!");
					}
				} catch (Exception e) {
					getLog().error("Error during the STARTUP of the Selenium2 Automated Server caused by :");
					getLog().error(e);
				}
			}
		}
		else {
			getLog().error("No Server Configurations found for the Selenium2 Automated Server Plugin : check the configuration");
		}
		getLog().info("Selenium2 Automated Server Maven exit ....");
	}

	public MavenProject getProject() {
		return project;
	}

	public void setProject(MavenProject project) {
		this.project = project;
	}

	public String getTemporaryPath() {
		return temporaryPath;
	}

	public void setTemporaryPath(String temporaryPath) {
		this.temporaryPath = temporaryPath;
	}

	public List<String> getConfigFiles() {
		return configFiles;
	}

	public void setConfigFiles(List<String> configFiles) {
		this.configFiles = configFiles;
	}

	public Selenium2ServerConfig getSettings() {
		return settings;
	}

	public void setSettings(Selenium2ServerConfig configurations) {
		this.settings = configurations;
	}

	public List<SeleniumAutomatedServer> getServers() {
		return servers;
	}

	public void setServers(List<SeleniumAutomatedServer> servers) {
		this.servers = servers;
	}
}