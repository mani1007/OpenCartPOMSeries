package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1. Private By Locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPWdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	// 2. Public Page Class Const...
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	// 3. Public Page Actions/Method
	public String getLoginPageTitle()
	{
		String title = eleUtil.WaitUntilTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.MEDIUM_TIME);
		//System.out.println("Login Page Title: " + title);
		Log.info("Login Page Title: " + title);
		return title;
	}
	
	public String getLoginPageURL()
	{
		String url = eleUtil.WaitUntilURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, TimeUtil.MEDIUM_TIME);
		//System.out.println("Login Page URL: " + url);
		Log.info("Login Page URL: " + url);
		return url;
	}
	
	public boolean isForgotPwdLinkExist()
	{
		return eleUtil.isElementDisplayed(forgotPWdLink);
	}
	
	public AccountsPage doLogin(String username, String pwd)
	{
		//System.out.println("Username: "+ username);
		//System.out.println("Password: "+ pwd);
		Log.info("Username: "+ username);
		Log.info("Password: "+ pwd);
		eleUtil.WaitUntilElementVisibility(emailId, TimeUtil.MEDIUM_TIME).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.WaitUntilElementVisibility(registerLink, TimeUtil.LONG_TIME).click();
		return new RegistrationPage(driver);
	}
	
	
}
