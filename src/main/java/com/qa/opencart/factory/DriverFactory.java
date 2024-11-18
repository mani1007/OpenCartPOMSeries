package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.errors.FrameworkError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop)
	{
		String browserName = prop.getProperty("browser");
		Log.info("Browser Name is: " + browserName);
		String url = prop.getProperty("url");
		
		optionsManager = new OptionsManager(prop);
		
		switch(browserName.toLowerCase().trim())
		{
			case "chrome":
				//driver = new ChromeDriver(optionsManager.getChromeOptions());
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
				break;
				
			case "firefox":
				//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
				break;
				
			case "edge":
				//driver = new EdgeDriver(optionsManager.getEdgeOptions());
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
				break;
				
			default:
				//System.out.println("Invalid Browser name: " + browserName + ", Please pass correct browser name");
				Log.error("Invalid Browser name: " + browserName + ", Please pass correct browser name");
				throw new BrowserException("BROWSER NOT FOUND");
				
		}
				
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(url);
		
		return getDriver();
	}
	
	public static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	public Properties initProp()
	{
		//envName = qa,dev,stage,uat,prod
		//mvn clean install -Denv="qa"
		
		FileInputStream fis=null;
		prop = new Properties();
		
		
		String envName = System.getProperty("env");
		//System.out.println("Running Test on Evn Name: "+ envName);
		Log.info("Running Test on Evn Name: "+ envName);
		
		try {
			if(envName == null)
			{
				//System.out.println("Test Environment is null. Hence running test on QA enviromnet");
				Log.info("Test Environment is null. Hence running test on QA enviromnet");
				fis = new FileInputStream("./src/test/resources/config/config.qa.properties");
			}
			else
			{
				switch(envName.toLowerCase().trim())
				{
					case "qa":
						fis = new FileInputStream("./src/test/resources/config/config.qa.properties");
						break;
					case "dev":
						fis = new FileInputStream("./src/test/resources/config/config.dev.properties");
						break;
					case "stage":
						fis = new FileInputStream("./src/test/resources/config/config.stage.properties");
						break;
					case "uat":
						fis = new FileInputStream("./src/test/resources/config/config.uat.properties");
						break;
					case "prod":
						fis = new FileInputStream("./src/test/resources/config/config.properties");
						break;
						
					default:
						//System.out.println("Given Environment "+envName+" is not Valid. Please Pass the right environment");
						Log.warn("Given Environment "+envName+" is not Valid. Please Pass the right environment");
						throw new FrameworkException(FrameworkError.ENV_NAME_NOT_FOUND +" : "+envName);
				}
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	
	/**
	 * take screenshot
	 */
	
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
		
}
