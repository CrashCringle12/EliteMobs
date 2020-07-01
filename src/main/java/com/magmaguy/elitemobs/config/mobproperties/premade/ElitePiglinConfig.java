package com.magmaguy.elitemobs.config.mobproperties.premade;

import com.magmaguy.elitemobs.config.mobproperties.MobPropertiesConfigFields;
import org.bukkit.entity.EntityType;

import java.util.Arrays;

public class ElitePiglinConfig extends MobPropertiesConfigFields {
    public ElitePiglinConfig() {
        super("elite_piglin",
                EntityType.PIGLIN,
                true,
                "&fLvl &2$level &fElite &6Piglin",
                Arrays.asList("$player &cwas mobbed to death by $entity&c!",
                        "$entity &cgot $player's &cbacon!"));
    }
}
