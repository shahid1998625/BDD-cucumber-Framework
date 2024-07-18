package com.QM.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import com.QM.utils.ExtentReportUtil;
import com.QM.listeners.ScreenshotListener;
import org.testng.annotations.AfterClass;

@CucumberOptions(

        tags =  "@run",
        features = {"src/test/resources/features"},
        glue = {"com/QM/steps", "com/QM/hooks"},
        plugin = {
                "pretty",
//                "html:target/cucumber-reports.html",
                "html:targetoutput/cucumber-reports.html"
        },
        monochrome = true

)
@Listeners(ScreenshotListener.class)

public class TestRunner extends AbstractTestNGCucumberTests{

@AfterClass
        public void flushReport(){
        ExtentReportUtil.flushReport();
}
}