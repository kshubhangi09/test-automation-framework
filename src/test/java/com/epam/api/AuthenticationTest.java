package com.epam.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthenticationTest {
    @Test
    public void testAuthenticationReturnsToken() {
        AuthApi authApi = new AuthApi();
        String token = authApi.getToken("admin", "admin");

        System.out.println("Received Token: " + token);
        Assert.assertNotNull(token, "Token should not be null");
        Assert.assertFalse(token.isEmpty(), "Token should not be empty");
    }
}
