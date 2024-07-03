package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RahulShettyLoginPage1;
import runners.BaseClass;
import utils.CaptureScreenshot;
import utils.ScreenshotListener;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RahulShettyStepDefinitionPage1 extends BaseClass {
    RahulShettyLoginPage1 rp1 ;
    CaptureScreenshot cp = new CaptureScreenshot();

    @Given("user launches the login page")
    public void user_launches_the_login_page(){
        setUp();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        rp1 = new RahulShettyLoginPage1(driver);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));

    }

    @When("User enters the username and password {string} {string}")
    public void User_enters_the_username_and_password(String uname , String pword){
        rp1 = new RahulShettyLoginPage1(driver);
        rp1.enterusernameandpassword(uname, pword);
        rp1.getUsercheckmark().click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='okayBtn']")));

        rp1.getOkayBtninPopup().click();

        Select sel = new Select(rp1.getDrpdownP1());
        sel.selectByVisibleText("Teacher");

        rp1.getTermsp1().click();
        rp1.getSigninBtn().click();





        tearDown();
    }

}
