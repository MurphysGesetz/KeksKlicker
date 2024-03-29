package de.skullmc.cookie.player;

import de.skullmc.cookie.Cookie;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class CookiePlayer {

    private final UUID uuid;
    private final Cookie plugin;
    private long cookies;
    private int clicksPerSecond;
    private int achievments;
    private int clickCounter;
    private final Inventory upgradeInventory;
    private Inventory achievmentInventory;

    public CookiePlayer(UUID uuid, Cookie plugin) {
        this.plugin = plugin;
        this.uuid = uuid;
        plugin.getMySQLTableHelper().getCookies(uuid.toString()); // Do not remove!!
        this.cookies = plugin.getMySQLTableHelper().getCookies(uuid.toString());
        this.achievments = plugin.getMySQLTableHelper().getAchievments(uuid.toString());
        this.clickCounter = 0;
        this.upgradeInventory = Bukkit.createInventory(null, 6 * 9, "§6▰§e▰ Verbessern §8▰ §7Auswahl");
        calculateCookiesPerClick();
    }

    public boolean isBanned() {
        return plugin.getMySQLTableHelper().getName(uuid.toString()).endsWith("~");
    }

    public void resetClickCounter() {
        this.clickCounter = 0;
    }

    private void addClickCounter() {
        this.clickCounter += 1;
    }

    public int getClickCounter() {
        addClickCounter();
        return clickCounter;
    }

    public Inventory getAchievmentInventory() {
        return achievmentInventory;
    }

    public void updateAchievmentInventory() {
        this.achievmentInventory = plugin.getInventoryCustomizer().customizeAchievmentInventory(Bukkit.getPlayer(uuid));
    }

    public Inventory getUpgradeInventory() {
        return upgradeInventory;
    }

    public void updateUpgradeInventory() {
        plugin.getInventoryCustomizer().customizeUpgradeInventory(Bukkit.getPlayer(uuid));
    }

    public int getAchievments() {
        return achievments;
    }

    public void setAchievments(int achievments) {
        this.achievments = achievments;
    }

    public int getClicksPerSecond() {
        return clicksPerSecond;
    }

    public long getCookies() {
        return cookies;
    }

    public void setCookies(long cookies) {
        this.cookies = cookies;
    }

    public void addCookies(long cookies) {
        this.cookies += cookies;
    }

    public void calculateCookiesPerClick() {
        final String uuid = this.uuid.toString();
        this.clicksPerSecond = 1 + plugin.getMySQLTableHelper().getUpgrade(uuid, "HELPER") +
                (plugin.getMySQLTableHelper().getUpgrade(uuid, "GRANDMA") * 3) +
                (plugin.getMySQLTableHelper().getUpgrade(uuid, "FINGER") * 10) +
                (plugin.getMySQLTableHelper().getUpgrade(uuid, "CHICKEN") * 30) +
                (plugin.getMySQLTableHelper().getUpgrade(uuid, "MINIGUN") * 100) +
                (plugin.getMySQLTableHelper().getUpgrade(uuid, "CODER") * 500) +
                (plugin.getMySQLTableHelper().getUpgrade(uuid, "PLANTATION") * 1000) +
                (plugin.getMySQLTableHelper().getUpgrade(uuid, "TURBINE") * 5000) +
                (plugin.getMySQLTableHelper().getUpgrade(uuid, "HUSTLER") * 10000) +
                (plugin.getMySQLTableHelper().getUpgrade(uuid, "CHINA") * 25000) +
                (plugin.getMySQLTableHelper().getUpgrade(uuid, "NUCLEAR") * 50000);
    }

    public void banPlayer() {
        final String uuid = this.uuid.toString();
        plugin.getMySQLTableHelper().resetUpgrade(uuid, "HELPER");
        plugin.getMySQLTableHelper().resetUpgrade(uuid, "GRANDMA");
        plugin.getMySQLTableHelper().resetUpgrade(uuid, "FINGER");
        plugin.getMySQLTableHelper().resetUpgrade(uuid, "CHICKEN");
        plugin.getMySQLTableHelper().resetUpgrade(uuid, "MINIGUN");
        plugin.getMySQLTableHelper().resetUpgrade(uuid, "CODER");
        plugin.getMySQLTableHelper().resetUpgrade(uuid, "PLANTATION");
        plugin.getMySQLTableHelper().resetUpgrade(uuid, "TURBINE");
        plugin.getMySQLTableHelper().resetUpgrade(uuid, "HUSTLER");
        plugin.getMySQLTableHelper().resetUpgrade(uuid, "CHINA");
        plugin.getMySQLTableHelper().resetUpgrade(uuid, "NUCLEAR");
        plugin.getMySQLTableHelper().setName(uuid, plugin.getMySQLTableHelper().getName(uuid) + "~");
        plugin.getMySQLTableHelper().setCookies(uuid, 0);
        plugin.getMySQLTableHelper().setAchievments(uuid, 0);
    }
}
