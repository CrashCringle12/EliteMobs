package com.magmaguy.elitemobs.items.customenchantments;

import com.magmaguy.elitemobs.entitytracker.EntityTracker;
import com.magmaguy.elitemobs.MetadataHandler;
import com.magmaguy.elitemobs.api.internal.RemovalReason;
import com.magmaguy.elitemobs.combatsystem.EliteProjectile;
import static com.magmaguy.elitemobs.items.customenchantments.CustomEnchantment.hasCustomEnchantment;
import com.magmaguy.elitemobs.powers.offensivepowers.AttackTrident;
import com.magmaguy.elitemobs.utils.CooldownHandler;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import org.bukkit.SoundCategory;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Trident;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JusticeStormEnchantment extends CustomEnchantment {

    public static String key = "justice_storm";
    private static final ArrayList<Player> playersUsingJusticeStorm = new ArrayList<>();

    public JusticeStormEnchantment() {
        super(key, false);
    }

    private static void trackingTridentLoop(LivingEntity player, Trident trident, Player p1) {
        new BukkitRunnable() {
            int counter = 0;

            @Override
            public void run() {
                if (player.isValid() && !player.isDead() && player != (LivingEntity) p1 && trident.isValid() && trident.getWorld().equals(player.getWorld())
                        && player.getLocation().distanceSquared(trident.getLocation()) < 900 && !trident.isOnGround()) {
                    if (counter % 10 == 0)
                        trident.setVelocity(trident.getVelocity().add(tridentAdjustmentVector(trident, player)));
                    trident.getWorld().spawnParticle(Particle.NAUTILUS, trident.getLocation(), 10, 0.01, 0.01, 0.01, 0.01);
                } else {
                    trident.setGravity(true);
                    EntityTracker.unregister(trident, RemovalReason.EFFECT_TIMEOUT);
                    cancel();
                }
                if (counter > 20 * 10) {
                    EntityTracker.unregister(trident, RemovalReason.EFFECT_TIMEOUT);
                    trident.setGravity(true);
                    cancel();
                }
                counter++;
            }
        }.runTaskTimer(MetadataHandler.PLUGIN, 0, 1);
    }

    private static Vector tridentAdjustmentVector(Trident trident, LivingEntity player) {
        return player.getEyeLocation().subtract(trident.getLocation()).toVector().normalize().multiply(0.1);
    }

    public static class StormJusticeEvents implements Listener {
        @EventHandler
        public void onInteract(PlayerInteractEvent event) {
            Player player = event.getPlayer();
            if (!(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)))
                return;
            if (playersUsingJusticeStorm.contains(event.getPlayer())) return;
             ItemStack justiceStorm = player.getInventory().getItemInMainHand();
            if (hasCustomEnchantment(justiceStorm, "justice_storm")) {
                if (justiceStorm.getDurability() + 4 > justiceStorm.getType().getMaxDurability()) {
                    return;
                } else justiceStorm.setDurability((short) (justiceStorm.getDurability() + 15));
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 5, 40));
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2, 15));
                JusticeStormEnchantment.doJusticeStorm(event.getPlayer());
                CooldownHandler.initialize(playersUsingJusticeStorm, event.getPlayer(), 2000);
            }

        }
    }
    public static void doJusticeStorm(Player player) {        
        player.getWorld().playSound(player.getLocation(), Sound.ITEM_TRIDENT_THUNDER, SoundCategory.NEUTRAL, 100, 0);
        new BukkitRunnable() {
            int counter = 0;
            @Override
            public void run() {

                if (!player.isValid() || player.isDead()) {
                    cancel();
                    return;
                }

                player.getWorld().spawnParticle(Particle.DRIP_WATER, player.getLocation(), 10, 1, 1, 1);
                for (int i = 0; i < 10; i++) {
                    player.getLocation().getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, new Location(
                            player.getLocation().getWorld(),
                            (ThreadLocalRandom.current().nextDouble() - 0.5) * 3 + player.getLocation().getX(),
                            player.getLocation().getY(),
                            (ThreadLocalRandom.current().nextDouble() - 0.5) * 3 + player.getLocation().getZ()
                    ), 0, 0, 1, 0, ThreadLocalRandom.current().nextDouble() * 2);
                }
                for (Entity nearbyEntity : player.getNearbyEntities(20, 5, 20))
                    if (nearbyEntity instanceof Player) {
                        if (((Player) nearbyEntity).getGameMode().equals(GameMode.ADVENTURE) ||
                                ((Player) nearbyEntity).getGameMode().equals(GameMode.SURVIVAL)) {
                            player.getWorld().playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 100, 2);
                            Trident trident = AttackTrident.shootTrident(player, (Player) nearbyEntity);                            
                            trident.setVelocity(trident.getVelocity().multiply(2));
                            trident.setGravity(false);
                            trackingTridentLoop((LivingEntity) nearbyEntity, trident, player);
                        } 
                    }else if (nearbyEntity instanceof Monster) {
                            player.getWorld().playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 100, 2);
                            Trident trident = AttackTrident.shootTrident(player, (LivingEntity) nearbyEntity);
                            trident.setVelocity(trident.getVelocity().multiply(2));
                            trident.setGravity(false);
                            trackingTridentLoop((LivingEntity) nearbyEntity, trident, player);
                    }

                counter++;
                if (counter > 25) {
                    cancel();      
                player.getWorld().playSound(player.getLocation(), Sound.ITEM_TRIDENT_THUNDER, SoundCategory.NEUTRAL, 100, 0);
                }

            }
        }.runTaskTimer(MetadataHandler.PLUGIN, 0, 10);
    }
}