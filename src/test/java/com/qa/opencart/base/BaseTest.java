package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public DriverFactory df;
    public LoginPage loginPage;
    public Properties prop;
    public AccountsPage accPage;
    public ResultsPage resultsPage;
    public ProductInfoPage productInfoPage;
    public RegistrationPage registrationPage;
    public SoftAssert softAssert;

    @BeforeTest
    public void setUp() {
        df = new DriverFactory();
        prop = df.initProperties();
        driver = df.initDriver(prop);
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();

    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}