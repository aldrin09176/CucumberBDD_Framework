package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonUtils;
import utils.ElementUtils;

public class AccountPage {

	WebDriver driver;
	private ElementUtils elementUtils;
	private final int explicitWaitTime;

	public AccountPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
		this.explicitWaitTime = CommonUtils.EXPLICIT_WAIT_BASIC_TIME;
	}

	@FindBy(linkText = "Edit your account information")
	private WebElement editYourAccountInformationOption;

	public boolean displayStatusOfEditYourAccountInformationOption() {
		return elementUtils.displayStatusOfElement(editYourAccountInformationOption, explicitWaitTime);
	}

}
