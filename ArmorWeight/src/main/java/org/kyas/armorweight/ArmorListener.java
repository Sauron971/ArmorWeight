package org.kyas.armorweight;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class ArmorListener implements Listener {
    private float DIAMOND_HELMET = 0.004f; //set attribute speed 0.043
    private float DIAMOND_CHESTPLATE = 0.03f;
    private float DIAMOND_LEGGINGS = 0.012f;
    private float DIAMOND_BOOTS = 0.005f;

    private float IRON_HELMET = 0.008f;  //set attribute speed 0.062
    private float IRON_CHESTPLATE = 0.015f;
    private float IRON_LEGGINGS = 0.01f;
    private float IRON_BOOTS = 0.005f;

    private float GOLD_HELMET = 0.004f;  //set attribute speed 0.05
    private float GOLD_CHESTPLATE = 0.025f;
    private float GOLD_LEGGINGS = 0.015f;
    private float GOLD_BOOTS = 0.006f;

    private float CHAINMAIL_HELMET = 0.001f; //set attribute speed 0.08
    private float CHAINMAIL_CHESTPLATE = 0.015f;
    private float CHAINMAIL_LEGGINGS = 0.003f;
    private float CHAINMAIL_BOOTS = 0.001f;
    private final FileConfiguration config;
    private final ArmorWeight plugin;

    public ArmorListener(ArmorWeight plugin) {
        this.config = plugin.getConfig();
        this.plugin = plugin;

        this.DIAMOND_HELMET = (float) config.getDouble("armor_weight.diamond_helmet");
        this.DIAMOND_CHESTPLATE = (float) config.getDouble("armor_weight.diamond_chestplate");
        this.DIAMOND_LEGGINGS = (float) config.getDouble("armor_weight.diamond_leggings");
        this.DIAMOND_BOOTS = (float) config.getDouble("armor_weight.diamond_boots");

        this.IRON_HELMET = (float) config.getDouble("armor_weight.iron_helmet");
        this.IRON_CHESTPLATE = (float) config.getDouble("armor_weight.iron_chestplate");
        this.IRON_LEGGINGS = (float) config.getDouble("armor_weight.iron_leggings");
        this.IRON_BOOTS = (float) config.getDouble("armor_weight.iron_boots");

        this.GOLD_HELMET = (float) config.getDouble("armor_weight.golden_helmet");
        this.GOLD_CHESTPLATE = (float) config.getDouble("armor_weight.golden_chestplate");
        this.GOLD_LEGGINGS = (float) config.getDouble("armor_weight.golden_leggings");
        this.GOLD_BOOTS = (float) config.getDouble("armor_weight.golden_boots");

        this.CHAINMAIL_HELMET = (float) config.getDouble("armor_weight.chainmail_helmet");
        this.CHAINMAIL_CHESTPLATE = (float) config.getDouble("armor_weight.chainmail_chestplate");
        this.CHAINMAIL_LEGGINGS = (float) config.getDouble("armor_weight.chainmail_leggings");
        this.CHAINMAIL_BOOTS = (float) config.getDouble("armor_weight.chainmail_boots");
    }

    @EventHandler
    public void onPlayerCloseInventory(InventoryCloseEvent e) {
        AttributeInstance movementSpeedAttribute = e.getPlayer().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        assert movementSpeedAttribute != null;
        if (config.getBoolean("enableWeight")) {
            ItemStack helmet = e.getPlayer().getInventory().getHelmet();
            ItemStack chestplate = e.getPlayer().getInventory().getChestplate();
            ItemStack leggings = e.getPlayer().getInventory().getLeggings();
            ItemStack boots = e.getPlayer().getInventory().getBoots();
            Player p = (Player) e.getPlayer();
            float attribute_speed = 0.1f;
            movementSpeedAttribute.setBaseValue(attribute_speed);

            if (helmet != null) {
                switch (helmet.getType()) {
                    case DIAMOND_HELMET:
                        attribute_speed -= DIAMOND_HELMET;
                        break;
                    case IRON_HELMET:
                        attribute_speed -= IRON_HELMET;
                        break;
                    case GOLDEN_HELMET:
                        attribute_speed -= GOLD_HELMET;
                        break;
                    case CHAINMAIL_HELMET:
                        attribute_speed -= CHAINMAIL_HELMET;
                        break;
                }
            }

            if (chestplate != null) {
                switch (chestplate.getType()) {
                    case DIAMOND_CHESTPLATE:
                        attribute_speed -= DIAMOND_CHESTPLATE;
                        break;
                    case IRON_CHESTPLATE:
                        attribute_speed -= IRON_CHESTPLATE;
                        break;
                    case GOLDEN_CHESTPLATE:
                        attribute_speed -= GOLD_CHESTPLATE;
                        break;
                    case CHAINMAIL_CHESTPLATE:
                        attribute_speed -= CHAINMAIL_CHESTPLATE;
                        break;
                }
            }

            if (leggings != null) {
                switch (leggings.getType()) {
                    case DIAMOND_LEGGINGS:
                        attribute_speed -= DIAMOND_LEGGINGS;
                        break;
                    case IRON_LEGGINGS:
                        attribute_speed -= IRON_LEGGINGS;
                        break;
                    case GOLDEN_LEGGINGS:
                        attribute_speed -= GOLD_LEGGINGS;
                        break;
                    case CHAINMAIL_LEGGINGS:
                        attribute_speed -= CHAINMAIL_LEGGINGS;
                        break;
                }
            }

            if (boots != null) {
                switch (boots.getType()) {
                    case DIAMOND_BOOTS:
                        attribute_speed -= DIAMOND_BOOTS;
                        break;
                    case IRON_BOOTS:
                        attribute_speed -= IRON_BOOTS;
                        break;
                    case GOLDEN_BOOTS:
                        attribute_speed -= GOLD_BOOTS;
                        break;
                    case CHAINMAIL_BOOTS:
                        attribute_speed -= CHAINMAIL_BOOTS;
                        break;
                }
            }
            movementSpeedAttribute.setBaseValue(attribute_speed);
        } else {
            movementSpeedAttribute.setBaseValue(0.1f);
        }
    }

    private boolean isSetSwimming(Player p) {
        if (p.isInWater()) {
            ItemStack helmet = p.getInventory().getHelmet();
            ItemStack chestplate = p.getInventory().getChestplate();
            ItemStack leggings = p.getInventory().getLeggings();
            ItemStack boots = p.getInventory().getBoots();

            boolean helmetCheck = helmet != null && (!helmet.getType().equals(Material.LEATHER_HELMET) && !helmet.getType().equals(Material.CHAINMAIL_HELMET));
            boolean chestplateCheck = chestplate != null && (!chestplate.getType().equals(Material.LEATHER_CHESTPLATE) && !chestplate.getType().equals(Material.CHAINMAIL_CHESTPLATE));
            boolean leggingsCheck = leggings != null && (!leggings.getType().equals(Material.LEATHER_LEGGINGS) && !leggings.getType().equals(Material.CHAINMAIL_LEGGINGS));
            boolean bootsCheck = boots != null && (!boots.getType().equals(Material.LEATHER_BOOTS) && !boots.getType().equals(Material.CHAINMAIL_BOOTS));

            return helmetCheck && chestplateCheck && leggingsCheck && bootsCheck;
        } else {
            return false;
        }
    }

    private void spawnBubblesOnPlayerShoulders(Player player) {
        Location leftShoulder = player.getLocation().add(-0.5, 1.6, 0); // Adjust coordinates as needed
        Location rightShoulder = player.getLocation().add(0.5, 1.6, 0); // Adjust coordinates as needed

        for (int i = 0; i < 5; i++) {
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                player.spawnParticle(Particle.BUBBLE_COLUMN_UP, leftShoulder, 0);
                player.spawnParticle(Particle.BUBBLE_COLUMN_UP, rightShoulder, 0);
            }, 20);
        }
    }
    private boolean playSound = true;

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        AttributeInstance gravityAttribute = event.getPlayer().getAttribute(Attribute.GENERIC_GRAVITY);
        AttributeInstance stepHeightAttribute = event.getPlayer().getAttribute(Attribute.GENERIC_STEP_HEIGHT);
        assert gravityAttribute != null;
        assert stepHeightAttribute != null;
        if (config.getBoolean("enableDrowning")) {
            if (isSetSwimming(event.getPlayer())) {
                Player p = event.getPlayer();

                if (p.isSwimming()) {
                    event.setTo(event.getFrom().setDirection(new Vector(0, 1, 0)));
                }

                if (playSound) {
                    Location loc = event.getPlayer().getLocation();
                    event.getPlayer().playSound(loc, Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, 1000, 5);
                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> spawnBubblesOnPlayerShoulders(event.getPlayer()), 40);
                    spawnBubblesOnPlayerShoulders(event.getPlayer());
                    playSound = false;
                }
                gravityAttribute.setBaseValue(0.75);
                stepHeightAttribute.setBaseValue(1.0);

            } else {
                if (gravityAttribute.getValue() != 0.08 && stepHeightAttribute.getValue() != 0.6) {
                    gravityAttribute.setBaseValue(0.08);
                    stepHeightAttribute.setBaseValue(0.6);
                    playSound = true;
                }
            }
        } else {
            if (gravityAttribute.getValue() != 0.08 && stepHeightAttribute.getValue() != 0.6) {
                gravityAttribute.setBaseValue(0.08);
                stepHeightAttribute.setBaseValue(0.6);
                playSound = true;
            }
        }
    }
}
