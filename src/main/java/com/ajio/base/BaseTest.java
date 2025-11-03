package com.ajio.base;
 
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.ajio.utilities.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
 
public class BaseTest {
    protected WebDriver driver;
    protected ExtentTest test;
    protected ExtentReports extent;
 
    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance(); // ✅ This must be called before any test
    }
 
    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
 
    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Fixed typo here
    }
 
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            //driver.quit();
        }
    }
 
    public void navigateUrl(String url) {
        driver.get(url);
    }
}
 