package by.coolout.bot.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatDTO {

    private String chatId;
    private String messageText;
    private String login;
}
