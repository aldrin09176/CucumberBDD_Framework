package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

	private WebDriver driver;
	private Actions actions;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);
	}
	
	public void clickOnElement(WebElement element, long durationInSeconds) {
		WebElement webElement = waitForElement(element, durationInSeconds);
		webElement.click();
	}
	
//	Ginagamit to if may pare-parehong element tapos gusto mo lang i-click yung specific TEXT. Usually sa headers.
//	Pag gagamitin to. Dapat pag nag declare ng FindBy sa Page Class, dapat naka List<WebElement>.
//	Example ng pag declare ng Find By:
//	FindBy(css = "span.rtsTxt")
//	public List<WebElement> headerList 
//	Example sa pag create naman ng Method:
//	public void clickHeader(String text) {
//		elementUtils.clickOnText(headerList, text, explicitWaitTime);
//	}
	public void clickOnText(List<WebElement> elements, String text, long durationInSeconds) {
		List<WebElement> visibleElements = waitForElements(elements, durationInSeconds);
		for (WebElement webElement : visibleElements) {
	        if(webElement.getText().equals(text)) {
	            webElement.click();
	            break; }	
		}
	}

	public void typeTextIntoElement(WebElement element, String textToBeTyped, long durationInSeconds) {
		WebElement webElement = waitForElement(element, durationInSeconds);
		webElement.clear();
		webElement.sendKeys(textToBeTyped);
	}
	
	//FOR textfield na ayaw gumana yung typeTextIntoElement
	public void clickAndTypeTextIntoElement(WebElement element, String textToBeTyped, long durationInSeconds) {
		WebElement webElement = waitForElement(element, durationInSeconds);
		webElement.click();
		webElement.sendKeys(textToBeTyped);
	}

	public void selectOptionInDropdown(WebElement element, String dropDownOption, long durationInSeconds) {
		WebElement webElement = waitForElement(element, durationInSeconds);
		Select select = new Select(webElement);
		select.selectByVisibleText(dropDownOption);
	}
	
	//FOR DROPDOWN na hindi gumagana yung select function. Need i manual Click and Click Down Arrow
	public void clickOnElementAndPressDownAction(WebElement element, long durationInSeconds) {
		WebElement webElement = waitForElement(element, durationInSeconds);
		webElement.click();
		actions.moveToElement(webElement).sendKeys(Keys.ARROW_DOWN).perform();
		actions.moveToElement(webElement).sendKeys(Keys.TAB).perform();	
	}

	public void acceptAlert(long durationInSeconds) {
		waitForAlert(durationInSeconds).accept();
	}

	public void dismissAlert(long durationInSeconds) {
		waitForAlert(durationInSeconds).dismiss();
	}

	public void mouseHoverAndClick(WebElement element, long durationInSeconds) {
		WebElement webElement = waitForVisibilityOfElement(element, durationInSeconds);
		Actions actions = new Actions(driver);
		actions.moveToElement(webElement).click().build().perform();
	}
	
	public void mouseHover(WebElement element, long durationInSeconds) {
		WebElement webElement = waitForVisibilityOfElement(element, durationInSeconds);
		Actions actions = new Actions(driver);
		actions.moveToElement(webElement).build().perform();
	}
	
	public void javaScriptScroll() throws Exception {
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);
	}

	public void javaScriptClick(WebElement element, long durationInSeconds) {
		WebElement webElement = waitForVisibilityOfElement(element, durationInSeconds);
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("arguments[0].click();", webElement);
	}

	public void javaScriptType(WebElement element, long durationInSeconds, String textToBeTyped) {
		WebElement webElement = waitForVisibilityOfElement(element, durationInSeconds);
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("arguments[0].value='" + textToBeTyped + "'", webElement);
	}

	public String getTextFromElement(WebElement element, long durationInSeconds) {
		WebElement webElement = waitForElement(element, durationInSeconds);
		return webElement.getText();
	}
	
	public boolean displayStatusOfText(WebElement element, String text, long durationInSeconds) {
		try {
			WebElement webElement = waitForElement(element, durationInSeconds);
			return webElement.getText().contains(text);
		} 
		catch (Throwable e) {
			return false;
		}
	}

	public boolean displayStatusOfElement(WebElement element, long durationInSeconds) {
		try {
			WebElement webElement = waitForVisibilityOfElement(element, durationInSeconds);
			return webElement.isDisplayed();
		} 
		catch (Throwable e) {
			return false;
		}
	}
	
	public boolean isFieldEmpty(WebElement element, long durationInSeconds) {
		WebElement webElement = waitForVisibilityOfElement(element, durationInSeconds);
		String value = webElement.getAttribute("value");
		return (value == null || value.trim().isEmpty());
	}
	
	public boolean isElementEnable(WebElement element, long durationInSeconds) {
		WebElement webElement = waitForVisibilityOfElement(element, durationInSeconds);
		
		if(webElement.isEnabled()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void switchToIframe(WebElement element, long durationInSeconds) {
		WebElement webElement = waitForElement(element, durationInSeconds);
		driver.switchTo().frame(webElement);
	}
	
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}
	
	///////////
	public WebElement waitForElement(WebElement element, long durationInSeconds) {

		WebElement webElement = null;

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
			webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
		} 
		catch (Throwable e) {
			e.printStackTrace();
		}
		return webElement;
	}
	
    public List<WebElement> waitForElements(List<WebElement> elements, long durationInSeconds) {
        List<WebElement> webElements = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
            webElements = wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return webElements;
    }

	public Alert waitForAlert(long durationInSeconds) {
		Alert alert = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
			alert = wait.until(ExpectedConditions.alertIsPresent());
		} 
		catch (Throwable e) {
			e.printStackTrace();
		}
		return alert;
	}

	public WebElement waitForVisibilityOfElement(WebElement element, long durationInSeconds) {
		WebElement webElement = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
			webElement = wait.until(ExpectedConditions.visibilityOf(element));
		} 
		catch (Throwable e) {
			e.printStackTrace();
		}
		return webElement;
	}
	
}
