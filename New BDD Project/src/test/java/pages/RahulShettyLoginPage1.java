package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RahulShettyLoginPage1 {

    WebDriver driver;

    @FindBy(id = "username")
    WebElement userName;
    public WebElement getUserName(){
        return userName;
    }

    @FindBy(id = "password")
    WebElement passWord;
    public WebElement getPassWord(){
        return passWord;
    }
    @FindBy(xpath = "//span[contains(text(),'Admin')]/following::label[1]")
    WebElement usercheckmark;
    public WebElement getUsercheckmark(){
        return usercheckmark;
    }

    @FindBy(xpath = "//button[@id='okayBtn']")
    WebElement okayBtninPopup;
    public WebElement getOkayBtninPopup(){
        return okayBtninPopup;
    }

    @FindBy(xpath = "//select[@class='form-control']")
    WebElement drpdownP1;
    public WebElement getDrpdownP1(){
        return drpdownP1;
    }
    @FindBy(xpath = "//label[@for='terms']")
    WebElement termsp1;
    public WebElement getTermsp1(){
        return termsp1;
    }
    @FindBy(xpath = "//input[@id='signInBtn']")
    WebElement signinBtn;
    public WebElement getSigninBtn(){
        return signinBtn;
    }

    public RahulShettyLoginPage1(WebDriver driver){
        this.driver = driver ;
        PageFactory.initElements(driver,this);
    }

    public void enterusernameandpassword(String u , String p){
        getUserName().sendKeys(u);
        getPassWord().sendKeys(p);
    }



}
