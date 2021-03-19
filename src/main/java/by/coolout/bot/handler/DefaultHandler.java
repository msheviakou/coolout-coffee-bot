package by.coolout.bot.handler;

import by.coolout.bot.entity.ChatDTO;
import by.coolout.bot.service.TelegramService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public abstract class DefaultHandler {

    private DefaultHandler next;

    private TelegramService telegramService;

    DefaultHandler() { telegramService = new TelegramService(); }

    public void setNext(DefaultHandler next) { this.next = next; }

    public TelegramService getTelegramService() { return telegramService; }

    public SendMessage handle(ChatDTO chatDTO) throws Exception { return next.handle(chatDTO); }
}
