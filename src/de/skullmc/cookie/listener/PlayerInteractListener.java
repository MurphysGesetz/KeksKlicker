package de.skullmc.cookie.listener;

import de.skullmc.cookie.Cookie;
import de.skullmc.cookie.player.CookiePlayer;
import de.skullmc.cookie.utils.ItemBuilder;
import de.skullmc.cookie.utils.MessageFormatter;
import de.skullmc.cookie.utils.Particles;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerInteractListener implements Listener {

    private final Cookie plugin;
    private int animationStep;
    private int taskID;
    private boolean animationRunning;
    private final List<Integer> achievments;
    private final List<String> achievmentName;
    private final ThreadLocalRandom random;

    public PlayerInteractListener(Cookie plugin) {
        this.achievments = Arrays.asList(100, 1000, 10000, 50000, 100000, 250000, 500000, 1000000, 10000000, 1000000000);
        this.achievmentName = Arrays.asList("Keks-Lehrling", "Keks-Dieb", "Keks-Ernter", "Alles meine!",
                "Keine Hobbies", "Meister der Kekse", "Ich bekomme sie alle!", "Finger brennt!", "Gott der Kekse", "KeksKlicker durchgespielt");
        this.plugin = plugin;
        this.random = ThreadLocalRandom.current();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handlePlayerInteract(PlayerInteractEvent event) {
        final Block block = event.getClickedBlock();
        final boolean result = (block == null || !plugin.getLocations().getCookieLocation().equals(block.getLocation()));
        if (result) return;

        final Player player = event.getPlayer();
        final CookiePlayer cookiePlayer = plugin.getCookiePlayerHelper().getCookiePlayer(player);

        if (cookiePlayer == null) return;
        if (cookiePlayer.getClickCounter() > 34) {
            plugin.getTitles().sendTitle(player, "§4§l✕", "§fStopp", 20);
            return;
        }
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            final int cps = cookiePlayer.getClicksPerSecond();
            cookiePlayer.addCookies(cps);
            final long cookies = cookiePlayer.getCookies();
            plugin.getTitles().sendTitle(player, "§e" + MessageFormatter.format(cookies) + " §6Kekse", "§8+§b" + MessageFormatter.format(cps), 20);
            if (random.nextInt(1000) < 4) {
                player.sendMessage(Cookie.PREFIX + "§aLecker! §7Du hast einen kritischen Treffer gelandet!");
                cookiePlayer.addCookies(cps * 10L);
            }
            final int achievmentValue = cookiePlayer.getAchievments();
            if (achievmentValue == 10) return;
            if (cookies >= achievments.get(achievmentValue)) {
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1L, 1L);
                player.sendMessage(Cookie.PREFIX + "Du hast den Erfolg §9" + achievmentName.get(achievmentValue) + " §7errungen!");
                cookiePlayer.setAchievments(achievmentValue + 1);
                cookiePlayer.updateAchievmentInventory();
                startAchievmentAnimation(block.getLocation());
            }
            return;
        }
        player.openInventory(plugin.getInventoryLoader().getCookieMenu());
    }

    private void startAchievmentAnimation(Location location) {
        if (animationRunning) return;
        animationRunning = true;
        final ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location.add(0.5, -0.4, 0.5), EntityType.ARMOR_STAND);
        armorStand.setHelmet(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTFmZDk4MWNlMTVhMjk4MGE5NjExNWM4ZDY3MDk5ZjRiY2I0OTFmMmU2Yzc5MDg0N2E3OWMzNjBlNWZjIn19fQ==").build());
        armorStand.setCustomName("cookie_skull");
        armorStand.setMarker(false);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setSmall(true);
        animationStep = 0;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (animationStep == 32) {
                armorStand.remove();
                animationRunning = false;
                Bukkit.getScheduler().cancelTask(taskID);
                return;
            }
            final Location armorStandLocation = armorStand.getLocation();
            armorStand.teleport(new Location(armorStand.getWorld(), armorStandLocation.getX(), armorStandLocation.getY() + 0.075, armorStandLocation.getZ(), armorStandLocation.getYaw() + 15, 0));
            Particles particles = new Particles(EnumParticle.FLAME, armorStandLocation.add(0, 0.42, 0), true, 0.05F, 0F, 0.05F, 0, 2);
            particles.sendAll();
            animationStep++;
        }, 2, 2L);
    }
}