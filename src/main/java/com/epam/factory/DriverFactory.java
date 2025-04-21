package com.epam.factory;

import com.epam.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {
    public static WebDriver createDriver(){
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        System.out.println("Launching: " + browser);

        //We can use WebDriverManager as well -->
        switch(browser){
            case "edge":
                return new EdgeDriver();
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}
