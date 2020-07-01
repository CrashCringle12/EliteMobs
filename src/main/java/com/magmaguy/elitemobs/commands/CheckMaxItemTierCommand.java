package com.magmaguy.elitemobs.commands;

import com.magmaguy.elitemobs.config.ItemSettingsConfig;
import com.magmaguy.elitemobs.config.MobCombatSettingsConfig;
import com.magmaguy.elitemobs.config.enchantments.EnchantmentsConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;

public class CheckMaxItemTierCommand {

    public static void checkMaxItemTier(CommandSender commandSender) {

        commandSender.sendMessage("§2The current maximum item tier is tier §a" + ItemSettingsConfig.maximumLootTier);
        commandSender.sendMessage("§2The current maximum power enchantment is §a" + EnchantmentsConfig.getEnchantment(Enchantment.ARROW_DAMAGE).getMaxLevel());
        commandSender.sendMessage("§2The current maximum sharpness enchantment is §a" + EnchantmentsConfig.getEnchantment(Enchantment.DAMAGE_ALL).getMaxLevel());
        commandSender.sendMessage("§2The current maximum protection enchantment is §a" + EnchantmentsConfig.getEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL).getMaxLevel());
        commandSender.sendMessage("§2The current maximum natural mob level is §a" + MobCombatSettingsConfig.naturalElitemobLevelCap);

    }

}
