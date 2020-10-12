package com.magmaguy.elitemobs.commands;

import com.magmaguy.elitemobs.commands.guild.AdventurersGuildCommand;
import com.magmaguy.elitemobs.config.ConfigValues;
import com.magmaguy.elitemobs.config.DefaultConfig;
import com.magmaguy.elitemobs.config.TranslationConfig;
import com.magmaguy.elitemobs.items.ShareItem;
import com.magmaguy.elitemobs.playerdata.PlayerStatusScreen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Created by MagmaGuy on 21/01/2017.
 */

public class CommandHandler implements CommandExecutor {

    public final static String STATS = "elitemobs.stats";
    public final static String VERSION = "elitemobs.version";
    public final static String GETLOOT = "elitemobs.getloot";
    public final static String SIMLOOT = "elitemobs.simloot";
    public final static String RELOAD = "elitemobs.reload";
    public final static String GIVELOOT = "elitemobs.giveloot";
    public final static String SPAWNMOB = "elitemobs.spawnmob";
    public final static String KILLALL_AGGRESSIVEELITES = "elitemobs.killall.aggressiveelites";
    public final static String KILLALL_PASSIVEELITES = "elitemobs.killall.passiveelites";
    public final static String KILLALL_SPECIFICENTITY = "elitemobs.killall.specificentity";
    public final static String SHOP = "elitemobs.shop";
    public final static String CUSTOMSHOP = "elitemobs.customshop";
    public final static String CURRENCY_PAY = "elitemobs.currency.pay";
    public final static String CURRENCY_ADD = "elitemobs.currency.add";
    public final static String CURRENCY_SUBTRACT = "elitemobs.currency.subtract";
    public final static String CURRENCY_SET = "elitemobs.currency.set";
    public final static String CURRENCY_WALLET = "elitemobs.currency.check";
    public final static String CURRENCY_CHECK = "elitemobs.currency.check.others";
    public final static String EVENTS = "elitemobs.events";
    public final static String CHECK_TIER = "elitemobs.checktier";
    public final static String CHECK_TIER_OTHERS = "elitemobs.checktier.others";
    public final static String CHECK_MAX_TIER = "elitemobs.checkmaxtier";
    public final static String GET_TIER = "elitemobs.gettier";
    public final static String ADVENTURERS_GUILD = "elitemobs.adventurersguild";
    public static final String NPC = "elitemobs.npc";
    public static final String AUTOSETUP = "elitemobs.autosetup";
    public static final String QUEST = "elitemobs.quest";
    public static final String CUSTOMBOSS = "elitemobs.customboss";
    public static final String DISCORD = "elitemobs.discord";
    public static final String DEBUG = "elitemobs.debug";
    public static final String SET_RANK = "elitemobs.maxrank";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        switch (label) {
            case "ag":
            case "adventurersguild":
            case "adventurerguild":
                new AdventurersGuildCommand((Player) commandSender);
                return true;
            case "shareitem":
                ShareItem.showOnChat((Player) commandSender);
                return true;
        }

        if (args.length == 0) {
            if (commandSender instanceof Player && DefaultConfig.emLeadsToStatusMenu) {
                new PlayerStatusScreen((Player) commandSender);
                return true;
            }
            validCommands(commandSender);
            return true;
        }

        args[0] = args[0].toLowerCase();

        if (commandSender instanceof Player && UserCommands.parseUserCommand((Player) commandSender, args))
            return true;

        if (AdminCommands.parseAdminCommand(commandSender, args))
            return true;

        if (commandSender.isOp())
            switch (args[0]) {
                case "usepermissions":
                    DefaultConfig.setUsePermissions(true, commandSender);
                    return true;
                case "dontusepermissions":
                    DefaultConfig.setUsePermissions(false, commandSender);
                    return true;
            }

        validCommands(commandSender);
        return true;

    }

    public static boolean permCheck(String permission, CommandSender commandSender) {

        if (!DefaultConfig.usePermissions) {
            if (permission.equals(SHOP) ||
                    permission.equals(SHOP + ".command") ||
                    permission.equals(CUSTOMSHOP) ||
                    permission.equals(CUSTOMSHOP + ".command") ||
                    permission.equals(CURRENCY_PAY) ||
                    permission.equals(CURRENCY_WALLET) ||
                    permission.equals(ADVENTURERS_GUILD) ||
                    permission.equals(QUEST))
                return true;
            else if (commandSender.isOp())
                return true;
        } else if (commandSender.hasPermission(permission)) return true;

        if (commandSender instanceof Player && DefaultConfig.doPermissionTitles) {

            Player player = (Player) commandSender;

            player.sendTitle(ConfigValues.translationConfig.getString(TranslationConfig.MISSING_PERMISSION_TITLE).replace("$username", player.getDisplayName()),
                    ConfigValues.translationConfig.getString(TranslationConfig.MISSING_PERMISSION_SUBTITLE).replace("$permission", permission));

        } else {

            commandSender.sendMessage("[EliteMobs] You may not run this command.");
            commandSender.sendMessage("[EliteMobs] You don't have the permission " + permission);

        }

        return false;

    }

    public static boolean userPermCheck(String permission, CommandSender commandSender) {
        if (commandSender instanceof Player)
            return permCheck(permission, commandSender);

        commandSender.sendMessage("[EliteMobs] You may not run this command.");
        commandSender.sendMessage("[EliteMobs] This is a user command.");
        return false;
    }

    public static void validCommands(CommandSender commandSender) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            player.sendMessage("[EliteMobs] " + ConfigValues.translationConfig.getString(TranslationConfig.VALID_COMMANDS));
            player.sendMessage("/em");
            if (silentPermCheck(STATS, commandSender))
                player.sendMessage("§2/EliteMobs stats");
            if (silentPermCheck(VERSION, commandSender))
                player.sendMessage("§2/EliteMobs version");
            if (silentPermCheck(GETLOOT, commandSender))
                player.sendMessage(
                        "§2/EliteMobs getloot [loot name (no loot name = AdventurersGuildMenu)]");
            if (silentPermCheck(SIMLOOT, commandSender))
                player.sendMessage("§2/EliteMobs simloot [mob level]");
            if (silentPermCheck(RELOAD, commandSender))
                player.sendMessage("§2/EliteMobs reload");
            if (silentPermCheck(GIVELOOT, commandSender))
                player.sendMessage("§2/EliteMobs giveloot [player name] random/[loot_name_underscore_for_spaces]");
            if (silentPermCheck(SPAWNMOB, commandSender))
                player.sendMessage("§2/EliteMobs SpawnMob [mobType] [mobLevel] [mobPower] [mobPower2(keep adding as many as you'd like)]");
            if (silentPermCheck(KILLALL_AGGRESSIVEELITES, commandSender))
                player.sendMessage("§2/EliteMobs kill aggressive");
            if (silentPermCheck(KILLALL_PASSIVEELITES, commandSender))
                player.sendMessage("§2/EliteMobs kill passive");
            if (silentPermCheck(KILLALL_SPECIFICENTITY, commandSender))
                player.sendMessage("§2/EliteMobs kill [entityType] [radius]");
            if (silentPermCheck(SHOP, commandSender))
                player.sendMessage("§2/EliteMobs shop");
            if (silentPermCheck(CUSTOMSHOP, commandSender))
                player.sendMessage("§2/EliteMobs customshop");
            if (silentPermCheck(CURRENCY_PAY, commandSender))
                player.sendMessage("§2/EliteMobs pay §a<username>");
            if (silentPermCheck(CURRENCY_ADD, commandSender))
                player.sendMessage("§2/EliteMobs add §a<username>");
            if (silentPermCheck(CURRENCY_SUBTRACT, commandSender))
                player.sendMessage("§2/EliteMobs subtract §a<username>");
            if (silentPermCheck(CURRENCY_SET, commandSender))
                player.sendMessage("§2/EliteMobs set [username]");
            if (silentPermCheck(CURRENCY_WALLET, commandSender))
                player.sendMessage("§2/EliteMobs wallet");
            if (silentPermCheck(CURRENCY_CHECK, commandSender))
                player.sendMessage("§2/EliteMobs check [username]");
            if (silentPermCheck(EVENTS, commandSender))
                player.sendMessage("§2/EliteMobs event [eventName]");
            if (silentPermCheck(CHECK_TIER, commandSender))
                player.sendMessage("§2/EliteMobs checktier");
            if (silentPermCheck(CHECK_TIER_OTHERS, commandSender))
                player.sendMessage("§2/EliteMobs checktier [player]");
            if (silentPermCheck(CHECK_MAX_TIER, commandSender))
                player.sendMessage("§2/Elitemobs checkmaxtier");
            if (silentPermCheck(ADVENTURERS_GUILD, commandSender))
                player.sendMessage("/ag");
            if (silentPermCheck(NPC, commandSender))
                player.sendMessage("§2/EliteMobs npc");
            if (silentPermCheck(AUTOSETUP, commandSender))
                player.sendMessage("§2/EliteMobs autosetup");
            if (silentPermCheck(QUEST, commandSender))
                player.sendMessage("§2/EliteMobs quest");
            if (silentPermCheck(CUSTOMBOSS, commandSender))
                player.sendMessage("§2/EliteMobs customboss");
            if (silentPermCheck(DISCORD, commandSender))
                player.sendMessage("§2/EliteMobs discord");
            if (silentPermCheck(DISCORD, commandSender))
                player.sendMessage("§2/EliteMobs discord [message]");
            if (silentPermCheck(DEBUG, commandSender))
                player.sendMessage("§2/EliteMobs debug");
            if (silentPermCheck(GET_TIER, commandSender))
                commandSender.sendMessage("§2/EliteMobs gettier [tier]");
            if (silentPermCheck(QUEST, commandSender)) {
                commandSender.sendMessage("§2/EliteMobs quest");
                commandSender.sendMessage("§2/EliteMobs quest status");
            }
            if (silentPermCheck(SET_RANK, commandSender))
                commandSender.sendMessage("§2/EliteMobs setrank [player] [prestigeTier] [guildTier]");
            if (silentPermCheck("elitemobs.*", commandSender))
                commandSender.sendMessage("&aNeed help?  &9https://discord.gg/9f5QSka (only people with OP or elitemobs.* can see this)");


        } else if (commandSender instanceof ConsoleCommandSender) {

            commandSender.sendMessage("§7[EliteMobs] " + ConfigValues.translationConfig.getString(TranslationConfig.INVALID_COMMAND));
            commandSender.sendMessage("§2EliteMobs §astats");
            commandSender.sendMessage("§2EliteMobs §areload");
            commandSender.sendMessage("§2EliteMobs §acheck §2<username>");
            commandSender.sendMessage("§2EliteMobs §aset §2<username>");
            commandSender.sendMessage("§2EliteMobs §aadd §2<username>");
            commandSender.sendMessage("§2EliteMobs §asubtract §2<username>");
            commandSender.sendMessage("§2EliteMobs §akillall passiveelites");
            commandSender.sendMessage("§2Elitemobs §akillall aggressiveelites");
            commandSender.sendMessage("§2Elitemobs §agiveloot <player name> random/[loot_name_underscore_for_spaces]");
            commandSender.sendMessage("§2Elitemobs §aSpawnMob <worldName> <x> <y> <z> <mobType> <mobLevel> [mobPower] [mobPower2(keep adding as many as you'd like)]");

        }

    }

    private static boolean silentPermCheck(String permission, CommandSender commandSender) {
        if (!DefaultConfig.usePermissions) {
            if (commandSender instanceof Player) {
                if (permission.equals(SHOP) ||
                        permission.equals(SHOP + ".command") ||
                        permission.equals(CUSTOMSHOP) ||
                        permission.equals(CUSTOMSHOP + ".command") ||
                        permission.equals(CURRENCY_PAY) ||
                        permission.equals(CURRENCY_WALLET) ||
                        permission.equals(ADVENTURERS_GUILD) ||
                        permission.equals(QUEST))
                    return true;
                else if (commandSender.isOp())
                    return true;
            }
        }

        return commandSender.hasPermission(permission);
    }


}
