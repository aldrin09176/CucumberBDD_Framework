package stepdefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountPage;
import pages.CommonPage;
import pages.HomePage;
import pages.LoginPage;

public class Login {

	WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	private AccountPage accountPage;
	private CommonPage commonPage;

	public Login() {
		driver = DriverFactory.getDriver();
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		accountPage = new AccountPage(driver);
		commonPage = new CommonPage(driver);
	}

	@Given("User navigates to login page")
	public void user_navigates_to_login_page() {
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();
	}

	@When("^User enters valid email address (.+) into email field$")
	public void User_enters_valid_email_address_into_email_field(String emailText) {
		loginPage.enterEmailAddress(emailText);
	}

	@And("^User enters valid password (.+) into password field$")
	public void user_enters_valid_password_into_password_field(String passwordText) {
		loginPage.enterPassword(passwordText);
	}

	@And("User clicks on Login button")
	public void user_clicks_on_login_button() {
		loginPage.clickOnLoginButton();
	}

	@Then("User should get successfully logged in")
	public void user_should_get_successfully_logged_in() {
		Assert.assertTrue(commonPage.isElementDisplayed(accountPage.editYourAccountInformationOption));
	}

	@When("User enters invalid email address into email field")
	public void user_enters_invalid_email_address_into_email_field() {
		loginPage.enterEmailAddress(commonPage.getEmailWithTimeStamp());

	}

	@When("User enters invalid password {string} into password field")
	public void user_enters_invalid_password_into_password_field(String invalidPasswordText) {
		loginPage.enterPassword(invalidPasswordText);
	}

	@Then("User should see this warning message {string} regarding about credentials mismatch")
	public void user_should_get_a_proper_warning_message_about_credentials_mismatch(String text) {
		Assert.assertTrue(loginPage.getWarningMessageText(text));
	}

	@When("User dont enter email address into email field")
	public void user_dont_enter_email_address_into_email_field() {
		loginPage.enterEmailAddress("");
	}

	@When("User dont enter password into password field")
	public void user_dont_enter_password_into_password_field() {
		loginPage.enterPassword("");
	}

}