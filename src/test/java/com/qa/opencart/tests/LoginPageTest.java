package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;


@Epic("Epic 001: Open cart demo app- Login Page ")
@Story("US 001: Login page features with some basic modules and functionalities ")
public class LoginPageTest extends BaseTest {


    @Description("Login Page Title Test")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void LoginPageTitleTest(){
        String title =loginPage.getLoginPageTitle();
        System.out.println("Title of the page is"+ title);
        Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
    }


    @Description("Login Page URL Test")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void LoginPageURLTest(){
        String url = loginPage.getLoginPageUrl();
        System.out.println("Title of the page is"+ url);
        Assert.assertTrue(url.contains( Constants.LOGIN_PAGE_URL));
    }

    @Description("Forgot Password link Test")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void isForgtPswrdDisplayingorNot(){
        Assert.assertTrue(loginPage.isForgtPswrdLinkExist());
    }

    @Description("Registered link Test")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void isRegisteredLinkPrsntorNot(){
        Assert.assertTrue(loginPage.isRegisterLinkExist());
    }

    @Description("Login Functionality Test")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void loginTest(){
        accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
    }







}