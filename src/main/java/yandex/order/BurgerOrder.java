package yandex.order;

import io.restassured.response.ValidatableResponse;
import yandex.example.RestClient;
import yandex.client.User;

import static io.restassured.RestAssured.given;

/**
 * класс создания космобургера
 **/

public class BurgerOrder extends RestClient {
    public ValidatableResponse createOrder(Burger burger, User user) {
        if (user.getAccessToken() != null) {
            return given()
                    .spec(getBaseSpec())
                    .header("Authorization", user.getAccessToken())
                    .when()
                    .body(burger)
                    .post("orders")
                    .then();
        } else {
            return given()
                    .spec(getBaseSpec())
                    .when()
                    .body(burger)
                    .post("orders")
                    .then();
        }

    }


}
