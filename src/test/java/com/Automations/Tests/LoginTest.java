package com.Automations.Tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Automations.PageObjects.HomePage;
import com.Automations.PageObjects.LandingPage;
import com.Automations.TestComponents.BaseClass;


public class LoginTest extends BaseClass{
	String message = "Epic sadface: Username and password do not match any user in this service";
	@Test(dataProvider ="getValidData", priority=1)
	public void validloginTest(String email, String password) throws InterruptedException {
		launchApplication();
		LandingPage landingPage = new LandingPage(driver);
		HomePage page = landingPage.loginApplication(email, password);
		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		String actualUrl = page.checkHomePage(driver);
		//Here we are verifying 'signing page url' and 'homepage url' after logged in as ther is no login message on saucelab website after log in
		Assert.assertEquals(actualUrl, expectedUrl);	
	}
	
	@Test(dataProvider ="getInvalidData", priority =2)
	public void invalidLoginTest(String email, String password) throws InterruptedException {
		launchApplication();
		LandingPage landingPage = new LandingPage(driver);
		HomePage page = landingPage.loginApplication(email, password);
		if(message.equals(landingPage.getErrorMsg())) {
			System.out.println("User is not logged in due to wrong email or password");
		}

	}
	@DataProvider
	public Object[][] getValidData(){
		return new Object[][] {{"standard_user","secret_sauce"},{"visual_user","secret_sauce"}};
		
	}
	
	@DataProvider
	public Object[][] getInvalidData(){
		return new Object[][] {{"invalid_user","secret_sauce"}};
		
	}
}
