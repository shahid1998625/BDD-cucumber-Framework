package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.GmailPage;
import runners.BaseClass;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

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
    public void user_enters_the_username_and_password(String Guname, String Gpw){
        gp.getUsername().sendKeys(Guname);
        gp.getNextBtnuser().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
        gp.getPassword().sendKeys(Gpw);
        gp.getNextBtnPw().click();

//       tearDown();
    }
    @Then("Verify Gmail Homepage is displayed and compose and email")
    public void Verify_Gmail_Homepage_is_displayed_and_compose_and_email() throws InterruptedException {
        gp.getComposeBtn().click();
        String ExpectedURL = "https://mail.google.com/mail/u/0/#inbox";
        String Actualurl = driver.getCurrentUrl();
        System.out.println(Actualurl);
        assertTrue(Actualurl.contains(ExpectedURL));
//        Thread.sleep(5000);
//        gp.getRecepientsBtn().click();
        Thread.sleep(3000);
        gp.getToBtnincompose().sendKeys("mohd1024shahid@gmail.com");
        gp.getToBtnincompose().sendKeys(Keys.TAB);
        gp.getSubjectgmail().sendKeys("Leave Request for July 10th - July 14th - Shahid");
        gp.getMessageBody().sendKeys("Hi Shahid,\n" +
                "\n" +
                "I hope this message finds you well. I am writing to formally request leave from July 10th to July 14th, totaling five working days.\n" +
                "\n" +
                "The reason for this leave is a planned family vacation. I have ensured that all my current tasks are up-to-date, and I am happy to assist in any way to ensure a smooth workflow during my absence.\n" +
                "\n" +
                "In my absence, Jane Roe has kindly agreed to cover my responsibilities. I will also be available via email or phone for any urgent matters that may require my attention.\n" +
                "\n" +
                "Thank you for considering my request. I appreciate your understanding and support. Please let me know if you need any further information or if there are any forms I need to complete.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                "Shahid\n" +
                "Automation Test Engineer\n" +
                "Testing Team\n" +
                "shahid@example.com\n" +
                "(555) 555-5555");
        Thread.sleep(5000);
    }
}
