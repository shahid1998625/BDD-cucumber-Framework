package com.QM.steps;

import com.QM.listeners.CaptureScreenshot;
import com.QM.listeners.ScreenshotListener;
import com.QM.pages.greyQMPage;
import com.QM.runners.BaseClass;
import com.QM.utils.DataDecrypt;
import com.QM.utils.ExcelUtil;
import com.QM.utils.ExtentReportUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;

public class GreyQMStepDef {
    private WebDriver driver;
    private greyQMPage gq;
    private WebDriverWait wait;
    private final CaptureScreenshot cp = new CaptureScreenshot();
    private final int specific_Row = 4;
    private ExcelUtil sheet;


    public GreyQMStepDef() throws IOException {
        this.driver = BaseClass.getDriver();
        this.gq = new greyQMPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.sheet = new ExcelUtil("src/test/resources/Test Data/InputTestData.xlsx","Sheet1");
    }

    @Given("user Launches the greytHR portal")
    public void user_Launches_the_greytHR_portal() {
        ExtentReportUtil.getTest().info("Given user Launches the greytHR portal");
//        setUp();
        int Url = 0;
        String URL = sheet.getCellData(specific_Row, Url);
        driver.get(URL);
        ExtentReportUtil.logPass("Opened the Application URL");
        gq = new greyQMPage(driver);
        ScreenshotListener.screenshotlist.add(cp.TestScreenshot(driver));
        ExtentReportUtil.logPass("Launched in to greyhr application portal");
    }

    @When("user enters the username and key in launched application")
    public void user_enters_the_username_and_key_in_launched_application() throws Exception {
        int user = 1;
        int key = 2;

        String uname = "256";
//        String uname = sheet.getCellData(specific_Row,user);
        String Key = sheet.getCellData(specific_Row,key);
        String DecryptedKey = DataDecrypt.decrypt(Key);
        Thread.sleep(2000);
        enterusernameandkey(uname,DecryptedKey);
        gq.getSignInBtn().click();
//        wait.until(ExpectedConditions.titleContains("greytHR"));
        Thread.sleep(4000);
        String expectedtext = "greytHR";
        String actualtext = driver.getTitle();
        System.out.println(actualtext);
        Assert.assertEquals(actualtext,expectedtext);
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        Thread.sleep(3000);
//        WebElement shadowHost = driver.findElement(By.cssSelector("shadow-host-selector"));
        WebElement shadowHost = driver.findElement(By.cssSelector("#shadow-root"));

        // Use JavaScript to access the shadow root
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement shadowRoot = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);

        // Locate an element within the shadow DOM
        WebElement shadowElement = shadowRoot.findElement(By.cssSelector("nav > ul > span.collapsed.active > li > a > span"));

        // Perform actions on the shadow DOM element
        shadowElement.click();




//        gq.getHolidays().click();
//        String expectedtext1 = "Holiday Calendar";
//        String actualtext1 = gq.getHolidaycalendertext().getText();
//        System.out.println(actualtext1);
//        Assert.assertEquals(actualtext1,expectedtext1);
//        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));

    }

    public void enterusernameandkey(String uname, String pw){
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(gq.getUsername())));
        gq.getUsername().sendKeys(uname);
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
        gq.getKey().sendKeys(pw);
        ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));
    }

}
