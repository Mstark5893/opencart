package com.qa.opencart.tests;


import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductInfoTest extends BaseTest {


    @BeforeClass
    public void productInfoPageSetup() {
        accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());

    }

    @DataProvider
    public Object[][] getProductSelectData() {
        return new Object[][]{
                {"macbook", "MacBook", "MacBook"},
                {"macbook", "MacBook Air", "MacBook Air"},
                {"macbook", "MacBook Pro", "MacBook Pro"},

        };
    }

    @Test(dataProvider = "getProductSelectData")
    public void verifyProductHeaderTest(String productName, String mainProductName, String headerText) {
        resultsPage = accPage.doSearch(productName);
        productInfoPage = resultsPage.selectProductName(mainProductName);
        String ActProdHeaderText = productInfoPage.getProductHeaderText();
        Assert.assertEquals(ActProdHeaderText, headerText);
    }


    @DataProvider
    public Object[][] getImageData() {
        return new Object[][]{
                {"macbook", "MacBook Air", 4},
                {"iMac", "iMac", 3},
                {"Apple", "Apple Cinema 30\"", 6},

        };
    }

    @Test(dataProvider = "getImageData")
    public void getProductImageCountTest(String productName, String mainProductName, int imageCount) {
        resultsPage = accPage.doSearch(productName);
        productInfoPage = resultsPage.selectProductName(mainProductName);
        Assert.assertEquals(productInfoPage.getProductImgCount(), imageCount);
    }

    @Test
    public void verifyAddToCrtBtnExist() {
        Assert.assertTrue(productInfoPage.doAddToCartbtnExistorNot());
    }

    @Test
    public void productMetaDataTest() {
        resultsPage = accPage.doSearch("macbook");
        productInfoPage = resultsPage.selectProductName("MacBook Pro");
        Map<String, String> actualProdMap = productInfoPage.getProductMetaData();
        actualProdMap.forEach((k, v) -> System.out.println(k + ":" + v));
        softAssert.assertEquals(actualProdMap.get("productname"),"MacBook Pro");
        softAssert.assertEquals(actualProdMap.get("Brand"),"Apple");
        softAssert.assertEquals(actualProdMap.get("Product Code"),"Product 18");
        softAssert.assertEquals(actualProdMap.get("price"),"$2,000.00");
        softAssert.assertAll();




    }

}