package by.coolout.bot.statics;

import java.util.EnumSet;

public enum Drinks {

    ESPRESSO ("Эспрессо"),
    AMERICANO ("Американо"),
    CAPPUCCINO ("Капучино"),
    LATTE ("Латте"),
    RAF ("Раф"),
    FLAT ("Флэт Уайт"),
    MOCHACINO ("Мокачино"),
    COCOA ("Какао"),
    CHOCOLATE ("Горячий шоколад"),
    CHILD_CAPPUCCINO ("Детский капучино"),
    TEA ("Чай"),
    TEA_LATTE("Чай Латте"),
    TEA_MATCHA_GREEN("Матча зелёная"),
    TEA_MATCHA_BLUE("Матча голубая");

    private String name;

    Drinks(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Drinks getByName(String name) {
        return EnumSet.allOf(Drinks.class)
                .stream()
                .filter(e -> e.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported drink %s.", name)));
    }
}
