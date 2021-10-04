package de.skullmc.cookie.player;

import de.skullmc.cookie.Cookie;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CookiePlayerHelper {

    private final Map<UUID, CookiePlayer> playerCache;
    private final Cookie plugin;

    public CookiePlayerHelper(final Cookie plugin) {
        this.playerCache = new ConcurrentHashMap<>();
        this.plugin = plugin;
    }

    public CookiePlayer getCookiePlayer(final Player player) {
        if (!playerCache.containsKey(player.getUniqueId())) return null;
        final CookiePlayer cookiePlayer = playerCache.get(player.getUniqueId());
        if (cookiePlayer == null) {
            return create(player);
        }
        return cookiePlayer;
    }

    public CookiePlayer create(final Player player) {
        final CookiePlayer cookiePlayer = new CookiePlayer(player.getUniqueId(), plugin);
        playerCache.put(player.getUniqueId(), cookiePlayer);
        return cookiePlayer;
    }

    public void remove(final Player player) {
        playerCache.remove(player.getUniqueId());
    }
}