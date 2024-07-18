package com.QM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPracticePage {

    private WebDriver driver;
    public LoginPracticePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

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

    @FindBy(xpath = "(//button[@class = 'btn btn-info'])[1]")
    private WebElement cartBtn;
    public WebElement getCartBtn(){
        return cartBtn;
    }
    @FindBy(xpath = "//a[@class = 'nav-link btn btn-primary']")
    private WebElement CheckoutBtn1;
    public WebElement getCheckoutBtn1(){
        return CheckoutBtn1;
    }
    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement ContinueShoppingBtn;
    public WebElement getContinueShoppingBtn(){
        return ContinueShoppingBtn;
    }
    @FindBy(xpath = "//button[@class='btn btn-success']")
    private WebElement checkoutfinalBtn;
    public WebElement getCheckoutfinalBtn(){
        return checkoutfinalBtn;
    }
    @FindBy(xpath = "//input[@id='country']")
    private WebElement deliverylocationBtn;
    public WebElement getDeliverylocationBtn(){
        return deliverylocationBtn;
    }
    @FindBy(xpath = "//label[contains(text(), 'I agree with the')]")
    private WebElement agreeTerms;
    public WebElement getAgreeTerms(){
        return agreeTerms;
    }
    @FindBy(xpath = "//input[@value='Purchase']")
    private WebElement purchasebtn;
    public WebElement getPurchasebtn(){
        return purchasebtn;
    }
    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    private WebElement successmessage;
    public WebElement getSuccessmessage(){
        return successmessage;
    }

}

