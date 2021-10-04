package de.skullmc.cookie.listener;

import de.skullmc.cookie.Cookie;
import de.skullmc.cookie.player.CookiePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    private final Cookie plugin;

    public InventoryClickListener(Cookie plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handleInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        final int clickedSlot = event.getSlot();
        final Player player = (Player) event.getWhoClicked();
        switch (event.getClickedInventory().getTitle().toLowerCase()) {
            case "§6▰§e▰ keks §8▰ §7auswahl":
                final CookiePlayer cookiePlayer = plugin.getCookiePlayerHelper().getCookiePlayer(player);
                event.setCancelled(true);
                if (clickedSlot == 11) {
                    player.openInventory(cookiePlayer.getAchievmentInventory());
                    player.playSound(player.getLocation(), Sound.BURP, 1L, 1L);
                } else if (clickedSlot == 15) {
                    player.openInventory(cookiePlayer.getUpgradeInventory());
                    player.playSound(player.getLocation(), Sound.BURP, 1L, 1L);
                }
                break;
            case "§6▰§e▰ erfolge §8▰ §7auswahl":
                event.setCancelled(true);
                if (clickedSlot == 49) {
                    player.openInventory(plugin.getInventoryLoader().getCookieMenu());
                }
                break;
            case "§6▰§e▰ verbessern §8▰ §7auswahl":
                event.setCancelled(true);
                switch (clickedSlot) {
                    case 49:
                        player.openInventory(plugin.getInventoryLoader().getCookieMenu());
                        break;
                    case 10:
                        tryUpgradeBuy(player, "Helferlein", "HELPER", 40, 120);
                        break;
                    case 11:
                        tryUpgradeBuy(player, "Alte Oma", "GRANDMA", 150, 360);
                        break;
                    case 12:
                        tryUpgradeBuy(player, "Schneller Finger", "FINGER", 5000, 7500);
                        break;
                    case 13:
                        tryUpgradeBuy(player, "Mechanisches Huhn", "CHICKEN", 30000, 45000);
                        break;
                    case 14:
                        tryUpgradeBuy(player, "MiniGun", "MINIGUN", 100000, 150000);
                        break;
                    case 15:
                        tryUpgradeBuy(player, "Profi Coder", "CODER", 500000, 750000);
                        break;
                    case 16:
                        tryUpgradeBuy(player, "Recycle Plantage", "PLANTATION", 1500000, 2500000);
                        break;
                    case 19:
                        tryUpgradeBuy(player, "Turbine", "TURBINE", 10000000, 5000000);
                        break;
                    case 20:
                        tryUpgradeBuy(player, "Hustler", "HUSTLER", 25000000, 10000000);
                        break;
                    case 21:
                        tryUpgradeBuy(player, "Chinafarmer", "CHINA", 75000000, 25000000);
                        break;
                    case 22:
                        tryUpgradeBuy(player, "Atomkraftwerk", "NUCLEAR", 200000000, 150000000);
                        break;
                }
        }
    }

    private void tryUpgradeBuy(Player player, String upgradeName, String columnLabel, long price, long increase) {
        final int alreadyBuyedAmount = plugin.getMySQLTableHelper().getUpgrade(player.getUniqueId().toString(), columnLabel);
        if (alreadyBuyedAmount < 100) {
            final CookiePlayer cookiePlayer = plugin.getCookiePlayerHelper().getCookiePlayer(player);
            final long priceWithIncrease = (price + (increase * alreadyBuyedAmount));
            if (cookiePlayer.getCookies() >= priceWithIncrease) {
                cookiePlayer.setCookies(cookiePlayer.getCookies() - priceWithIncrease);
                plugin.getMySQLTableHelper().addUpgrade(player.getUniqueId().toString(), columnLabel);
                player.sendMessage(Cookie.PREFIX + "Du hast die Verbesserung §d" + upgradeName + " §7gekauft.");
                cookiePlayer.updateUpgradeInventory();
                player.openInventory(cookiePlayer.getUpgradeInventory());
                cookiePlayer.calculateCookiesPerClick(player.getUniqueId().toString());
            } else {
                player.sendMessage(Cookie.PREFIX + "§cDu hast nicht genug Kekse. §8(§e" + plugin.getMessageFormatter().format(cookiePlayer.getCookies()) + "§8/§6" + plugin.getMessageFormatter().format(priceWithIncrease) + "§8)");
                player.playSound(player.getLocation(), Sound.VILLAGER_HIT, 1L, 1L);
            }
        } else {
            player.sendMessage(Cookie.PREFIX + "Du hast bereits 100 §d" + upgradeName + " §7Verbesserungen gekauft.");
            player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1L, 1L);
        }
    }
}
