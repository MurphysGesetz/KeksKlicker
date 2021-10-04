package de.skullmc.cookie.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Locations {

    private final File file = new File("plugins/KeksKlicker", "locations.yml");
    private final FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
    private final Location cookieLocation;

    public Locations() {
        if (fileConfiguration.contains("keks")) {
            this.cookieLocation = getLocation();
        } else {
            this.cookieLocation = new Location(Bukkit.getWorld("world"), 0, 0, 0);
            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage("§4§lDie Keks-Location wurde noch nicht festgelegt!");
            Bukkit.broadcastMessage("§eNutze dafür §6/cookie");
            Bukkit.broadcastMessage("");
        }

    }

    private Location getLocation() {
        Location location = new Location(Bukkit.getWorld(fileConfiguration.getString("keks.world")), fileConfiguration.getDouble("keks.x"), fileConfiguration.getDouble("keks.y"),
                fileConfiguration.getDouble("keks.z"));
        location.setYaw((float) fileConfiguration.getDouble("keks.yaw"));
        location.setPitch((float) fileConfiguration.getDouble("keks.pitch"));
        return location;
    }

    public void setLocation(Location location) {
        fileConfiguration.set("keks.world", location.getWorld().getName());
        fileConfiguration.set("keks.x", location.getX());
        fileConfiguration.set("keks.y", location.getY());
        fileConfiguration.set("keks.z", location.getZ());
        fileConfiguration.set("keks.yaw", location.getYaw());
        fileConfiguration.set("keks.pitch", location.getPitch());
        try {
            fileConfiguration.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Location getCookieLocation() {
        return cookieLocation;
    }
}
