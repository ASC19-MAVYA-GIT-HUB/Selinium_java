package com.ajio.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.ajio.utilities.ElementUtil;

public class WishlistPage {

    WebDriver driver;
    ElementUtil utilities;

    // ✅ Flexible XPath for wishlist icon
    By wishlistIcon = By.xpath("//span[contains(@aria-label,'wishlist') or contains(@class,'wishlist')]");

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        this.utilities = new ElementUtil(driver); // ✅ Initialize ElementUtil
    }

    public void clickWishlistIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // 🔐 Check if login modal is present
            if (!driver.findElements(By.xpath("//div[contains(@class,'login-modal')]")).isEmpty()) {
                System.out.println("🔐 Login required before wishlist. Handling login...");
                handleLoginFlow();
            }

            // 🧱 Wait for loader to disappear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));

            // 📌 Wait for wishlist icon to be present
            By wishlistIcon = By.xpath("//span[contains(@class,'wishlist') and contains(@aria-label,'WISHLIST')]");
            WebElement icon = wait.until(ExpectedConditions.presenceOfElementLocated(wishlistIcon));

            // 🧭 Scroll into view and click via JS
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", icon);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", icon);

            System.out.println("✅ Wishlist icon clicked.");
        } catch (TimeoutException te) {
            System.out.println("❌ Wishlist icon not found in time: " + te.getMessage());
            throw new RuntimeException("Could not click wishlist icon.");
        } catch (Exception e) {
            System.out.println("❌ Wishlist click failed: " + e.getMessage());
            throw new RuntimeException("Could not click wishlist icon.");
        }
    }

    public void handleLoginFlow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // 🧱 Wait for modal and loader to settle
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'login-modal')]")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));

            // 📱 Enter phone number
            WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='tel']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", phoneInput);
            phoneInput.clear();
            phoneInput.sendKeys("9949325222"); // Replace with your actual number

            // 👉 Click Continue via JS
            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);

            // ⏳ Wait for manual OTP entry
            System.out.println("📲 Waiting for manual OTP entry...");
            Thread.sleep(15000); // Adjust if needed

            // ✅ Wait for modal to fully disappear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='login-modal' and contains(@class,'modal-sign-in-up')]")));
            System.out.println("✅ Login modal cleared. Proceeding with test.");
        } catch (Exception e) {
            System.out.println("Login flow failed: " + e.getMessage());
            throw new RuntimeException("❌ Login flow interrupted.");
        }
    }
    public boolean isWishlistActive() {
        try {
            Thread.sleep(2000); // Let DOM settle
        } catch (InterruptedException ignored) {}

        return driver.findElement(wishlistIcon).isDisplayed();
    }
}