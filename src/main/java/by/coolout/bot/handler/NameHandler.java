package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import by.coolout.bot.entity.ChatDTO;
import by.coolout.bot.entity.Drink;
import by.coolout.bot.service.DrinkService;
import by.coolout.bot.statics.Drinks;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

import static by.coolout.bot.context.Context.*;
import static by.coolout.bot.statics.Messages.*;
import static by.coolout.bot.statics.Syrups.NOT_NEEDED;

public class NameHandler extends DefaultHandler {

    private DrinkService drinkService;

    public void setDrinkService(DrinkService drinkService) { this.drinkService = drinkService; }

    @Override
    public SendMessage handle(ChatDTO chatDTO) throws Exception {
        SendMessage message;
        Context context = ContextManager.get(chatDTO.getChatId());
        if (context.getIntegerAttribute(CTX_STEP) == 7) {
            context.put(CTX_STEP, 8);
            context.put(CTX_USERNAME, chatDTO.getMessageText());
            ContextManager.put(chatDTO.getChatId(), context);

            Drink drink = drinkService.getByNameAndVolume(Drinks.getByName(context.getStringAttribute(CTX_DRINK)).name(), Integer.parseInt(context.getStringAttribute(CTX_VOLUME)));

            Double cost = drink.getPrice();
            String syrup = context.getStringAttribute(CTX_SYRUP);
            if (!NOT_NEEDED.getName().equals(syrup)) {
                cost += 0.45;
            }

            String messageText = APPROVE_PRICE + Math.round(cost * 100.0) / 100.0 + " \uD83D\uDCB8";
            if (PLACE_DELIVERY.equals(context.getStringAttribute(CTX_PLACE))) {
                messageText = WAIT_CALL;
            }

            message = super.getTelegramService().createMessage(chatDTO.getChatId(), messageText, super.getTelegramService().createKeyboard(List.of(YES, NO)));
        } else {
            message = super.handle(chatDTO);
        }
        return message;
    }
}
