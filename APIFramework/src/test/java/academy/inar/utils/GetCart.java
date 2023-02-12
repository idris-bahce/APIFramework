package academy.inar.utils;

import academy.inar.pojos.response.Cart;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetCart {
    public static Cart getCart(){
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me").build();
        RequestSpecification request = given().spec(req);
        return request.when().post("/carts").as(Cart.class);
    }
}
