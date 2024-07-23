package dev.pilati.enhancedwindcharge.messages;

import dev.pilati.enhancedwindcharge.EnhancedWindCharge;
import net.md_5.bungee.api.ChatColor;

public class MessageUtil {

    public static String getMessage(String key) {
        String message = EnhancedWindCharge.getInstance().getConfig().getString("messages." + key);
        if (message == null) {
            return null;
        }

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String getPrefix() {
        return getMessage("prefix");
    }

    public static String getPrefixedMessage(String key) {
        return getPrefix() + getMessage(key);
    }
}
