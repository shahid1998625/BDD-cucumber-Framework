package com.QM.steps;

import com.QM.listeners.CaptureScreenshot;
import com.QM.listeners.ScreenshotListener;
import com.QM.runners.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.QM.pages.outlookPage;
//import utils.*;
import com.QM.utils.ExcelUtil;
import com.QM.utils.ExtentReportUtil;
import com.QM.utils.DecryptionUtil;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;


public class outlookStepDef {
    private WebDriver driver;
    private outlookPage op;
    private CaptureScreenshot cp = new CaptureScreenshot();
    private ExcelUtil Exsheet;
    private int specific_Row = 1;
    private WebDriverWait wait;

    public outlookStepDef() throws IOException {
        this.driver = BaseClass.getDriver();
        op = new outlookPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        Exsheet = new ExcelUtil("src/test/resources/Test Data/InputTestData.xlsx", "Sheet1");
    }


    @Given("user launches microsoft link")
    public void user_launches_microsoft_link() {
        System.out.println("Executing step: Given a precondition");
        ExtentReportUtil.getTest().info("Given user launches microsoft link");
        int Url = 0;
        String URL = Exsheet.getCellData(specific_Row, Url);
        try {
            driver.get(URL);
            ExtentReportUtil.logPass("Opened the Application URL");
        } catch (UnhandledAlertException e) {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        }
        op = new outlookPage(driver);
        ExtentReportUtil.logPassWithScreenshot(driver,"Launched the microsoft URL");

    }

    @When("user enters username and password")
    public void user_enters_username_and_password() throws Exception {
        ExtentReportUtil.getTest().info("When user enters username and password");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        int UserName = 1;
        int Key = 2;
        String username = Exsheet.getCellData(specific_Row, UserName);
        String keypw = Exsheet.getCellData(specific_Row, Key);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));

        String Decryptedusername = DecryptionUtil.decrypt(username);
        op.getUsernameoutlook().sendKeys(Decryptedusername);


        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPassWithScreenshot(driver,"Entered the Username");
        op.getNextBtn1().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(op.getPasswordoutlook())));

        String DecryptedPW = DecryptionUtil.decrypt(keypw);
        op.getPasswordoutlook().sendKeys(DecryptedPW);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='idSIButton9']"))));

        ExtentReportUtil.logPassWithScreenshot(driver,"Entered the key");
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        op.getSignInBtn().click();
        op.getBackBtnpopup().click();
        op.getNoBtnpopup().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPassWithScreenshot(driver,"Logged in to Microsoft application");
   }

    @Then("User will be navigated to Microsoft dashboard homepage")
    public void User_will_be_navigated_to_Microsoft_dashboard_homepage() {
        ExtentReportUtil.getTest().info("Then User will be navigated to Microsoft dashboard homepage");
        op.getMainnavlinkBtn().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPassWithScreenshot(driver,"User is navigated to Microsoft dashboard");
    }

    @Then("Now click on outlook , will be navigated to outlook mail homepage")
    public void Now_click_on_outlook_will_be_navigated_to_outlook_mail_homepage() {
        ExtentReportUtil.getTest().info("Then Now click on outlook , will be navigated to outlook mail homepage");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(op.getOutlookBtn())));
        op.getOutlookBtn().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPassWithScreenshot(driver,"Clicked on outlook button");
        //new window handle code
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        System.out.println("ABC:" + allWindows);
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
        // Perform your actions on the new tab
        WebElement newTabElement = driver.findElement(By.xpath("(//span[contains(text(), 'Inbox')])[1]"));
        newTabElement.click();
        String title = driver.getTitle();
        System.out.println("tile :" + title);
        if (title.equals("domain")) {
            ExtentReportUtil.logPass("no wrong domain");
            System.out.println("tile is : " + title);
        } else {
            ExtentReportUtil.logPassWithScreenshot(driver,"New Window is opened with outlook application");
            System.out.println("Screenshot jpeg op");
            op.getNewemailBtn().click();
        }

        String ActualURL = "https://outlook.office.com/mail/";
        String ExpectedURL = driver.getCurrentUrl();
        System.out.println("URL Output: " + ExpectedURL);
        Assert.assertEquals(ActualURL, ExpectedURL);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPassWithScreenshot(driver,"New Window is opened with outlook application");

    }

    @And("Now click on new email and write a email and send")
    public void Now_click_on_new_email_and_write_a_email_and_send() throws Exception {
        ExtentReportUtil.getTest().info("And Now click on new email and write a email and send ");
        int To = 3;
        int Cc = 4;
        int Subject = 5;
        int Addmail = 6;

        String to = Exsheet.getCellData(specific_Row, To);
        String cc = Exsheet.getCellData(specific_Row, Cc);
        String subject = Exsheet.getCellData(specific_Row, Subject);
        String addMailText = Exsheet.getCellData(specific_Row, Addmail);

        sendanEmail(to, cc, subject);
        op.getAddSubjecttextBox().sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        op.getMessageBodyText().sendKeys(addMailText);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPassWithScreenshot(driver,"Email Drafted");

    }

    public void sendanEmail(String toemail, String cc, String subject) throws Exception {
        String DecryptedToemail = DecryptionUtil.decrypt(toemail);
        op.getToBtnEmail().sendKeys(DecryptedToemail);

        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));

        String DecryptedCcmail = DecryptionUtil.decrypt(cc);
        op.getCcBtnEmail().sendKeys(DecryptedCcmail);

        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPassWithScreenshot(driver,"Entered cc mail id");

        op.getAddSubjecttextBox().sendKeys(subject);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPassWithScreenshot(driver,"Added Subject");

    }

}


