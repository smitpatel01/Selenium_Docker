package com.newtours.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.base.test.Basetest;
import com.newtours.pages.FindFlightPage;
import com.newtours.pages.FlightConfirmationPage;
import com.newtours.pages.FlightDetailsPage;
import com.newtours.pages.RegistarionConfirmationPage;
import com.newtours.pages.RegistrationPage;

public class BookFlightTest extends Basetest{
	
	private String noOfPassanger;
	private String expectedPrice;
	
	@BeforeTest
	@Parameters({"noOfPassanger","expectedPrice"})
	public void setupParameters(String noOfPassanger, String expectedPrice) {
		this.noOfPassanger = noOfPassanger;
		this.expectedPrice = expectedPrice;
	}
	
	@Test
	public void registrationPage() {
		
		RegistrationPage registrationpage = new RegistrationPage(driver);
		registrationpage.goTo();
		registrationpage.enterUserDetails("Selenium", "docker");
		registrationpage.enterUserCredentials("Selenium", "docker");
		registrationpage.submit();
		
	}
	
	@Test(dependsOnMethods = "registrationPage") 
	public void registrationConfirmationPage() {
		
		RegistarionConfirmationPage registrationConfirmationPage = new RegistarionConfirmationPage(driver);
		registrationConfirmationPage.gotoFlightDetailsPage();
	}

	@Test(dependsOnMethods = "registrationConfirmationPage")
	public void flightDetailsPage() {
		FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
		flightDetailsPage.selectPassanger(noOfPassanger);
		flightDetailsPage.gotoFindFlightsPage();
	}
	
	@Test(dependsOnMethods = "flightDetailsPage")
	public void findFlightPage() {
		FindFlightPage findFlightPage = new FindFlightPage(driver);
		findFlightPage.submitFindFlightPage();
		findFlightPage.gotoFlightConfirmationPage();
	}

	@Test(dependsOnMethods = "findFlightPage")
	public void fightConfirmationPage() {
		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
		String actualPrice = flightConfirmationPage.getPrice();	
		Assert.assertEquals(actualPrice, expectedPrice);
		System.out.println("the price "+ actualPrice +"is as expected");
	}
}