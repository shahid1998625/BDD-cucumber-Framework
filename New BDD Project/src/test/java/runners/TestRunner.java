package runners;

import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import utils.ExtentReportUtil;
import utils.ScreenshotListener;
import org.testng.annotations.AfterClass;

@CucumberOptions(

        tags = "@T1u",
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "utils"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "html:target/cucumber-reports/advanced-reports/cucumber-html-reports/output.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml"
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