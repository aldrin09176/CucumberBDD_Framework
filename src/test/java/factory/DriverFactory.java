package factory;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import utils.ConfigReader;

public class DriverFactory {

	private static WebDriver driver = null;

	public enum BrowserType {
		CHROME, 
		FIREFOX, 
		EDGE, 
		SAFARI,
		IE
	}

	public static WebDriver initializeBrowser(String browserName) throws Exception, IOException {
		BrowserType browserType = BrowserType.valueOf(browserName.toUpperCase());

		switch (browserType) {
		case CHROME:
			initializeChrome();
			break;
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case EDGE:
			initEdgeDriver();
			break;
		case SAFARI:
			driver = new SafariDriver();
			break;
		case IE:
			initIEPermissionVBScript();
			initializeIEDriver();
			break;
		default:
			throw new IllegalArgumentException("Browser Type Not Supported");
		}
		return driver;
	}

	private static void initializeChrome() {
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--ignore-ssl-errors=yes","--ignore-certificate-errors");
		driver = new ChromeDriver(options);
	}
	
	private static void initEdgeDriver() {
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--ignore-ssl-errors=yes","--ignore-certificate-errors");
		driver = new EdgeDriver(options);
	}
	
	private static void initializeIEDriver() {
		System.setProperty("webdriver.ie.driver", ConfigReader.getIEDriverPath());
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.attachToEdgeChrome();
		//options.withEdgeExecutablePath(ConfigReader.getIEDriverPath());
		driver = new InternetExplorerDriver(options);
	}
	
	private static void initIEPermissionVBScript() throws InterruptedException, IOException {
		String[] command = {"cscript", ConfigReader.getIESecurityScriptPath()};
		Process process = Runtime.getRuntime().exec(command);
		process.waitFor();
		int exitValue = process.exitValue();
			if(exitValue !=0) {
				// Handle Error
				System.out.println("VBScript execution failed with exit code: " + exitValue);
			} else {
				System.out.println("VBScript executed successfully");
			}
		
	}

	public static WebDriver getDriver() {
		return driver;
	}

}