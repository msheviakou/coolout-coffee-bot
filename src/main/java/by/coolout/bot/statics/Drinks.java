package by.coolout.bot.statics;

import java.util.EnumSet;

public enum Drinks {

    ESPRESSO ("Эспрессо - 3.0"),
    AMERICANO ("Американо - 2.7 | 3.2"),
    CAPPUCCINO ("Капучино - 2.8 | 3.3 | 3.8"),
    LATTE ("Латте - 2.9 | 3.4 | 3.9"),
    RAF ("Раф - 3.2 | 4.0 | 4.8"),
    FLAT ("Флэт Уайт - 4.0"),
    MOCHACINO ("Мокачино - 3.0 | 3.5 | 4.2"),
    COCOA ("Какао - 2.8"),
    CHOCOLATE ("Горячий шоколад - 3.0"),
    CHILD_CAPPUCCINO ("Детский капучино - 2.0"),
    TEA_BLACK ("Чай чёрный - 2.0"),
    TEA_GREEN ("Чай зелёный - 2.0"),
    TEA_RED ("Чай красный - 2.0"),
    TEA_1 ("Чай имбирный - 3.0"),
    TEA_2 ("Чай малиновый - 3.0"),
    TEA_3 ("Чай пряная вишня - 3.0"),
    TEA_4 ("Чай облепиховый - 3.0"),
    TEA_LATTE("Пуэр Латте - 4.2"),
    TEA_MATCHA_GREEN("Матча зелёная - 5.0"),
    TEA_MATCHA_BLUE("Матча голубая - 5.0"),
    ESPRESSO_TONIK("Эспрессо тоник - 3.3"),
    FRAPPE("Фраппе - 3.5"),
    BUMBLE("Бамбл (кофе с апельсиновым соком) - 3.5"),
    MILKSHAKE("Молочный коктейль классика - 2.5"),
    MILKSHAKE_VANILLA("Молочный коктейль ваниль - 3.0"),
    MILKSHAKE_COFFEE("Молочный коктейль кофейный - 3.5"),
    MILKSHAKE_BANANA("Молочный коктейль банан - 3.0"),
    MILKSHAKE_STRAWBERRY("Молочный коктейль клубника - 3.0"),
    MILKSHAKE_CHOCOLATE("Молочный коктейль шоколад - 3.0");

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
