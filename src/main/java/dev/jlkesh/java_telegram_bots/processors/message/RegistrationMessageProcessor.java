package dev.jlkesh.java_telegram_bots.processors.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.DeleteMessage;
import dev.jlkesh.java_telegram_bots.config.TelegramBotConfiguration;
import dev.jlkesh.java_telegram_bots.processors.Processor;
import dev.jlkesh.java_telegram_bots.state.RegistrationState;
import dev.jlkesh.java_telegram_bots.utils.factory.InlineKeyboardMarkupFactory;

import static dev.jlkesh.java_telegram_bots.config.ThreadSafeBeansContainer.collected;
import static dev.jlkesh.java_telegram_bots.config.ThreadSafeBeansContainer.userState;

public class RegistrationMessageProcessor implements Processor<RegistrationState> {
    private final TelegramBot bot = TelegramBotConfiguration.get();

    @Override
    public void process(Update update, RegistrationState state) {
        // TODO: 05/02/23 Localize
        Message message = update.message();
        String text = null;
        Long chatID = message.chat().id();
        if ( state.equals(RegistrationState.USERNAME) ) {
            text = "Enter Password Please \n _ _ _ _ ";
            bot.execute(InlineKeyboardMarkupFactory.getSendMessageWithPasswordKeyboard(chatID, text));
            userState.put(chatID, RegistrationState.PASSWORD);
            collected.get(chatID).put("username", message.text());
            collected.get(chatID).put("language", message.from().languageCode());
        } else if ( state.equals(RegistrationState.PASSWORD) ) {
            bot.execute(new DeleteMessage(chatID, message.messageId()));
        }
    }
}
