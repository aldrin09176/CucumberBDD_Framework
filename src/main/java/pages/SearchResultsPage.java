package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonUtils;
import utils.ElementUtils;

public class SearchResultsPage {

	WebDriver driver;
	private ElementUtils elementUtils;
	private final int explicitWaitTime;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementUtils = new ElementUtils(driver);
		this.explicitWaitTime = CommonUtils.EXPLICIT_WAIT_BASIC_TIME;
	}

	@FindBy(linkText = "HP LP3065")
	public WebElement validHPProduct;

	@FindBy(xpath = "//input[@id='button-search']/following-sibling::p")
	public WebElement messageText;

//	public boolean displayStatusOfValidProduct() {
//		return elementUtils.displayStatusOfElement(validHPProduct, explicitWaitTime);
//	}

	public String getMessageText() {
		return elementUtils.getTextFromElement(messageText, explicitWaitTime);
	}

}
