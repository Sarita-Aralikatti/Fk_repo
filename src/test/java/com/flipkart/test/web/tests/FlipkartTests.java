package com.flipkart.test.web.tests;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class FlipkartTests {

	@Before
	public void chromeDriver(){
		System.setProperty("webdriver.chrome.driver","D:\\Flipkart_Web_Project\\flipkart-web-functional\\src\\test\\resources\\bin\\x32\\win\\chromedriver.exe");
	}
	
	@Test
	public void test_to_close_flipkart_login_modal(){
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.flipkart.com"); 
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		try { 
			if(driver.findElement(By.xpath("//input[@type='password']")).isDisplayed()) { 
				driver.findElement(By.xpath("(//button)[2]")).click(); 
			} 
		}catch(Throwable t) { 
			System.out.println("Flipkart Lightbox not displayed"); 
		} 
		driver.close();
	}

	@Test
	public void test_to_verify_add_to_cart_functionality(){

		WebDriver driver = new ChromeDriver();
		driver.get("http://www.flipkart.com"); 
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 

		//	Login to flipkart 
		driver.findElement(By.className("_2zrpKA")).sendKeys("aralikattisarita@gmail.com");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input")).sendKeys("flipkart@031");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[3]/button")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div._2aUbKa")).isDisplayed());
		driver.manage().window().maximize(); 
		driver.findElement(By.name("q")).click();
		driver.findElement(By.name("q")).sendKeys("Acer Aspire 3 Pentium Quad Core"); 
		driver.findElement(By.xpath("//button[@type='submit']")).click(); 
		driver.findElement(By.xpath("(//div[contains(text(),'Laptop')])[1]")).click(); 
		Set<String> windows = driver.getWindowHandles(); 
		Iterator<String> itr = windows.iterator(); 
		String mainWindow = itr.next(); 
		String childWindow = itr.next(); 
		driver.switchTo().window(childWindow); 
		if(driver.getTitle().contains("Acer Aspire")) { 
			driver.findElement(By.xpath("(//button)[2]")).click(); 
		} 
		System.out.println("Item added to bag sucessfully"); 
		driver.close();
	}

	@Test
	public void test_to_do_all_action_items_flipkart_pages() throws InterruptedException {

		WebDriver webDriver = new ChromeDriver();

		//close login modal
		webDriver.get("https://www.flipkart.com");
		try { 
			if(webDriver.findElement(By.xpath("//input[@type='password']")).isDisplayed()) { 

				webDriver.findElement(By.xpath("(//button)[2]")).click(); 
			} 
		}catch(Throwable t) { 
			System.out.println("Lightbox not displayed"); 
		} 
		webDriver.manage().window().maximize(); 
		
		// click on mouse hover Electronics
		Actions a = new Actions(webDriver);
		a.moveToElement(webDriver.findElement(By.xpath("//span[text()='Electronics']"))).build().perform();
		webDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 

		// click on mobile
		webDriver.findElement(By.xpath("//a[text()='Mobiles']/span")).click();

		// click on mobile brands
		webDriver.findElement(By.cssSelector("img._1N-ymU")).click();		
		webDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		Assert.assertTrue(webDriver.findElement(By.cssSelector("h1._2yAnYN")).isDisplayed());

		// Select price range
		WebElement ele = webDriver.findElement(By.cssSelector("select.fPjUPw"));
		Select range = new Select(ele);
		range.selectByValue("20000");
		webDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		Assert.assertTrue("selected range is not 20000", webDriver.findElement(By.cssSelector("div._3UZZGt")).getText().contains("20000"));
		webDriver.close();
	}

	@Test
	public void test_to_select_random_product_adddTocart_functionalities(){

		WebDriver driver = new ChromeDriver();
		driver.get("http://www.flipkart.com");

		//Login to Flipkart
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		driver.findElement(By.className("_2zrpKA")).sendKeys("aralikattisarita@gmail.com");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input")).sendKeys("flipkart@031");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[3]/button")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div._2aUbKa")).isDisplayed());
		driver.manage().window().maximize(); 

		//site search
		driver.findElement(By.name("q")).click();
		driver.findElement(By.name("q")).sendKeys("Camera"); 
		driver.findElement(By.xpath("//button[@type='submit']")).click(); 

		//get random product from list
		List<WebElement> product_lists = driver.findElements(By.className("_1UoZlX"));
		System.out.println(product_lists.size());
		Random rand = new Random();
		WebElement random_Product = product_lists.get(rand.nextInt(product_lists.size()));
		random_Product.click();

		//switch next tab for to add to cart
		Set<String> get_All_Windows = driver.getWindowHandles(); 
		Iterator<String> itr = get_All_Windows.iterator(); 
		String main_Window = itr.next(); 
		String child_Window = itr.next(); 
		driver.switchTo().window(child_Window); 
		Assert.assertTrue(driver.getPageSource().contains("ADD TO CART"));

		//product added to cart successfully
		driver.findElement(By.xpath("(//button)[2]")).click(); 
		System.out.println("Item added to cart sucessfully"); 

		//remove product from cart
		driver.findElement(By.xpath("//div[@class='_3IO2ev _2K02N8 _2x63a8']//div[2]")).click();
		driver.findElement(By.xpath("	//div[@class='gdUKd9 _3Z4XMp _2nQDKB']")).click();
		driver.quit();

	} 

}