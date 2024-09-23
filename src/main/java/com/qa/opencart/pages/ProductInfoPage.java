package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {

    private WebDriver driver;
    private ElementUtil elementUtil;


    private By productImages = By.xpath("//a[@class='thumbnail']");
    private By productHeader = By.cssSelector("div h1");
    private By addtoCartBtn = By.id("button-cart");
    private By productQuantity = By.id("input-quantity");
    private By addcartSuccessMsg = By.cssSelector("div.alert.alert-success.alert-dismissible");
    private By gotoCartIcon = By.cssSelector("div button.btn.btn-inverse.btn-block.btn-lg.dropdown-toggle");
    private By viewCartLink = By.xpath("(//p[@class='text-right']/a)[1]");
    private By productmetaDetails = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
    private By priceDetails = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public int getProductImgCount() {
        return elementUtil.getElements(productImages).size();
    }

    public Map<String, String> getProductMetaData() {
        Map<String, String> prodMap = new HashMap<String, String>();
        String productname = elementUtil.doGetText(productHeader);
        prodMap.put("productname", productname);
        getProdMetaDetails(prodMap);
        getProductPriceData(prodMap);
        return prodMap;
    }

    private void getProdMetaDetails(Map<String, String> prodMap) {
        List<WebElement> metaList = elementUtil.getElements(productmetaDetails);
        for (WebElement e : metaList) {
            String metaText = e.getText();
            String metaKey = metaText.split(":")[0].trim();
            String metaValue = metaText.split(":")[1].trim();
            prodMap.put(metaKey, metaValue);

        }
    }

    private void getProductPriceData(Map<String, String> prodMap) {
        List<WebElement> priceList = elementUtil.getElements(priceDetails);
        String actPrice = priceList.get(0).getText().trim();
        String exTaxPrice = priceList.get(1).getText().trim();
        prodMap.put("price",actPrice);
        prodMap.put("ExTaxPrice",exTaxPrice.split(":")[1].trim());

    }

    public String getProductHeaderText() {
        return elementUtil.doGetText(productHeader);
    }

    public boolean doAddToCartbtnExistorNot() {
        return elementUtil.doIsDiplayed(addtoCartBtn);
    }

    public WebElement doProductQuanityFieldExistorNot() {
        return elementUtil.getElement(productQuantity);
    }

    public ShoppingCartPage addtoCartFunctionality() {
        elementUtil.doGetText(addtoCartBtn);
        return new ShoppingCartPage(driver);
    }


}