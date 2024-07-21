package org.kyas.armorweight;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArmorWeight extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(new ArmorListener(this), this);
        this.getServer().getPluginManager().registerEvents(new NotSpawners(this), this);
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
