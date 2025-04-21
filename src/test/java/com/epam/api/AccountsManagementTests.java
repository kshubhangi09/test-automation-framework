package com.epam.api;

import org.example.pojo.Account;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AccountsManagementTests {

    RequestSpecification requestSpecification;
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTc0MDM4MzM4MSwiYXV0aCI6IlJPTEVfQURNSU4gUk9MRV9VU0VSIiwiaWF0IjoxNzQwMjk2OTgxfQ.Ekh-rATyRAep52IrGFnxKdDQouE26nNXoWVU-OOKdTvIybEHUZ9hNm37ZfRShvdQFO3zZmncG-jCMzZEq_T9Vg";



    @BeforeTest
    public void setUp(){
        RestAssured.baseURI = "http://localhost:9000";
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    @Test
    public void registerUser() {
        Account newUser = new Account("aj", "aj@example.com", "ajPassword");

        Response response = given()
                .spec(requestSpecification)
                .body(newUser)
                .log().all()

                .when()
                .post("/api/account")

                .then()
                .assertThat()
                .statusCode(201)
                .body("login", notNullValue())
                .log().all()
                .extract().response();
    }
    @Test
    public void resetPassword() {
        String email = "testuser@example.com";

        Response response = given()
                .spec(requestSpecification)
                .body("{\"email\": \"" + email + "\"}")
                .log().all()

                .when()
                .post("/api/account/reset-password/init")

                .then()
                .assertThat()
                .statusCode(200)
                .log().all()
                .extract().response();
    }


}
