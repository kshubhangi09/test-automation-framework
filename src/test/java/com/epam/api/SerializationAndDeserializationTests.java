package com.epam.api;

import org.example.pojo.Post;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SerializationAndDeserializationTests {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    // Setup Base URI
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void serializedRequest() {
        // Create a new Post object
        Post post = new Post(1, 1, "Serialization Test", "This is a test post for serialization.");

        // Serialize and send POST request
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(post) // Serialization

                .when()
                .post("/posts")

                .then()
                .statusCode(201)
                .log().all()
                .extract()
                .response();

    }

    @Test
    public void deserializedRequest() {
        // Send GET request and Deserialize JSON response as an array
        Post[] posts = RestAssured
                .given()
                .contentType(ContentType.JSON)

                .when()
                .get("/posts")

                .then()
                .statusCode(200)
                .extract()
                .as(Post[].class); // Deserialization

        // Basic assertion to check if the array is not empty
        Assert.assertTrue(posts.length > 0, "Posts array is empty!");

    }
}
