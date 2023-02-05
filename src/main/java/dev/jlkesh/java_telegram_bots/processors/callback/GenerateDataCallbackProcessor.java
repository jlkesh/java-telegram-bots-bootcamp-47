package dev.jlkesh.java_telegram_bots.processors.callback;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import dev.jlkesh.java_telegram_bots.config.TelegramBotConfiguration;
import dev.jlkesh.java_telegram_bots.processors.Processor;
import dev.jlkesh.java_telegram_bots.state.GenerateDataState;
import dev.jlkesh.java_telegram_bots.utils.factory.AnswerCallbackQueryFactory;
import dev.jlkesh.java_telegram_bots.utils.factory.InlineKeyboardMarkupFactory;
import dev.jlkesh.java_telegram_bots.utils.factory.SendMessageFactory;

import static dev.jlkesh.java_telegram_bots.config.ThreadSafeBeansContainer.collected;
import static dev.jlkesh.java_telegram_bots.config.ThreadSafeBeansContainer.userState;
import static dev.jlkesh.java_telegram_bots.utils.MessageSourceUtils.getLocalizedMessage;

public class GenerateDataCallbackProcessor implements Processor<GenerateDataState> {
    private final TelegramBot bot = TelegramBotConfiguration.get();

    @Override
    public void process(Update update, GenerateDataState state) {
        CallbackQuery callbackQuery = update.callbackQuery();
        Message message = callbackQuery.message();
        String callbackData = callbackQuery.data();
        Chat chat = message.chat();
        Long chatID = chat.id();
        if ( state.equals(GenerateDataState.FILE_TYPE) ) {
            if ( callbackData.equals("json") ) {
                collected.get(chatID).put("filetype", callbackData);
                // TODO: 05/02/23 localize
                bot.execute(new SendMessage(chatID, "Enter rows Count "));
                userState.put(chatID, GenerateDataState.ROW_COUNT);
            } else if ( callbackData.equals("csv") ) {
                // TODO: 05/02/23 localize here
                bot.execute(AnswerCallbackQueryFactory.answerCallbackQuery(callbackQuery.id(), "Sorry This CSV Type not supported yet"));
            } else {
                // TODO: 05/02/23 localize here
                bot.execute(AnswerCallbackQueryFactory.answerCallbackQuery(callbackQuery.id(), "Sorry This SQL Type not supported yet"));
            }
        }

    }
}
