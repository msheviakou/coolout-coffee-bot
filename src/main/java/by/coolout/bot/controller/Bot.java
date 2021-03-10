package by.coolout.bot.controller;

import by.coolout.bot.handler.*;
import by.coolout.bot.service.DrinkService;
import by.coolout.bot.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static by.coolout.bot.statics.Messages.DONT_UNDERSTAND;
import static by.coolout.bot.statics.Messages.ORDER_ACCEPTED;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Value("${coolout.chat.prod}")
    private String cooloutChat;

    private DefaultHandler startHandler;
    private OrderHandler orderHandler;

    @Autowired
    public Bot(DrinkService drinkService, OrderService orderService) {
        startHandler = new StartHandler();
        DrinkHandler drinkHandler = new DrinkHandler();
        VolumeHandler volumeHandler = new VolumeHandler();
        SyrupHandler syrupHandler = new SyrupHandler();
        WishesHandler wishesHandler = new WishesHandler();
        TimeHandler timeHandler = new TimeHandler();
        PlaceHandler placeHandler = new PlaceHandler();
        NameHandler nameHandler = new NameHandler();
        orderHandler = new OrderHandler();

        nameHandler.setDrinkService(drinkService);
        orderHandler.setServices(drinkService, orderService);

        nameHandler.setNext(orderHandler);
        placeHandler.setNext(nameHandler);
        timeHandler.setNext(placeHandler);
        wishesHandler.setNext(timeHandler);
        syrupHandler.setNext(wishesHandler);
        volumeHandler.setNext(syrupHandler);
        drinkHandler.setNext(volumeHandler);
        startHandler.setNext(drinkHandler);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            final String chatId = String.valueOf(update.getMessage().getChatId());
            final String messageText = update.getMessage().getText();

            Chat chat = update.getMessage().getChat();
            log.info(chat.getFirstName() + " " + chat.getLastName() + " clicked: " + messageText);

            try {
                SendMessage message = startHandler.handle(chatId, messageText);
                sendMessage(message);

                if (message.getText().contains(ORDER_ACCEPTED)) {
                    sendMessage(orderHandler.sendMessageToCoolout(cooloutChat));
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                sendMessage(new SendMessage(chatId, DONT_UNDERSTAND));
            }
        }
    }

    private void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() { return botName; }

    @Override
    public String getBotToken() { return botToken; }
}
