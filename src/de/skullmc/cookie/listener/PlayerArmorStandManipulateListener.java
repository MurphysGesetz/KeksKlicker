package de.skullmc.cookie.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class PlayerArmorStandManipulateListener implements Listener {

    @EventHandler
    public void handlePlayerArmorStandManipulate(PlayerArmorStandManipulateEvent event) {
        if(event.getRightClicked().getCustomName().equals("cookie_skull")) {
            event.setCancelled(true);
        }
    }
}
