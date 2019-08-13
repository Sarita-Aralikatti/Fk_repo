package com.test.web.components;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

public class CrossBrowser {

	WebDriver driver;

	/**
	 * This function will execute before each Test tag in testng.xml
	 * @param browser
	 * @throws Exception
	 */

	@Before

	@Parameters(name = "Browser Type: {0}")
	public static Collection<?> data() {
	  return Arrays.asList(new Object[][] { { BrowserType.IE },
	    { BrowserType.FIREFOX }, { BrowserType.GOOGLECHROME }});
	}
	
	
	public void setup(String alist) throws Exception{
		//Check if parameter passed from TestNG is 'firefox'
		if(alist.equalsIgnoreCase("firefox")){
			//create firefox instance
			System.setProperty("webdriver.firefox.marionette", "D:\\Flipkart_Web_Project\\flipkart-web-functional\\src\\test\\resources\\bin\\x32\\win\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		//Check if parameter passed as 'chrome'
		else if(alist.equalsIgnoreCase("googlechrome")){
			//set path to chromedriver.exe
			System.setProperty("webdriver.chrome.driver","D:\\Flipkart_Web_Project\\flipkart-web-functional\\src\\test\\resources\\bin\\x32\\win\\chromedriver.exe");
			//create chrome instance
			driver = new ChromeDriver();
		}
		
		//Check if parameter passed as 'Edge'
		else if(alist.equalsIgnoreCase("Ie")){
			//set path to Edge.exe
			System.setProperty("webdriver.ie.driver","D:\\Flipkart_Web_Project\\flipkart-web-functional\\src\\test\\resources\\bin\\x32\\win\\IEDriver.exe");
			//create Edge instance
			driver = new EdgeDriver();
		}
		
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}


}
