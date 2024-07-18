package com.QM.runners;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import com.QM.drivers.DriverManager;

public class BaseClass {

    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown() {
        DriverManager.quitDriver();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}