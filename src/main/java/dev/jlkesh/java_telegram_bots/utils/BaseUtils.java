package dev.jlkesh.java_telegram_bots.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class BaseUtils {
    public static final String STAR = "*️⃣";
    public static final String CLEAR = "\uD83C\uDD91";
    public static final String TICK = "✅";

    public static String getStackStraceAsString(Throwable e) {
        StringWriter out = new StringWriter();
        e.printStackTrace(new PrintWriter(out));
        return out.toString();
    }
}
