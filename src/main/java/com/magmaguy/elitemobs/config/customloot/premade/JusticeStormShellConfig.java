package com.magmaguy.elitemobs.config.customloot.premade;

import com.magmaguy.elitemobs.config.customloot.CustomLootConfigFields;
import org.bukkit.Material;

import java.util.Arrays;

public class JusticeStormShellConfig extends CustomLootConfigFields {
    public JusticeStormShellConfig() {
        super("justice_storm_shell",
                true,
                Material.NAUTILUS_SHELL.toString(),
                "&bShell of Justice",
                Arrays.asList("&3Call forth a mighty storm of righteousness", "&3Single-use."),
                Arrays.asList("JUSTICE_STORM,1"),
                Arrays.asList(),
                "dynamic",
                "fixed",
                "unique");
    }
}
