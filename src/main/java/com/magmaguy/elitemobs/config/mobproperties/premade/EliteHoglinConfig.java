package com.magmaguy.elitemobs.config.mobproperties.premade;

import com.magmaguy.elitemobs.config.mobproperties.MobPropertiesConfigFields;
import org.bukkit.entity.EntityType;

import java.util.Arrays;

public class EliteHoglinConfig extends MobPropertiesConfigFields {
    public EliteHoglinConfig() {
        super("elite_hoglin",
                EntityType.HOGLIN,
                true,
                "&fLvl &2$level &fElite &cHoglin",
                Arrays.asList("$entity &cshowed $player &cwho's in charge!"));
    }
}
