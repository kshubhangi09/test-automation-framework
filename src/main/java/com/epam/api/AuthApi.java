package com.epam.api;

import org.example.pojo.Authenticate;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseApi {

    public String authenticate(String username, String password) {
        Authenticate credentials = new Authenticate(username, password, true);

        Response response = given()
                .spec(requestSpecification)
                .body(credentials)
                .when()
                .post("/api/authenticate");

        response.then().statusCode(200); // Optional check
        return response.jsonPath().getString("id_token");
    }


    public Response login(Authenticate authRequest) {
        return given()
                .spec(requestSpecification)
                .body(authRequest)
                .when()
                .post("/api/authenticate");
    }

    public String getToken(String username, String password) {
        Authenticate authRequest = new Authenticate(username, password, true);

        Response response = login(authRequest);
        response.then().statusCode(201);

        return response.jsonPath().getString("id_token");
    }
}
