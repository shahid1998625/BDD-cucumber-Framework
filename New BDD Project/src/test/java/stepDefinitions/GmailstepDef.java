package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.GmailPage;
import runners.BaseClass;
import utils.*;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class GmailstepDef extends BaseClass{

    private GmailPage gp;
    private ExcelUtil Exsheet;
    private CaptureScreenshot cp = new CaptureScreenshot();
    private int specific_Row = 2;
    private WebDriverWait wait ;

    public GmailstepDef() throws IOException  {
        driver = BaseClass.getDriver();
        gp = new GmailPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Exsheet= new ExcelUtil("src/test/resources/Test Data/InputTestData.xlsx", "Sheet1");

    }

    @Given("User Launches the URL")
    public void User_Launches_the_URL(){
    ExtentReportUtil.getTest().info("Given user launches Gmail link");
    setUp();
    int Url = 0;
    String URL = Exsheet.getCellData(specific_Row,Url);
    driver.get(URL);
    ExtentReportUtil.logPass("Opened the Application URL");
    gp = new GmailPage(driver);
    ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
    ExtentReportUtil.logPass("Launched in to Gmail application");
    ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));

    }

    @When("user enters the username and password")
    public void user_enters_the_username_and_password() throws Exception {
    ExtentReportUtil.getTest().info("When user enters the username and password");
        int userName = 1;
        int Key = 2;

        String username = Exsheet.getCellData(specific_Row,userName);
        String keypw = Exsheet.getCellData(specific_Row,Key);

        gp.getUsername().sendKeys(username);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        gp.getNextBtnuser().click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
        String Decryptedkey = DecryptionUtil.decrypt(keypw);
        gp.getPassword().sendKeys(Decryptedkey);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        if(gp.getNextBtnPw().isDisplayed()){
            ExtentReportUtil.logPass("next btn is displayed");
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] data = screenshot.getScreenshotAs(OutputType.BYTES);
            ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
            System.out.println("Screenshot jpeg op");
            gp.getNextBtnPw().click();
        }
        else {
            ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
            System.out.println("else print");
        }

        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPass("user entered the username and password");
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
        wait.until(ExpectedConditions.visibilityOf(gp.getToBtnincompose()));

        int To = 3;
        int Cc = 4;
        int Subject = 5;
        int Addmail = 6;

        String to = Exsheet.getCellData(specific_Row,To);
        String cc = Exsheet.getCellData(specific_Row,Cc);
        String subject = Exsheet.getCellData(specific_Row,Subject);
        String addMailText = Exsheet.getCellData(specific_Row,Addmail);


        gp.getToBtnincompose().sendKeys(to);
        ExtentReportUtil.logPass("writing new email draft");
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));

        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        gp.getToBtnincompose().sendKeys(Keys.TAB);
        gp.getSubjectgmail().sendKeys(subject);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPass("Added subject to draft mail");
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        gp.getMessageBody().sendKeys(addMailText);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        Thread.sleep(3000);
        gp.getSignatureBtn().click();
        gp.getMysignature().click();

        Actions ac = new Actions(driver);
        ac.scrollToElement(gp.getAfterMySignatureText());
        wait.until(ExpectedConditions.visibilityOf(gp.getAfterMySignatureText()));

        if(gp.getAfterMySignatureText().isDisplayed()){
            ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
            ExtentReportUtil.logPass("Added Signature to draft mail");
            ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
            System.out.println("Added Signature to draft mail");
        }
        else {
            System.out.println("else print2");
        }

    }
    @Given("User Launches the Google Account URL")
    public void User_Launches_the_Google_Account_URL() throws IOException {
        ExtentReportUtil.getTest().info("Given user launches Google accounts link");
        setUp();
        Exsheet= new ExcelUtil("src/test/resources/Test Data/InputTestData.xlsx", "Sheet1");
        int Url = 7;
        String URL = Exsheet.getCellData(specific_Row,Url);
        driver.get(URL);
        ExtentReportUtil.logPass("Opened the Application URL");
        gp = new GmailPage(driver);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPass("Launched in to Google account link");
        gp.getGotoGoogleAcc().click();

    }
    @Then("user clicks on google apps button and navigate to")
    public void user_clicks_on_google_apps_button_and_navigate_to() throws InterruptedException {
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
        System.out.println("ABC:" +allWindows);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        // Wait for the new window or tab
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                break;
            }
            ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        }
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(gp.getComposeBtn())));
//        gp.getComposeBtn().click();

    }
    @Then("Close the browser")
    public void Close_the_browser(){
        tearDown();
    }
}
