package dev.jlkesh.java_telegram_bots.utils;

import lombok.NonNull;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageSourceUtils {
    private static final ThreadLocal<ResourceBundle> en = ThreadLocal.withInitial(() -> ResourceBundle.getBundle("messages"));
    private static final ThreadLocal<ResourceBundle> uz = ThreadLocal.withInitial(() -> ResourceBundle.getBundle("messages", Locale.forLanguageTag("uz")));

    public static String getLocalizedMessage(@NonNull String key, @NonNull String language) {
        if ( language.equals("uz") )
            return uz.get().getString(key);
        return en.get().getString(key);
    }
}