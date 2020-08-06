package com.magmaguy.elitemobs.powers.offensivepowers;

import com.magmaguy.elitemobs.MetadataHandler;
import com.magmaguy.elitemobs.api.EliteMobTargetPlayerEvent;
import com.magmaguy.elitemobs.config.powers.PowersConfig;
import com.magmaguy.elitemobs.mobconstructor.EliteMobEntity;
import com.magmaguy.elitemobs.powers.MinorPower;
import com.magmaguy.elitemobs.powers.ProjectileLocationGenerator;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 * Created by MagmaGuy on 06/05/2017.
 */
public class AttackTrident extends MinorPower implements Listener {

    public AttackTrident() {
        super(PowersConfig.getPower("attack_trident.yml"));
    }

    @EventHandler
    public void targetEvent(EliteMobTargetPlayerEvent event) {
        if (!(event.getEliteMobEntity().getLivingEntity() instanceof Monster)) return;
        AttackTrident attackTrident = (AttackTrident) event.getEliteMobEntity().getPower(this);
        if (attackTrident == null) return;
        if (attackTrident.getIsFiring()) return;

        attackTrident.setIsFiring(true);
        repeatingTridentTask(attackTrident, event.getEliteMobEntity());
    }

    private void repeatingTridentTask(AttackTrident attackTrident, EliteMobEntity eliteMobEntity) {

        new BukkitRunnable() {

            @Override
            public void run() {

                if (!eliteMobEntity.getLivingEntity().isValid() || ((Monster) eliteMobEntity.getLivingEntity()).getTarget() == null) {
                    attackTrident.setIsFiring(false);
                    cancel();
                    return;
                }

                for (Entity nearbyEntity : eliteMobEntity.getLivingEntity().getNearbyEntities(20, 20, 20))
                    if (nearbyEntity instanceof Player)
                        if (((Player) nearbyEntity).getGameMode().equals(GameMode.ADVENTURE) ||
                                ((Player) nearbyEntity).getGameMode().equals(GameMode.SURVIVAL))
                            shootTrident(eliteMobEntity.getLivingEntity(), (Player) nearbyEntity);

            }

        }.runTaskTimer(MetadataHandler.PLUGIN, 0, 20 * 8);

    }

    public static Trident shootTrident(Entity entity, Player player) {

        Location offsetLocation = ProjectileLocationGenerator.generateLocation((LivingEntity) entity, player);
        Trident repeatingTrident = (Trident) entity.getWorld().spawnEntity(offsetLocation, EntityType.TRIDENT);
        Vector targetterToTargetted = player.getEyeLocation().subtract(repeatingTrident.getLocation()).toVector()
                .normalize().multiply(2);

        repeatingTrident.setVelocity(targetterToTargetted);
        repeatingTrident.setShooter((ProjectileSource) entity);

        return repeatingTrident;

    }
    public static Trident shootTrident(Entity entity, LivingEntity player) {

        Location offsetLocation = ProjectileLocationGenerator.generateLocation((LivingEntity) entity, player);
        Trident repeatingTrident = (Trident) entity.getWorld().spawnEntity(offsetLocation, EntityType.TRIDENT);
        Vector targetterToTargetted = player.getEyeLocation().subtract(repeatingTrident.getLocation()).toVector()
                .normalize().multiply(2);

        repeatingTrident.setVelocity(targetterToTargetted);
        repeatingTrident.setShooter((ProjectileSource) entity);

        return repeatingTrident;

    }
}
