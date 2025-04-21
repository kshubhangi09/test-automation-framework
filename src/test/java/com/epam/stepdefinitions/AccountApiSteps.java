package com.epam.stepdefinitions;

import com.epam.api.AccountApi;
import org.example.pojo.Account;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;

public class AccountApiSteps {

    private AccountApi accountApi;
    private Account account;
    private Response response;

    public AccountApiSteps() {
        this.accountApi = new AccountApi();
    }

    @Given("a user with login {string}, email {string}, and password {string}")
    public void a_user_with_login_email_and_password(String login, String email, String password) {
        account = new Account(login, email, password);
    }

    @When("the user registers via the API with username {string} and password {string}")
    public void the_user_registers_via_the_api_with_auth(String username, String password) {
        response = accountApi.registerUser(account);
    }


    @Then("the API response status code should be {int}")
    public void the_api_response_status_code_should_be(Integer expectedStatusCode) {
        assertEquals((Integer) response.statusCode(), expectedStatusCode);
    }

    @Then("the API response should not have a body")
    public void the_api_response_should_not_have_a_body() {
        assertEquals("", response.getBody().asString());
    }
}
