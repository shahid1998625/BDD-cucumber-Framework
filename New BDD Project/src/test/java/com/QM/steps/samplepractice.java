package com.QM.steps;

import com.QM.utils.ExtentReportUtil;
import io.cucumber.java.it.Ed;
import org.checkerframework.checker.units.qual.N;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.QM.listeners.CaptureScreenshot;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class samplepractice {

    @Test
    public void practiceTest() throws InterruptedException, IOException {
//    ExtentReportUtil.getTest().info("Given user launches greyHr link");
        WebDriver driver = new ChromeDriver();
        String URL = "https://wesorg--westxqa.sandbox.my.site.com/community/login?ec=302&startURL=%2Fcommunity%2Fapplications%2Fapplication-step%3FapplicationId%3D006O900000LODrGIAX%26referenceNumber%3D";
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//    ExtentReportUtil.attachScreenshot(CaptureScreenshot.captureScreenshotAsBase64(driver));

        WebElement emai = driver.findElement(By.xpath("//input[@name = 'email']"));
        WebElement pw = driver.findElement(By.xpath("//input[@name = 'password']"));
        WebElement loginBtn = driver.findElement(By.xpath("//button[contains(text() , 'Log In')]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        emai.sendKeys("deshmuhadithy5+333@gmail.com");
        pw.sendKeys("Quality@123");
        loginBtn.click();
        Thread.sleep(4000);
        WebElement NoBtn = driver.findElement(By.xpath("//button[@class='slds-button btn-blue']"));
        wait.until(ExpectedConditions.visibilityOf(NoBtn));
        WebElement appBtn = driver.findElement(By.xpath("(//button[@class='slds-button btn-green btn-fix-width'])[1]"));

        NoBtn.click();

//    wait.until(ExpectedConditions.visibilityOf(appBtn));
        Thread.sleep(10000);
        appBtn.click();
        Thread.sleep(4000);

        WebElement canadaBtn = driver.findElement(By.xpath("//div[@data-key='Canada']"));
        wait.until(ExpectedConditions.visibilityOf(canadaBtn));
        canadaBtn.click();
        Thread.sleep(2000);

        WebElement NextBtn = driver.findElement(By.xpath("//button[contains(text(), 'Next')]"));
//        NextBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(NextBtn)).click();
        Thread.sleep(5000);

//    Thread.sleep(5000);
        WebElement EmployementBtn = driver.findElement(By.xpath("//div[@data-key = 'Employment']"));
        wait.until(ExpectedConditions.visibilityOf(EmployementBtn)).click();

//        WebElement GraduateBtn = driver.findElement(By.xpath("//span[contains(text(), 'Graduate Admission')]"));
//        GraduateBtn.click();

//        WebElement SaveBtn = driver.findElement(By.xpath("//button[contains(text(), 'Save')]"));
//        SaveBtn.click();

        Actions act = new Actions(driver);
//    wait.until(ExpectedConditions.visibilityOf(NextBtn));
        WebElement nextBtn = driver.findElement(By.xpath("//button[contains(text(), 'Next')]"));
        act.scrollToElement(nextBtn).perform();
        wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
        Thread.sleep(2000);


        WebElement genderDropdown = driver.findElement(By.xpath("//button[@name='gender']"));
        genderDropdown.click();

        WebElement genderDropdownContent = driver.findElement(By.xpath("(//div[@role='listbox'])[1]"));

        List<WebElement> dropDownList = genderDropdownContent.findElements(By.tagName("lightning-base-combobox-item"));


        for (WebElement Gender : dropDownList) {
            String text = Gender.getText();
            String value = Gender.getAttribute("data-value");
            System.out.println("Item text: " + text + ", Item value: " + value);
        }

        for (WebElement Gender : dropDownList) {
            if (Gender.getText().equals("Man")) {
                Gender.click(); // Click the item to select it
                break;
            }
        }

        String buttonText = genderDropdown.getText();
        System.out.println("Selected option: " + buttonText);
//        } catch (Exception e) {
//            System.out.println("Dropdown not selected" +e.getMessage());
//        }
    }


//    ExtentReportUtil.AttachScreenshot(driver);


//    driver.switchTo().frame("iframe-name");
//    WebElement el = driver.findElement(By.xpath("(//a[contains(text(), 'Home')])[1]"));
//    el.click();
//    System.out.println(el.getText());
//    WebElement el1 = driver.findElement(By.id("mousehover"));
//    WebElement el2 = driver.findElement(By.xpath("(//div[@class='mouse-hover-content']//following-sibling::a)[2]"));
//
//    Actions act = new Actions(driver);
//    act.scrollToElement(el1);
//    Thread.sleep(3000);
//    act.moveToElement(el1).perform();
////        Thread.sleep(3000);
//    act.moveToElement(el2).click().perform();
//        Thread.sleep(3000);
//    WebElement el


}
