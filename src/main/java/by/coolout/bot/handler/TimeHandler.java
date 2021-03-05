package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

import static by.coolout.bot.context.Context.CTX_STEP;
import static by.coolout.bot.context.Context.CTX_TIME;
import static by.coolout.bot.statics.Messages.*;

public class TimeHandler extends DefaultHandler {

    @Override
    public SendMessage handle(String chatId, String messageText) throws Exception {
        SendMessage message;
        Context context = ContextManager.get(chatId);
        if (context.getIntegerAttribute(CTX_STEP) == 5) {
            context.put(CTX_STEP, 6);
            context.put(CTX_TIME, messageText);
            ContextManager.put(chatId, context);

            message = super.getTelegramService().createMessage(chatId, CHOOSE_PLACE, super.getTelegramService().createKeyboard(List.of(PLACE_WITH_MYSELF, PLACE_IN)));
        } else {
            message = super.handle(chatId, messageText);
        }
        return message;
    }
}
