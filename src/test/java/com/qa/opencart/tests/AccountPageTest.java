package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class AccountPageTest extends BaseTest {


    @BeforeClass
    public void accPageSetup() {
        accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
    }

    @Test
    public void searchBoxTest() {
        Assert.assertTrue(accPage.doSearchBoxExist());
    }

    @Test
    public void logoutLinkTest() {
        Assert.assertTrue(accPage.doLogoutLinkExist());
    }

    @Test
    public void searchIconTest() {
        Assert.assertTrue(accPage.doSearchIconExist());
    }

    @Test
    public void accountHeaderTest() {
        List<String> actHeaderList = accPage.verifyAccountHeaders();
        System.out.println(actHeaderList);
        Assert.assertEquals(actHeaderList, Constants.EXPECTED_HEADER_LIST);
    }

    @Test
    public void searchResultsHeaderTest() {
        String actHeaderText = resultsPage.getSearchResultPageHeader();
        Assert.assertEquals(actHeaderText,Constants.SEARCH_PAGE_HEADER_TEXT_APPLE);
    }

    @DataProvider
    public Object[][] getSearchProductData() {
        return new Object[][]{
                {"macbook"},
                {"imac"},
                {"apple"}
        };
    }

    @Test(dataProvider = "getSearchProductData")
    public void searchProductTest(String productName) {
        resultsPage = accPage.doSearch(productName);
        Assert.assertTrue(resultsPage.getSearchResultsCount() > 0);
    }

    @DataProvider
    public Object[][] getProductSelectData() {
        return new Object[][]{
                {"macbook", "MacBook Air"},
                {"imac", "iMac"},
                {"apple", "Apple Cinema 30\""}
        };
    }

    @Test(dataProvider = "getProductSelectData")
    public void selectProductFromSearchResults(String productName, String mainProductName) {
        resultsPage = accPage.doSearch(productName);
        resultsPage.selectProductName(mainProductName);


    }

}