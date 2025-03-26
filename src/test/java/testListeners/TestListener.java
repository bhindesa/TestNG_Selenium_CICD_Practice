package testListeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseTest;
import utility.ExtentReporterTestNG;


public class TestListener implements ITestListener {

	WebDriver driver = null;

	ExtentReports extentObj = ExtentReporterTestNG.getReportObject();
	ExtentTest test ;
	
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return ITestListener.super.isEnabled();
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		test = extentObj.createTest(result.getName());
		
		//Making this thread safe
		extentTestThread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTestThread.get().fail(result.getThrowable());
		
		//AttachScreenShot
		String scPath = null;
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			scPath =BaseTest.getScreenShotPath(result.getName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTestThread.get().addScreenCaptureFromPath(scPath, result.getName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extentObj.flush();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

//	@Override
//	protected void finalize() throws Throwable {
//		// TODO Auto-generated method stub
//		super.finalize();
//	}
	
	


	
	
    
}
