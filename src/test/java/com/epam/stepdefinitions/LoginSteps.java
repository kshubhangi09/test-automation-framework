package com.epam.stepdefinitions;

import com.epam.base.DriverManager;
import com.epam.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    public LoginSteps() {
        this.driver = DriverManager.getInstance().getDriver();
        loginPage = new LoginPage();
    }

    @Given("the user is on the base page")
    public void basePage() {
        loginPage.navigateToBasePage();
    }

    @And("the user clicks on signIn")
    public void clickSignInLink(){
        loginPage.clickSignIn();
    }

    @When("the user enters valid credentials")
    public void validCredential(){
        loginPage.enterCredentials("admin", "admin");
    }

    @When("the user enters invalid credentials")
    public void invalidCredentials(){
        loginPage.enterCredentials("user1", "pasword1");
    }

    @And("clicks on the login button")
    public void loginButton(){
        loginPage.clickLogin();
    }

    @Then("the user should be redirected to the homepage")
    public void homePage(){
        Assert.assertTrue("User should be on homepage", loginPage.isHomePageDisplayed());
        driver.quit();
    }

    @Then("the user should see an error message")
    public void errorMessage(){
        Assert.assertTrue("Error message will be displayed", loginPage.isErrorMessageDisplayed());
    }

    @And("should not be redirected to the homepage")
    public void notRedirected() {
        try {
            Thread.sleep(2000);  // Temporary wait to avoid race condition
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertFalse("Failed to sign in! Please check your credentials and try again.", loginPage.isHomePageDisplayed());
    }

    @Given("the user navigates to the login page")
    public void navigatesToLoginPage() {
        loginPage.navigateToBasePage();
        loginPage.clickSignIn();
    }

    @When("the user clicks the login button without entering credentials")
    public void withoutRredentials() {
        loginPage.clickLogin();
    }

    @When("the user enters password")
    public void maskPassword(){
        Assert.assertTrue("Password field is not masked.", loginPage.isPasswordFieldMasked());
    }

    @Then("the password field should mask the entered characters")
    public  void maskedPassword(){
        Assert.assertTrue("Password field is not masked.", loginPage.isPasswordFieldMasked());
    }

    @Then("an error message should be displayed")
    public void errorMessageDisplayed() {
        Assert.assertTrue("Error message was not displayed", loginPage.isErrorMessageDisplayed());
    }
}
