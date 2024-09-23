package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTest extends BaseTest {

    @BeforeClass
    public void regPageSetup() {
        registrationPage = loginPage.navigateToRegisterPage();
    }

    public String getRandomNumber(){
        Random random = new Random();
        String email = "testautomation"+ random.nextInt(7000)+"@gmail.com";
        return email;
    }

    @DataProvider
    public Object[][] getRegData(){
        Object data[][]= ExcelUtils.getTestData(Constants.REGISTER_SHEET_NAME);
        return data;
    }


    @Test(dataProvider = "getRegData")
    public void registerUserTest(String firstName, String lastName, String email, String telePhone, String password, String subscribe) {
        Assert.assertTrue(registrationPage.registration(firstName, lastName, getRandomNumber(), telePhone, password, subscribe));
    }


}