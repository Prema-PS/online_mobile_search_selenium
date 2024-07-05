
package miniProject;
 
import org.testng.ITestContext;

import org.testng.ITestListener;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extentreport implements ITestListener

{
	// UI of the report
	public ExtentSparkReporter sparkReporter;  
	//populate common info on the report
	public ExtentReports extent;  
	// creating test case entries in the report and update status of the test methods
	public ExtentTest test; 

	public void onStart(ITestContext context) {
		//specify location of the report
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/Report/myReport.html");
		// TiTle of report
		sparkReporter.config().setDocumentTitle(" Test Automation Report"); 
		// name of the report
		sparkReporter.config().setReportName("Mini project : Online Mobile Search "); 

		sparkReporter.config().setTheme(Theme.STANDARD);

		extent=new ExtentReports();

		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Computer Name","localhost");

		extent.setSystemInfo("Environment","QA");

		extent.setSystemInfo("Tester Name","Prema");

		extent.setSystemInfo("os","Windows11");

		extent.setSystemInfo("Browser name","Chrome,Edge");

	}
 
	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getName()); // create a new enty in the report

		test.log(Status.PASS, "Test case PASSED is:" + result.getName()); // update status p/f/s

	}

	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getName());

		test.log(Status.FAIL, "Test case FAILED is:" + result.getName());

		test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable()); 

	}

	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getName());

		test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());

	}
 
	public void onFinish(ITestContext context) {

		extent.flush();

	}

}