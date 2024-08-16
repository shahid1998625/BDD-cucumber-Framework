package com.QM.runners;

import io.cucumber.java.After;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import com.QM.utils.ExtentReportUtil;
import com.QM.listeners.ScreenshotListener;

@CucumberOptions(

        tags = "@run61",
        features = {"src/test/resources/features"},
        glue = {"com/QM/steps", "com/QM/hooks"},
        plugin = {
                "pretty",
//                "html:target/cucumber-reports.html",
                "json:targetoutput/cucumber-reports/Cucumber.json",
                "html:targetoutput/cucumber-reports.html"
        },
        monochrome = true

)
@Listeners(ScreenshotListener.class)

public class TestRunner extends AbstractTestNGCucumberTests {

    @After
    public void flushReport() {
        ExtentReportUtil.flushReport();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}