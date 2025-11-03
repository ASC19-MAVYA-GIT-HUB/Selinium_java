package com.ajio.pages;
 
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import java.util.Set;
 
public class AddToCart {

    WebDriver driver;
 
    By firstProduct = By.cssSelector(".rilrtl-products-list__item");

    By addToCartButton = By.xpath("//span[text()='ADD TO BAG']");
 
    public AddToCart(WebDriver driver) {

        this.driver = driver;

    }
 
    public void selectFirstProductAndAddToCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
 
        // Switch to new tab

        Set<String> handles = driver.getWindowHandles();

        for (String handle : handles) {

            driver.switchTo().window(handle);

        }
 
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

    }

}
 