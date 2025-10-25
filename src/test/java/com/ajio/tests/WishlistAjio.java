package com.ajio.tests;

import org.testng.annotations.Test;
import com.ajio.base.BaseTest;
import com.ajio.pages.LoginPage;
import com.ajio.pages.ProductPage;
import com.ajio.pages.WishlistPage;

public class WishlistAjio extends BaseTest {

    @Test
    public void addProductToWishlist() {
        test = extent.createTest("Add Product to Wishlist");

        try {
            navigateToUrl("https://www.ajio.com");
            test.pass("Navigated to Ajio homepage");

            // ✅ Login first
            LoginPage login = new LoginPage(driver);
            login.clickSignIn();
            login.enterPhoneNumber("9949325222");
            login.clickContinue();
            test.pass("Logged in with phone number");

            // ✅ Manually enter OTP if needed
            Thread.sleep(10000); // Wait for OTP entry manually

            ProductPage product = new ProductPage(driver);
            product.searchProduct("Nike Shoes");
            product.selectFirstProduct();
            test.pass("Selected product");

            WishlistPage wishlist = new WishlistPage(driver);
            wishlist.clickWishlistIcon();
            test.pass("Clicked wishlist icon");

            if (wishlist.isWishlistActive()) {
                test.pass("Wishlist icon changed to 'REMOVE FROM WISHLIST'");
            } else {
                test.fail("Wishlist icon did not update — check login or locator.");
            }

        } catch (Exception e) {
            test.fail("Wishlist test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}