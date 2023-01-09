package yandex.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

/**
 * класс коннекта к Api
 **/
public class RestClient {

    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/";
    protected RequestSpecification getBaseSpec(){
        return new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(BASE_URL)
                .build();
    }
}
