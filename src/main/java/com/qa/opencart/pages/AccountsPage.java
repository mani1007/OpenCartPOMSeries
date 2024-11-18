package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1. Private By Locators
	private By logoutLink = By.linkText("Logout");
	private By myAccountLink = By.linkText("My Account");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	// 2. Public Page Class Const...
	public AccountsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	// 3. Public Page Actions/Method
	public String getAccountsPageTitle()
	{
		String title = eleUtil.WaitUntilTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, 5);
		//System.out.println("Accounts Page Title: " + title);
		Log.info("Accounts Page Title: " + title);
		return title;
	}
	
	public String getAccountsPageURL()
	{
		String url = eleUtil.WaitUntilURLContains(AppConstants.ACC_PAGE_URL_FRACTION, 5);
		//System.out.println("Accounts Page URL: " + url);
		Log.info("Accounts Page URL: " + url);
		return url;
	}
	
	public boolean isLogoutLinkExist()
	{
		return eleUtil.isElementDisplayed(logoutLink);
	}
	
	public boolean isMyAccountLinkExist()
	{
		return eleUtil.isElementDisplayed(myAccountLink);
	}
	
	public List<String> getAccountsPageHeadersList()
	{
		List<WebElement> headersElementList = eleUtil.getElements(headers);
		List<String> headersList = new ArrayList<String>();
		for(WebElement e:headersElementList)
		{
			String header = e.getText();
			headersList.add(header);			
		}
		return headersList;
	}
	
	public SearchResultsPage doSearch(String searchKey)
	{
		//System.out.println("Search Key: " + searchKey);
		Log.info("Search Key: " + searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
	
}
