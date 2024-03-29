package de.skullmc.cookie.utils;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Particles {

    private final EnumParticle particletype;
    private final boolean longdistance;
    private final Location location;
    private final float offsetx, offsety, offsetz, speed;
    private final int amount;

    public Particles(EnumParticle particletype, Location location, boolean longdistance, float offsetx, float offsety, float offsetz, float speed, int amount) {
        this.particletype = particletype;
        this.location = location;
        this.longdistance = longdistance;
        this.offsetx = offsetx;
        this.offsety = offsety;
        this.offsetz = offsetz;
        this.speed = speed;
        this.amount = amount;
    }

    public void sendAll() {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(this.particletype, this.longdistance,
                (float) this.location.getX(), (float) this.location.getY(), (float) this.location.getZ(), this.offsetx,
                this.offsety, this.offsetz, this.speed, this.amount, 0);
        for (Player player : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

        }
    }
}
