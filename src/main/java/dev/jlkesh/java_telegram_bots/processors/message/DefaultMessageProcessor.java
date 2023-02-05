package dev.jlkesh.java_telegram_bots.processors.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
import dev.jlkesh.java_telegram_bots.config.TelegramBotConfiguration;
import dev.jlkesh.java_telegram_bots.dto.Dictionary;
import dev.jlkesh.java_telegram_bots.processors.Processor;
import dev.jlkesh.java_telegram_bots.state.DefaultState;
import dev.jlkesh.java_telegram_bots.state.GenerateDataState;
import dev.jlkesh.java_telegram_bots.utils.factory.SendMessageFactory;

import static dev.jlkesh.java_telegram_bots.config.ThreadSafeBeansContainer.collected;
import static dev.jlkesh.java_telegram_bots.config.ThreadSafeBeansContainer.userState;
import static dev.jlkesh.java_telegram_bots.utils.MessageSourceUtils.getLocalizedMessage;

public class DefaultMessageProcessor implements Processor<DefaultState> {
    private final TelegramBot bot = TelegramBotConfiguration.get();

    @Override
    public void process(Update update, DefaultState state) {
        Message message = update.message();
        User from = message.from();
        String text = message.text();
        Long chatID = message.chat().id();
        String language = message.from().languageCode();
        if ( state.equals(DefaultState.DELETE) ) {
            bot.execute(new DeleteMessage(chatID, message.messageId()));
        } else if ( state.equals(DefaultState.MAIN_STATE) ) {
            if ( text.equals(getLocalizedMessage("main.menu.generate.data", language)) ) {
                bot.execute(new SendMessage(chatID, "Enter file Name"));
                userState.put(chatID, GenerateDataState.FILE_NAME);
                collected.put(chatID, new Dictionary<>(4));
            } else {
                bot.execute(SendMessageFactory.sendMessageWithMainMenu(chatID, "Fell Free To use bla", from.languageCode()));
            }
        }
    }
}
