package com.ajio.pages;
 
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;
 
public class SearchProduct {

    WebDriver driver;
 
    By searchBox = By.name("searchVal");
 
    public SearchProduct(WebDriver driver) {

        this.driver = driver;

    }
 
    public void searchProduct(String productName) {

        driver.findElement(searchBox).sendKeys(productName + Keys.ENTER);

    }

}
 