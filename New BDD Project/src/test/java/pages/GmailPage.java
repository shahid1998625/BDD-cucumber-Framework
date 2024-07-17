package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.font.TextHitInfo;
import java.time.Duration;

public class GmailPage {
    WebDriver driver;
    public GmailPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@type='email']")
    WebElement username;
    public WebElement getUsername(){
        return username;
    }
    @FindBy(xpath = "//div[@id='identifierNext']")
    WebElement nextBtnuser;
    public WebElement getNextBtnuser(){
        return nextBtnuser;
    }

    @FindBy(xpath = "//input[@type='password']")
    WebElement password;
    public WebElement getPassword(){
        return password;
    }
    @FindBy(xpath = "//span[contains(text(), 'Next')]")
    WebElement nextBtnPw;
    public WebElement getNextBtnPw(){
        return nextBtnPw;
    }
    @FindBy(xpath = "//div[contains(text(), 'Compose')]")
    WebElement composeBtn;
    public WebElement getComposeBtn(){
        return composeBtn;
    }
    @FindBy(xpath = "//input[@name='subjectbox']")
    WebElement subjectgmail;
    public WebElement getSubjectgmail(){
        return subjectgmail;
    }
    @FindBy(xpath = "//input[@aria-label='To recipients']")
    WebElement toBtnincompose;
    public WebElement getToBtnincompose(){
        return toBtnincompose;
    }
    @FindBy(xpath = "//div[@role='textbox']")
    WebElement messageBody;
    public WebElement getMessageBody(){
        return messageBody;
    }
    @FindBy(xpath = "//div[@command='signature']")
    WebElement signatureBtn;
    public WebElement getSignatureBtn(){
        return signatureBtn;
    }

    @FindBy(xpath = "//div[contains(text(), 'My Signature')]")
    WebElement Mysignature;
    public WebElement getMysignature(){
        return Mysignature;
    }

    @FindBy(xpath = "//div[@class='gmail_signature']")
    WebElement AfterMySignatureText;
    public WebElement getAfterMySignatureText(){
        return AfterMySignatureText;
    }
    @FindBy(xpath = "//ul[@class='h-c-header__cta-list']/li[2]")
    WebElement gotoGoogleAcc;
    public WebElement getGotoGoogleAcc(){
        return gotoGoogleAcc;
    }
    @FindBy(xpath = "//a[@aria-label='Google apps']")
    WebElement googleAppsBtn;
    public WebElement getGoogleAppsBtn(){
        return googleAppsBtn;
    }
    @FindBy(xpath = "//span[contains(text(),'Gmail')]")
    WebElement gmailBtn;
    public WebElement getGmailBtn(){
        return gmailBtn;
    }

    public void enterusernameandpasswordgmail(String pw){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
        getPassword().sendKeys(pw);
        getNextBtnPw().click();
    }
}
