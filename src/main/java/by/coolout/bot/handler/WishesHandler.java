package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static by.coolout.bot.context.Context.CTX_STEP;
import static by.coolout.bot.context.Context.CTX_WISHES;
import static by.coolout.bot.statics.Messages.PRESS_TIME;

public class WishesHandler extends DefaultHandler {

    @Override
    public SendMessage handle(String chatId, String messageText) throws Exception {
        SendMessage message;
        Context context = ContextManager.get(chatId);
        if (context.getIntegerAttribute(CTX_STEP) == 4) {
            context.put(CTX_STEP, 5);
            context.put(CTX_WISHES, messageText);
            ContextManager.put(chatId, context);

            message = super.getTelegramService().createMessage(chatId, PRESS_TIME);
        } else {
            message = super.handle(chatId, messageText);
        }
        return message;
    }
}
