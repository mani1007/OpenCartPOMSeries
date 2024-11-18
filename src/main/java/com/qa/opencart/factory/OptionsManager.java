package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.opencart.logger.Log;

public class OptionsManager {
	
	private Properties prop;
	
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	public OptionsManager(Properties prop)
	{
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions()
	{
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			//System.out.println("Running Chrome in headless Mode");
			Log.info("Running Chrome in headless Mode");
			co.addArguments("--headless");
		}			
		
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
		{
			//System.out.println("Running Chrome in Incognito Mode");
			Log.info("Running Chrome in Incognito Mode");
			co.addArguments("--incognito");
		}			
		
		return co;		
	}
	
	public FirefoxOptions getFirefoxOptions()
	{
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			//System.out.println("Running Firefox in headless Mode");
			Log.info("Running Firefox in headless Mode");
			fo.addArguments("--headless");
		}
			
		
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
		{
			//System.out.println("Running Firefox in Incognito Mode");
			Log.info("Running Firefox in Incognito Mode");
			fo.addArguments("--inprivate");
		}			
		
		return fo;		
	}
	
	public EdgeOptions getEdgeOptions()
	{
		eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			//System.out.println("Running Edge in headless Mode");
			Log.info("Running Edge in headless Mode");
			eo.addArguments("--headless");
		}			
		
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
		{
			//System.out.println("Running Edge in Incognito Mode");
			Log.info("Running Edge in Incognito Mode");
			eo.addArguments("--incognito");
		}			
		
		return eo;		
	}

}
