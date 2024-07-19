package com.QM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class greyQMPage {
    WebDriver driver;

    public greyQMPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "username")
    WebElement username;
    public WebElement getUsername(){
        return username;
    }

    @FindBy(id = "password")
    WebElement key;
    public WebElement getKey(){
        return key;
    }

    @FindBy(css = "button[type='submit']")
    WebElement signInBtn;
    public WebElement getSignInBtn(){
        return signInBtn;
    }

    @FindBy(xpath = "//span[@class='inline-block image-gt-icon-widget h-2.5x w-2.5x ng-star-inserted']")
    WebElement holidays;
    public WebElement getHolidays(){
        return holidays;
    }
    @FindBy(xpath = "//h1[contains(text(), 'Holiday Calendar')]")
    WebElement holidaycalendertext;
    public WebElement getHolidaycalendertext(){
        return holidaycalendertext;
    }

}

