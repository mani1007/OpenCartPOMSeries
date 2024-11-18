package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void infoPagesetup()
	{
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getDataProductHeaderTest(){
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},
			{"macbook","MacBook"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(dataProvider = "getDataProductHeaderTest")
	public void productHeaderTest(String searchKey, String productName)
	{
		searchResultsPage = accountsPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(), productName);
	}
	
	@DataProvider
	public Object[][] getProductImageCountTestData(){
		return new Object[][] {
			{"macbook","MacBook Pro","4"},
			{"macbook","MacBook Air","4"},
			{"macbook","MacBook","5"},
			{"imac","iMac","3"},
			{"samsung","Samsung SyncMaster 941BW","1"},
			{"samsung","Samsung Galaxy Tab 10.1","7"}
		};
	}
	
	@DataProvider
	public Object[][] getProductImageCountTestDataFromExcel(){
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
	}
	
	@DataProvider
	public Object[][] getProductImageCountTestDataFromCSV(){
		return CSVUtil.csvData("data");
	}
	
	@Test(dataProvider = "getProductImageCountTestDataFromCSV")
	public void productImagesCountTest(String searchKey, String productName, String imagesCount)
	{
		searchResultsPage = accountsPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Integer.parseInt(imagesCount));
	}
	
	@Test
	public void productInfoTest()
	{
		searchResultsPage = accountsPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		 Map<String, String> productActualDetailsMap = productInfoPage.getProductDetailsMap();
		 softAssert.assertEquals(productActualDetailsMap.get("Brand"), "Apple");
		 softAssert.assertEquals(productActualDetailsMap.get("Product Code"), "Product 18");
		 softAssert.assertEquals(productActualDetailsMap.get("Reward Points"), "800");
		 softAssert.assertEquals(productActualDetailsMap.get("Availability"), "In Stock");
		 softAssert.assertEquals(productActualDetailsMap.get("productprice"), "$2,000.00");
		 softAssert.assertEquals(productActualDetailsMap.get("extaxprice"), "$2,000.00");
		 softAssert.assertAll();
		 
	}

}
