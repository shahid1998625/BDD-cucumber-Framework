package com.QM.runners;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import com.QM.drivers.DriverManager;

public class BaseClass {

//    protected static WebDriver driver;

    @BeforeMethod
    public void setUp() {
//        driver = DriverManager.getDriver();
        DriverManager.initializeDriver();
//        driver.manage().window().maximize();
    }

    @AfterMethod
    public static void tearDown() {
        DriverManager.quitDriver();
    }

    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
