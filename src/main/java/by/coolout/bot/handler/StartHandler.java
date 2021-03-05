package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import by.coolout.bot.statics.Drinks;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Arrays;
import java.util.stream.Collectors;

import static by.coolout.bot.context.Context.CTX_STEP;
import static by.coolout.bot.statics.Messages.CHOOSE_DRINK;

public class StartHandler extends DefaultHandler {

    @Override
    public SendMessage handle(String chatId, String messageText) throws Exception {
        SendMessage message;
        if ("/start".equals(messageText)) {
            ContextManager.clear(chatId);
            Context context = ContextManager.build();
            context.put(CTX_STEP, 1);
            ContextManager.put(chatId, context);

            ReplyKeyboardMarkup keyboard = super.getTelegramService().createKeyboard(Arrays.stream(Drinks.values()).map(Drinks::getName).collect(Collectors.toList()));
            message = super.getTelegramService().createMessage(chatId, CHOOSE_DRINK, keyboard);
        } else {
            message = super.handle(chatId, messageText);
        }
        return message;
    }
}
