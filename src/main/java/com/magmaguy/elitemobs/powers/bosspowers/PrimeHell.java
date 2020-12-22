package com.magmaguy.elitemobs.powers.bosspowers;

import com.magmaguy.elitemobs.entitytracker.EntityTracker;
import com.magmaguy.elitemobs.MetadataHandler;
import com.magmaguy.elitemobs.api.EliteMobDamagedByPlayerEvent;
import com.magmaguy.elitemobs.config.powers.PowersConfig;
import com.magmaguy.elitemobs.mobconstructor.EliteMobEntity;
import com.magmaguy.elitemobs.powers.BossPower;
import com.magmaguy.elitemobs.powers.offensivepowers.AttackTrident;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Trident;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.entity.Trident;

public class PrimeHell extends BossPower implements Listener {
    public PrimeHell() {
        super(PowersConfig.getPower("prime_hell.yml"));
    }

    @EventHandler
    public void onDamage(EliteMobDamagedByPlayerEvent event) {
        PrimeHell primeHell = (PrimeHell) event.getEliteMobEntity().getPower(this);
        if (primeHell == null) return;
        if (!eventIsValid(event, primeHell)) return;
        if (ThreadLocalRandom.current().nextDouble() > 0.25) return;

        primeHell.doCooldown(20 * 20, event.getEliteMobEntity());
        doPrimeHell(event.getEliteMobEntity());

    }

    public void doPrimeHell(EliteMobEntity eliteMobEntity) {
        if (eliteMobEntity.getLivingEntity().getLocation().clone().add(new Vector(0, 10, 0)).getBlock().getType().equals(Material.AIR))
            eliteMobEntity.getLivingEntity().teleport(eliteMobEntity.getLivingEntity().getLocation().clone().add(new Vector(0, 10, 0)));
        new BukkitRunnable() {
            int counter = 0;
            final Location initialLocation = eliteMobEntity.getLivingEntity().getLocation().clone();

            @Override
            public void run() {

                if (!eliteMobEntity.getLivingEntity().isValid() || eliteMobEntity.getLivingEntity().isDead()) {
                    cancel();
                    return;
                }

                eliteMobEntity.getLivingEntity().getWorld().spawnParticle(Particle.DRIP_WATER, eliteMobEntity.getLivingEntity().getLocation(), 10, 1, 1, 1);

                for (Entity nearbyEntity : eliteMobEntity.getLivingEntity().getNearbyEntities(25, 20, 20))
                    if (nearbyEntity instanceof Player)
                        if (((Player) nearbyEntity).getGameMode().equals(GameMode.ADVENTURE) ||
                                ((Player) nearbyEntity).getGameMode().equals(GameMode.SURVIVAL)) {
                            Trident trident = AttackTrident.shootTrident(eliteMobEntity.getLivingEntity(), (Player) nearbyEntity);
                            trident.setVelocity(trident.getVelocity().multiply(0.4));
                            trident.setGravity(false);
                            trackingTridentLoop((Player) nearbyEntity, trident);
                        }

                counter++;
                if (counter > 35) {
                    cancel();
                    eliteMobEntity.getLivingEntity().teleport(initialLocation);
                }

            }
        }.runTaskTimer(MetadataHandler.PLUGIN, 0, 10);
    }


    private static void trackingTridentLoop(Player player, Trident trident) {
        EntityTracker.registerCullableEntity(trident);
        new BukkitRunnable() {
            int counter = 0;

            @Override
            public void run() {
                if (player.isValid() && !player.isDead() && trident.isValid() && trident.getWorld().equals(player.getWorld())
                        && player.getLocation().distanceSquared(trident.getLocation()) < 900 && !trident.isOnGround()) {
                    if (counter % 10 == 0)
                        trident.setVelocity(trident.getVelocity().add(tridentAdjustmentVector(trident, player)));
                    trident.getWorld().spawnParticle(Particle.NAUTILUS, trident.getLocation(), 10, 0.01, 0.01, 0.01, 0.01);
                } else {
                    trident.setGravity(true);
                    EntityTracker.unregisterCullableEntity(trident);
                    cancel();
                }
                if (counter > 20 * 10) {
                    EntityTracker.unregisterCullableEntity(trident);
                    trident.setGravity(true);
                    cancel();
                }
                counter++;
            }
        }.runTaskTimer(MetadataHandler.PLUGIN, 0, 1);
    }

    private static Vector tridentAdjustmentVector(Trident trident, Player player) {
        return player.getEyeLocation().subtract(trident.getLocation()).toVector().normalize().multiply(0.1);
    }

}
