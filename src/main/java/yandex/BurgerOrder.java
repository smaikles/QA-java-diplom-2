package yandex;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import yandex.client.RestClient;
import yandex.pojo.Burger;
import yandex.pojo.User;

import static io.restassured.RestAssured.given;

/**
 * класс работы с заказами
 **/

public class BurgerOrder extends RestClient {

    protected final String ROOT = "/orders";

    @Step("Создание Космобургера")
    public ValidatableResponse createOrder(Burger burger, User user) {
        if (user.getAccessToken() != null) {
            return given()
                    .spec(getBaseSpec())
                    .header("Authorization", user.getAccessToken())
                    .when()
                    .body(burger)
                    .post(ROOT)
                    .then();
        } else {
            return given()
                    .spec(getBaseSpec())
                    .when()
                    .body(burger)
                    .post(ROOT)
                    .then();
        }

    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrders(User user) {
        if (user.getAccessToken() != null) {
            return given()
                    .spec(getBaseSpec())
                    .header("Authorization", user.getAccessToken())
                    .when()
                    .get(ROOT)
                    .then();
        } else {
            return given()
                    .spec(getBaseSpec())
                    .when()
                    .get(ROOT)
                    .then();
        }
    }
}