package com.magmaguy.elitemobs.config.powers.premade;

import com.magmaguy.elitemobs.config.powers.PowersConfigFields;
import org.bukkit.Material;

public class AttackTridentConfig extends PowersConfigFields {
    public AttackTridentConfig() {
        super("attack_trident",
                true,
                "Trident",
                Material.DISPENSER.toString());
    }
}
