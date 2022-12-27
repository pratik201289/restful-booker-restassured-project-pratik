package com.restful.booker.crudtest;

import com.restful.booker.models.LoginPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GenerateAuthTest {

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "/auth";
    }

    @Test
    public void generateAuthToken() {

        LoginPojo bookingPojo = new LoginPojo();
        bookingPojo.setUsername("admin");
        bookingPojo.setPassword("password123");
        Response response =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .body(bookingPojo)
                        .when()
                        .post();
        response.then().statusCode(200);
        response.prettyPrint();
    }


}
