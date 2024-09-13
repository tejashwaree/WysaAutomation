package com.Automations.TestComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import com.Automations.PageObjects.LandingPage;


public class BaseClass {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializedriver() {

		driver = new ChromeDriver();;
		driver.manage().window().maximize();
		// implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public void launchApplication() {
		driver = initializedriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
