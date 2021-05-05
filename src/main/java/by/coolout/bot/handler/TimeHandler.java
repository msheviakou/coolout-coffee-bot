package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import by.coolout.bot.entity.ChatDTO;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

import static by.coolout.bot.context.Context.*;
import static by.coolout.bot.statics.Messages.*;

public class TimeHandler extends DefaultHandler {

    @Override
    public SendMessage handle(ChatDTO chatDTO) throws Exception {
        SendMessage message;
        Context context = ContextManager.get(chatDTO.getChatId());
        if (context.getIntegerAttribute(CTX_STEP) == 6) {
            context.put(CTX_STEP, 7);
            context.put(CTX_TIME, chatDTO.getMessageText());
            ContextManager.put(chatDTO.getChatId(), context);

            String text = PRESS_USERNAME;
            if (PLACE_DELIVERY.equals(context.getStringAttribute(CTX_PLACE))) {
                text = PRESS_CONTACT_INFO;
            }

            message = super.getTelegramService().createMessage(chatDTO.getChatId(), text, new ReplyKeyboardRemove());
        } else {
            message = super.handle(chatDTO);
        }
        return message;
    }
}
