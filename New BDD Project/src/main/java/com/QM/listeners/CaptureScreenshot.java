package com.QM.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class CaptureScreenshot {

    public String TestScreenshot(WebDriver driver) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            String srcFile = screenshot.getScreenshotAs(OutputType.BASE64);
            File destFile = new File("D:\\OTR\\screenshot.jpeg" + System.currentTimeMillis() + ".jpeg");
            System.out.println("Screenshot taken and saved at: " + destFile.getAbsolutePath());
            return srcFile;
        } catch (Exception e) {
            System.out.println("An error occurred while taking screenshot: " + e.getMessage());
        }
        return null;
    }

    public static String captureScreenshotAsBase64(WebDriver driver) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            String base64Screenshot = screenshot.getScreenshotAs(OutputType.BASE64);
            System.out.println("Screenshot taken successfully.");
            return base64Screenshot;
        } catch (Exception e) {
            System.out.println("An error occurred while taking screenshot: " + e.getMessage());
            return null;
        }
    }

}

