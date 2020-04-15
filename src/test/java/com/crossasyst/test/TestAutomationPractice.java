package com.crossasyst.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Helper.AutomationPracticePage;

/**
 * @Summary: The test class to place an order on automation practice website.
 * @author: Aishvarya Nagaonkar
 * @date: 4/15/2020
 */

public class TestAutomationPractice {

	private WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "E:\\Test\\driver\\chromedriver.exe");
		driver=new ChromeDriver();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@Test(description = "This test creates a user account and place an order on automation practice")
	public void automationPractice() throws IOException  {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();

		AutomationPracticePage helper = new AutomationPracticePage(driver);
		helper.createAccount(driver);
		//if user is already registered
		//helper.signIn(driver);
		
		helper.selectOrder(driver);
		String cartTotal =  helper.checkout(driver);
		String orderPrice = helper.orderHistory(driver);
		Assert.assertEquals(cartTotal, orderPrice, "The amount of order is equal to the total cost of product");
	}
}




