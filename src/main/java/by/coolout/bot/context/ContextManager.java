package by.coolout.bot.context;

import java.util.HashMap;
import java.util.Map;

public class ContextManager {

    private static Map<String, Context> context = new HashMap<>();

    public static Context build() { return new Context(); }

    public static Context get(String chatId) { return context.get(chatId); }

    public static void put(String chatId, Context ctx) { context.put(chatId, ctx); }

    public static void clear(String chatId) { put(chatId, null); }
}
