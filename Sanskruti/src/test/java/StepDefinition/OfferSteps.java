package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OfferSteps {
	public static WebDriver driver;
	@Given("User is on yatras page")
	public void user_is_on_yatras_page() {
		String driverPath = System.getProperty("user.dir") +"\\Driver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get("https://www.yatra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	}

	@When("Offer link is clicked")
	public void offer_link_is_clicked() throws Exception{
	    driver.findElement(By.linkText("Offers")).click();
	    Thread.sleep(2000);
	}

	@Then("New Page is Displayed")
	public void new_Page_is_Displayed() {
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
	   driver.quit();
	}



}
