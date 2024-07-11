package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static runners.BaseClass.driver;

public class ExtentReportUtil {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    private static ExtentTest test;

    public static void setup() {
        System.out.println("Setting up ExtentReports...");
        if (extent == null) {
            sparkReporter = new ExtentSparkReporter("reports/extent-report" +System.currentTimeMillis() + ".html");
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

    public static void attachScreenshot(String path) {
        test.addScreenCaptureFromPath(path);
    }

    public static void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] data = screenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(data, "image/jpeg", "Failed Step Screenshot");
            test.fail("Test Failed").addScreenCaptureFromPath("screenshot.jpeg");
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