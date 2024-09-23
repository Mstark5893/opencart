package com.qa.opencart.pages;


import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private By accountheaders = By.cssSelector("div#content h2");
    private By searchBox = By.name("search");
    private By searchIcon = By.cssSelector("button.btn.btn-default.btn-lg");
    private By logoutLink = By.linkText("Logout");


    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public boolean doSearchBoxExist() {
        return elementUtil.doIsDiplayed(searchBox);
    }

    public boolean doLogoutLinkExist() {
        return elementUtil.doIsDiplayed(logoutLink);
    }

    public boolean doSearchIconExist() {
        return elementUtil.doIsDiplayed(searchIcon);
    }

    public List<String> verifyAccountHeaders() {
        List<WebElement> accntHeaders = elementUtil.getElements(accountheaders);
        List<String> headerList = new ArrayList<String>();
        for (WebElement e : accntHeaders) {
            headerList.add(e.getText());
        }
        return headerList;
    }

    public ResultsPage doSearch(String productName) {
        System.out.println("The name of the product to be searched is " + productName);
        elementUtil.doSendKeys(searchBox, productName);
        elementUtil.doClick(searchIcon);
        return new ResultsPage(driver);


    }


}

