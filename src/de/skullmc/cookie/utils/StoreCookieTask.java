package de.skullmc.cookie.utils;

import de.skullmc.cookie.Cookie;
import de.skullmc.cookie.player.CookiePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StoreCookieTask implements Runnable{

    private final Cookie plugin;

    public StoreCookieTask(Cookie plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
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
    }
}
