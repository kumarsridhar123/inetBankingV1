package com.inetbanking.testCases;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class Baseclass {
	
	ReadConfig readconfig = new ReadConfig();
	
	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		
  	    logger= Logger.getLogger("ebanking");
	    PropertyConfigurator.configure("Log4j.properties");
	    
	    if (br.equals("chrome"))
	    { 
	    	System.setProperty("webdriver.chrome.driver",readconfig.getchromepath());
		    driver = new ChromeDriver();
	    }
	    
	    if (br.equals("firefox"))
	    { 
	    	System.setProperty("webdriver.gecko.driver",readconfig.getfirefox());
		    driver = new FirefoxDriver();
	    }
	    
	    if (br.equals("ie"))
	    { 
	    	System.setProperty("webdriver.edge.driver",readconfig.getiepath());
		    driver = new EdgeDriver();
	    }
	    
	    driver.get(baseURL);
	    
	    
	    
	    
	}
	@AfterClass
	public void teardown() 
	{
        driver.quit();
}
	
	public void captureScreen(WebDriver driver,String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+ "/Screenshots/"+ tname +".png");
		FileUtils.copyFile(source, target);
		System.out.println("screenshot taken");
		
				
	}
}