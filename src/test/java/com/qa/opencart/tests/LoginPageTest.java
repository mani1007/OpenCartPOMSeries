package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.FrameworkError;

public class LoginPageTest extends BaseTest {
	
	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, FrameworkError.TITLE_NOT_FOUND);
	}	
	
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), FrameworkError.URL_NOT_FOUND);
	}
	
	
	@Test(priority = 3)
	public void forgotPwdLinkTest()
	{
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test(priority = 4)
	public void loginTest()
	{
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccountsPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, FrameworkError.ELEMENT_NOT_FOUND);
	}
	

}
