package yandex.pojo;

/**
 * класс с полями космобургера
 **/

public class Burger {

    private String[] ingredients;

    public Burger(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public Burger() {

    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }
}
