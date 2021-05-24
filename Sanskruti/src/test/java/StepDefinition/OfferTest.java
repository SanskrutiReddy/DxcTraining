package StepDefinition;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class OfferTest {
	public static ExtentHtmlReporter reporter=new ExtentHtmlReporter("./ExtentReports/rep2.html");
	public static ExtentReports extent=new ExtentReports();
	public static WebDriver driver;
	static String expectedResult;
	static String actualResult;
	
	@BeforeMethod
	public static void OpenBrowser()
	{
		System.out.println("Open");
		extent.attachReporter(reporter);
		ExtentTest logger1=extent.createTest("OpenURL");
		logger1.log(Status.INFO, "Opening the url");
		try
		{
			String driverPath = System.getProperty("user.dir") +"\\Driver\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			driver.get("https://www.yatra.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			logger1.log(Status.PASS, "Opening passed");
		}
		catch(Exception e)
		{
			logger1.log(Status.FAIL, "Opening failed");
		}
		extent.flush();
	}
	@Test
	public static void navigation() throws Exception
	{
		//String screenshot;
		System.out.println("Offers");
		extent.attachReporter(reporter);
		ExtentTest logger2=extent.createTest("Offer Link");
		logger2.log(Status.INFO, "Navigating to offers");
		try
		{
			 driver.findElement(By.linkText("Offers")).click();
			 Thread.sleep(2000);
			 System.out.println("Offers opened");
			logger2.log(Status.PASS, "Offers link opened");
		}
		catch(Exception e)
		{
			logger2.log(Status.FAIL, "Offers link open failed");
		}
		extent.flush();
	}
	@AfterMethod
	public static void closeBrowser()
	{
		System.out.println("Close");
		extent.attachReporter(reporter);
		ExtentTest logger3=extent.createTest("Closing");
		logger3.log(Status.INFO, "Closing the browser");
		try
		{
			String expectedResult ="Domestic Flights Offers | Deals on Domestic Flight Booking | Yatra.com";
			  String actualResult = (driver.getTitle());
			  try {
					Assert.assertTrue((expectedResult.equals(actualResult)) ? true : false);
					System.out.println("Title Pass");
					} catch (AssertionError e) 
					{
					System.out.println(e);
					System.out.println("Fail page title");
					}
			  String expectedResult1 ="Great Offers & Amazing Deals";
			  String actualResult1 = driver.findElement(By.xpath("//*[@id=\"collapsibleSection\"]/section[1]/div[1]/div/div/h2")).getText();
			  try {
					Assert.assertTrue((expectedResult1.equals(actualResult1)) ? true : false);
					System.out.println("Heading Pass");
					} catch (AssertionError e) 
					{
					System.out.println(e);
					System.out.println("Fail heading");
					}
			  driver.close();
		}
		catch(Exception e)
		{
			logger3.log(Status.FAIL, "Closing failed");
		}
		extent.flush();
	}
}
