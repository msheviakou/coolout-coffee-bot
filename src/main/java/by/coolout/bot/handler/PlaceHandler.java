package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

import static by.coolout.bot.context.Context.CTX_PLACE;
import static by.coolout.bot.context.Context.CTX_STEP;
import static by.coolout.bot.statics.Messages.PRESS_USERNAME;

public class PlaceHandler extends DefaultHandler {

    @Override
    public SendMessage handle(String chatId, String messageText) throws Exception {
        SendMessage message;
        Context context = ContextManager.get(chatId);
        if (context.getIntegerAttribute(CTX_STEP) == 6) {
            context.put(CTX_STEP, 7);
            context.put(CTX_PLACE, messageText);
            ContextManager.put(chatId, context);

            message = super.getTelegramService().createMessage(chatId, PRESS_USERNAME, new ReplyKeyboardRemove());
        } else {
            message = super.handle(chatId, messageText);
        }
        return message;
    }
}
