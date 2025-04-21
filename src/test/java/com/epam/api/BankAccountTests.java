package com.epam.api;

import org.example.pojo.BankAccount;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;

public class BankAccountTests {
    RequestSpecification requestSpecification;
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTc0MDM4MzM4MSwiYXV0aCI6IlJPTEVfQURNSU4gUk9MRV9VU0VSIiwiaWF0IjoxNzQwMjk2OTgxfQ.Ekh-rATyRAep52IrGFnxKdDQouE26nNXoWVU-OOKdTvIybEHUZ9hNm37ZfRShvdQFO3zZmncG-jCMzZEq_T9Vg";

    String bankAccountId;

    @BeforeTest
    public void setUp(){
        RestAssured.baseURI = "http://localhost:9000";
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    @Test
    public void createBankAccount(){
        BankAccount bankAccount = new BankAccount(null, "Shubh Account", 10000);

        Response response = given()
                .spec(requestSpecification)
                .body(bankAccount)

                .when()
                .post("/api/bank-accounts")

                .then()
                .assertThat()
                .statusCode(201)
                .body("id", notNullValue())
                .log().all()
                .extract().response();

        bankAccountId = response.jsonPath().getString("id");
    }

    @Test
    public void getBankAccount(){
        given()
                .spec(requestSpecification)

                .when()
                .get("/api/bank-accounts")

                .then()
                .assertThat()
                .statusCode(200)
                .body("id", notNullValue())
                .log().all();
    }

    @Test(dependsOnMethods = "createBankAccount")
    public void updateBankAccount() {
        BankAccount bankAccount = new BankAccount(bankAccountId, "Savings Account", 10000);

        given()
                .spec(requestSpecification)
                .body(bankAccount)

                .when()
                .put("/api/bank-accounts/" + bankAccountId)

                .then()
                .assertThat()
                .statusCode(200)
                .log().all();
    }

    @Test(dependsOnMethods = "createBankAccount")
    public void patchBankAccount() {
        BankAccount bankAccount = new BankAccount(bankAccountId, "Savings Account", 10000);

        given()
                .spec(requestSpecification)
                .body(bankAccount)

                .when()
                .patch("/api/bank-accounts/" + bankAccountId)

                .then()
                .assertThat()
                .statusCode(200)
                .log().all();
    }

    @Test(dependsOnMethods = "createBankAccount")
    public void deleteBankAccount() {
        given()
                .spec(requestSpecification)

                .when()
                .delete("/api/bank-accounts/" + bankAccountId)

                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void getDeletedBankAccount() {
        given()
                .spec(requestSpecification)

                .when()
                .get("/api/bank-accounts/" + bankAccountId)

                .then()
                .assertThat()
                .statusCode(404)
                .log().all();
    }
}
