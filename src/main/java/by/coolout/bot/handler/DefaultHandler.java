package by.coolout.bot.handler;

import by.coolout.bot.service.TelegramService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public abstract class DefaultHandler {

    private DefaultHandler next;

    private TelegramService telegramService;

    DefaultHandler() { telegramService = new TelegramService(); }

    public void setNext(DefaultHandler next) { this.next = next; }

    public TelegramService getTelegramService() { return telegramService; }

    public SendMessage handle(String chatId, String messageText) throws Exception { return next.handle(chatId, messageText); }
}
