package by.coolout.bot.controller;

import by.coolout.bot.entity.ChatDTO;
import by.coolout.bot.filter.WorkScheduleFilter;
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

import static by.coolout.bot.statics.Messages.*;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Value("${coolout.chat}")
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
            ChatDTO chatDto = ChatDTO.builder()
                    .chatId(String.valueOf(update.getMessage().getChatId()))
                    .messageText(update.getMessage().getText())
                    .login(update.getMessage().getChat().getUserName())
                    .build();

            Chat chat = update.getMessage().getChat();
            log.info(chat.getFirstName() + " " + chat.getLastName() + " clicked: " + chatDto.getMessageText());

            try {
//                if (WorkScheduleFilter.isOpened()) {
                    SendMessage message = startHandler.handle(chatDto);
                    sendMessage(message);

                    if (message.getText().contains(ORDER_ACCEPTED)) {
                        sendMessage(orderHandler.sendMessageToCoolout(cooloutChat));
                    }
//                } else {
//                    sendMessage(new SendMessage(chatDto.getChatId(), WORK_SCHEDULE));
//                }
            } catch (Exception e) {
                log.error(e.getMessage());
                sendMessage(new SendMessage(chatDto.getChatId(), DONT_UNDERSTAND));
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
