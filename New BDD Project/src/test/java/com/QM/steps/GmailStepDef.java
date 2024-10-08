package com.QM.steps;

import com.QM.drivers.DriverManager;
import com.QM.listeners.CaptureScreenshot;
import com.QM.listeners.ScreenshotListener;
import com.QM.runners.BaseClass;
import com.QM.utils.ExcelUtil;
import com.QM.utils.DecryptionUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.QM.pages.GmailPage;
import com.QM.utils.ExtentReportUtil;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class GmailStepDef {
    ScreenshotListener sl;
    private WebDriver driver;
    private GmailPage gp;
    private ExcelUtil exsheet;
    private final CaptureScreenshot cp = new CaptureScreenshot();
    private final int specific_Row = 2;
    private WebDriverWait wait;

    public GmailStepDef() throws IOException {
        this.driver = BaseClass.getDriver();
        this.gp = new GmailPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        this.exsheet = new ExcelUtil("src/test/resources/Test Data/InputTestData.xlsx", "Sheet1");

    }

    @Given("User Launches the URL")
    public void User_Launches_the_URL() {
        ExtentReportUtil.getTest().info("Given user launches Gmail link");
        int Url = 0;
        String URL = exsheet.getCellData(specific_Row, Url);
        driver.get(URL);
        ExtentReportUtil.logPassWithScreenshot(driver,"Opened the Application URL");
        gp = new GmailPage(driver);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPassWithScreenshot(driver,"Launched in to Gmail application");
    }

    @When("user enters the username and password")
    public void user_enters_the_username_and_password() throws Exception {
        ExtentReportUtil.getTest().info("When user enters the username and password");
        int userName = 1;
        int Key = 2;

        String username = exsheet.getCellData(specific_Row, userName);
        String keypw = exsheet.getCellData(specific_Row, Key);

        gp.getUsername().sendKeys(username);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        gp.getNextBtnuser().click();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(gp.getPassword())));

        String Decryptedkey = DecryptionUtil.decrypt(keypw);
        gp.getPassword().sendKeys(Decryptedkey);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        if (gp.getNextBtnPw().isDisplayed()) {
            ExtentReportUtil.logPassWithScreenshot(driver,"Next btn is displayed");
            System.out.println("Screenshot jpeg op");
            gp.getNextBtnPw().click();
        } else {
            ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
            System.out.println("else print");
        }

        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPassWithScreenshot(driver,"user entered the username and password");

    }

    @Then("Verify Gmail Homepage is displayed and compose and email")
    public void Verify_Gmail_Homepage_is_displayed_and_compose_and_email() throws InterruptedException {
        ExtentReportUtil.getTest().info("Then Verify Gmail Homepage is displayed and compose and email");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), 'Compose')]")));
        gp.getComposeBtn().click();
        String ExpectedURL = "https://mail.google.com/mail/u/0/#inbox";
        String Actualurl = driver.getCurrentUrl();
        System.out.println(Actualurl);
        assertTrue(Actualurl.contains(ExpectedURL));

        int To = 3;
        int Subject = 5;
        int Addmail = 6;

        String to = exsheet.getCellData(specific_Row, To);
        String subject = exsheet.getCellData(specific_Row, Subject);
        String addMailText = exsheet.getCellData(specific_Row, Addmail);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(gp.getToBtnincompose())));
        gp.getToBtnincompose().sendKeys(to);
        ExtentReportUtil.logPassWithScreenshot(driver,"writing new email draft");

        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        gp.getToBtnincompose().sendKeys(Keys.TAB);
        gp.getSubjectgmail().sendKeys(subject);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPassWithScreenshot(driver,"Added subject to draft mail");
        gp.getMessageBody().sendKeys(addMailText);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        Thread.sleep(3000);
        gp.getSignatureBtn().click();
        gp.getMysignature().click();

        Actions ac = new Actions(driver);
        ac.scrollToElement(gp.getAfterMySignatureText());
        wait.until(ExpectedConditions.visibilityOf(gp.getAfterMySignatureText()));

        if (gp.getAfterMySignatureText().isDisplayed()) {
            ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
            ExtentReportUtil.logPassWithScreenshot(driver,"Added Signature to draft mail");
            System.out.println("Added Signature to draft mail");
        } else {
            System.out.println("else print2");
        }

    }

    @Given("User Launches the Google Account URL")
    public void User_Launches_the_Google_Account_URL() throws IOException {
        ExtentReportUtil.getTest().info("Given user launches Google accounts link");
        exsheet = new ExcelUtil("src/test/resources/Test Data/InputTestData.xlsx", "Sheet1");
        int Url = 7;
        String URL = exsheet.getCellData(specific_Row, Url);
        driver.get(URL);
        ExtentReportUtil.logPassWithScreenshot(driver,"Opened the Application URL");
        gp = new GmailPage(driver);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPassWithScreenshot(driver,"Launched in to Google account link");
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(gp.getGotoGoogleAcc())));
        gp.getGotoGoogleAcc().click();

    }

    @Then("user clicks on google apps button and navigate to")
    public void user_clicks_on_google_apps_button_and_navigate_to() {
        ExtentReportUtil.getTest().info("Then user clicks on google apps button and navigate to");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(gp.getGoogleAppsBtn()));
        gp.getGoogleAppsBtn().click();
        driver.switchTo().frame("app");
        wait.until(ExpectedConditions.elementToBeClickable(gp.getGmailBtn()));
        gp.getGmailBtn().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        System.out.println("ABC:" + allWindows);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        // Wait for the new window or tab
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Loop through until we find a new window handle
        for (String windowHandle : allWindows) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                break;
            }
            ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        }
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(gp.getComposeBtn())));
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        ExtentReportUtil.logPassWithScreenshot(driver,"user clicked on google apps button and navigated to window switch");


    }

    @Then("Close the browser")
    public void Close_the_browser() {
        DriverManager.quitDriver();
    }
}
