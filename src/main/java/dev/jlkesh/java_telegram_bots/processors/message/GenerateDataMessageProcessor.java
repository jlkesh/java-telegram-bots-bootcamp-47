package dev.jlkesh.java_telegram_bots.processors.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.DeleteMessage;
import dev.jlkesh.java_telegram_bots.config.TelegramBotConfiguration;
import dev.jlkesh.java_telegram_bots.processors.Processor;
import dev.jlkesh.java_telegram_bots.state.GenerateDataState;
import dev.jlkesh.java_telegram_bots.state.RegistrationState;
import dev.jlkesh.java_telegram_bots.utils.factory.SendMessageFactory;

import static dev.jlkesh.java_telegram_bots.config.ThreadSafeBeansContainer.collected;
import static dev.jlkesh.java_telegram_bots.config.ThreadSafeBeansContainer.userState;

public class GenerateDataMessageProcessor implements Processor<GenerateDataState> {
    private final TelegramBot bot = TelegramBotConfiguration.get();

    @Override
    public void process(Update update, GenerateDataState state) {
        Message message = update.message();
        String text = message.text();
        Long chatID = message.chat().id();
        String language = message.from().languageCode();
        if ( state.equals(GenerateDataState.FILE_NAME) ) {
            collected.get(chatID).put("filename", text);
            bot.execute(SendMessageFactory.getSendMessageWithFileTypeKeyboard(chatID, "key", language));
            userState.put(chatID, GenerateDataState.FILE_TYPE);
        } else if ( state.equals(GenerateDataState.FILE_TYPE) )
            bot.execute(new DeleteMessage(chatID, message.messageId()));
    }
}
