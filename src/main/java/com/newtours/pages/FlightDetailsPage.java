package com.newtours.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightDetailsPage {

	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(name = "passCount")
	private WebElement passanger;
	
	@FindBy(name = "findFlights")
	private WebElement submitBtn;
	
	
	public FlightDetailsPage (WebDriver driver){
		this.driver = driver;
		this.wait = new  WebDriverWait(driver , Duration.ofSeconds(30));
		PageFactory.initElements(driver,this);
	}
	
	public void selectPassanger(String noofPassanger) {
		this.wait.until(ExpectedConditions.elementToBeClickable(passanger));
		Select select = new Select(passanger);
		select.selectByValue(noofPassanger);
	}
	
	public void gotoFindFlightsPage() {
		this.submitBtn.click();
	}
	
}
