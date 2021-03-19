package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import by.coolout.bot.entity.ChatDTO;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

import static by.coolout.bot.context.Context.CTX_STEP;
import static by.coolout.bot.context.Context.CTX_SYRUP;
import static by.coolout.bot.statics.Messages.WRITE_WISHES;

public class SyrupHandler extends DefaultHandler {

    @Override
    public SendMessage handle(ChatDTO chatDTO) throws Exception {
        SendMessage message;
        Context context = ContextManager.get(chatDTO.getChatId());
        if (context.getIntegerAttribute(CTX_STEP) == 3) {
            context.put(CTX_STEP, 4);
            context.put(CTX_SYRUP, chatDTO.getMessageText());
            ContextManager.put(chatDTO.getChatId(), context);

            message = super.getTelegramService().createMessage(chatDTO.getChatId(), WRITE_WISHES, new ReplyKeyboardRemove());
        } else {
            message = super.handle(chatDTO);
        }
        return message;
    }
}
