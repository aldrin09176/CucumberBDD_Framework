package stepdefinitions;

import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.en.When;
import pages.CommonPage;

public class Common {

	WebDriver driver;
	private CommonPage commonPage;

	public Common() {
		driver = DriverFactory.getDriver();
		commonPage = new CommonPage(driver);
	}

	@When("User go to this {string} linl")
	public void user_enters_valid_product_into_search_box_field(String url) {
		commonPage.goTo(url);
	}

	
}
