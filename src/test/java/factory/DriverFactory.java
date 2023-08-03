package factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import utils.CommonUtils;

public class DriverFactory {
	
	static WebDriver driver = null;
	
	public static WebDriver initializeBrowser(String browserName) throws MalformedURLException {
		
		if(browserName.equals("chrome")) {
			
			
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");	
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("disabled-gpu");
			//Running in Local
			driver = new ChromeDriver(options);
			
			
			//DesiredCapabilities capabilities = new DesiredCapabilities();
			//capabilities.setBrowserName("chrome");
			
			//Running in Docker
			//driver = new RemoteWebDriver(new URL("http://localhost:4444"),capabilities);
			
		}else if(browserName.equals("firefox")) {
			
			//Running in Local
			driver = new FirefoxDriver();
			
		}else if(browserName.equals("edge")) {
			
			//Running in Local
			driver = new EdgeDriver();
			
		}else if(browserName.equals("safari")) {
			
			//Running in Local
			driver = new SafariDriver();
			
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(CommonUtils.PAGE_LOAD_TIME));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CommonUtils.IMPLICIT_WAIT_TIME));
		
		return driver;
		
	}
	
	public static WebDriver getDriver() {
		
		return driver;
		
	}

}
