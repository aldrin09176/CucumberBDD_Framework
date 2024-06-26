package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonUtils;
import utils.ElementUtils;

public class LoginPage {

	WebDriver driver;
	private ElementUtils elementUtils;
	private final int explicitWaitTime;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
		this.explicitWaitTime = CommonUtils.EXPLICIT_WAIT_BASIC_TIME;
	}

	@FindBy(id = "input-email")
	public WebElement emailField;

	@FindBy(id = "input-password")
	public WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login']")
	public WebElement loginButton;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	public WebElement warningMessage;

	public void enterEmailAddress(String emailText) {
		elementUtils.typeTextIntoElement(emailField, emailText, explicitWaitTime);
	}

	public void enterPassword(String passwordText) {
		elementUtils.typeTextIntoElement(passwordField, passwordText, explicitWaitTime);
	}

	public void clickOnLoginButton() {
		elementUtils.clickOnElement(loginButton, explicitWaitTime);
	}

	public boolean getWarningMessageText(String text) {
		return elementUtils.displayStatusOfText(warningMessage, text, explicitWaitTime);
	}

}