package com.ajio.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.ajio.base.BaseTest;
import com.ajio.pages.ProductPage;
import com.ajio.pages.CheckoutPage;
import com.ajio.utilities.ScreenshotUtil;

public class CheckoutAjio extends BaseTest {

    @Test
    public void checkoutFlowTest() {
        test = extent.createTest("Checkout Flow");

        try {
            navigateToUrl("https://www.ajio.com");
            test.pass("Navigated to Ajio homepage");

            ProductPage product = new ProductPage(driver);
            product.searchProduct("Nike Shoes");
            product.selectFirstProduct();
            product.addToCart();
            product.goToCart();
            test.pass("Product added and navigated to cart");

            CheckoutPage checkout = new CheckoutPage(driver);
            checkout.proceedToCheckout();
            test.pass("Clicked Proceed to shipping");

            // ✅ Use your new validation method
            checkout.validateCheckoutFlow();

            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("checkout") || currentUrl.contains("shipping")) {
                test.pass("Checkout page reached: " + currentUrl);
                Assert.assertTrue(true);
            } else {
                test.warning("Blocked by login modal, toast, or still on cart. Checkout requires authentication.");
                Assert.fail("Checkout flow interrupted by login requirement.");
            }

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "CheckoutError");
            test.fail("Checkout flow failed: " + e.getMessage());
            test.addScreenCaptureFromPath(screenshotPath);
            Assert.fail("Checkout flow failed", e);
        }
    }
}