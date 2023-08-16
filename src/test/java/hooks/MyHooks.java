package hooks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;

public class MyHooks {

	private WebDriver driver;

	@Before
	public void setup(Scenario scenario) throws Exception {
		driver = DriverFactory.initializeBrowser(ConfigReader.getBrowser());
		driver.get(ConfigReader.getUrl());
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			String scenarioName = formatScenarioName(scenario.getName());
			byte[] srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(srcScreenshot, "image/png", scenarioName);
		}
		driver.quit();
	}

	private String formatScenarioName(String name) {
		String scenarioName = name.replaceAll(" ", "_");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		String dateAndTime = dtf.format(now);
		
		return scenarioName + "_" + dateAndTime;
	}
}