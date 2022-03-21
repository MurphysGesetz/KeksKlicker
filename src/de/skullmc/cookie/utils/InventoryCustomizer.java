package de.skullmc.cookie.utils;

import de.skullmc.cookie.Cookie;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class InventoryCustomizer {

    private final Cookie plugin;

    public InventoryCustomizer(Cookie plugin) {
        this.plugin = plugin;
    }

    public void customizeUpgradeInventory(Player player) {
        final Inventory inventory = Bukkit.createInventory(null, 6 * 9, "");
        inventory.setContents(plugin.getInventoryLoader().getCookieUpgrades().getContents());
        final long helperAmount = plugin.getMySQLTableHelper().getUpgrade(player.getUniqueId().toString(), "HELPER");
        final long grandmaAmount = plugin.getMySQLTableHelper().getUpgrade(player.getUniqueId().toString(), "GRANDMA");
        final long fingerAmount = plugin.getMySQLTableHelper().getUpgrade(player.getUniqueId().toString(), "FINGER");
        final long chickenAmount = plugin.getMySQLTableHelper().getUpgrade(player.getUniqueId().toString(), "CHICKEN");
        final long minigunAmount = plugin.getMySQLTableHelper().getUpgrade(player.getUniqueId().toString(), "MINIGUN");
        final long coderAmount = plugin.getMySQLTableHelper().getUpgrade(player.getUniqueId().toString(), "CODER");
        final long plantationAmount = plugin.getMySQLTableHelper().getUpgrade(player.getUniqueId().toString(), "PLANTATION");
        final long turbineAmount = plugin.getMySQLTableHelper().getUpgrade(player.getUniqueId().toString(), "TURBINE");
        final long hustlerAmount = plugin.getMySQLTableHelper().getUpgrade(player.getUniqueId().toString(), "HUSTLER");
        final long chinaAmount = plugin.getMySQLTableHelper().getUpgrade(player.getUniqueId().toString(), "CHINA");
        final long nuclearAmount = plugin.getMySQLTableHelper().getUpgrade(player.getUniqueId().toString(), "NUCLEAR");
        inventory.setItem(10, new ItemBuilder(Material.WOOL).setName("§5▰§d▰ Helferlein").setLore("§7Kekse/Klick §8▰ §a+§f1", "§7Gekauft §8▰ §f" + helperAmount, (helperAmount == 100) ? "§7Preis §8▰ §cAusverkauft" : "§7Preis §8▰ §f" + MessageFormatter.format((40 + (120 * helperAmount))) + " §6Kekse", "", (helperAmount == 100) ? "§a§l100 Verbesserungen erworben" : "§aKlicke, um die Verbesserung zu kaufen.").build());
        inventory.setItem(11, new ItemBuilder(Material.STICK).setName("§5▰§d▰ Alte Oma").setLore("§7Kekse/Klick §8▰ §a+§f3", "§7Gekauft §8▰ §f" + grandmaAmount, (grandmaAmount == 100) ? "§7Preis §8▰ §cAusverkauft" : "§7Preis §8▰ §f" + MessageFormatter.format((150 + (360 * grandmaAmount))) + " §6Kekse", "", (grandmaAmount == 100) ? "§a§l100 Verbesserungen erworben" : "§aKlicke, um die Verbesserung zu kaufen.").build());
        inventory.setItem(12, new ItemBuilder(Material.POTATO_ITEM).setName("§5▰§d▰ Schneller Finger").setLore("§7Kekse/Klick §8▰ §a+§f10", "§7Gekauft §8▰ §f" + fingerAmount, (fingerAmount == 100) ? "§7Preis §8▰ §cAusverkauft" : "§7Preis §8▰ §f" + MessageFormatter.format((5000 + (7500 * fingerAmount))) + " §6Kekse", "", (fingerAmount == 100) ? "§a§l100 Verbesserungen erworben" : "§aKlicke, um die Verbesserung zu kaufen.").build());
        inventory.setItem(13, new ItemBuilder(Material.EGG).setName("§5▰§d▰ Mechanisches Huhn").setLore("§7Kekse/Klick §8▰ §a+§f30", "§7Gekauft §8▰ §f" + chickenAmount, (chickenAmount == 100) ? "§7Preis §8▰ §cAusverkauft" : "§7Preis §8▰ §f" + MessageFormatter.format((30000 + (45000 * chickenAmount))) + " §6Kekse", "", (chickenAmount == 100) ? "§a§l100 Verbesserungen erworben" : "§aKlicke, um die Verbesserung zu kaufen.").build());
        inventory.setItem(14, new ItemBuilder(Material.CLAY_BALL).setName("§5▰§d▰ MiniGun").setLore("§7Kekse/Klick §8▰ §a+§f100", "§7Gekauft §8▰ §f" + minigunAmount, (minigunAmount == 100) ? "§7Preis §8▰ §cAusverkauft" : "§7Preis §8▰ §f" + MessageFormatter.format((100000 + (150000 * minigunAmount))) + " §6Kekse", "", (minigunAmount == 100) ? "§a§l100 Verbesserungen erworben" : "§aKlicke, um die Verbesserung zu kaufen.").build());
        inventory.setItem(15, new ItemBuilder(Material.PAPER).setName("§5▰§d▰ Profi Coder").setLore("§7Kekse/Klick §8▰ §a+§f500", "§7Gekauft §8▰ §f" + coderAmount, (coderAmount == 100) ? "§7Preis §8▰ §cAusverkauft" : "§7Preis §8▰ §f" + MessageFormatter.format((500000 + (750000 * coderAmount))) + " §6Kekse", "", (coderAmount == 100) ? "§a§l100 Verbesserungen erworben" : "§aKlicke, um die Verbesserung zu kaufen.").build());
        inventory.setItem(16, new ItemBuilder(Material.SAPLING).setName("§5▰§d▰ Recycle Plantage").setLore("§7Kekse/Klick §8▰ §a+§f1.000", "§7Gekauft §8▰ §f" + plantationAmount, (plantationAmount == 100) ? "§7Preis §8▰ §cAusverkauft" : "§7Preis §8▰ §f" + MessageFormatter.format((1500000 + (2500000 * plantationAmount))) + " §6Kekse", "", (plantationAmount == 100) ? "§a§l100 Verbesserungen erworben" : "§aKlicke, um die Verbesserung zu kaufen.").build());
        inventory.setItem(19, new ItemBuilder(Material.COMPASS).setName("§5▰§d▰ Turbine").setLore("§7Kekse/Klick §8▰ §a+§f5.000", "§7Gekauft §8▰ §f" + turbineAmount, (turbineAmount == 100) ? "§7Preis §8▰ §cAusverkauft" : "§7Preis §8▰ §f" + MessageFormatter.format((10000000 + (5000000 * turbineAmount))) + " §6Kekse", "", (turbineAmount == 100) ? "§a§l100 Verbesserungen erworben" : "§aKlicke, um die Verbesserung zu kaufen.").build());
        inventory.setItem(20, new ItemBuilder(Material.BOOK).setName("§5▰§d▰ Hustler").setLore("§7Kekse/Klick §8▰ §a+§f10.000", "§7Gekauft §8▰ §f" + hustlerAmount, (hustlerAmount == 100) ? "§7Preis §8▰ §cAusverkauft" : "§7Preis §8▰ §f" + MessageFormatter.format((25000000 + (10000000 * hustlerAmount))) + " §6Kekse", "", (hustlerAmount == 100) ? "§a§l100 Verbesserungen erworben" : "§aKlicke, um die Verbesserung zu kaufen.").build());
        inventory.setItem(21, new ItemBuilder(Material.DIAMOND).setName("§5▰§d▰ Chinafarmer").setLore("§7Kekse/Klick §8▰ §a+§f25.000", "§7Gekauft §8▰ §f" + chinaAmount, (chinaAmount == 100) ? "§7Preis §8▰ §cAusverkauft" : "§7Preis §8▰ §f" + MessageFormatter.format((75000000 + (25000000 * chinaAmount))) + " §6Kekse", "", (chinaAmount == 100) ? "§a§l100 Verbesserungen erworben" : "§aKlicke, um die Verbesserung zu kaufen.").build());
        inventory.setItem(22, new ItemBuilder(Material.EMERALD).setName("§5▰§d▰ Atomkraftwerk").setLore("§7Kekse/Klick §8▰ §a+§f50.000", "§7Gekauft §8▰ §f" + nuclearAmount, (nuclearAmount == 100) ? "§7Preis §8▰ §cAusverkauft" : "§7Preis §8▰ §f" + MessageFormatter.format((200000000 + (150000000 * nuclearAmount))) + " §6Kekse", "", (nuclearAmount == 100) ? "§a§l100 Verbesserungen erworben" : "§aKlicke, um die Verbesserung zu kaufen.").build());
        plugin.getCookiePlayerHelper().getCookiePlayer(player).getUpgradeInventory().setContents(inventory.getContents());
    }

    public Inventory customizeAchievmentInventory(Player player) {
        final Inventory inventory = Bukkit.createInventory(null, 6 * 9, "§6▰§e▰ Erfolge §8▰ §7Einsehen");
        final int achievmentValue = plugin.getCookiePlayerHelper().getCookiePlayer(player).getAchievments();
        inventory.setContents(plugin.getInventoryLoader().getCookieAchievments().getContents());
        for (int i = 0; i < achievmentValue; i++) {
            inventory.setItem(plugin.getInventoryLoader().getAchievmentSlots().get(i), plugin.getInventoryLoader().getAchievmentItemStacks().get(i));
        }
        try {
            final ResultSet resultSet = plugin.getMySqlConnection().getStatement("SELECT UUID FROM cookies ORDER BY COOKIES DESC LIMIT 5").executeQuery();
            final Map<Integer, String> top3Players = new HashMap<>();
            int order = 0;
            while (resultSet.next()) {
                order++;
                top3Players.put(order, resultSet.getString("UUID"));
            }
            resultSet.close();
            int slot = 1;
            for (int i = 0; i < 5; i++) {
                int id = i + 1;
                slot++;
                if (top3Players.get(id) == null) continue;
                String name = plugin.getMySQLTableHelper().getName(top3Players.get(id));
                inventory.setItem(slot, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner(name).setName("§7» §e§l" + name + " §7«").setLore(
                        "§7Ranking §8▰ §e" + id, "§7Kekse §8▰ §e" + MessageFormatter.format(plugin.getMySQLTableHelper().getCookies(top3Players.get(id))), "§7Erfolge §8▰ §e" + plugin.getMySQLTableHelper().getAchievments(top3Players.get(id))).build());
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return inventory;
    }
}