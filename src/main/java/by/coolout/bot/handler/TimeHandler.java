package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import by.coolout.bot.entity.ChatDTO;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

import static by.coolout.bot.context.Context.CTX_STEP;
import static by.coolout.bot.context.Context.CTX_TIME;
import static by.coolout.bot.statics.Messages.*;

public class TimeHandler extends DefaultHandler {

    @Override
    public SendMessage handle(ChatDTO chatDTO) throws Exception {
        SendMessage message;
        Context context = ContextManager.get(chatDTO.getChatId());
        if (context.getIntegerAttribute(CTX_STEP) == 5) {
            context.put(CTX_STEP, 6);
            context.put(CTX_TIME, chatDTO.getMessageText());
            ContextManager.put(chatDTO.getChatId(), context);

//            message = super.getTelegramService().createMessage(chatDTO.getChatId(), CHOOSE_PLACE, super.getTelegramService().createKeyboard(List.of(PLACE_WITH_MYSELF, PLACE_IN, PLACE_DELIVERY)));
            message = super.getTelegramService().createMessage(chatDTO.getChatId(), CHOOSE_PLACE, super.getTelegramService().createKeyboard(List.of(PLACE_WITH_MYSELF, PLACE_IN)));
        } else {
            message = super.handle(chatDTO);
        }
        return message;
    }
}
