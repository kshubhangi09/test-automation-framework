package com.epam.base;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    //ThreadLocal to maintain separate Webdriver insatnces for each thread
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    //To prevent instantiation
    private DriverManager(){}

    private static DriverManager instance = new DriverManager();

    public static DriverManager getInstance(){
        return instance;
    }

    public void setDriver(WebDriver driver){
        driverThreadLocal.set(driver);
    }

    public WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    public void removeDriver() {
        System.out.println("Inside removeDriver");
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
            System.out.println("Removed driver");
        }
    }
}
