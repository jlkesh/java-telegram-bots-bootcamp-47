package dev.jlkesh.java_telegram_bots.utils.factory;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import dev.jlkesh.java_telegram_bots.utils.BaseUtils;

import java.util.Objects;

public class InlineKeyboardMarkupFactory {
    public static InlineKeyboardMarkup enterPasswordKeyboard() {
        InlineKeyboardMarkup replyMarkup = new InlineKeyboardMarkup();
        replyMarkup.addRow(
                getInlineButton(1, 1),
                getInlineButton(2, 2),
                getInlineButton(3, 3)
        );
        replyMarkup.addRow(
                getInlineButton(4, 4),
                getInlineButton(5, 5),
                getInlineButton(6, 6)
        );
        replyMarkup.addRow(
                getInlineButton(7, 7),
                getInlineButton(8, 8),
                getInlineButton(9, 9)
        );
        replyMarkup.addRow(
                getInlineButton(0, 0),
                getInlineButton(BaseUtils.TICK, "done"),
                getInlineButton(BaseUtils.CLEAR, "d")
        );
        return replyMarkup;
    }

    private static InlineKeyboardButton getInlineButton(final Object text, final Object callbackData) {
        var button = new InlineKeyboardButton(Objects.toString(text));
        button.callbackData(Objects.toString(callbackData));
        return button;
    }


    public static SendMessage getSendMessageWithPasswordKeyboard(Object chatID, String message) {
        SendMessage sendMessage = new SendMessage(chatID, message);
        sendMessage.replyMarkup(enterPasswordKeyboard());
        return sendMessage;
    }

    public static InlineKeyboardMarkup getFileTypeKeyboard() {
        return new InlineKeyboardMarkup(
                getInlineButton("JSON", "json"),
                getInlineButton("CSV", "csv"),
                getInlineButton("SQL", "sql")
        );
    }
}
