package stepdefinitions;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountSuccessPage;
import pages.CommonPage;
import pages.HomePage;
import pages.RegisterPage;

public class Register {

	WebDriver driver;
	private HomePage homePage;
	private RegisterPage registerPage;
	private AccountSuccessPage accountSuccessPage;
	private CommonPage commonPage;

	public Register() {
		driver = DriverFactory.getDriver();
		homePage = new HomePage(driver);
		registerPage = new RegisterPage(driver);
		accountSuccessPage = new AccountSuccessPage(driver);
		commonPage = new CommonPage(driver);
	}

	@Given("User navigates to Register Account page")
	public void user_navigates_to_register_account_page() {
		homePage.clickOnMyAccount();
		homePage.selectRegisterOption();
	}

	@When("User enters the details into below fields")
	public void user_enters_the_details_into_below_fields(DataTable dataTable) {
		Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
		registerPage.enterFirstName(dataMap.get("firstName"));
		registerPage.enterLastName(dataMap.get("lastName"));
		registerPage.enterEmailAddress(commonPage.getEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataMap.get("telephone"));
		registerPage.enterPassword(dataMap.get("password"));
		registerPage.enterConfirmPassword(dataMap.get("password"));
	}

	@When("User enters the details into below fields with duplicate email")
	public void user_enters_the_details_into_below_fields_with_duplicate_email(DataTable dataTable) {
		Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
		registerPage.enterFirstName(dataMap.get("firstName"));
		registerPage.enterLastName(dataMap.get("lastName"));
		registerPage.enterEmailAddress(dataMap.get("email"));
		registerPage.enterTelephoneNumber(dataMap.get("telephone"));
		registerPage.enterPassword(dataMap.get("password"));
		registerPage.enterConfirmPassword(dataMap.get("password"));

	}

	@When("User selects Privacy Policy")
	public void user_selects_privacy_policy() {
		registerPage.selectPrivacyPolicy();
	}

	@When("User clicks on Continue button")
	public void user_clicks_on_continue_button() {
		registerPage.clickOnContinueButton();
	}

	@Then("User account should get created successfully")
	public void user_account_should_get_created_successfully() {
		Assert.assertEquals("Your Account Has Been Created!", accountSuccessPage.getPageHeading());
	}

	@When("User selects Yes for Newsletter")
	public void user_selects_yes_for_newsletter() {
		registerPage.selectYesNewsletterOption();
	}

	@Then("User should get this warning {string} about duplicate email")
	public void user_should_get_a_proper_warning_about_duplicate_email(String text) {
		Assert.assertTrue(registerPage.getWarningMessageText(text));
	}
	
//	@Then("User should see this warning message {string} regarding about credentials mismatch")
//	public void user_should_get_a_proper_warning_message_about_credentials_mismatch(String text) {
//		Assert.assertTrue(loginPage.getWarningMessageText(text));
//	}

	@When("User dont enter any details into fields")
	public void user_dont_enter_any_details_into_fields() {
		registerPage.enterFirstName("");
		registerPage.enterLastName("");
		registerPage.enterEmailAddress("");
		registerPage.enterTelephoneNumber("");
		registerPage.enterPassword("");
		registerPage.enterConfirmPassword("");
	}

	@Then("User should get proper warning messages for every mandatory field")
	public void user_should_get_proper_warning_messages_for_every_mandatory_field() {
		Assert.assertEquals("First Name must be between 1 and 32 characters!", registerPage.getFirstNameWarning());
		Assert.assertEquals("Last Name must be between 1 and 32 characters!", registerPage.getLastNameWarning());
		Assert.assertEquals("E-Mail Address does not appear to be valid!", registerPage.getEmailWarning());
		Assert.assertEquals("Telephone must be between 3 and 32 characters!", registerPage.getTelephoneWarning());
		Assert.assertEquals("Password must be between 4 and 20 characters!", registerPage.getPasswordWarning());
	}

}
