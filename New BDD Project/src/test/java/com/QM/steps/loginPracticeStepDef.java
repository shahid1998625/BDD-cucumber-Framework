package com.QM.steps;

import com.QM.runners.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.QM.utils.DataDecrypt;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.QM.pages.LoginPracticePage;
import com.QM.listeners.CaptureScreenshot;
import com.QM.utils.ExcelUtil;
import com.QM.utils.ExtentReportUtil;
import com.QM.listeners.ScreenshotListener;

import java.io.IOException;
import java.time.Duration;

public class loginPracticeStepDef extends BaseClass {
    private LoginPracticePage rp1 ;
    private final CaptureScreenshot  cp = new CaptureScreenshot();
    private final int specific_Row = 3;
    private final ExcelUtil sheet;
    WebDriverWait wait;

    public loginPracticeStepDef() throws IOException {
        driver = BaseClass.getDriver();
        rp1 = new LoginPracticePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        sheet = new ExcelUtil("src/test/resources/Test Data/InputTestData.xlsx", "Sheet1");
    }

    @Given("user launches the rahulshetty automation login page")
    public void user_launches_the_rahulshetty_automation_login_page() {
        ExtentReportUtil.getTest().info("Given user launches the rahulshetty automation login page");
        setUp();
        int Url =0;
        String URL = sheet.getCellData(specific_Row,Url);
        driver.get(URL);
        rp1 = new LoginPracticePage(driver);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPass("Opened the Application URL");
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
    }

    @When("User enters the username and password")
    public void User_enters_the_username_and_password() throws Exception {
        rp1 = new LoginPracticePage(driver);
        int userName = 1;
        int Key = 2;

        String uname = sheet.getCellData(specific_Row, userName);
        String pword = sheet.getCellData(specific_Row, Key);

        String DecryptedPw = DataDecrypt.decrypt(pword);

        enterusernameandpassword(uname, DecryptedPw);
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
    }
    @Then("Validate login page details")
    public void Validate_login_page_details(){
        int Dloc = 3;
        String delivloc = sheet.getCellData(specific_Row, Dloc);
        rp1.getUsercheckmark().click();
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(rp1.getOkayBtninPopup())));
        rp1.getOkayBtninPopup().click();

        Select sel = new Select(rp1.getDrpdownP1());
        sel.selectByVisibleText("Teacher");

        rp1.getTermsp1().click();
        rp1.getSigninBtn().click();
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        Actions act = new Actions(driver);
        act.scrollToElement(rp1.getCartBtn());
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        wait.until(ExpectedConditions.visibilityOf(rp1.getCartBtn()));
        rp1.getCartBtn().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        act.scrollToElement(rp1.getCheckoutBtn1());
        rp1.getCheckoutBtn1().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));

        rp1.getCheckoutfinalBtn().click();
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        rp1.getDeliverylocationBtn().sendKeys(delivloc);
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

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
    public void enterusernameandpassword(String u , String p){
        rp1.getUserName().sendKeys(u);
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        rp1.getPassWord().sendKeys(p);
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
    }

}
