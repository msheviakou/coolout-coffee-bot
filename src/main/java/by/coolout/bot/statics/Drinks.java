package by.coolout.bot.statics;

import java.util.EnumSet;

public enum Drinks {

    ESPRESSO ("Эспрессо - 3.0"),
    AMERICANO ("Американо - 2.7 | 3.2"),
    CAPPUCCINO ("Капучино - 2.8 | 3.3 | 4.0"),
    LATTE ("Латте - 2.9 | 3.4 | 4.1"),
    RAF ("Раф - 3.2 | 4.0 | 4.8"),
    FLAT ("Флэт Уайт - 4.0"),
    MOCHACINO ("Мокачино - 3.0 | 3.5 | 4.2"),
    RAF_HALVA ("Раф Халва - 5.0"),
    LATTE_POPKORN ("Латте Попкорн - 4.5"),
    COCOA ("Какао - 2.8"),
    CHOCOLATE ("Шоколад - 3.0"),
    CHILD_CAPPUCCINO ("Детский капучино - 2.0"),
    TEA_BLACK ("Чай чёрный - 2.0"),
    TEA_GREEN ("Чай зелёный - 2.0"),
    TEA_RED ("Чай красный - 2.0"),
    TEA_1 ("Чай имбирный - 3.0"),
    TEA_2 ("Чай малиновый - 3.0"),
    TEA_3 ("Чай пряная вишня - 3.0"),
    TEA_4 ("Чай облепиховый - 3.0"),
    TEA_5 ("Чай Молочный Улун - 3.0"),
    TEA_6 ("Чай Банановый - 3.0"),
    TEA_MATCHA_GREEN("Матча зелёная - 5.0"),
    TEA_MATCHA_BLUE("Матча голубая - 5.0"),
    FRAPPE("Фраппе (холодный капучино) - 3.7"),
    BUMBLE("Бамбл (кофе с апельсиновым соком) - 4.0"),
    ICE_MATCHA("Айс Матча (синяя или зелёная) - 4.2"),
    ICE_LATTE("Айс Латте - 4.2"),
    ESPRESSO_TONIK("Эспрессо тоник (кофе с тоником) - 4.3"),
    MAZAGRAN("Мазагран (кофе с кока колой и ежевикой) - 4.5"),
    ICE_CACAO("Айс Какао - 4.5"),
    MILKSHAKE_VANILLA("Молочный коктейль - 3.3"),
    MILKSHAKE_BANANA("Молочный коктейль банан - 3.5"),
    MILKSHAKE_STRAWBERRY("Молочный коктейль клубника - 3.5"),
    MILKSHAKE_CHOCOLATE("Молочный коктейль шоколад - 3.5"),
    MILKSHAKE_COFFEE("Молочный коктейль кофейный - 4.0"),
    LEMONADE_1("Лимонад Ананасовый Сёрфинг (Розовый грейпфрут) - 4.5"),
    LEMONADE_2("Лимонад Малиновый Рассвет (Малина-Маракуйя) - 4.5"),
    LEMONADE_3("Лимонад Фруктовая Вечеринка (Сангрия) - 4.5"),
    SMUZI_1("Смузи Вишня-Апельсин - 4.5"),
    SMUZI_2("Смузи Ягоды-Банан - 4.5"),
    SMUZI_3("Смузи Груша-Киви - 4.7"),
    SMUZI_4("Смузи Ананас-Апельсин - 4.7"),
    SMUZI_5("Смузи Миндаль-Банан - 5.0");

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
