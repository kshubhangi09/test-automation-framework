package com.epam.hooks;

import com.epam.base.DriverManager;
import com.epam.factory.DriverFactory;
import com.epam.utils.Log;
import com.epam.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.Scenario;

public class TestHooks {
    WebDriver driver;

    @Before
    public void setUp(Scenario scenario){
        RestAssured.defaultParser = Parser.JSON;
        Log.startTestCase(scenario.getName());
        Log.info("Scenario Tags: " + scenario.getSourceTagNames());

        Log.info("Initializing WebDriver....");
        driver = DriverFactory.createDriver();
        DriverManager.getInstance().setDriver(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            Log.error("Scenario failed: " + scenario.getName());
            ScreenshotUtil.captureScreenshot(DriverManager.getInstance().getDriver(), scenario.getName());

        } else {
            Log.info("Scenario passed: " + scenario.getName());
        }
        Log.info("Closing WebDriver after scenario execution");

        if (DriverManager.getInstance().getDriver() != null) {
            DriverManager.getInstance().getDriver().quit();
            DriverManager.getInstance().removeDriver();
        }

        Log.endTestCase(scenario.getName());
    }
}
