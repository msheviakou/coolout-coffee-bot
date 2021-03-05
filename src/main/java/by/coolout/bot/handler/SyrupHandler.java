package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

import static by.coolout.bot.context.Context.CTX_STEP;
import static by.coolout.bot.context.Context.CTX_SYRUP;
import static by.coolout.bot.statics.Messages.WRITE_WISHES;

public class SyrupHandler extends DefaultHandler {

    @Override
    public SendMessage handle(String chatId, String messageText) throws Exception {
        SendMessage message;
        Context context = ContextManager.get(chatId);
        if (context.getIntegerAttribute(CTX_STEP) == 3) {
            context.put(CTX_STEP, 4);
            context.put(CTX_SYRUP, messageText);
            ContextManager.put(chatId, context);

            message = super.getTelegramService().createMessage(chatId, WRITE_WISHES, new ReplyKeyboardRemove());
        } else {
            message = super.handle(chatId, messageText);
        }
        return message;
    }
}
