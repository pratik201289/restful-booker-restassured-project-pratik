package com.restful.booker.crudtest;

import com.restful.booker.models.BookingPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BookingCURDTest extends TestBase {

    @Test
    public void getAllBookingIDs() {
        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getSingleBookingIDs() {
        Response response = given()
                .when()
                .get("/153");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void createBooking() {

        BookingPojo bookingPojo = new BookingPojo();
        HashMap<Object, Object> bookingDatesData = new HashMap<>();
        bookingDatesData.put("checkin", 2024 - 01 - 01);
        bookingDatesData.put("checkout", 2025 - 01 - 01);
        bookingPojo.setFirstname("Rango");
        bookingPojo.setLastname("Rio");
        bookingPojo.setTotalPrice(123);
        bookingPojo.setDepositPaid(true);
        bookingPojo.setBookingdates(bookingDatesData);
        bookingPojo.setAdditionalNeeds("Lunch");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("cookie", "token=4c6e40bd010c279")
                .body(bookingPojo)
                .when()
                .post("/booking");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void updateBooking() {
        HashMap<Object, Object> bookingDatesData = new HashMap<>();
        bookingDatesData.put("checkin", 2024 - 01 - 01);
        bookingDatesData.put("checkout", 2025 - 01 - 01);

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("Pratik");
        bookingPojo.setLastname("BB");
        bookingPojo.setTotalPrice(1200);
        bookingPojo.setDepositPaid(true);
        bookingPojo.setBookingdates(bookingDatesData);
        bookingPojo.setAdditionalNeeds("Lunch");
        Response response =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .auth().preemptive().basic("admin", "password123")
                        .header("Accept", "application/json")
                        .body(bookingPojo)
                        .when()
                        .put("/booking/48");
         response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void partialUpdateBooking() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("Rock");
        bookingPojo.setLastname("Rio");
        Response response =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                        .body(bookingPojo)
                        .when()
                        .patch("/1470");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void deleteBooking() {
        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete("/2559");
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void pingCheck() {
        Response response = given().log().all()
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .get("https://restful-booker.herokuapp.com/ping");
        response.then().statusCode(201);
        response.prettyPrint();

    }

}
