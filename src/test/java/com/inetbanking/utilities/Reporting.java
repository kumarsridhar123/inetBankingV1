package com.inetbanking.utilities;

import java.io.File;

// listeners class used to generate extent reports

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//Cannot invoke "com.aventstack.extentreports.ExtentReports.flush()" because "this.extent" is null

public class Reporting extends TestListenerAdapter {
	
	public ExtentSparkReporter htmlReporter ;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onstart(ITestContext testContext ) throws IOException {
		
	String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
	String reportname = "Test-Report-" +timestamp+".html";

	htmlReporter= new ExtentSparkReporter(System.getProperty("user.dir")+ "/test-output/"+reportname);
	htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
	
	extent =new ExtentReports();  //C:\Users\USER\eclipse-workspace\inetBankingV1
	
	extent.attachReporter(htmlReporter);
	extent.setSystemInfo("Host name","localhost");
	extent.setSystemInfo("Environment","QA");
	extent.setSystemInfo("user","sri");
	
	htmlReporter.config().setDocumentTitle("Inetbanking test Project");
	htmlReporter.config().setReportName("Functional test report");
//	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	htmlReporter.config().setTheme(Theme.DARK);
	}
	
	public void ontestsuccess (ITestResult tr) {
    
	logger=extent.createTest(tr.getName()); // create new entru in the report
	logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));// send the passed information
	}
	public void ontestfailure (ITestResult tr) {
	logger=extent.createTest(tr.getName()); // create new entru in the report
	logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));// send the failed  information
	
	String screenshotpath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
	File f =new File(screenshotpath);
	
	if(f.exists())
	{
	logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotpath));
	
	}
	}

    public void onTestSkipped(ITestResult tr)
    {
	 logger=extent.createTest(tr.getName()); // create new entry in the report
	 logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	
    }
	
	
	
	  public void onFinish(ITestContext testContext) {
	 
	  extent.flush();
	  
	  }
	 
	 
	
    }
    
