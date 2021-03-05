package by.coolout.bot.handler;

import by.coolout.bot.context.Context;
import by.coolout.bot.context.ContextManager;
import by.coolout.bot.entity.Drink;
import by.coolout.bot.entity.Order;
import by.coolout.bot.service.DrinkService;
import by.coolout.bot.service.OrderService;
import by.coolout.bot.statics.Drinks;
import by.coolout.bot.statics.Syrups;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

import static by.coolout.bot.context.Context.*;
import static by.coolout.bot.statics.Messages.*;
import static by.coolout.bot.statics.Syrups.NOT_NEEDED;

public class OrderHandler extends DefaultHandler {

    private DrinkService drinkService;
    private OrderService orderService;
    private Order order;

    public void setServices(DrinkService drinkService, OrderService orderService) {
        this.drinkService = drinkService;
        this.orderService = orderService;
    }

    @Override
    public SendMessage handle(String chatId, String messageText) {
        SendMessage message;
        Context context = ContextManager.get(chatId);
        String text;
        if (context.getIntegerAttribute(CTX_STEP) == 8 && YES.equals(messageText)) {
            context.put(CTX_PRICE, messageText);
            ContextManager.put(chatId, context);
            text = context.getStringAttribute(CTX_USERNAME) + ORDER_ACCEPTED;

            createOrder(context);
            orderService.save(order);
        } else {
            text = CAN_TRY_AGAIN;
        }
        ContextManager.clear(chatId);
        ReplyKeyboardMarkup keyboard = super.getTelegramService().createKeyboard(List.of("/start"));
        message = super.getTelegramService().createMessage(chatId, text, keyboard);
        return message;
    }

    public SendMessage sendMessageToCoolout(String chatId) {
        String text = String.format("Готовим %s объёма %s %s\nСиропчик: %s\nПожелания клиента: %s\nВремя прибытия: %s\nСтоимость заказа: %s\nЗовут гостя: %s",
                Drinks.valueOf(order.getDrink().getName()).getName(),
                order.getDrink().getVolume(),
                order.getPlace(),
                order.getSyrup().getName(),
                order.getWishes(),
                order.getTime(),
                order.getCost(),
                order.getUsername());
        return super.getTelegramService().createMessage(chatId, NEW_ORDER + '\n' + text);
    }

    private void createOrder(Context context) {
        Drink drink = this.drinkService.getByNameAndVolume(Drinks.getByName(context.getStringAttribute(CTX_DRINK)).name(), Integer.parseInt(context.getStringAttribute(CTX_VOLUME)));
        order = new Order();

        Double cost = drink.getPrice();

        String syrup = context.getStringAttribute(CTX_SYRUP);
        if (!NOT_NEEDED.getName().equals(syrup)) {
            cost += 0.45;
        }

        order.setSyrup(Syrups.getByName(syrup));
        order.setDrink(drink);
        order.setTime(context.getStringAttribute(CTX_TIME));
        order.setWishes(context.getStringAttribute(CTX_WISHES));
        order.setCost(Math.round(cost * 100.0) / 100.0);
        order.setUsername(context.getStringAttribute(CTX_USERNAME));
        order.setPlace(context.getStringAttribute(CTX_PLACE));
    }
}
