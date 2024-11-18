package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1. Private By Locators
	private By searchProducts = By.cssSelector("div.product-thumb");
	
	// 2. Public Page Class Const...
	public SearchResultsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getSearchProductCount()
	{
		return eleUtil.WaitUntilElementsVisibility(searchProducts, 10).size();
	}
	
	public ProductInfoPage selectProduct(String productName)
	{
		//System.out.println("Search Product Name: "+ productName);
		Log.info("Search Product Name: "+ productName);
		eleUtil.WaitUntilElementVisibility(By.linkText(productName), 10).click();
		return new ProductInfoPage(driver);
	}

}
