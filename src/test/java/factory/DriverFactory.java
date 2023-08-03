package factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import utils.CommonUtils;

public class DriverFactory {

	private static WebDriver driver = null;
	
    public enum BrowserType {
        CHROME,
        FIREFOX,
        EDGE,
        SAFARI
    }

	public static WebDriver initializeBrowser(String browserName) {
		BrowserType browserType = BrowserType.valueOf(browserName.toUpperCase());

	
        switch (browserType) {
        	case CHROME:
        		driver = new ChromeDriver();
        		break;
        	case FIREFOX:
        		driver = new FirefoxDriver();
        		break;
        	case EDGE:
        		driver = new EdgeDriver();
        		break;
        	case SAFARI:
        		driver = new SafariDriver();
        		break;
        	default:
        		throw new IllegalArgumentException("Browser Type Not Supported");
    }

        configureDriver();
		return driver;

	}
	
	private static void configureDriver() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(CommonUtils.PAGE_LOAD_TIME));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CommonUtils.IMPLICIT_WAIT_TIME));
    }

	public static WebDriver getDriver() {
		return driver;

	}

}