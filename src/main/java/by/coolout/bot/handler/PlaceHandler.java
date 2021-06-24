package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import by.coolout.bot.entity.ChatDTO;
import by.coolout.bot.statics.Drinks;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Arrays;
import java.util.stream.Collectors;

import static by.coolout.bot.context.Context.CTX_PLACE;
import static by.coolout.bot.context.Context.CTX_STEP;
import static by.coolout.bot.statics.Messages.BIRTHDAY;
import static by.coolout.bot.statics.Messages.CHOOSE_DRINK;

public class PlaceHandler extends DefaultHandler {

    @Override
    public SendMessage handle(ChatDTO chatDTO) throws Exception {
        SendMessage message;
        Context context = ContextManager.get(chatDTO.getChatId());
        if (context.getIntegerAttribute(CTX_STEP) == 1) {
            if (BIRTHDAY.equals(chatDTO.getMessageText())) {
                return new SendMessage(chatDTO.getChatId(), chatDTO.getLogin() + " ты успешно записался на День Рождения кофейни COOLOUT \uD83D\uDC9B\nЖдём тебя 02.07 (пятница) в 19:00 \uD83D\uDD56 ");
            }

            context.put(CTX_STEP, 2);
            context.put(CTX_PLACE, chatDTO.getMessageText());
            ContextManager.put(chatDTO.getChatId(), context);

            ReplyKeyboardMarkup keyboard = super.getTelegramService().createKeyboard(Arrays.stream(Drinks.values()).map(Drinks::getName).collect(Collectors.toList()));
            message = super.getTelegramService().createMessage(chatDTO.getChatId(), CHOOSE_DRINK, keyboard);
        } else {
            message = super.handle(chatDTO);
        }
        return message;
    }
}
