package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import by.coolout.bot.entity.ChatDTO;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static by.coolout.bot.context.Context.CTX_STEP;
import static by.coolout.bot.context.Context.CTX_WISHES;
import static by.coolout.bot.statics.Messages.PRESS_TIME;

public class WishesHandler extends DefaultHandler {

    @Override
    public SendMessage handle(ChatDTO chatDTO) throws Exception {
        SendMessage message;
        Context context = ContextManager.get(chatDTO.getChatId());
        if (context.getIntegerAttribute(CTX_STEP) == 4) {
            context.put(CTX_STEP, 5);
            context.put(CTX_WISHES, chatDTO.getMessageText());
            ContextManager.put(chatDTO.getChatId(), context);

            message = super.getTelegramService().createMessage(chatDTO.getChatId(), PRESS_TIME);
        } else {
            message = super.handle(chatDTO);
        }
        return message;
    }
}
