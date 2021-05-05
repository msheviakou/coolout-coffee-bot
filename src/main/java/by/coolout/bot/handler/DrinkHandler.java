package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import by.coolout.bot.entity.ChatDTO;
import by.coolout.bot.statics.DrinkVolumes;
import by.coolout.bot.statics.Drinks;
import org.springframework.util.ObjectUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.stream.Collectors;

import static by.coolout.bot.context.Context.CTX_DRINK;
import static by.coolout.bot.context.Context.CTX_STEP;
import static by.coolout.bot.statics.Messages.CHOOSE_VOLUME;
import static by.coolout.bot.statics.Messages.DONT_UNDERSTAND;

public class DrinkHandler extends DefaultHandler {

    @Override
    public SendMessage handle(ChatDTO chatDTO) throws Exception {
        SendMessage message;
        Context context = ContextManager.get(chatDTO.getChatId());

        if (ObjectUtils.isEmpty(context)) {
            message = super.getTelegramService().createMessage(chatDTO.getChatId(), DONT_UNDERSTAND);
        } else if (context.getIntegerAttribute(CTX_STEP) == 2) {
            context.put(CTX_STEP, 3);
            context.put(CTX_DRINK, chatDTO.getMessageText());
            ContextManager.put(chatDTO.getChatId(), context);

            ReplyKeyboardMarkup keyboard = super.getTelegramService().createKeyboard(DrinkVolumes.drinkVolumes.get(Drinks.getByName(chatDTO.getMessageText())).stream().map(volume -> String.valueOf(volume.getVolume())).collect(Collectors.toList()));
            message = super.getTelegramService().createMessage(chatDTO.getChatId(), CHOOSE_VOLUME, keyboard);
        } else {
            message = super.handle(chatDTO);
        }
        return message;
    }
}
