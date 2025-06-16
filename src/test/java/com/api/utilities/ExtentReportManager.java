package com.api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;*/
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.sample.testBase.baseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter; // UI Of the report
	public ExtentReports extent; // populate common info on the report
	public ExtentTest test; // Config test case entries in the report and update status of the test methods
	
	String repName;

	public void onStart(ITestContext testContext) {
		System.out.println("Test execution is started.........");
		
		
		String timeStamp =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\Reports\\"+repName);

		
		sparkReporter.config().setDocumentTitle("RestAssured Automation Report"); // Title of the report
		sparkReporter.config().setReportName("PET Store User API Testing"); // Name of the report
		sparkReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "Pet Store API Automation Test");
		extent.setSystemInfo("OS Name",System.getProperty("os.name"));
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user","Venkatesh Devan");

	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getName()); // create a new entry to the report
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed" ); // update status

	}

	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ "Test Skipped"  );
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		// System.out.println("Test execution is completed.........");

		extent.flush();
		
		
		//Code ends for automatically open report
		
		
		/*
		
		//Report send to particular mail using below code
		
		
		try {
			
			URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
			
			//Create the email message
			
			ImageHtmlEmail email = new ImageHtmlEmail();
			
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("venkatitmec@gmail.com", "Amma@2020"));
			email.setSSLOnConnect(true);
			email.setFrom("venkatitmec@gmail.com"); //Sender
			email.setSubject("Automation Report");
			email.setMsg("Please Find the Attached Report.....");
			email.addTo("venkatitmec@gmail.com"); //Receiver
			email.attach(url,"extent report","Please check the report...");
			email.send(); //send the email
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		*/
		
		
	}

}
