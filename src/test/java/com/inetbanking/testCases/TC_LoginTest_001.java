package com.inetbanking.testCases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends Baseclass {
	 
	 
	@Test	
	public void logintest() throws IOException
	{
		
	 driver.get(baseURL);
	 logger.info("url is opened");
	 LoginPage lp = new LoginPage(driver);
	 lp.setUserName(username);
	 logger.info("Entered username");
	 lp.setPassword(password);
	 logger.info("Entered password");
	 lp.clicksubmit();

	 if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
		 captureScreen(driver,"loginTest");
		 Assert.assertTrue(true);
		 logger.info("Login test passed");
		 
	}else {
		 Assert.assertTrue(false);
}
}
}	