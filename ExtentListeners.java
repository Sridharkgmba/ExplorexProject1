package Genericutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListeners extends Baseclass implements ITestListener {

    static Date d = new Date();
    static String fileName = "Partner" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
    private static ExtentReports extent;
    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
    public static String screenshotPath;
    public static String screenshotName;
    public static String concatenate = ".";

    public void onStart(ITestContext context) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(
                "./ExtentReporter/" + fileName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("App Name", "Bridge");
        extent.setSystemInfo("App version", "v2");
        extent.setSystemInfo("Automation Tester", "Sridhar");
        extent.setSystemInfo("Organization", "Explorex");
        extent.setSystemInfo("Build no", "1");

    }

    public void onTestStart(ITestResult result) {
        ExtentTest test = extent
                .createTest(result.getTestClass().getName() + " @TestCase : " + result.getMethod().getMethodName());
        testReport.set(test);
        test.log(Status.INFO, "Test Started " + result.getTestClass().getName());
    }

    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        testReport.get().pass(m);
    }

    public void onTestFailure(ITestResult result) {
            // Extract the exception type
            String exceptionType = result.getThrowable().getClass().getName();
            
            // Extract the stack trace
            String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        
            // Add the exception type and stack trace to the report
            testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Type: " + exceptionType + " - Click to see"
                    + "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>"
                    + " \n");
        
            // Capture a screenshot and add it to the report
            captureScreenshot(result.getMethod().getMethodName());
            testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(ExtentListeners.screenshotPath).build());
        
            // Add a failure label to the report
            String failureLogg = "TEST CASE FAILED";
            Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
            testReport.get().log(Status.FAIL, m);
        }
        
    

    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.BLUE);
        testReport.get().skip(m);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

    public static void captureScreenshot(String methodName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Date d = new Date();
        screenshotName = d.toString().replace(":", "_").replace(" ", "_");

        screenshotPath = "C:\\Users\\Lenovo\\Desktop\\inventory\\Screenshots\\" + methodName + screenshotName
                + ".jpg";
        try {
            File finalDestination = new File(screenshotPath);
            FileUtils.copyFile(scrFile, finalDestination);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}