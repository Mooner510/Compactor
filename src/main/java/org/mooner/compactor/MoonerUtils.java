package org.mooner.compactor;

import org.bukkit.ChatColor;

public final class MoonerUtils {
    public static String chat(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
