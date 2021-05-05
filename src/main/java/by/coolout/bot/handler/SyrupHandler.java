package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import by.coolout.bot.entity.ChatDTO;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

import static by.coolout.bot.context.Context.*;
import static by.coolout.bot.statics.Messages.*;

public class SyrupHandler extends DefaultHandler {

    @Override
    public SendMessage handle(ChatDTO chatDTO) throws Exception {
        SendMessage message;
        Context context = ContextManager.get(chatDTO.getChatId());
        if (context.getIntegerAttribute(CTX_STEP) == 4) {
            context.put(CTX_STEP, 5);
            context.put(CTX_SYRUP, chatDTO.getMessageText());
            ContextManager.put(chatDTO.getChatId(), context);

            String text = WRITE_WISHES;
            if (PLACE_DELIVERY.equals(context.getStringAttribute(CTX_PLACE))) {
                text = createMenu();
            }

            message = super.getTelegramService().createMessage(chatDTO.getChatId(), text, new ReplyKeyboardRemove());
        } else {
            message = super.handle(chatDTO);
        }
        return message;
    }

    private String createMenu() {
        StringBuilder menu = new StringBuilder();

        menu.append(MENU)
            .append(MENU_BAKERY)
                .append(MENU_SANDWICH_MINI)
                .append(MENU_SANDWICH_MAX)
                .append(MENU_CROISSANT)
                .append(MENU_PUFF)
                .append(MENU_TWIST)
            .append(MENU_DESSERT)
                .append(MENU_CHEESCAKE_MINI)
                .append(MENU_CHEESCAKE_MAX)
                .append(MENU_POTATO)
                .append(MENU_RING)
                .append(MENU_MUFFIN)
                .append(MENU_COOKIES)
            .append(MENU_BARS)
                .append(MENU_CROCANT)
                .append(MENU_BANABAR)
                .append(MENU_BONTIME)
                .append(MENU_CLICK)
                .append(MENU_OZERA_ORANGE)
                .append(MENU_OZERA_RED)
                .append(MENU_IMMUNITY)
                .append(MENU_WEIGHT_CONTROL)
                .append(MENU_YOTA)
                .append(MENU_ZEBRA)
                .append(MENU_CHOCOLATE_OZERA)
                .append(MENU_CHOCOLATE_OZERA_HAZELNUT)
            .append(MENU_JUICE)
                .append(MENU_JUICE_KINDS)
                .append(MENU_LEMONADE_KINDS)
                .append(MENU_COCKTAIL_KINDS);

        return menu.toString();
    }
}
