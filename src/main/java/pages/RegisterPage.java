package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonUtils;
import utils.ElementUtils;

public class RegisterPage {
	
	WebDriver driver;
	private ElementUtils elementUtils;
    private final int explicitWaitTime;
	
	public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
		elementUtils = new ElementUtils(driver);
		this.explicitWaitTime = CommonUtils.EXPLICIT_WAIT_BASIC_TIME;
		
	}
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyOption;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement YesNewsletterOption;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement warningMessage;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWaring;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public void enterFirstName(String firstNameText) {
		
		elementUtils.typeTextIntoElement(firstNameField, firstNameText,explicitWaitTime);
		
	}
	
	public void enterLastName(String lastNameText) {
		
		elementUtils.typeTextIntoElement(lastNameField, lastNameText,explicitWaitTime);
		
	}
	
	public void enterEmailAddress(String emailText) {
		
		elementUtils.typeTextIntoElement(emailField, emailText,explicitWaitTime);
		
	}
	
	public void enterTelephoneNumber(String telephoneText) {
		
		elementUtils.typeTextIntoElement(telephoneField, telephoneText,explicitWaitTime);
		
	}
	
	public void enterPassword(String passwordText) {
		
		elementUtils.typeTextIntoElement(passwordField, passwordText,explicitWaitTime);
		
	}
	
	public void enterConfirmPassword(String passwordText) {
		
		elementUtils.typeTextIntoElement(passwordConfirmField, passwordText,explicitWaitTime);
		
	}
	
	public void selectPrivacyPolicy() {
		
		elementUtils.clickOnElement(privacyPolicyOption,explicitWaitTime);
		
	}
	
	public void clickOnContinueButton() {
		
		elementUtils.clickOnElement(continueButton,explicitWaitTime);
		
	}
	
	public void selectYesNewsletterOption() {
		
		elementUtils.clickOnElement(YesNewsletterOption,explicitWaitTime);
		
	}
	
	public String getWarningMessageText() {
		
		return elementUtils.getTextFromElement(warningMessage,explicitWaitTime);
		
	}
	
	public String getFirstNameWarning() {
		
		return elementUtils.getTextFromElement(firstNameWarning,explicitWaitTime);
		
	}
	
	public String getLastNameWarning() {
		
		return elementUtils.getTextFromElement(lastNameWaring,explicitWaitTime);
		
	}
	
	public String getEmailWarning() {
		
		return elementUtils.getTextFromElement(emailWarning,explicitWaitTime);
		
	}
	
	public String getTelephoneWarning() {
		
		return elementUtils.getTextFromElement(telephoneWarning,explicitWaitTime);
		
	}
	
	public String getPasswordWarning() {
		
		return elementUtils.getTextFromElement(passwordWarning,explicitWaitTime);
		
	}
	

}
