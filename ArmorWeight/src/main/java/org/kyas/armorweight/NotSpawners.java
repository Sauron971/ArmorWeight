package org.kyas.armorweight;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class NotSpawners implements Listener {

    private final ArmorWeight plugin;



    public NotSpawners(ArmorWeight plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void entitySpawn(CreatureSpawnEvent e) {
        boolean toggle = plugin.getConfig().getBoolean("disableSpawner");
        if (toggle) {
            if (e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER)) {
                e.setCancelled(true);
            }
        }
    }
}
