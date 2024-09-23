package com.qa.opencart.pages;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.SQLOutput;

public class RegistrationPage {

    private ElementUtil elementUtil;


    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telePhone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmPassword = By.id("input-confirm");
    private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[1]/input[@type='radio']");
    private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[2]/input[@type='radio']");
    private By checkBox = By.xpath("//input[@type='checkbox']");
    private By continueBtn = By.xpath("//input[@type='submit']");
    private By sucessMsg = By.cssSelector("div#content h1");
    private By logoutlink = By.linkText("Logout");
    private By regiserLink = By.linkText("Register");


    public RegistrationPage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
    }

    public boolean registration(String firstName, String lastName, String email, String telePhone, String password, String subscribe) {
        getregistration(firstName, lastName, email, telePhone, password);
        getsubscription(subscribe);
        agreechkAndContinue();
        return registrationStatus();
    }

    private void getregistration(String firstName, String lastName, String email, String telePhone, String password) {
        elementUtil.doSendKeys(this.firstName, firstName);
        elementUtil.doSendKeys(this.lastName, lastName);
        elementUtil.doSendKeys(this.email, email);
        elementUtil.doSendKeys(this.telePhone, telePhone);
        elementUtil.doSendKeys(this.password, password);
        elementUtil.doSendKeys(this.confirmPassword, password);
    }

    private void getsubscription(String subscribe) {
        if (subscribe.equalsIgnoreCase("yes")) {
            elementUtil.doClick(subscribeYes);
        }
        elementUtil.doClick(subscribeNo);
    }

    private void agreechkAndContinue() {
        elementUtil.doClick(checkBox);
        elementUtil.doClick(continueBtn);


    }

    private boolean registrationStatus() {
        elementUtil.waitForElementPresent(sucessMsg, 10);
        String msg = elementUtil.doGetText(sucessMsg);

        System.out.println(msg);
        if (msg.contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
            elementUtil.doClick(logoutlink);
            elementUtil.doClick(regiserLink);
            return true;
        }

        return false;
    }


}
