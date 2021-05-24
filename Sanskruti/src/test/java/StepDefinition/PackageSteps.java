package StepDefinition;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PackageSteps {
	public static WebDriver driver;
	@Given("User is on offers page")
	public void user_is_on_offers_page() throws Exception {
		String driverPath = System.getProperty("user.dir") +"\\Driver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get("https://www.yatra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	    driver.findElement(By.linkText("Offers")).click();
	    Thread.sleep(2000);
	}
	@When("Holiday tab is clicked")
	public void holiday_tab_is_clicked() throws Exception {
	   driver.findElement(By.linkText("Holidays")).click();
	   Thread.sleep(2000);
	}

	@Then("Destinations are Displayed")
	public void destinations_are_Displayed() {
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
	    driver.quit();
	}

}
