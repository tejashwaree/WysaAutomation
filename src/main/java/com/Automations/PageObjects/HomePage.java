package com.Automations.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.Automations.AbstarctComponent.AbstarctComponent;

public class HomePage extends AbstarctComponent{
	public WebDriver driver;

	public HomePage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public  String checkHomePage(WebDriver driver) {
		String actualUrl = driver.getCurrentUrl();
		return actualUrl;
		
	}
}
