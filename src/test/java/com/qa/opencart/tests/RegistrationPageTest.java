package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtil;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void registerationsetup()
	{
		registrationPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getUserRegistrationTestData(){
		return new Object[][] {
				{"Sharan","Mani","9995554441","Selenium@123","yes"},
				{"Iniyan","Mani","9995554442","Selenium@456","no"},
				{"Anbu","Mani","9995554443","Selenium@789","yes"}
		};
	}
	
	
	@DataProvider
	public Object[][] getUserRegistrationTestDataFromExcel(){
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider = "getUserRegistrationTestDataFromExcel")
	public void userRegisterTest(String firstName, String lastName, String phone, String password, String subscribe) {
		Assert.assertTrue(registrationPage.registerUser(firstName, lastName, StringUtil.getRandomEmail(), phone, password, subscribe));
		
	}
	

}
