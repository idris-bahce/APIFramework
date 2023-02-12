package academy.inar.utils;

import academy.inar.pojos.request.AddItem;
import academy.inar.pojos.response.Cart;
import academy.inar.pojos.response.Products;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class AddItemsToCart {
    public static void addItemsToCart(Cart cart){
        RequestSpecification requestForJson = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me")
                .setContentType(ContentType.JSON).build();
        //adding some items to cart
        Products[] products = GetProducts.getProducts();
        AddItem addItem = new AddItem();//serialising the request body
        addItem.setProductId(products[0].getId());
        RequestSpecification request = given().spec(requestForJson).body(addItem);
        request.when().post("/carts/"+cart.getCartId()+"/items");
    }
}
