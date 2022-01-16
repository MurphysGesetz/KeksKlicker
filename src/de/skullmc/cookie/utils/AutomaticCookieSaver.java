package de.skullmc.cookie.utils;

import de.skullmc.cookie.Cookie;
import de.skullmc.cookie.player.CookiePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AutomaticCookieSaver {

    private final Cookie plugin;

    public AutomaticCookieSaver(Cookie plugin) {
        this.plugin = plugin;
    }

    public void startScheduler() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            for (Player current : Bukkit.getOnlinePlayers()) {
                final CookiePlayer cookiePlayer = plugin.getCookiePlayerHelper().getCookiePlayer(current);
                if(cookiePlayer == null) return;
                plugin.getMySQLTableHelper().setCookies(current.getUniqueId().toString(), cookiePlayer.getCookies());
                plugin.getMySQLTableHelper().setAchievments(current.getUniqueId().toString(), cookiePlayer.getAchievments());
            }
            for(Player current : Bukkit.getOnlinePlayers()) {
                final CookiePlayer cookiePlayer = plugin.getCookiePlayerHelper().getCookiePlayer(current);
                if(cookiePlayer == null) return;
                cookiePlayer.updateAchievmentInventory();
            }
        }, 1200L, 6000L);
    }
}
