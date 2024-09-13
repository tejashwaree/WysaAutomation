package com.Automations.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Automations.AbstarctComponent.AbstarctComponent;

public class LandingPage extends AbstarctComponent {
	
	public WebDriver driver;
	public LandingPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="user-name")
	WebElement email;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="login-button")
	WebElement login;
	
	@FindBy(xpath="//button[@data-test='error-button']//*[name()='svg']")
	WebElement errorMessage;
	
	public HomePage loginApplication(String email1, String pasword) throws InterruptedException {
		email.sendKeys(email1);
		password.sendKeys(pasword);
		login.click();
		HomePage page = new HomePage(driver);
		return page;

	}
	
	public void goTo() {
		driver.get("https://www.saucedemo.com/");
	}
	
	public String getErrorMsg() {
		return errorMessage.getText();
	}
}
