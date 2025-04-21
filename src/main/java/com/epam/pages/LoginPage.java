package com.epam.pages;

import com.epam.base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;


    @FindBy(xpath = "//a[@class = 'alert-link']")
    WebElement signIn;

    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(xpath = "//button[@class = 'btn btn-primary']")
    WebElement loginButton;

    @FindBy(xpath = "//div[@class = 'alert alert-danger']")
    WebElement errorMessage;

    public LoginPage() {
        this.driver = DriverManager.getInstance().getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver, this);
    }

    public void navigateToBasePage() {
        driver.get("http://localhost:8080/");
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signIn)).click();
    }

    public void enterCredentials(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isHomePageDisplayed() {
        try {

            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);

            WebElement homepageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class = 'lead']")));
            return homepageElement.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for homepage to load.");
            return false;
        }
    }

    public boolean isPasswordFieldMasked() {
        return passwordField.getAttribute("type").equals("password");
    }

    public boolean isErrorMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
