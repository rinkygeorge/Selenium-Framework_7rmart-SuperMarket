package com.obsqura.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.obsqura.pages.AddAdminUser;
import com.obsqura.pages.CreateDeleteEditExpense;
import com.obsqura.pages.HomePage;
import com.obsqura.pages.LoginPage;
import com.obsqura.utilities.TestProperties;

public class BaseTest {

	public WebDriver driver;
	Properties prop;
	LoginPage lp;
	HomePage hp;
	CreateDeleteEditExpense cdp;
	AddAdminUser aau;

	@BeforeTest
	public void initializeDriver() throws IOException {


		//calling properties class
		prop = TestProperties.getProperties();
		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("URL");

		if (browserName.equalsIgnoreCase("chrome")) {
		 driver = new ChromeDriver();
			
		}
		else if (browserName.equalsIgnoreCase("Edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		initializePages();

	}
	
	public void initializePages() {
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		cdp = new CreateDeleteEditExpense(driver);
		aau = new AddAdminUser(driver);
	}
	
	public String getScreenshot(String testcaseName,  WebDriver driver) throws IOException {
		TakesScreenshot scrshot=(TakesScreenshot)driver;
		File source = scrshot.getScreenshotAs(OutputType.FILE);
		File path= new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(source, path);
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
	}


	
	  @AfterTest public void tearDown() {
		  
		  driver.quit();
	  
	  }
	 
	 

}
