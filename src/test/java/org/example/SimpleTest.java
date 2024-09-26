package org.example;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class SimpleTest {
    @Test
    public void createUnicorn(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        String id = given()
                .body("{\n" +
                      "  \"name\": \"Pinky Pie\",\n" +
                      "  \"color\": \"pink\"\n" +
                      "}")
                .contentType(ContentType.JSON)
        .when()
                .post("https://crudcrud.com/api/857ed21a992c4387a41b686060c99574/unicorn")
        .then()
                .assertThat()
                .statusCode(201)
                .body("$", hasKey("_id"))
        .extract()
                .path("_id");

        given()
                .body("{\n" +
                      "  \"name\": \"Pinky Pie\",\n" +
                      "  \"color\": \"purple\"\n" +
                      "}")
                .contentType(ContentType.JSON)
                .when()
                .put("https://crudcrud.com/api/857ed21a992c4387a41b686060c99574/unicorn/" + id)
                .then()
                .assertThat()
                .statusCode(200);



        given().delete("https://crudcrud.com/api/857ed21a992c4387a41b686060c99574/unicorn/" + id)
                .then()
                .assertThat()
                .statusCode(200);

        given()
                .get("https://crudcrud.com/api/857ed21a992c4387a41b686060c99574/unicorn/" + id)
                .then()
                .assertThat()
                .statusCode(404);




    }

}

