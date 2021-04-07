package com.magmaguy.elitemobs.powers.bosspowers;

import com.magmaguy.elitemobs.MetadataHandler;
import com.magmaguy.elitemobs.entitytracker.EntityTracker;
import com.magmaguy.elitemobs.events.BossCustomAttackDamage;
import com.magmaguy.elitemobs.mobconstructor.EliteMobEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.List;
import org.bukkit.entity.Player;

public class ProjectileDamage {

    public static void doGoldNuggetDamage(List<Item> goldNuggets, EliteMobEntity eliteMobEntity) {

        new BukkitRunnable() {

            int timer = 0;

            @Override
            public void run() {

                timer++;
                if (goldNuggets.isEmpty()) {
                    cancel();
                    return;
                }

                for (Iterator<Item> iterator = goldNuggets.iterator(); iterator.hasNext(); ) {
                    Item goldNugget = iterator.next();
                    if (goldNugget == null) {
                        iterator.remove();
                        continue;
                    }
                    boolean removed = false;
                    for (Entity entity : goldNugget.getNearbyEntities(0.1, 0.1, 0.1))
                        if (entity != eliteMobEntity.getLivingEntity())
                            if (entity instanceof LivingEntity) {
                                BossCustomAttackDamage.dealCustomDamage(eliteMobEntity.getLivingEntity(), (LivingEntity) entity, 1);
                                iterator.remove();
                                goldNugget.remove();
                                removed = true;
                            }
                    if (!removed && goldNugget.isOnGround()) {
                        iterator.remove();
                        goldNugget.remove();
                    }
                }

                if (timer < 5 * 20) return;

                for (Iterator<Item> endIterator = goldNuggets.iterator(); endIterator.hasNext(); ) {
                    Item goldNugget = endIterator.next();
                    goldNugget.remove();
                    endIterator.remove();
                }

                cancel();

            }

        }.runTaskTimer(MetadataHandler.PLUGIN, 0, 1);

    }
 public static void doPrimordialDamage(List<Item> goldNuggets, Player player) {

        new BukkitRunnable() {

            int timer = 0;

            @Override
            public void run() {

                timer++;
                if (goldNuggets.isEmpty()) {
                    cancel();
                    return;
                }

                for (Iterator<Item> iterator = goldNuggets.iterator(); iterator.hasNext(); ) {
                    Item goldNugget = iterator.next();
                    if (goldNugget == null) {
                        iterator.remove();
                        continue;
                    }
                    boolean removed = false;
                    for (Entity entity : goldNugget.getNearbyEntities(0.1, 0.1, 0.1))
                        if (entity instanceof LivingEntity) {
                                BossCustomAttackDamage.dealCustomDamage(player, (LivingEntity) entity, 8);
                                iterator.remove();
                                goldNugget.remove();
                                removed = true;
                            }
                    if (!removed && goldNugget.isOnGround()) {
                        iterator.remove();
                        goldNugget.remove();
                    }
                }

                if (timer < 5 * 20) return;

                for (Iterator<Item> endIterator = goldNuggets.iterator(); endIterator.hasNext(); ) {
                    Item goldNugget = endIterator.next();
                    goldNugget.remove();
                    endIterator.remove();
                }

                cancel();

            }

        }.runTaskTimer(MetadataHandler.PLUGIN, 0, 1);

    }


    public static void configureVisualProjectile(Item goldNugget) {

        EntityTracker.registerItemVisualEffects(goldNugget);
        goldNugget.setPickupDelay(Integer.MAX_VALUE);

    }

}
