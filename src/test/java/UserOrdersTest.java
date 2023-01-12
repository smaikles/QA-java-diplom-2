import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import yandex.BurgerOrder;
import yandex.UserClient;
import yandex.client.DataGenerator;
import yandex.pojo.User;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * тест класс Получение заказов конкретного пользователя
 **/

public class UserOrdersTest {

    BurgerOrder burgerOrder;
    UserClient userClient;
    User user;

    @Before
    public void setUp() {
        user = DataGenerator.getRandomUser();
        userClient = new UserClient();
        burgerOrder = new BurgerOrder();
        userClient.create(user);
    }

    @After
    public void tearDown() {
        userClient.delete(user);
    }


    @Test
    @DisplayName("Получение заказов авторизованного пользователя")
    public void getOrdersForAuthorizedUser() {
        ValidatableResponse validatableResponse = burgerOrder.getOrders(user);
        validatableResponse.assertThat().statusCode(SC_OK);
        validatableResponse.assertThat().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Получение заказов неавторизованного пользователя")
    public void getOrdersForUnauthorizedUser() {
        user.setAccessToken(null);
        ValidatableResponse validatableResponse = burgerOrder.getOrders(user);
        validatableResponse.assertThat().statusCode(SC_UNAUTHORIZED);
        validatableResponse.assertThat().body("message", equalTo("You should be authorised"));
    }

}
