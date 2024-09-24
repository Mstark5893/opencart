package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class CartPage {

    private By cart = By.id("cart");

    public CartPage(){
        System.out.println("Cart Page");
    }

    public void addToCart(){
        System.out.println("Add to cart page");
    }
}
