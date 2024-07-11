package stepDefinitions.Outlook;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.Outlook.outlookPage;
import runners.BaseClass;
import utils.CaptureScreenshot;
import utils.DecryptionUtil;
import utils.ExtentReportUtil;
import utils.ScreenshotListener;

import java.io.File;
import java.time.Duration;
import java.util.Set;

public class OutlookstepDefinition extends BaseClass {
        outlookPage op ;
        CaptureScreenshot cp = new CaptureScreenshot();

    @Given("user launches microsoft link {string}")
    public void user_launches_microsoft_link(String Url){
        System.out.println("Executing step: Given a precondition");
            ExtentReportUtil.getTest().info("Given user launches microsoft link");
            setUp();

            driver.get(Url);
            ExtentReportUtil.logPass("Opened the Application URL");
            op = new outlookPage(driver);

        }

    @When("user enters username and password {} {}")
    public void user_enters_username_and_password(String uname , String pw) throws Exception {
        ExtentReportUtil.getTest().info("When user enters username and password");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));

        String Decryptedusername = DecryptionUtil.decrypt(uname);
        op.getUsernameoutlook().sendKeys(Decryptedusername);


//        op.getUsernameoutlook().sendKeys(uname);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        op.getNextBtn1().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(op.getPasswordoutlook())));

        String DecryptedPW = DecryptionUtil.decrypt(pw);
        op.getPasswordoutlook().sendKeys(DecryptedPW);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='idSIButton9']"))));

        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        op.getSignInBtn().click();
        //  ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        op.getBackBtnpopup().click();
        op.getNoBtnpopup().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPass("Logged in to Microsoft application");
    }
    @Then("User will be navigated to Microsoft dashboard homepage")
        public void User_will_be_navigated_to_Microsoft_dashboard_homepage(){
        ExtentReportUtil.getTest().info("Then User will be navigated to Microsoft dashboard homepage");
        op.getMainnavlinkBtn().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));

    }
    @Then("Now click on outlook , will be navigated to outlook mail homepage")
    public void Now_click_on_outlook_will_be_navigated_to_outlook_mail_homepage(){
        ExtentReportUtil.getTest().info("Then Now click on outlook , will be navigated to outlook mail homepage");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        op.getOutlookBtn().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));

        //new window handle code
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
        // Perform your actions on the new tab
        WebElement newTabElement = driver.findElement(By.xpath("(//span[contains(text(), 'Inbox')])[1]"));
        newTabElement.click();
        String title = driver.getTitle();
        System.out.println("tile :" +title);
        if(title.equals("domain")){
            ExtentReportUtil.logPass("no wrong domain");
            System.out.println("tile is : " +title);
        }
        else {
            ExtentReportUtil.logPass("Right page");
//            cp.CapturedScreenshot(driver);
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] data = screenshot.getScreenshotAs(OutputType.BYTES);
//            File data1 = screenshot.getScreenshotAs(OutputType.FILE);
            // Save the screenshot to a file
            // Assuming you save it to "screenshot.png"
            ExtentReportUtil.attachScreenshot("reports/screenshot.png");
            System.out.println("Screenshot jpeg op");
            op.getNewemailBtn().click();
        }

        String ActualURL = "https://outlook.office.com/mail/";
        String ExpectedURL = driver.getCurrentUrl();
        System.out.println("URL Output: " +ExpectedURL);
        Assert.assertEquals(ActualURL,ExpectedURL);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
    }

    @And("Now click on new email and write a email and send {string} {string} {string}")
    public void Now_click_on_new_email_and_write_a_email_and_send(String to, String cc, String subject) throws InterruptedException {
        ExtentReportUtil.getTest().info("And Now click on new email and write a email and send ");
        sendanEmail(to,cc,subject);
        op.getAddSubjecttextBox().sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        op.getMessageBodyText().sendKeys("Hi Shahid,\n" +
                "\n" +
                "I hope this message finds you well. I am writing to formally request leave on July 6th to July 7th, totaling 2 days.\n" +
                "\n" +
                "The reason for this leave is a planned family vacation. I have ensured that all my current tasks are up-to-date, and I am happy to assist in any way to ensure a smooth workflow during my absence.\n" +
                "\n" +
                "In my absence, Alekhya/Kishore has agreed to cover my responsibilities. I will also be available via email or phone for any urgent matters that may require my attention.\n" +
                "\n" +
                "Thank you for considering my request. I appreciate your understanding and support. Please let me know if you need any further information or if there are any forms I need to complete.\n");
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
//        op.getSendemailBtn().click();
//        tearDown();

    }
    public void sendanEmail(String toemail, String cc, String subject){
        op.getToBtnEmail().sendKeys(toemail);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        op.getCcBtnEmail().sendKeys(cc);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        op.getAddSubjecttextBox().sendKeys(subject);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
//        op.getMessageBodyText().sendKeys(messagebody);
//        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));

    }


}


