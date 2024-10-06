package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    //1. By locators- page object
    private By emailId = By.id("input-email");
    private By password = By.id("input-password");
    private By loginbtn = By.xpath("//input[@type='submit']");
    private By forgotPasswrdLink = By.partialLinkText("Forgotten Password");
    private By registerLink = By.partialLinkText("Register");


    //2. Constructor public so that we can create obj

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    //3. Page actions

    @Step("Getting login page title ")
    public String getLoginPageTitle() {
        return driver.getTitle();
    }

    @Step("Getting login page url ")
    public String getLoginPageUrl() {
        return driver.getCurrentUrl();
    }


    @Step("Checking forgot password link present or not")
    public boolean isForgtPswrdLinkExist() {
        //return driver.findElement(forgotPasswrdLink).isDisplayed();
        return elementUtil.doIsDiplayed(forgotPasswrdLink);
    }

    @Step("Checking registered link present or not")
    public boolean isRegisterLinkExist() {
        return elementUtil.doIsDiplayed(registerLink);
    }


    @Step("Login with username : {0} and password : {1}")
    public AccountsPage doLogin(String un, String pswrd) {
        System.out.println("Credentaials are :" + un + ":" + pswrd);
        elementUtil.doSendKeys(emailId, un);
        elementUtil.doSendKeys(password, pswrd);
        elementUtil.doClick(loginbtn);
        return new AccountsPage(driver);

    }

    @Step("Navigating to registration page...")
    public RegistrationPage navigateToRegisterPage() {
        elementUtil.doClick(registerLink);
        return new RegistrationPage(driver);
    }


}