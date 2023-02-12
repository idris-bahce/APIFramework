package academy.inar.stepDefinitions;

import academy.inar.pojos.request.AddItem;
import academy.inar.pojos.request.Order;
import academy.inar.pojos.response.Cart;
import academy.inar.pojos.response.Products;
import academy.inar.pojos.response.StatusResponse;
import academy.inar.utils.AddItemsToCart;
import academy.inar.utils.GetCart;
import academy.inar.utils.GetProducts;
import academy.inar.utils.RegisterAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class APIStep {
    Products[] products;//This our product list. We can use it any time.
    Cart cart;
    RequestSpecification request;
    RequestSpecification requestForJson;
    @Given("send the uri with given status")
    public void send_the_uri_with_given_status() {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me").build();
        request = given().when().spec(req);
    }
    @Then("verify that status code is {string}")
    public void verify_that_status_code_is(String expected) {
        StatusResponse response = request.when().get("/status").then().extract().response().as(StatusResponse.class);
        Assert.assertEquals(response.getStatus(),expected);
    }
    @Given("enter given url {string}")
    public void enter_given_url(String uri) {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri(uri).build();
        request = given().spec(req);
    }
    @Then("enter the end point {string} store the response to products array and verify status code {string}")
    public void enter_the_end_point_store_the_response_to_products_array_and_verify_status_code(String endPoint, String statusCode) {
        products = request.when().get(endPoint).as(Products[].class);
        System.out.println(products[4].getCategory());
        request.when().get(endPoint).then().assertThat().statusCode(Integer.parseInt(statusCode));
    }
    @Then("post the end point {string} and verify status code {string}")
    public void post_the_end_point_and_verify_status_code(String endPoint, String statusCode) {
        cart = request.when().post(endPoint).as(Cart.class);
        request.when().post(endPoint).then().assertThat().statusCode(Integer.parseInt(statusCode));
    }
    @Given("enter given url {string} and use json format")
    public void enter_given_url_and_use_json_format(String uri) {
        requestForJson = new RequestSpecBuilder().setBaseUri(uri).setContentType(ContentType.JSON).build();

    }
    @Then("serialise the request json body and post the end point and verify status code {string}")
    public void serialise_the_request_json_body_and_post_the_end_point_and_verify_status_code(String statusCode) {
        products = GetProducts.getProducts();
        cart = GetCart.getCart();
        AddItem addItem = new AddItem();//serialising the request body
        addItem.setProductId(products[0].getId());
        request = given().spec(requestForJson).body(addItem);
        request.when().post("/carts/"+cart.getCartId()+"/items").then().statusCode(Integer.parseInt(statusCode));
    }
    @Then("enter requested parameters with end point {string} and verify statuscode {string}")
    public void enter_requested_parameters_with_end_point_and_verify_statuscode(String endPoint, String statusCode) {
        String token = RegisterAPI.getAuthorizationToken();
        cart = GetCart.getCart();
        Order order = new Order();//serialising the request body
        order.setCartId(cart.getCartId());
        order.setCustomerName("idris");

        AddItemsToCart.addItemsToCart(cart);//adding some items to cart

        request = given().when().spec(requestForJson).body(order).header("Authorization",token);
        request.when().post(endPoint).then().statusCode(Integer.parseInt(statusCode));
    }
}
