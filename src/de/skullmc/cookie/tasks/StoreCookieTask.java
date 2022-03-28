package de.skullmc.cookie.tasks;

import de.skullmc.cookie.Cookie;
import de.skullmc.cookie.player.CookiePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.*;

public class StoreCookieTask implements Runnable{

    private final Cookie plugin;
    private final Server server;

    public StoreCookieTask(Cookie plugin, Server server) {
        this.plugin = plugin;
        this.server = server;
    }

    @Override
    public void run() {
        final Collection<? extends Player> players = server.getOnlinePlayers();
        players.forEach(current -> {
            final CookiePlayer cookiePlayer = plugin.getCookiePlayerHelper().getCookiePlayer(current);
            if(cookiePlayer == null) return;
            plugin.getMySQLTableHelper().setCookies(current.getUniqueId().toString(), cookiePlayer.getCookies());
            plugin.getMySQLTableHelper().setAchievments(current.getUniqueId().toString(), cookiePlayer.getAchievments());
        });
        players.forEach(current -> {
            final CookiePlayer cookiePlayer = plugin.getCookiePlayerHelper().getCookiePlayer(current);
            if(cookiePlayer == null) return;
            cookiePlayer.updateAchievmentInventory();
        });
    }
}
