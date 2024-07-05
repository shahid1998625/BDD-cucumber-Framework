package pages.Outlook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class outlookPage {
    WebDriver driver;

    public outlookPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@type='email']")
    WebElement usernameoutlook;
    public WebElement getUsernameoutlook(){
        return usernameoutlook;
    }
    @FindBy(xpath = "//input[@id='idSIButton9']")
    WebElement nextBtn1;
    public WebElement getNextBtn1(){
        return nextBtn1;
    }
    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordoutlook;
    public WebElement getPasswordoutlook(){
        return passwordoutlook;
    }
    @FindBy(xpath = "//input[@id='idSIButton9']")
    WebElement signInBtn;
    public WebElement getSignInBtn(){
        return signInBtn;
    }
    @FindBy(xpath = "//input[@id='idBtn_Back']")
    WebElement noBtnpopup;
    public WebElement getNoBtnpopup(){
        return noBtnpopup;
    }
    @FindBy(xpath = "//input[@id='KmsiCheckboxField']")
    WebElement backBtnpopup;
    public WebElement getBackBtnpopup(){
        return backBtnpopup;
    }
    @FindBy(xpath = "//button[@id='O365_MainLink_NavMenu']")
    WebElement mainnavlinkBtn;
    public WebElement getMainnavlinkBtn(){
        return mainnavlinkBtn;
    }
    @FindBy(xpath = "//button[@title='Outlook']")
    WebElement outlookBtn;
    public WebElement getOutlookBtn(){
        return outlookBtn;
    }
    @FindBy(xpath = "//button[@aria-label='New mail']")
    WebElement newemailBtn;
    public WebElement getNewemailBtn(){
        return newemailBtn;
    }
    @FindBy(xpath = "//div[@aria-label='To']")
    WebElement toBtnEmail;

    public WebElement getToBtnEmail(){
        return toBtnEmail;
    }
    @FindBy(xpath = "//div[@aria-label='Cc']")
    WebElement ccBtnEmail;

    public WebElement getCcBtnEmail(){
        return ccBtnEmail;
    }
    @FindBy(xpath = "//input[@aria-label='Add a subject']")
    WebElement addSubjecttextBox;
    public WebElement getAddSubjecttextBox(){
        return addSubjecttextBox;
    }
    @FindBy(xpath = "//div[@id='Signature']/preceding-sibling::div[1]")
    WebElement messageBodyText;
    public WebElement getMessageBodyText(){
        return messageBodyText;
    }
    @FindBy(xpath = "//button[@title='Send (Ctrl+Enter)']")
    WebElement sendemailBtn;
    public WebElement getSendemailBtn(){
        return sendemailBtn;
    }






}
