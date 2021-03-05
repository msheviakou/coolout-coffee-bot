package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
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
    public SendMessage handle(String chatId, String messageText) throws Exception {
        SendMessage message;
        Context context = ContextManager.get(chatId);
        if (context.getIntegerAttribute(CTX_STEP) == 2) {
            context.put(CTX_STEP, 3);
            context.put(CTX_VOLUME, messageText);
            ContextManager.put(chatId, context);

            ReplyKeyboardMarkup keyboard = super.getTelegramService().createKeyboard(Arrays.stream(Syrups.values()).map(Syrups::getName).collect(Collectors.toList()));
            message = super.getTelegramService().createMessage(chatId, CHOOSE_SYRUP, keyboard);
        } else {
            message = super.handle(chatId, messageText);
        }
        return message;
    }
}
