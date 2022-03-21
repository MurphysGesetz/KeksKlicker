package de.skullmc.cookie.tasks;

import de.skullmc.cookie.Cookie;
import de.skullmc.cookie.player.CookiePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class ReconstructDataTask implements Runnable{

    private final Cookie plugin;
    private final Server server;

    public ReconstructDataTask(Cookie plugin, Server server) {
        this.plugin = plugin;
        this.server = server;
    }

    @Override
    public void run() {
        if (!plugin.getMySqlConnection().isConnected()) return;
        for (Player current : server.getOnlinePlayers()) {
            CookiePlayer cookiePlayer = plugin.getCookiePlayerHelper().create(current);
            cookiePlayer.updateUpgradeInventory();
            cookiePlayer.updateAchievmentInventory();
        }
    }
}
