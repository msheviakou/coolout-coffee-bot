package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import by.coolout.bot.entity.ChatDTO;
import by.coolout.bot.statics.Syrups;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Arrays;
import java.util.stream.Collectors;

import static by.coolout.bot.context.Context.CTX_STEP;
import static by.coolout.bot.context.Context.CTX_VOLUME;
import static by.coolout.bot.statics.Messages.CHOOSE_SYRUP;

public class VolumeHandler extends DefaultHandler {

    @Override
    public SendMessage handle(ChatDTO chatDTO) throws Exception {
        SendMessage message;
        Context context = ContextManager.get(chatDTO.getChatId());
        if (context.getIntegerAttribute(CTX_STEP) == 2) {
            context.put(CTX_STEP, 3);
            context.put(CTX_VOLUME, chatDTO.getMessageText());
            ContextManager.put(chatDTO.getChatId(), context);

            ReplyKeyboardMarkup keyboard = super.getTelegramService().createKeyboard(Arrays.stream(Syrups.values()).map(Syrups::getName).collect(Collectors.toList()));
            message = super.getTelegramService().createMessage(chatDTO.getChatId(), CHOOSE_SYRUP, keyboard);
        } else {
            message = super.handle(chatDTO);
        }
        return message;
    }
}
