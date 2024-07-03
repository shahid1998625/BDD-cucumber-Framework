package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.GmailPage;
import runners.BaseClass;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class GmailstepDef extends BaseClass {

    GmailPage gp;

    @Given("User Launches the URL {string}")
    public void User_Launches_the_URL(String URL){
    setUp();
    driver.get(URL);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    gp = new GmailPage(driver);

    }

    @When("user enters the username and password {string} {string}")
    public void user_enters_the_username_and_password(String Guname, String Gpw) {
        gp.getUsername().sendKeys(Guname);
        gp.getNextBtnuser().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
        gp.getPassword().sendKeys(Gpw);
        gp.getNextBtnPw().click();
        tearDown();
    }
}
