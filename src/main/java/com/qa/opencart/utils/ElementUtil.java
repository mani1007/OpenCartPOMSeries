package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.ElementException;

public class ElementUtil {
	
private WebDriver driver;
	
	private final String DEFAULT_ELEMENT_TIMEOUT_MSG = "TimeOut - Element NOT Found";
	private final String DEFAULT_ALERT_TIMEOUT_MSG = "TimeOut - Alert NOT Found";
	
	public ElementUtil(WebDriver driver)
	{
		this.driver = driver;
	}
	
	private void nullBlankCheck(String value)
	{
		if(value == null || value.length() == 0)
			throw new ElementException("Visible Text Cannot be Null");
	}
	
	public By getBy(String locatorType, String locatorValue)
	{
		By locator = null;
		
		switch(locatorType.toLowerCase().trim())
		{
		
		case "id":
			locator = By.id(locatorValue);
			break;
			
		case "name":
			locator = By.name(locatorValue);
			break;
			
		case "class":
			locator = By.className(locatorValue);
			break;
			
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
			
		case "css":
			locator = By.cssSelector(locatorValue);
			break;
			
		case "link":
			locator = By.linkText(locatorValue);
			break;
			
		case "partiallink":
			locator = By.partialLinkText(locatorValue);
			break;
			
		case "tagname":
			locator = By.tagName(locatorValue);
			break;
			
			default:
				break;
		
		}
		return locator;
	}
	
	public WebElement getElement(By locator)
	{
		WebElement element;
		try
		{
			element = driver.findElement(locator);
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Element Not Present on the page");
			e.printStackTrace();
			return null;
		}
		
		return element;
	}
	
	public WebElement getElement(String locatorType, String locatorValue)
	{
		By locator = getBy(locatorType, locatorValue);
		return driver.findElement(locator);
	}	
	
	
	public void doSendKeys(By locator, String input)
	{
		nullBlankCheck(input);
		getElement(locator).clear();
		getElement(locator).sendKeys(input);
	}
	
	public void doSendKeys(By locator, String input, int timeout)
	{
		nullBlankCheck(input);
		WaitUntilElementVisibility(locator, timeout).sendKeys(input);
	}
	
	public void doSendKeys(String locatorType, String locatorValue, String input)
	{
		getElement(locatorType, locatorValue).sendKeys(input);
	}
	
	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	public void doClick(By locator, int timeout)
	{
		WaitUntilElementVisibility(locator, timeout).click();
	}
	
	public void doClick(String locatorType, String locatorValue)
	{
		getElement(locatorType, locatorValue).click();
	}
	
	public String doGetElementText(By locator)
	{
		return getElement(locator).getText();
	}
	
	public String doGetElementText(String locatorType, String locatorValue)
	{
		return getElement(locatorType, locatorValue).getText();
	}
	
	public String doElementGetAttribute(By locator, String attrName)
	{
		return getElement(locator).getAttribute(attrName);
	}
	
	public boolean isElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public boolean isElementExist(By locator)
	{
		if(getElements(locator).size()==1)
		{
			return true;
		}
		return false;
	}
	
	public boolean multipleElementExist(By locator)
	{
		if(getElements(locator).size() > 0)
		{
			return true;
		}
		return false;
	}
	
	public List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}
	
	public int getElementsCount(By locator)
	{
		return getElements(locator).size();
	}
	
	public ArrayList<String> getElementsText(By locator)
	{
		List<WebElement> eleList = getElements(locator);
		ArrayList<String> eleText = new ArrayList<String>();
		
		for(WebElement e : eleList)
		{
			String text = e.getText();
			if(text.length()!=0)
			{
				eleText.add(text);
			}
		}
		return eleText;
	}
	
	
	public ArrayList<String> getElementsAttribute(By locator, String attrName)
	{
		List<WebElement> eleList = getElements(locator);
		ArrayList<String> eleAttr = new ArrayList<String>();
		
		for(WebElement e : eleList)
		{
			String text = e.getAttribute(attrName);
			if(text.length()!=0)
			{
				eleAttr.add(text);
			}
		}
		return eleAttr;
	}
	
	
	//////////////////////Select based Methods/////////////////////////////
	
	
	public void doSelectbyIndex(By locator, int index)
	{
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doSelectbyVisibleText(By locator, String visibleText)
	{
		nullBlankCheck(visibleText);
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}
	
	public void doSelectbyValue(By locator, String value)
	{
		nullBlankCheck(value);
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}
	
	public List<String> getDropDownOptionsTextList(By locator)
	{
		List<WebElement> elements = getDropDownOptionsList(locator);
		List<String> textList = new ArrayList<String>();
		
		for(WebElement e : elements)
		{
			textList.add(e.getText());
		}
		return textList;		
	}
	
	public List<WebElement> getDropDownOptionsList(By locator)
	{
		Select select = new Select(getElement(locator));
		return select.getOptions();
	}
	
	public int getDropDownOptionsCount(By locator)
	{
		return getDropDownOptionsList(locator).size();
	}
	
	public void doSelectDropDownValue(By locator, String value)
	{
		nullBlankCheck(value);
		List<WebElement> list = getDropDownOptionsList(locator);
		
		for(WebElement e : list)
		{
			String text = e.getText();
			if(text.equals(value))
			{
				e.click();
				break;
			}
		}		
	}
	
	public void printDropDownValues(By locator)
	{
		List<String> optionList = getDropDownOptionsTextList(locator);
		for(String e : optionList)
		{
			System.out.println(e);
		}		
	}
	
	public void doSelectValueFromDropDown(By locator, String value)
	{
		List<WebElement> options = getElements(locator);
		
		for(WebElement e : options)
		{
			String text = e.getText();
			if(text.equals(value))
			{
				e.click();
				break;
			}
		}
	}
	
	public void doSearchAndSelectOption(By searchLocator, String searchKey, By suggLocator, String findValue) throws InterruptedException
	{
		doSendKeys(searchLocator, searchKey);
        Thread.sleep(3000);
        
        List<WebElement> suggOptions = getElements(suggLocator);
        System.out.println(suggOptions.size());
        
        for(WebElement e : suggOptions)
        {
        	String text = e.getText();
        	if(text.contains(findValue))
        		e.click();
        }
	}
	
	//////////////////////Actions Methods/////////////////////////////
	
	public void handleTwoLevelMenuSubMenu(By parentMenuLocator, By subMenuLocator) throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.moveToElement(getElement(parentMenuLocator)).perform();
		Thread.sleep(3000);
		
		doClick(subMenuLocator);	
	}
	
	public void handleThreeLevelMenuSubMenu(By level1Locator, By level2Locator, By level3Locator) throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.moveToElement(getElement(level1Locator)).perform();
		Thread.sleep(1500);
		action.moveToElement(getElement(level2Locator)).perform();
		Thread.sleep(1500);		
		doClick(level3Locator);	
	}
	
	public void doActionsSendKeys(By locator, String value)
	{
		Actions actions = new Actions(driver);
		actions.sendKeys(getElement(locator), value).perform();
	}
	
	public void doActionsClick(By locator)
	{
		Actions actions = new Actions(driver);
		actions.click(getElement(locator)).perform();		
	}
	
	
	//***************************** Wait Utils ******************************//
	
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public void ClickWhenReady(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	/**
	   * An expectation for checking that an element is present on the DOM of a page. This does not
	   * necessarily mean that the element is visible.
	   *
	   * @param locator used to find the element
	   * @return the WebElement once it is located
	   */
	public WebElement WaitUntilElementPresence(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement WaitUntilElementPresenceWithFluentWait(By locator, int timeout, int pollingTime)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(ElementNotInteractableException.class)
				.withMessage(DEFAULT_ELEMENT_TIMEOUT_MSG);
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	   * An expectation for checking that an element is present on the DOM of a page and visible.
	   * Visibility means that the element is not only displayed but also has a height and width that is
	   * greater than 0.
	   *
	   * @param locator used to find the element
	   * @return the WebElement once it is located and visible
	   */
	
	public WebElement WaitUntilElementVisibility(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> WaitUntilElementsPresence(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public List<WebElement> WaitUntilElementsPresenceWithFluentWait(By locator, int timeout, int pollingTime)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(ElementNotInteractableException.class)
				.withMessage(DEFAULT_ELEMENT_TIMEOUT_MSG);
		
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	/**
	 * An expectation for checking that all elements present on the web page that match the locator are visible. 
	 * Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.

	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> WaitUntilElementsVisibility(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
		
	public String WaitUntilTitleContains(String titleFraction, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			if(wait.until(ExpectedConditions.titleContains(titleFraction)))
			{
				return driver.getTitle();
			}
		}
		catch(Exception e)
		{
			System.out.println("Title Fraction could not be captured in "+ timeout + " seconds of timeout");
		}
		
		return null;		
	}
	
	public String WaitUntilTitleIs(String title, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			if(wait.until(ExpectedConditions.titleIs(title)))
			{
				return driver.getTitle();
			}
		}
		catch(Exception e)
		{
			System.out.println("Title could not be captured in "+ timeout + " seconds of timeout");
		}
		
		return null;		
	}
	
	public String WaitUntilURLContains(String urlFraction, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			if(wait.until(ExpectedConditions.urlContains(urlFraction)))
			{
				return driver.getCurrentUrl();
			}
		}
		catch(Exception e)
		{
			System.out.println("URL Fraction could not be captured in "+ timeout + " seconds of timeout");
		}
		
		return null;		
	}
	
	public String WaitUntilURLIs(String url, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			if(wait.until(ExpectedConditions.urlToBe(url)))
			{
				return driver.getCurrentUrl();
			}
		}
		catch(Exception e)
		{
			System.out.println("URL could not be captured in "+ timeout + " seconds of timeout");
		}
		
		return null;		
	}
	
	public Alert waitForJSAlert(int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public Alert waitForJSAlertWithFluentWait(int timeout, int pollingTime)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoAlertPresentException.class)
				.withMessage(DEFAULT_ALERT_TIMEOUT_MSG);
		
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public String getJSAlertText(int timeout)
	{
		return waitForJSAlert(timeout).getText();
	}
	
	public void acceptAlert(int timeout)
	{
		waitForJSAlert(timeout).accept();
	}
	
	public void dismissAlert(int timeout)
	{
		waitForJSAlert(timeout).dismiss();
	}
	
	public void alertSendKeys(int timeout, String input)
	{
		waitForJSAlert(timeout).sendKeys(input);
	}
	
	public boolean waitUntilWindow(int totalWindows, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.numberOfWindowsToBe(totalWindows));
	}
	
	public boolean isPageLoaded(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"))
				.toString();//"true"(Javascript)
		return Boolean.parseBoolean(flag);//true(Java)
	}
	
	//***************************** Custom Wait Utils ******************************//
	
	public WebElement retryingElement(By locator, int timeout)
	{
		WebElement element = null;
		int attempt = 0;
				
		while(attempt < timeout)
		{
			try 
			{
				element=getElement(locator);
				System.out.println("Element Found at attempt number: " + (attempt+1));
				break;
			}
			catch(NoSuchElementException e)
			{
				System.out.println("element is not found in attempt number: " + (attempt+1));
				TimeUtil.defaultTime();				
			}
			
			attempt++;
		}
		
		if(element == null)
		{
			System.out.println("Element NOT Found in " + timeout + " attempts with 500 milliseconds of interval");
			throw new ElementException("No Such Element Found");
		}
		
		return element;
	}
	
	public WebElement retryingElement(By locator, int timeout, int interval)
	{
		WebElement element = null;
		int attempt = 0;
				
		while(attempt < timeout)
		{
			try 
			{
				element=getElement(locator);
				System.out.println("Element Found at attempt number: " + (attempt+1));
				break;
			}
			catch(NoSuchElementException e)
			{
				System.out.println("element is not found in attempt number: " + (attempt+1));
				TimeUtil.myCustomTime(interval);				
			}
			
			attempt++;
		}
		
		if(element == null)
		{
			System.out.println("Element NOT Found in " + timeout + " attempts with " + (interval * 1000) + " milliseconds of interval");
			throw new ElementException("No Such Element Found");
		}
		
		return element;
	}

}
