package de.skullmc.cookie.tasks;

import de.skullmc.cookie.Cookie;
import org.bukkit.Server;

public class ReduceClicksTask implements Runnable{

    private final Cookie plugin;
    private final Server server;

    public ReduceClicksTask(Cookie plugin, Server server) {
        this.plugin = plugin;
        this.server = server;
    }

    @Override
    public void run() {
        server.getOnlinePlayers().stream().filter(current -> plugin.getCookiePlayerHelper().getCookiePlayer(current) != null)
                .forEach(current -> plugin.getCookiePlayerHelper().getCookiePlayer(current).resetClickCounter());
    }
}
