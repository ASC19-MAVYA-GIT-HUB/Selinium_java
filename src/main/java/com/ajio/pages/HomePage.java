package com.ajio.pages;
 
import java.time.Duration;
 
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
 
public class HomePage {

    WebDriver driver;

    WebDriverWait wait;
 
    public HomePage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
 
    public void searchProduct(String productName) {

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(

            By.cssSelector("input[placeholder*='Search']")));

        searchBox.sendKeys(productName + Keys.ENTER);

    }

}
 