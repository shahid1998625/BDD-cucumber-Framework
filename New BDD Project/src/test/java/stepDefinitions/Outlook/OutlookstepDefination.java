package stepDefinitions.Outlook;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.Outlook.outlookPage;
import runners.BaseClass;
import utils.CaptureScreenshot;
import utils.ScreenshotListener;

import java.time.Duration;
import java.util.Set;

public class OutlookstepDefination extends BaseClass {
        outlookPage op ;
        CaptureScreenshot cp = new CaptureScreenshot();

    @Given("user launches outlook {string}")
    public void user_launches_outlook(String Url){
            setUp();
            driver.get(Url);
            op = new outlookPage(driver);

        }

    @When("user enters username and password {} {}")
    public void user_enters_username_and_password(String uname , String pw){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        op.getUsernameoutlook().sendKeys(uname);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        op.getNextBtn1().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(op.getPasswordoutlook())));
        op.getPasswordoutlook().sendKeys(pw);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='idSIButton9']"))));
  /*      WebElement element = null;
        for (int i = 0; i < 5; i++) {
            try {
                element = driver.findElement(By.xpath("//input[@id='idSIButton9']"));
                element.click();
                break;
            } catch (StaleElementReferenceException e) {
                // Retry logic or logging
                System.out.println("Element not located");
            }
        }*/

        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        op.getSignInBtn().click();
      //  ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        op.getBackBtnpopup().click();
        op.getNoBtnpopup().click();
       // ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        op.getMainnavlinkBtn().click();
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
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
        }
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));

        // Perform your actions on the new tab
        WebElement newTabElement = driver.findElement(By.xpath("(//span[contains(text(), 'Inbox')])[1]"));
        //ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        newTabElement.click();
//        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));


    }
    @Then("User will be navigated to outlook homepage")
    public void User_will_be_navigated_to_outlook_homepage(){
        op.getNewemailBtn().click();
        String ActualURL = "https://outlook.office.com/mail/";
        String ExpectedURL = driver.getCurrentUrl();
        System.out.println(ExpectedURL);
        Assert.assertEquals(ActualURL,ExpectedURL);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));

    }

    @And("Now click on new email and write a email and send {string} {string} {string}")
    public void Now_click_on_new_email_and_write_a_email_and_send(String to, String cc, String subject) throws InterruptedException {
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


