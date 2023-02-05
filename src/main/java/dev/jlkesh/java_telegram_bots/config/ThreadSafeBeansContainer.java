package dev.jlkesh.java_telegram_bots.config;

import dev.jlkesh.java_telegram_bots.daos.UserDao;
import dev.jlkesh.java_telegram_bots.dto.Dictionary;
import dev.jlkesh.java_telegram_bots.handlers.CallbackHandler;
import dev.jlkesh.java_telegram_bots.handlers.ClearHandler;
import dev.jlkesh.java_telegram_bots.handlers.Handler;
import dev.jlkesh.java_telegram_bots.handlers.MessageHandler;
import dev.jlkesh.java_telegram_bots.processors.RegisterUserCallbackProcessor;
import dev.jlkesh.java_telegram_bots.services.UserService;
import dev.jlkesh.java_telegram_bots.state.State;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafeBeansContainer {
    public static final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public static final ThreadLocal<Handler> messageHandler = ThreadLocal.withInitial(MessageHandler :: new);
    public static final ThreadLocal<Handler> callbackHandler = ThreadLocal.withInitial(CallbackHandler :: new);
    public static final ThreadLocal<Handler> clearHandler = ThreadLocal.withInitial(ClearHandler :: new);
    public static final ConcurrentHashMap<Long, State> userState = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<Object, Dictionary<String, String>> collected = new ConcurrentHashMap<>();
    public static final ThreadLocal<UserDao> userDao = ThreadLocal.withInitial(UserDao :: new);
    public static final ThreadLocal<UserService> userService = ThreadLocal.withInitial(() -> new UserService(userDao.get()));
    public static final ThreadLocal<RegisterUserCallbackProcessor> registerUserCallbackProcessor = ThreadLocal.withInitial(RegisterUserCallbackProcessor :: new);
}
