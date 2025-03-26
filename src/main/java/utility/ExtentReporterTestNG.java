package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTestNG {

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "/reports/index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Extent Report Automation");
		reporter.config().setDocumentTitle("Automation in TestNG");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sarbrinder");
		
		return extent;
	}
}

