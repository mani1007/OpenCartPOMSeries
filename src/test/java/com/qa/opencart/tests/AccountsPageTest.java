package com.qa.opencart.tests;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accsetup()
	{
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test
	public void accountsPageTitleTest()
	{
		String accountsTitle = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(accountsTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}
		
	@Test
	public void accountsPageURLTest()
	{
		String accountsURL = accountsPage.getAccountsPageURL();
		Assert.assertTrue(accountsURL.contains(AppConstants.ACC_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoutLinkExistTest()
	{
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}
	
	@Test
	public void isMyAccountLinkExistTest()
	{
		Assert.assertTrue(accountsPage.isMyAccountLinkExist());
	}
	
	@Test
	public void accountsPageHeadersTest()
	{
		List<String> headersList = accountsPage.getAccountsPageHeadersList();
		//System.out.println(headersList);
		Log.info("List of Headers: " + headersList);
	}
	
	@Test
	public void searchTest()
	{
		accountsPage.doSearch("macbook");
	}

}
