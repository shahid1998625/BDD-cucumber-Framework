package com.QM.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ExtentReportUtil {
    private static ExtentReports extent;
    static ExtentSparkReporter sparkReporter;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void setup() {
        System.out.println("Setting up ExtentReports...");
        if (extent == null) {
//            sparkReporter = new ExtentSparkReporter("reports/ExtentReportsSha/extent-report" + System.currentTimeMillis() + ".html");
//            sparkReporter = new ExtentSparkReporter("reports/new HTML Reports/new HTML Reports.html");
//            sparkReporter = new ExtentSparkReporter("D:new HTML Reports/extent-report.html");
            sparkReporter = new ExtentSparkReporter("D:/BDD-cucumber-Framework/New BDD Project/new HTML Reports/extent-report.html");
            sparkReporter.config().setTheme(Theme.STANDARD);
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
    }

    public static void startTest(String testName) {
        System.out.println("Starting test: " + testName);
        if (extent != null) {
            ExtentTest extentTest = extent.createTest(testName);
            test.set(extentTest);
        } else {
            throw new IllegalStateException("ExtentReports is not initialized. Call setup() first.");
        }
    }

    public static ExtentTest getTest() {
        ExtentTest extentTest = test.get();
        if (extentTest == null) {
            throw new IllegalStateException("ExtentTest is not initialized. Call startTest() first.");
        }
        return extentTest;
    }

    public static void logPass(String message) {
        getTest().pass(message);
    }

    public static void logFail(String message) {
        getTest().fail(message);
    }

    public static void logInfo(String message) {
        getTest().info(message);
    }

    public static String captureScreenshotAsBase64(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        return ts.getScreenshotAs(OutputType.BASE64);
    }

    public static void logPassWithScreenshot(WebDriver driver, String message) {
        String base64Screenshot = captureScreenshotAsBase64(driver);
        getTest().pass(message, com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    }

    public static void logFailWithScreenshot(WebDriver driver, String message) {
        String base64Screenshot = captureScreenshotAsBase64(driver);
        getTest().fail(message, com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    }

    public static void attachScreenshot(String base64Screenshot) {
        getTest().addScreenCaptureFromBase64String(base64Screenshot);
    }

    public static void tearDown(Scenario scenario) {
        ExtentTest extentTest = getTest();
        if (scenario.isFailed()) {
            extentTest.fail("Test Failed");
        } else {
            extentTest.pass("Test Passed");
        }
        extent.flush();
    }


    public static void flushReport() {
        System.out.println("Closing/flushing down ExtentReports...");
        if (extent != null) {
            extent.flush();
        }
    }
}
