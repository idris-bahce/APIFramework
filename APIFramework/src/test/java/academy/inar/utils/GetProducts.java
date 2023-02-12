package academy.inar.utils;

import academy.inar.pojos.response.Products;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetProducts {

    public static Products[] getProducts(){
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me").build();
        RequestSpecification request = given().spec(req);
        return request.when().get("/products").as(Products[].class);
    }
}
