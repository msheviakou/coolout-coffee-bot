package by.coolout.bot.statics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.coolout.bot.statics.Drinks.*;
import static by.coolout.bot.statics.Volumes.*;

public class DrinkVolumes {

    public static final Map<Drinks, List<Volumes>> drinkVolumes = new HashMap<>();

    static {
        drinkVolumes.put(ESPRESSO, List.of(VOLUME_50));
        drinkVolumes.put(AMERICANO, List.of(VOLUME_120, VOLUME_300));
        drinkVolumes.put(CAPPUCCINO, List.of(VOLUME_180, VOLUME_250, VOLUME_350));
        drinkVolumes.put(LATTE, List.of(VOLUME_180, VOLUME_250, VOLUME_350));
        drinkVolumes.put(RAF, List.of(VOLUME_180, VOLUME_250, VOLUME_350));
        drinkVolumes.put(FLAT, List.of(VOLUME_180));
        drinkVolumes.put(MOCHACINO, List.of(VOLUME_180, VOLUME_250, VOLUME_350));
        drinkVolumes.put(RAF_HALVA, List.of(VOLUME_350));
        drinkVolumes.put(LATTE_POPKORN, List.of(VOLUME_350));
        drinkVolumes.put(COCOA, List.of(VOLUME_250));
        drinkVolumes.put(CHOCOLATE, List.of(VOLUME_250));
        drinkVolumes.put(CHILD_CAPPUCCINO, List.of(VOLUME_250));
        drinkVolumes.put(TEA_BLACK, List.of(VOLUME_350));
        drinkVolumes.put(TEA_GREEN, List.of(VOLUME_350));
        drinkVolumes.put(TEA_RED, List.of(VOLUME_350));
        drinkVolumes.put(TEA_1, List.of(VOLUME_250));
        drinkVolumes.put(TEA_2, List.of(VOLUME_250));
        drinkVolumes.put(TEA_3, List.of(VOLUME_250));
        drinkVolumes.put(TEA_4, List.of(VOLUME_250));
        drinkVolumes.put(TEA_5, List.of(VOLUME_350));
        drinkVolumes.put(TEA_6, List.of(VOLUME_350));
        drinkVolumes.put(TEA_MATCHA_GREEN, List.of(VOLUME_350));
        drinkVolumes.put(TEA_MATCHA_BLUE, List.of(VOLUME_350));
        drinkVolumes.put(FRAPPE, List.of(VOLUME_300));
        drinkVolumes.put(BUMBLE, List.of(VOLUME_300));
        drinkVolumes.put(ICE_MATCHA, List.of(VOLUME_300));
        drinkVolumes.put(ICE_LATTE, List.of(VOLUME_300));
        drinkVolumes.put(ESPRESSO_TONIK, List.of(VOLUME_300));
        drinkVolumes.put(MAZAGRAN, List.of(VOLUME_300));
        drinkVolumes.put(ICE_CACAO, List.of(VOLUME_300));
        drinkVolumes.put(MILKSHAKE_VANILLA, List.of(VOLUME_300));
        drinkVolumes.put(MILKSHAKE_BANANA, List.of(VOLUME_300));
        drinkVolumes.put(MILKSHAKE_STRAWBERRY, List.of(VOLUME_300));
        drinkVolumes.put(MILKSHAKE_CHOCOLATE, List.of(VOLUME_300));
        drinkVolumes.put(MILKSHAKE_COFFEE, List.of(VOLUME_300));
        drinkVolumes.put(LEMONADE_1, List.of(VOLUME_300));
        drinkVolumes.put(LEMONADE_2, List.of(VOLUME_300));
        drinkVolumes.put(LEMONADE_3, List.of(VOLUME_300));
        drinkVolumes.put(SMUZI_1, List.of(VOLUME_300));
        drinkVolumes.put(SMUZI_2, List.of(VOLUME_300));
        drinkVolumes.put(SMUZI_3, List.of(VOLUME_300));
        drinkVolumes.put(SMUZI_4, List.of(VOLUME_300));
        drinkVolumes.put(SMUZI_5, List.of(VOLUME_300));
    }
}
