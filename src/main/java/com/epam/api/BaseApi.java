package com.epam.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.epam.utils.ConfigReader.properties;

public class BaseApi {

    protected RequestSpecification requestSpecification;

    public BaseApi() {
        String baseUri = properties.getProperty("base.api.url");
        int port = Integer.parseInt(properties.getProperty("base.port"));

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setPort(port)
                .setContentType("application/json")
                .build();
    }

    public void addToken(String token) {
        requestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(requestSpecification)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }
}
