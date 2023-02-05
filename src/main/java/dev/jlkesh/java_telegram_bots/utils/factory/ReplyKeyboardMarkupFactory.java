package dev.jlkesh.java_telegram_bots.utils.factory;

import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class ReplyKeyboardMarkupFactory {
    public static ReplyKeyboardMarkup mainMenu() {
        KeyboardButton generateDataButton = new KeyboardButton("Generate");
        KeyboardButton historyButton = new KeyboardButton("See History");
        KeyboardButton changeLanguageDataButton = new KeyboardButton("⚙️ Change Language");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{generateDataButton},
                new KeyboardButton[]{historyButton},
                new KeyboardButton[]{changeLanguageDataButton});
        replyKeyboardMarkup.selective(true);
        replyKeyboardMarkup.resizeKeyboard(true);
        return replyKeyboardMarkup;
    }
}
