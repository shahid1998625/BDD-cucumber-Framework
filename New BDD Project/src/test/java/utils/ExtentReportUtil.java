package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static runners.BaseClass.driver;

public class ExtentReportUtil {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    private static ExtentTest test;

    public static void setup() {
        System.out.println("Setting up ExtentReports...");
        if (extent == null) {
            sparkReporter = new ExtentSparkReporter("reports/extent-report" + System.currentTimeMillis() + ".html");
            sparkReporter.config().setTheme(Theme.STANDARD);
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
    }

    public static void startTest(String testName) {
        System.out.println("Starting test: " + testName);
        if (extent != null) {
            test = extent.createTest(testName);
        } else {
            throw new IllegalStateException("ExtentReports is not initialized. Call setup() first.");
        }
    }

    public static ExtentTest getTest() {
        if (test == null) {
            throw new IllegalStateException("ExtentTest is not initialized. Call startTest() first.");
        }
        return test;
    }

    public static void logPass(String message) {
        test.pass(message);
    }

    public static void logFail(String message) {
        test.fail(message);
    }

    public static void logInfo(String message) {
        test.info(message);
    }

    public static String captureScreenshotAsBase64(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        return ts.getScreenshotAs(OutputType.BASE64);
    }

    public static void logPassWithScreenshot(WebDriver driver, String message) {
        String base64Screenshot = captureScreenshotAsBase64(driver);
        test.pass(message, com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    }

    public static void logFailWithScreenshot(WebDriver driver, String message) {
        String base64Screenshot = captureScreenshotAsBase64(driver);
        test.fail(message, com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    }

    public static void attachScreenshot(String base64Screenshot) {
        test.addScreenCaptureFromBase64String(base64Screenshot);
    }

    public static void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] data = screenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(data, "image/jpeg", "Failed Step Screenshot");
            String base64Screenshot = captureScreenshotAsBase64(driver);
            test.fail("Test Failed").addScreenCaptureFromBase64String(base64Screenshot);
        } else {
            test.pass("Test Passed");
        }
    }

    public static void flushReport() {
        System.out.println("Closing/flushing down ExtentReports...");
        if (extent != null) {
            extent.flush();
        }
    }
}
