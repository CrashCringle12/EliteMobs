/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magmaguy.elitemobs.mobconstructor.mobdata.aggressivemobs;

import com.magmaguy.elitemobs.config.mobproperties.MobPropertiesConfig;
import static com.magmaguy.elitemobs.mobconstructor.mobdata.aggressivemobs.EliteMobProperties.eliteMobData;
import org.bukkit.entity.EntityType;

/**
 *
 * @author Lamar Cooley
 */
public class ElitePiglin extends EliteMobProperties {

    public ElitePiglin() {
        this.name = MobPropertiesConfig.getMobProperties().get(EntityType.PIGLIN).getName();
        this.entityType = EntityType.PIGLIN;
        this.defaultMaxHealth = 36;
        this.isEnabled = MobPropertiesConfig.getMobProperties().get(EntityType.PIGLIN).isEnabled();
        if (this.isEnabled)
            eliteMobData.add(this);
    }
}



