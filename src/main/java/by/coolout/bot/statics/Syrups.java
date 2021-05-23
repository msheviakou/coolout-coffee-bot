package by.coolout.bot.statics;

import java.util.EnumSet;

public enum Syrups {

    NOT_NEEDED("Не нужен"),
//    COCONUT ("Кокос"),
    SALTED_CARAMEL ("Солёная карамель"),
//    COOKIE_WITH_CHOCOLATE ("Печенье с шоколадной крошкой"),
    HAZELNUT ("Лесной орех"),
    VANILLA ("Ваниль"),
    GINGERBREAD ("Имбирный пряник"),
    MINT ("Мята"),
    GRENADINE ("Гренадин"),
    ALMOND ("Миндаль"),
    EJEVIKA ("Ежевика"),
    LAVENDER ("Лаванда");

    private String name;

    Syrups(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Syrups getByName(String name) {
        return EnumSet.allOf(Syrups.class)
                .stream()
                .filter(e -> e.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported syrup %s.", name)));
    }
}
