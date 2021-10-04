package de.skullmc.cookie.listener;

import de.skullmc.cookie.Cookie;
import de.skullmc.cookie.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private final Cookie plugin;

    public BlockPlaceListener(Cookie plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handleBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (event.getBlockPlaced().getType().equals(Material.STAINED_GLASS) && player.getItemInHand().getItemMeta().getDisplayName() != null) {
            if (player.getItemInHand().getItemMeta().getDisplayName().equals("§6▰§e▰ Keks")) {
                plugin.getLocations().setLocation(event.getBlockPlaced().getLocation());
                player.sendMessage(Cookie.PREFIX + "Der Keks wurde erfolgreich platziert");
                ArmorStand armorStand = (ArmorStand) event.getBlockPlaced().getWorld().spawnEntity(event.getBlockPlaced().getLocation().add(0.5, 1.5, 0.5), EntityType.ARMOR_STAND);
                armorStand.setMarker(true);
                armorStand.setCustomName("§8▰ §6Keks§eKlicker §8▰");
                armorStand.setCustomNameVisible(true);
                armorStand.setGravity(false);
                armorStand.setVisible(false);
                ArmorStand armorStand$2 = (ArmorStand) event.getBlockPlaced().getWorld().spawnEntity(event.getBlockPlaced().getLocation().add(0.5, 1.20, 0.5), EntityType.ARMOR_STAND);
                armorStand$2.setMarker(true);
                armorStand$2.setCustomName("§7Wirst du der neue Kekskönig?");
                armorStand$2.setCustomNameVisible(true);
                armorStand$2.setGravity(false);
                armorStand$2.setVisible(false);
                ArmorStand armorStand$3 = (ArmorStand) event.getBlockPlaced().getWorld().spawnEntity(event.getBlockPlaced().getLocation().add(0.5, -1.2, 0.5), EntityType.ARMOR_STAND);
                armorStand$3.setHelmet(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTFmZDk4MWNlMTVhMjk4MGE5NjExNWM4ZDY3MDk5ZjRiY2I0OTFmMmU2Yzc5MDg0N2E3OWMzNjBlNWZjIn19fQ==").build());
                armorStand$3.setCustomName("cookie_skull");
                armorStand$3.setMarker(false);
                armorStand$3.setGravity(false);
                armorStand$3.setVisible(false);
            }
        }
    }
}