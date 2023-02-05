package dev.jlkesh.java_telegram_bots.utils.factory;

import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;

public class SendMessageFactory {
    public static EditMessageText getEditMessageTextForPassword(Object chatID, int messageID, String messageText) {
        EditMessageText editMessageText = new EditMessageText(chatID, messageID, messageText);
        editMessageText.replyMarkup(InlineKeyboardMarkupFactory.enterPasswordKeyboard());
        return editMessageText;
    }

    public static SendMessage sendMessageWithMainMenu(Object chatID, String messageText) {
        SendMessage sendMessage = new SendMessage(chatID, messageText);
        sendMessage.replyMarkup(ReplyKeyboardMarkupFactory.mainMenu());
        return sendMessage;
    }


}
