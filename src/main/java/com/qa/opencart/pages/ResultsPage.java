package com.qa.opencart.pages;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultsPage {

    private WebDriver driver;
    private ElementUtil elementUtil;



    private By searchResultsHeading = By.cssSelector("div #content h1");
    private By searchResults = By.cssSelector("div.caption a");
    private By resultsProductName = By.cssSelector("div.caption h4 a");



    public ResultsPage(WebDriver driver) {
        this.driver= driver;
        elementUtil = new ElementUtil(driver);
    }


    public String getSearchResultPageHeader(){
        return elementUtil.doGetText(searchResultsHeading);

    }

    public int getSearchResultsCount(){
        return elementUtil.waitForElementsVisible(searchResults, Constants.DEFAULT_PAGE_LOAD_TIME).size();
    }

    public ProductInfoPage selectProductName(String mainProductName){
        List<WebElement> mainProductsList = elementUtil.getElements(resultsProductName);
        for (WebElement e: mainProductsList){
            String productsNames = e.getText().trim();
            if (productsNames.equalsIgnoreCase(mainProductName)){
                e.click();
                break;
            }
        }
        return new ProductInfoPage(driver);

    }





}
