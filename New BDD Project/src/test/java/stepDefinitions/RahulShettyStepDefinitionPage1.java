package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jars.DataDecrypt;
import org.checkerframework.checker.units.qual.K;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.RahulShettyLoginPage1;
import runners.BaseClass;
import utils.CaptureScreenshot;
import utils.ExcelUtil;
import utils.ExtentReportUtil;
import utils.ScreenshotListener;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RahulShettyStepDefinitionPage1 extends BaseClass {
    private RahulShettyLoginPage1 rp1 ;
    private CaptureScreenshot cp = new CaptureScreenshot();
    private int specific_Row = 3;
    private ExcelUtil sheet;
    WebDriverWait wait;

    public RahulShettyStepDefinitionPage1() throws IOException {
        driver = BaseClass.getDriver();
        rp1 = new RahulShettyLoginPage1(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        sheet = new ExcelUtil("src/test/resources/Test Data/InputTestData.xlsx", "Sheet1");

    }

    @Given("user launches the rahulshetty automation login page")
    public void user_launches_the_rahulshetty_automation_login_page() throws IOException {
        ExtentReportUtil.getTest().info("Given user launches the rahulshetty automation login page");
        setUp();
        int Url =0;
        String URL = sheet.getCellData(specific_Row,Url);
        driver.get(URL);
        //        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        rp1 = new RahulShettyLoginPage1(driver);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPass("Opened the Application URL");
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
//        sheet = new ExcelUtil("src/test/resources/Test Data/InputTestData.xlsx", "Sheet1");
    }

    @When("User enters the username and password")
    public void User_enters_the_username_and_password() throws Exception {
        rp1 = new RahulShettyLoginPage1(driver);
        int userName = 1;
        int Key = 2;
        int Dloc = 3;

        String uname = sheet.getCellData(specific_Row, userName);
        String pword = sheet.getCellData(specific_Row, Key);

        String DecryptedPw = DataDecrypt.decrypt(pword);

        rp1.enterusernameandpassword(uname, DecryptedPw);
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
    }
    @Then("Validate login page details")
    public void Validate_login_page_details(){
        int Dloc = 3;
        String delivloc = sheet.getCellData(specific_Row, Dloc);
        rp1.getUsercheckmark().click();
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='okayBtn']")));

        rp1.getOkayBtninPopup().click();

        Select sel = new Select(rp1.getDrpdownP1());
        sel.selectByVisibleText("Teacher");

        rp1.getTermsp1().click();
        rp1.getSigninBtn().click();
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        Actions act = new Actions(driver);
        act.scrollToElement(rp1.getCartBtn());
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        rp1.getCartBtn().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        act.scrollToElement(rp1.getCheckoutBtn1());
        rp1.getCheckoutBtn1().click();
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));

        rp1.getCheckoutfinalBtn().click();
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        rp1.getDeliverylocationBtn().sendKeys(delivloc);
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        Thread.sleep(6000);

        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));

        rp1.getAgreeTerms().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        rp1.getPurchasebtn().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));

        rp1.getSuccessmessage().isDisplayed();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));

        String ExpectedText = "×\n" +
                "Success! Thank you! Your order will be delivered in next few weeks :-).";
        String ActualText = rp1.getSuccessmessage().getText();
        System.out.println(ActualText);

        Assert.assertEquals(ActualText,ExpectedText);
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        tearDown();
    }

}
