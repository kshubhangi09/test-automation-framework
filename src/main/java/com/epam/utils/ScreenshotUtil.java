package com.epam.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    public static void captureScreenshot(WebDriver driver, String scenarioName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Simple file name without timestamp
            String fileName = scenarioName.replaceAll(" ", "_") + ".png";
            File destFile = new File("target/screenshots/" + fileName);

            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

