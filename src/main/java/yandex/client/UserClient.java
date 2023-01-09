package yandex.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import yandex.example.RestClient;

import static io.restassured.RestAssured.given;

/**
 * класс работы с методами клиента
 **/

public class UserClient extends RestClient {
    @Step("Авторизация пользователя")
    public ValidatableResponse login(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post("auth/login")
                .then();

    }

    @Step("Создание пользователя")
    public ValidatableResponse create(User user) {
        ValidatableResponse validatableResponse = given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post("auth/register")
                .then();
        user.setAccessToken(validatableResponse.extract().response().jsonPath().getString("accessToken"));
        return validatableResponse;
    }

    @Step("Удаление пользователя")
    public void delete(User user) {
        if (user.getAccessToken() != null) {
            given()
                    .spec(getBaseSpec())
                    .header("Authorization", user.getAccessToken())
                    .when()
                    .delete("auth/user")
                    .then();
        }
    }

    @Step("Редактирование пользователя")
    public ValidatableResponse edit(User oldUser, User newUserCredentials) {
        if (oldUser.getAccessToken() != null) {
            return given()
                    .spec(getBaseSpec())
                    .header("Authorization", oldUser.getAccessToken())
                    .when()
                    .body(newUserCredentials)
                    .patch("auth/user")
                    .then();
        } else {
            return given()
                    .spec(getBaseSpec())
                    .when()
                    .body(newUserCredentials)
                    .patch("auth/user")
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
                    .get("orders")
                    .then();
        } else {
            return given()
                    .spec(getBaseSpec())
                    .when()
                    .get("orders")
                    .then();
        }
    }
}

