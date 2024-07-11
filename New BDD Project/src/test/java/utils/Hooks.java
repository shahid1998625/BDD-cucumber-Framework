package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("Before Scenario: " + scenario.getName());
        ExtentReportUtil.setup();
        ExtentReportUtil.startTest(scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("After Scenario: " + scenario.getName());
        if (scenario.isFailed()) {
            ExtentReportUtil.getTest().fail("Scenario failed: " + scenario.getName());
        } else {
            ExtentReportUtil.getTest().pass("Scenario passed: " + scenario.getName());
        }
        ExtentReportUtil.tearDown(scenario);
    }
}