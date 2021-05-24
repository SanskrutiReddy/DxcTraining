package StepDefinition;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class PackageTest {
	public static ExtentHtmlReporter reporter=new ExtentHtmlReporter("./ExtentReports/rep3.html");
	public static ExtentReports extent=new ExtentReports();
	public static WebDriver driver;
	static String expectedResult;
	static String actualResult;
	
	@BeforeMethod
	public static void OpenBrowser()
	{
		System.out.println("Open");
		extent.attachReporter(reporter);
		ExtentTest logger1=extent.createTest("OpenOffers");
		logger1.log(Status.INFO, "Opening the offers");
		try
		{
			String driverPath = System.getProperty("user.dir") +"\\Driver\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			driver.get("https://www.yatra.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		    driver.findElement(By.linkText("Offers")).click();
		    Thread.sleep(2000);
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
		System.out.println("Holidays Page");
		extent.attachReporter(reporter);
		ExtentTest logger2=extent.createTest("Holidays");
		logger2.log(Status.INFO, "Opening holiday packages");
		try
		{
			 driver.findElement(By.linkText("Holidays")).click();
			 Thread.sleep(2000);
			//logger2.addScreenCaptureFromPath("C:\\Users\\sansk\\OneDrive\\Desktop\\Me.png");
			logger2.log(Status.PASS, "Holidays opened");
		}
		catch(AssertionError ae)
		{
			logger2.log(Status.FAIL, "Holidays open failed");
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
			System.out.println("Packages:");
			List<WebElement> packages = driver.findElements(By.className("offerMainTitle"));
			try
			{
		    for(int i=0;i<5;i++)
		    {
		    	WebElement p=packages.get(i);
		        System.out.println(p.getText());  
		    }
			}
			catch (Exception e) {
				System.out.println(e);
			}
			driver.close();
			logger3.log(Status.PASS, "Closing passed");
		}
		catch(Exception e)
		{
			logger3.log(Status.FAIL, "Closing failed");
		}
		extent.flush();
	}
}
