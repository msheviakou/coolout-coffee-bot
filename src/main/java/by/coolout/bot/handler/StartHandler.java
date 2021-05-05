package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import by.coolout.bot.entity.ChatDTO;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

import static by.coolout.bot.context.Context.CTX_STEP;
import static by.coolout.bot.statics.Messages.*;

public class StartHandler extends DefaultHandler {

    @Override
    public SendMessage handle(ChatDTO chatDTO) throws Exception {
        SendMessage message;
        if ("/start".equals(chatDTO.getMessageText())) {
            ContextManager.clear(chatDTO.getChatId());
            Context context = ContextManager.build();
            context.put(CTX_STEP, 1);
            ContextManager.put(chatDTO.getChatId(), context);

            message = super.getTelegramService().createMessage(chatDTO.getChatId(), CHOOSE_PLACE, super.getTelegramService().createKeyboard(List.of(PLACE_WITH_MYSELF, PLACE_IN, PLACE_DELIVERY)));
        } else {
            message = super.handle(chatDTO);
        }
        return message;
    }
}
