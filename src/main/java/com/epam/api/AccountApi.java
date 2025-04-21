package com.epam.api;

import io.restassured.response.Response;

import static com.epam.utils.ConfigReader.properties;
import static io.restassured.RestAssured.given;

public class AccountApi extends BaseApi {

    public Response registerUser(Object account) {
        String endpoint = properties.getProperty("account.post.endpoint");

        Response response = given()
                .spec(requestSpecification)
                .contentType("application/json") // explicitly set content type
                .body(account)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();

        System.out.println("Response Content-Type: " + response.getContentType());
        return response;
    }

    public Response getAccountDetails(String token) {
        addToken(token);

        Response response = given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get("/api/account")
                .then()
                .log().all()
                .extract()
                .response();

        System.out.println("Response Content-Type: " + response.getContentType());
        return response;
    }
}
