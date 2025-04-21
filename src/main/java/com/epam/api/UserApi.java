package com.epam.api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserApi extends BaseApi{
    public Response getUserByLogin(String login) {
        return given()
                    .spec(requestSpecification)
                    .pathParam("login", login)
                    .when()
                    .get("user.get.endpoint")
                    .then()
                    .extract().response();
    }

}
