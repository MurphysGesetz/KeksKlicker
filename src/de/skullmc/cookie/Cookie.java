package de.skullmc.cookie;

import de.skullmc.cookie.commands.CookieCommand;
import de.skullmc.cookie.mysql.MySQLTableHelper;
import de.skullmc.cookie.mysql.MySqlConnection;
import de.skullmc.cookie.player.CookiePlayer;
import de.skullmc.cookie.player.CookiePlayerHelper;
import de.skullmc.cookie.utils.StoreCookieTask;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Cookie extends JavaPlugin {

    public static final String PREFIX = "§8▰§7▰ §e§lKeksKlicker §8┃ §7";
    private de.skullmc.cookie.utils.Titles titles;
    private de.skullmc.cookie.utils.Locations locations;
    private MySqlConnection mySqlConnection;
    private de.skullmc.cookie.utils.InventoryLoader inventoryLoader;
    private MySQLTableHelper mySQLTableHelper;
    private CookiePlayerHelper cookiePlayerHelper;
    private de.skullmc.cookie.utils.InventoryCustomizer inventoryCustomizer;

    @Override
    public void onEnable() {
        Server server = getServer();
        getConfig().options().copyDefaults(true);
        saveConfig();
        inventoryLoader = new de.skullmc.cookie.utils.InventoryLoader();
        inventoryCustomizer = new de.skullmc.cookie.utils.InventoryCustomizer(this);
        cookiePlayerHelper = new CookiePlayerHelper(this);
        titles = new de.skullmc.cookie.utils.Titles();
        locations = new de.skullmc.cookie.utils.Locations();
        server.getScheduler().runTaskTimerAsynchronously(this, new StoreCookieTask(this), 1200L, 6000L);
        connectToMySQL();
        mySqlConnection.connect();
        init();
        server.getScheduler().scheduleSyncRepeatingTask(this, () -> server.getOnlinePlayers().stream().filter(current -> getCookiePlayerHelper().getCookiePlayer(current) != null).forEach(current -> getCookiePlayerHelper().getCookiePlayer(current).resetClickCounter()), 60L, 100L);
        server.getScheduler().runTaskLater(this, () -> {
            if (!getMySqlConnection().isConnected()) return;
            for (Player current : server.getOnlinePlayers()) {
                CookiePlayer cookiePlayer = getCookiePlayerHelper().create(current);
                cookiePlayer.updateUpgradeInventory();
                cookiePlayer.updateAchievmentInventory();
            }
        }, 40L);
    }

    @Override
    public void onDisable() {
        for (Player current : getServer().getOnlinePlayers()) {
            final CookiePlayer cookiePlayer = getCookiePlayerHelper().getCookiePlayer(current);
            getMySQLTableHelper().setCookies(current.getUniqueId().toString(), cookiePlayer.getCookies());
            getMySQLTableHelper().setAchievments(current.getUniqueId().toString(), cookiePlayer.getAchievments());
        }
        mySqlConnection.disconnect();
    }

    private void init() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new de.skullmc.cookie.listener.PlayerConnectionListener(this), this);
        pluginManager.registerEvents(new de.skullmc.cookie.listener.PlayerDisconnectListener(this), this);
        pluginManager.registerEvents(new de.skullmc.cookie.listener.PlayerArmorStandManipulateListener(), this);
        pluginManager.registerEvents(new de.skullmc.cookie.listener.PlayerInteractListener(this), this);
        pluginManager.registerEvents(new de.skullmc.cookie.listener.InventoryClickListener(this), this);
        pluginManager.registerEvents(new de.skullmc.cookie.listener.BlockPlaceListener(this), this);
        getCommand("cookie").setExecutor(new CookieCommand(this));
    }

    public de.skullmc.cookie.utils.Locations getLocations() {
        return locations;
    }

    public CookiePlayerHelper getCookiePlayerHelper() {
        return cookiePlayerHelper;
    }

    public de.skullmc.cookie.utils.Titles getTitles() {
        return titles;
    }

    public MySqlConnection getMySqlConnection() {
        return mySqlConnection;
    }

    public MySQLTableHelper getMySQLTableHelper() {
        return mySQLTableHelper;
    }

    public de.skullmc.cookie.utils.InventoryLoader getInventoryLoader() {
        return inventoryLoader;
    }

    public de.skullmc.cookie.utils.InventoryCustomizer getInventoryCustomizer() {
        return inventoryCustomizer;
    }

    private void connectToMySQL() {
        if (getConfig().getString("mysql.password").equals("12345abc")) {
            getServer().broadcastMessage(PREFIX + "§c§lÄndere die MySQL zugangsdaten!!!");
            return;
        }
        mySqlConnection = new MySqlConnection(getConfig().getString("mysql.host"), getConfig().getString("mysql.port"), getConfig().getString("mysql.database"),
                getConfig().getString("mysql.user"), getConfig().getString("mysql.password"));
        mySQLTableHelper = new MySQLTableHelper(this);
    }
}