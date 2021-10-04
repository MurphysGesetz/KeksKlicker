package de.skullmc.cookie.listener;

import de.skullmc.cookie.Cookie;
import de.skullmc.cookie.player.CookiePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDisconnectListener implements Listener {

    private final Cookie plugin;

    public PlayerDisconnectListener(Cookie plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handlePlayerDisconnect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        final CookiePlayer cookiePlayer = plugin.getCookiePlayerHelper().getCookiePlayer(player);
        if(cookiePlayer == null) return;
        plugin.getMySQLTableHelper().setCookies(player.getUniqueId().toString(), cookiePlayer.getCookies());
        plugin.getMySQLTableHelper().setAchievments(player.getUniqueId().toString(), cookiePlayer.getAchievments());
        plugin.getCookiePlayerHelper().remove(player);
    }
}
