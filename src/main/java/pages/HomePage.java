package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonUtils;
import utils.ElementUtils;

public class HomePage {

	WebDriver driver;
	private ElementUtils elementUtils;
	private final int explicitWaitTime;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
		this.explicitWaitTime = CommonUtils.EXPLICIT_WAIT_BASIC_TIME;
	}

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(name = "search")
	private WebElement searchBoxField;

	@FindBy(xpath = "//button[contains(@class,'btn-default')]")
	private WebElement searchButton;

	public void clickOnMyAccount() {
		elementUtils.clickOnElement(myAccountDropMenu, explicitWaitTime);
	}

	public void selectLoginOption() {
		elementUtils.clickOnElement(loginOption, explicitWaitTime);
	}

	public void selectRegisterOption() {
		elementUtils.clickOnElement(registerOption, explicitWaitTime);
	}

	public void clickOnSearchButton() {
		elementUtils.clickOnElement(searchButton, explicitWaitTime);
	}

	public void enterProductIntoSearchBox(String productText) {
		elementUtils.typeTextIntoElement(searchBoxField, productText, CommonUtils.EXPLICIT_WAIT_BASIC_TIME);
	}

}
