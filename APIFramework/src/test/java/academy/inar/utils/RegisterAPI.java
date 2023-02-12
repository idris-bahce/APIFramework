package academy.inar.utils;

import academy.inar.pojos.response.Token;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RegisterAPI {
    /*
    * This a class to take Authorization token
    */
    public static String getAuthorizationToken(){
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me")
                .setContentType(ContentType.JSON).build();

        RequestSpecification request = given().spec(req)
                .body("{\n" +
                        "   \"clientName\": \"sdfsd Boy\",\n" +
                        "   \"clientEmail\": \"sfdsd@example.com\"\n" +
                        "}");
        Token token = request.when().post("api-clients").as(Token.class);
        return token.getAccessToken();
    }
}
