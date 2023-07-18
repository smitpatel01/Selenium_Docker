package com.base.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.checkerframework.common.reflection.qual.GetMethod;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest2 {
	protected WebDriver driver;
	
	@BeforeTest
	public void setupDriver(){
//		System.setProperty("WebDriver.chrome.driver", "C:\\CloudCollege\\chromedriver_win32\\chromedriver");		
//		this.driver = new ChromeDriver();
//		
		driver = new ChromeDriver();
		WebDriverManager.chromedriver().setup();
	}
	
	@AfterTest
	public void quitDriver() {
		this.driver.quit();
		
	}
	
	@AfterMethod 
	public void tearDown(ITestResult result) throws IOException {
		String timeStamp = new SimpleDateFormat("HHmmss_ddMMyyyy").format(Calendar.getInstance().getTime());

		if (result.getStatus() == ITestResult.FAILURE) {
			String testName = result.getName();
			// Take screenshot on test failure 
			TakesScreenshot ts = (TakesScreenshot) driver;
			File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
			String screenshotPath = ".\\ScreenShots1\\screenshot.png"+"-"+testName+"-"+timeStamp;
			FileUtils.copyFile(screenshotFile, new File(screenshotPath));
			System.out.println("Screenshot captured: " + screenshotPath);
			}
		}
	}
	
