package by.coolout.bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelegramService {

    public SendMessage createMessage(String chatId, String messageText, ReplyKeyboardMarkup replyKeyboardMarkup) {
        SendMessage message = new SendMessage(chatId, messageText);
        message.setReplyMarkup(replyKeyboardMarkup);
        return message;
    }

    public SendMessage createMessage(String chatId, String messageText, ReplyKeyboardRemove replyKeyboardRemove) {
        SendMessage message = new SendMessage(chatId, messageText);
        replyKeyboardRemove.setRemoveKeyboard(true);
        message.setReplyMarkup(replyKeyboardRemove);
        return message;
    }

    public SendMessage createMessage(String chatId, String messageText) {
        return new SendMessage(chatId, messageText);
    }

    public ReplyKeyboardMarkup createKeyboard(List<String> values) {
        List<KeyboardRow> keyboardRows = values.stream().map(value -> {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(value);
            return keyboardRow;
        }).collect(Collectors.toList());

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }
}
