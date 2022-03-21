package de.skullmc.cookie.commands;

import de.skullmc.cookie.Cookie;
import de.skullmc.cookie.player.CookiePlayer;
import de.skullmc.cookie.utils.ItemBuilder;
import de.skullmc.cookie.utils.MessageFormatter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CookieCommand implements CommandExecutor {

    private final Cookie plugin;

    public CookieCommand(Cookie plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (player.hasPermission("cookie.command")) {
                if (args.length == 0) {
                    player.sendMessage("§6§lKeks§e§lKlicker §f1§8.§f2§8.§f1 §cby §4§lInterpunktion");
                    player.sendMessage(Cookie.PREFIX + "/cookie item §8| §7gib dir den Keks-Block");
                    player.sendMessage(Cookie.PREFIX + "/cookie remove §8| §7entfern den Keks-Block");
                    player.sendMessage(Cookie.PREFIX + "/cookie ban <Spieler> §8| §7Bann einen Spieler");
                    player.sendMessage(Cookie.PREFIX + "/cookie unban <Spieler> §8| §7Entbann einen Spieler");
                    player.sendMessage(Cookie.PREFIX + "/cookie amount <Spieler> §8| §7Kekse von Spieler");
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("item")) {
                        player.getInventory().addItem(new ItemBuilder(Material.STAINED_GLASS, 1, (byte) 12).setName("§6▰§e▰ Keks").build());
                        player.sendMessage(Cookie.PREFIX + "Du hast den §6Keks §7erhalten");
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        if (player.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.STAINED_GLASS) {
                            player.getLocation().subtract(0, 1, 0).getBlock().setType(Material.AIR);
                            player.sendMessage(Cookie.PREFIX + "Der §eKeksblock §7wurde entfernt§8!");
                            player.getWorld().getNearbyEntities(player.getLocation(), 3, 4, 3).forEach(current -> {
                                if (current.getCustomName() == null) return;
                                switch (current.getCustomName().toLowerCase()) {
                                    case "§8▰ §6Keks§eKlicker §8▰":
                                    case "§7Wirst du der neue Kekskönig?":
                                    case "cookie_skull":
                                        current.remove();
                                        break;
                                }
                            });
                        } else {
                            player.sendMessage(Cookie.PREFIX + "Stelle dich auf den §eKeksblock, um ihn zu entfernen§8!");
                        }
                    }
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("ban")) {
                        if (plugin.getMySQLTableHelper().playerExists(args[1])) {
                            String uuid = plugin.getMySQLTableHelper().getUniqueId(args[1]);
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
                            Player target = Bukkit.getPlayer(args[1]);
                            if (target != null) {
                                plugin.getCookiePlayerHelper().remove(target);
                            }
                            player.sendMessage(Cookie.PREFIX + "Der Spieler §e" + args[1] + " §7wurde vom KeksKlicker gebannt.");
                        } else {
                            player.sendMessage(Cookie.PREFIX + "Der Spieler §e" + args[1] + " §7wurde nicht gefunden.");
                        }
                    } else if (args[0].equalsIgnoreCase("unban")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        String uuid = plugin.getMySQLTableHelper().getUniqueId(args[1] + "~");
                        plugin.getMySQLTableHelper().setName(uuid, plugin.getMySQLTableHelper().getName(uuid).replace("~", ""));
                        if (target != null) {
                            CookiePlayer cookiePlayer = plugin.getCookiePlayerHelper().create(target);
                            cookiePlayer.updateUpgradeInventory();
                            cookiePlayer.updateAchievmentInventory();
                        }
                        player.sendMessage(Cookie.PREFIX + "Der Spieler §e" + args[1] + " §7wurde vom KeksKlicker entbannt.");
                    } else if (args[0].equalsIgnoreCase("amount")) {
                        if (plugin.getMySQLTableHelper().playerExists(args[1])) {
                            Player target = Bukkit.getPlayer(args[1]);
                            if (target != null) {
                                CookiePlayer cookiePlayer = plugin.getCookiePlayerHelper().getCookiePlayer(target);
                                player.sendMessage("§8[§fIngame§8] §7Der Spieler §e" + args[1] + "§7 hat §6§l" + MessageFormatter.format(cookiePlayer.getCookies()) + " Kekse");
                            } else {
                                player.sendMessage("§8[§fDatenbank§8] §7Der Spieler §e" + args[1] + "§7 hat §6§l" + MessageFormatter.format(plugin.getMySQLTableHelper().getCookiesByName(args[1])) + " Kekse");
                            }
                        } else {
                            player.sendMessage(Cookie.PREFIX + "Der Spieler §e" + args[1] + " §7wurde nicht gefunden.");
                        }
                    }
                }
            } else {
                player.sendMessage("§6§lKeks§e§lKlicker §f1§8.§f2§8.§f1 §cby §4§lInterpunktion");
            }
        }
        return false;
    }
}
