package yandex.client;


import com.github.javafaker.Faker;
import yandex.pojo.User;

/**
 * класс создания данных клиентов и смешивание ингридиентов
 **/

public class DataGenerator {
    public static User getRandomUser() {
        Faker faker = new Faker();
        return new User(faker.internet().emailAddress(), faker.internet().password(), faker.name().firstName());
    }

    public static String[] getIngredients() {
        String[] ingredients = {"61c0c5a71d1f82001bdaaa6c", "61c0c5a71d1f82001bdaaa76", "61c0c5a71d1f82001bdaaa78"};
        return ingredients;

    }

}
