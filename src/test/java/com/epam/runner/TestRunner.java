package com.epam.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features",//runs all features
        glue = {"com.epam.stepdefinitions", "com.epam.hooks"},//links step def
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber-reports/cucumber.json"        },
        monochrome = true,
        tags = "@abc"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}