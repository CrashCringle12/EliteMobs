/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.magmaguy.elitemobs.mobs.passive;

import com.magmaguy.elitemobs.MetadataHandler;
import com.magmaguy.elitemobs.config.ConfigValues;
import com.magmaguy.elitemobs.elitedrops.ItemDropVelocity;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.bukkit.Material.FEATHER;
import static org.bukkit.Material.RAW_CHICKEN;

/**
 * Created by MagmaGuy on 19/12/2016.
 */
public class ChickenHandler implements Listener {

    private static Random random = new Random();

//    public static void superEggs(Entity entity, int passiveStackAmount) {
//
//        List<Chicken> tempChickenList = new ArrayList<>();
//
//        if (entity instanceof Chicken && entity.hasMetadata(MetadataHandler.PASSIVE_ELITE_MOB_MD)) {
//
//            tempChickenList.add((Chicken) entity);
//
//        }
//
//        if (tempChickenList.size() > 0) {
//
//            int eggChance = random.nextInt(12000 / 20 * 5 / passiveStackAmount);
//
//            //Chicken lay eggs every 5-10 minutes, assuming 10 min that's 12000 ticks
//            //method runs every 20*5 ticks
//            //should spawn 1 by 1 but the odds of it spawning are scaled to fit config passivemob stack size
//            if (eggChance == 1) {
//
//                for (Chicken chicken : tempChickenList) {
//
//                    ItemStack eggStack = new ItemStack(EGG, 1);
//
//                    chicken.getWorld().dropItem(chicken.getLocation(), eggStack).setVelocity(ItemDropVelocity.ItemDropVelocity());
//
//                }
//
//            }
//
//        }
//
//    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void superDrops(EntityDamageByEntityEvent event) {

        if (event.isCancelled()) {

            return;

        }

        if (event.getFinalDamage() < 1) {

            return;

        }

        if (event.getEntity() instanceof Chicken && event.getEntity().hasMetadata(MetadataHandler.PASSIVE_ELITE_MOB_MD)) {

            Random random = new Random();

            Chicken chicken = (Chicken) event.getEntity();

            double damage = event.getFinalDamage();
            //health is hardcoded here, maybe change it at some point?
            double dropChance = damage / 4;
            double dropRandomizer = random.nextDouble();
            //this rounds down
            int dropMinAmount = (int) dropChance;

            ItemStack featherStack = new ItemStack(FEATHER, (random.nextInt(2) + 1));
            ItemStack poultryStack = new ItemStack(RAW_CHICKEN, 1);

            for (int i = 0; i < dropMinAmount; i++) {

                chicken.getWorld().dropItem(chicken.getLocation(), featherStack).setVelocity(ItemDropVelocity.ItemDropVelocity());
                chicken.getWorld().dropItem(chicken.getLocation(), poultryStack).setVelocity(ItemDropVelocity.ItemDropVelocity());

                ExperienceOrb xpDrop = chicken.getWorld().spawn(chicken.getLocation(), ExperienceOrb.class);
                xpDrop.setExperience(random.nextInt(3) + 1);

            }

            if (dropChance > dropRandomizer) {

                chicken.getWorld().dropItem(chicken.getLocation(), featherStack).setVelocity(ItemDropVelocity.ItemDropVelocity());
                chicken.getWorld().dropItem(chicken.getLocation(), poultryStack).setVelocity(ItemDropVelocity.ItemDropVelocity());

                ExperienceOrb xpDrop = chicken.getWorld().spawn(chicken.getLocation(), ExperienceOrb.class);
                xpDrop.setExperience(random.nextInt(3) + 1);

            }

        }

    }

    /*
    Augmented egg drops
    There's no egg dropping event and listening for new eggs can be extremely inaccurate due to high chicken density
    Use events to add and remove loaded chicken and use the scanner to update the list of active chicken
    */

    public static List<Chicken> activeChickenList = new ArrayList<>();

    @EventHandler
    public void superChickenAppearEvent(EntitySpawnEvent event) {

        if (event.getEntityType().equals(EntityType.CHICKEN)) {

            Chicken chicken = (Chicken) event.getEntity();

            if (chicken.hasMetadata(MetadataHandler.PASSIVE_ELITE_MOB_MD)) {

                if (!activeChickenList.contains(chicken)) {

                    activeChickenList.add(chicken);

                }

            }

        }

    }

    @EventHandler
    public void superChickenDeathEvent(EntityDeathEvent event) {

        if (event.getEntityType().equals(EntityType.CHICKEN)) {

            Chicken chicken = (Chicken) event.getEntity();

            if (activeChickenList.contains(chicken)) {

                activeChickenList.remove(chicken);

            }

        }

    }

    //Egg drop chance is based on the underlying timer
    public static void dropEggs() {

        if (activeChickenList.isEmpty()) return;

        ItemStack eggStack = new ItemStack(Material.EGG, 1);

        for (Chicken chicken : activeChickenList) {

            for (int i = 0; i < ConfigValues.defaultConfig.getInt("Passive EliteMob stack amount"); i++) {

                Item droppedItem = chicken.getWorld().dropItem(chicken.getLocation(), eggStack);
                droppedItem.setVelocity(ItemDropVelocity.ItemDropVelocity());

            }

        }

    }

}
