package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonUtils {
	
	WebDriver driver;
	private ElementUtils elementUtils;
	public static final int IMPLICIT_WAIT_TIME = 30;
	public static final int PAGE_LOAD_TIME = 30;
	public static final int EXPLICIT_WAIT_BASIC_TIME = 30;
	
	
	public CommonUtils(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}

	public boolean isElementDisplayed(WebElement element) {
		return  elementUtils.displayStatusOfElement(element, EXPLICIT_WAIT_BASIC_TIME);
	}

	

}
