package dev.jlkesh.java_telegram_bots.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
import dev.jlkesh.java_telegram_bots.config.TelegramBotConfiguration;
import dev.jlkesh.java_telegram_bots.dto.Dictionary;
import dev.jlkesh.java_telegram_bots.state.DefaultState;
import dev.jlkesh.java_telegram_bots.state.RegistrationState;
import dev.jlkesh.java_telegram_bots.state.State;
import dev.jlkesh.java_telegram_bots.utils.factory.InlineKeyboardMarkupFactory;

import static dev.jlkesh.java_telegram_bots.config.ThreadSafeBeansContainer.collected;
import static dev.jlkesh.java_telegram_bots.config.ThreadSafeBeansContainer.userState;


/*@Slf4j*/
public class MessageHandler implements Handler {
    private final TelegramBot bot = TelegramBotConfiguration.get();

    @Override
    public void handle(Update update) {
        Message message = update.message();
        Chat chat = message.chat();
        Long chatID = chat.id();
        State state = userState.get(chatID);
        if ( state == null )
            startRegister(chatID);
        else if ( state instanceof RegistrationState registrationState ) {
            if ( registrationState.equals(RegistrationState.USERNAME) ) {
                String messageText = "Enter Password Please \n _ _ _ _ ";
                bot.execute(InlineKeyboardMarkupFactory.getSendMessageWithPasswordKeyboard(chatID, messageText));
                userState.put(chatID, RegistrationState.PASSWORD);
                collected.get(chatID).put("username", message.text());
            } else if ( registrationState.equals(RegistrationState.PASSWORD) ) {
                bot.execute(new DeleteMessage(chatID, message.messageId()));
            }
        } else if ( state instanceof DefaultState defaultState ) {
            if ( defaultState.equals(DefaultState.DELETE) ) {
                bot.execute(new DeleteMessage(chatID, message.messageId()));
            } else if ( defaultState.equals(DefaultState.NO_ACTION) ) {
                String messageText = message.text();
            }
        }
    }

    private void startRegister(Long chatID) {
        Dictionary<String, String> pairs = new Dictionary<>(2);
        userState.put(chatID, RegistrationState.USERNAME);
        collected.put(chatID, pairs);
        SendMessage sendMessage = new SendMessage(chatID, "Welcome\nPlease Register\nUsername please");
        sendMessage.replyMarkup(new ForceReply());
        bot.execute(sendMessage);
    }
}
