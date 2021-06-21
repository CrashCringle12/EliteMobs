package com.magmaguy.elitemobs.powers.bosspowers;

import com.magmaguy.elitemobs.MetadataHandler;
import com.magmaguy.elitemobs.api.EliteMobDamagedByPlayerEvent;
import com.magmaguy.elitemobs.config.powers.PowersConfig;
import com.magmaguy.elitemobs.events.BossCustomAttackDamage;
import com.magmaguy.elitemobs.mobconstructor.EliteMobEntity;
import com.magmaguy.elitemobs.powers.BossPower;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ThreadLocalRandom;

public class WaterPyre extends BossPower implements Listener {

    public WaterPyre() {
        super(PowersConfig.getPower("water_pyre.yml"));
    }

    @EventHandler
    public void onHit(EliteMobDamagedByPlayerEvent event) {

        WaterPyre waterPyre = (WaterPyre) event.getEliteMobEntity().getPower(this);
        if (waterPyre == null) return;
        if (!eventIsValid(event, waterPyre)) return;
        if (ThreadLocalRandom.current().nextDouble() > 0.25) return;

        waterPyre.doCooldown(event.getEliteMobEntity());
        doWaterPyrePhase1(event.getEliteMobEntity());

    }

    /**
     * Warning phase
     *
     * @param eliteMobEntity
     */
    private void doWaterPyrePhase1(EliteMobEntity eliteMobEntity) {
        eliteMobEntity.getLivingEntity().setAI(false);
        new BukkitRunnable() {
            int counter = 0;

            @Override
            public void run() {
                counter++;
                spawnPhase1Particle(eliteMobEntity.getLivingEntity().getLocation().clone(), Particle.NAUTILUS);
                if (counter < 20 * 2) return;
                cancel();
                doWaterPyrePhase2(eliteMobEntity);
            }
        }.runTaskTimer(MetadataHandler.PLUGIN, 0, 1);
    }

    private void spawnPhase1Particle(Location location, Particle particle) {
        for (int i = 0; i < 10; i++) {
            location.getWorld().spawnParticle(particle, new Location(
                    location.getWorld(),
                    (ThreadLocalRandom.current().nextDouble() - 0.5) * 0.5 + location.getX(),
                    location.getY(),
                    (ThreadLocalRandom.current().nextDouble() - 0.5) * 0.5 + location.getZ()
            ), 0, 0, 1, 0, ThreadLocalRandom.current().nextDouble() * 2);
        }
    }

    /**
     * First damage phase
     *
     * @param eliteMobEntity
     */
    private void doWaterPyrePhase2(EliteMobEntity eliteMobEntity) {
        new BukkitRunnable() {
            int counter = 0;

            @Override
            public void run() {
                counter++;
                spawnPhase1Particle(eliteMobEntity.getLivingEntity().getLocation().clone(), Particle.WATER_SPLASH);
                doDamage(eliteMobEntity, 0.5, 50, 0.5);
                spawnPhase2Particle(eliteMobEntity.getLivingEntity().getLocation().clone(), Particle.SOUL_FIRE_FLAME);
                spawnPhase2Particle(eliteMobEntity.getLivingEntity().getLocation().clone(), Particle.DOLPHIN);
                if (counter < 20 * 2) return;
                cancel();
                doWaterPyrePhase3(eliteMobEntity);
            }
        }.runTaskTimer(MetadataHandler.PLUGIN, 0, 1);
    }

    private void spawnPhase2Particle(Location location, Particle particle) {
        for (int i = 0; i < 10; i++) {
            location.getWorld().spawnParticle(particle, new Location(
                    location.getWorld(),
                    (ThreadLocalRandom.current().nextDouble() - 0.5) * 3 + location.getX(),
                    location.getY(),
                    (ThreadLocalRandom.current().nextDouble() - 0.5) * 3 + location.getZ()
            ), 0, 0, 1, 0, ThreadLocalRandom.current().nextDouble() * 2);
        }
    }

    private void doDamage(EliteMobEntity eliteMobEntity, double range1, double range2, double range3) {
        for (Entity entity : eliteMobEntity.getLivingEntity().getNearbyEntities(range1, range2, range3))
            if (entity instanceof LivingEntity && !entity.equals(eliteMobEntity.getLivingEntity()))
                BossCustomAttackDamage.dealCustomDamage(eliteMobEntity.getLivingEntity(), (LivingEntity) entity, 1);
    }

    /**
     * Second damage phase / last warning phase
     *
     * @param eliteMobEntity
     */
    private void doWaterPyrePhase3(EliteMobEntity eliteMobEntity) {
        new BukkitRunnable() {
            int counter = 0;

            @Override
            public void run() {
                counter++;
                spawnPhase2Particle(eliteMobEntity.getLivingEntity().getLocation().clone(), Particle.WATER_DROP);
                spawnPhase2Particle(eliteMobEntity.getLivingEntity().getLocation().clone(), Particle.BUBBLE_COLUMN_UP);
                doDamage(eliteMobEntity, 3, 50, 3);
                spawnPhase3Particle(eliteMobEntity.getLivingEntity().getLocation().clone(), Particle.NAUTILUS);
                if (counter < 20 * 2) return;
                cancel();
                doWaterPyrePhase4(eliteMobEntity);
            }
        }.runTaskTimer(MetadataHandler.PLUGIN, 0, 1);
    }

    private void spawnPhase3Particle(Location location, Particle particle) {
        location.getWorld().spawnParticle(particle, location, 50, 0.01, 0.01, 0.01, 0.1);
    }

    /**
     * Final/full damage phase
     *
     * @param eliteMobEntity
     */
    private void doWaterPyrePhase4(EliteMobEntity eliteMobEntity) {
        new BukkitRunnable() {
            int counter = 0;

            @Override
            public void run() {
                counter++;
                spawnPhase3Particle(eliteMobEntity.getLivingEntity().getLocation().clone(), Particle.END_ROD);
                spawnPhase3Particle(eliteMobEntity.getLivingEntity().getLocation().clone(), Particle.WATER_BUBBLE);
                doDamage(eliteMobEntity, 5, 50, 5);
                if (counter < 20 * 2) return;
                cancel();
                eliteMobEntity.getLivingEntity().setAI(true);
            }
        }.runTaskTimer(MetadataHandler.PLUGIN, 0, 1);
    }

}
