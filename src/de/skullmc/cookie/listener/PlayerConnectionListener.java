package de.skullmc.cookie.listener;

import de.skullmc.cookie.Cookie;
import de.skullmc.cookie.player.CookiePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnectionListener implements Listener {

    private final Cookie plugin;
    public PlayerConnectionListener(Cookie plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final CookiePlayer cookiePlayer = plugin.getCookiePlayerHelper().create(player);
        cookiePlayer.updateUpgradeInventory();
        cookiePlayer.updateAchievmentInventory();
        if (!plugin.getMySQLTableHelper().playerExists(player.getUniqueId())) {
            plugin.getMySQLTableHelper().createPlayer(player.getUniqueId().toString(), player.getName());
        } else if(cookiePlayer.isBanned()) {
            plugin.getCookiePlayerHelper().remove(player);
        }
    }
}
