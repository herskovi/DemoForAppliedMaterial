package com.amat.webapps;

import javax.validation.constraints.AssertTrue;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {

	private String baseUrl;
	private WebDriver driver;

	  //private ScreenshotHelper screenshotHelper;
	  
	  @Test
	  public void openBrowser() 
	  {
		System.out.println("Openning a Browser");
	    baseUrl = System.getProperty("webdriver.base.url");
	   // ChromeDriverManager.getInstance().setup();

	    //driver = new FirefoxDriver();
	    //driver = new ChromeDriver();
	    //driver.get(baseUrl);
	    //screenshotHelper = new ScreenshotHelper();
	    
	  }
	@Before
	public void beforeTest()
	{
		System.out.println("Running before test");
	}


	@Test
	public void junitTest()
	{
		System.out.println("Running Junit test");
	}


	@Test
	public void secondJunitTest()
	{
		System.out.println("Running second Junit test");
	}

	
	
}
