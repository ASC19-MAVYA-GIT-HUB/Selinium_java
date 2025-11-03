package com.ajio.utilities;
 
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
 
import java.io.File;

import java.io.IOException;

import java.nio.file.Files;

import java.nio.file.StandardCopyOption;
 
public class Screenshots {
 
    public static String captureScreenshot(WebDriver driver, String screenshotName) {

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir") + "/src/test/resources/Screenshots/" + screenshotName + ".png";

        File destFile = new File(path);

        destFile.getParentFile().mkdirs(); // Ensure directory exists
 
        try {

            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {

            e.printStackTrace();

        }
 
        return path;

    }

}
 