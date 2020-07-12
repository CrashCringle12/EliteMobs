package com.magmaguy.elitemobs.commands;

import com.magmaguy.elitemobs.adventurersguild.GuildRankMenuHandler;
import com.magmaguy.elitemobs.commands.admin.CheckTierOthersCommand;
import com.magmaguy.elitemobs.commands.admin.DebugScreen;
import com.magmaguy.elitemobs.commands.admin.StatsCommand;
import com.magmaguy.elitemobs.commands.admin.npc.NPCCommands;
import com.magmaguy.elitemobs.commands.combat.CheckTierCommand;
import com.magmaguy.elitemobs.commands.guild.AdventurersGuildCommand;
import com.magmaguy.elitemobs.commands.quest.QuestCommand;
import com.magmaguy.elitemobs.commands.quest.QuestStatusCommand;
import com.magmaguy.elitemobs.commands.shops.CustomShopMenu;
import com.magmaguy.elitemobs.commands.shops.ProceduralShopMenu;
import com.magmaguy.elitemobs.config.AdventurersGuildConfig;
import com.magmaguy.elitemobs.config.ConfigValues;
import com.magmaguy.elitemobs.config.DefaultConfig;
import com.magmaguy.elitemobs.config.TranslationConfig;
import com.magmaguy.elitemobs.custombosses.CustomBossEntity;
import com.magmaguy.elitemobs.items.ShareItem;
import com.magmaguy.elitemobs.playerdata.PlayerStatusScreen;
import com.magmaguy.elitemobs.thirdparty.discordsrv.DiscordSRVAnnouncement;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

/**
 * Created by MagmaGuy on 21/01/2017.
 */

public class CommandHandler implements CommandExecutor {

    private final static String STATS = "elitemobs.stats";
    private final static String GETLOOT = "elitemobs.getloot";
    private final static String SIMLOOT = "elitemobs.simloot";
    public final static String RELOAD_CONFIGS = "elitemobs.reload";
    private final static String GIVELOOT = "elitemobs.giveloot";
    private final static String SPAWNMOB = "elitemobs.spawnmob";
    private final static String SPAWN_BOSS_MOB = "elitemobs.spawnbossmob";
    public final static String KILLALL_AGGRESSIVEELITES = "elitemobs.killall.aggressiveelites";
    public final static String KILLALL_PASSIVEELITES = "elitemobs.killall.passiveelites";
    public final static String KILLALL_SPECIFICENTITY = "elitemobs.killall.specificentity";
    private final static String SHOP = "elitemobs.shop";
    private final static String CUSTOMSHOP = "elitemobs.customshop";
    private final static String CURRENCY_PAY = "elitemobs.currency.pay";
    private final static String CURRENCY_ADD = "elitemobs.currency.add";
    private final static String CURRENCY_SUBTRACT = "elitemobs.currency.subtract";
    private final static String CURRENCY_SET = "elitemobs.currency.set";
    private final static String CURRENCY_CHECK = "elitemobs.currency.check";
    private final static String CURRENCY_WALLET = "elitemobs.currency.wallet";
    private final static String CURRENCY_COINTOP = "elitemobs.currency.cointop";
    private final static String VERSION = "elitemobs.version";
    public final static String EVENT_LAUNCH_SMALLTREASUREGOBLIN = "elitemobs.events.smalltreasuregoblin";
    public final static String EVENT_LAUNCH_DEADMOON = "elitemobs.events.smalltreasuregoblin";
    private final static String SET_MAX_TIER = "elitemobs.config.setmaxtier";
    private final static String GET_TIER = "elitemobs.gettier";
    private final static String CHECK_MAX_TIER = "elitemobs.checkmaxtier";

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

        switch (args[0]) {
            case "spawn":
            case "spawnmob":
                if (permCheck(SPAWNMOB, commandSender))
                    SpawnCommand.spawnMob(commandSender, args);
                return true;
            case "ag":
            case "adventurersguild":
            case "adventurerguild":
                new AdventurersGuildCommand((Player) commandSender);
                return true;
            case "stats":
                if (permCheck(STATS, commandSender))
                    StatsCommand.statsHandler(commandSender);
                return true;
            case "getloot":
            case "gl":
                if (userPermCheck(GETLOOT, commandSender) && args.length == 1) {
                    getLootMenu getLootMenu = new getLootMenu();
                    getLootMenu.lootGUI((Player) commandSender);
                } else {
                    if (GetLootCommandHandler.getLoot(((Player) commandSender), args[1]))
                        return true;
                    else
                        ((Player) commandSender).sendTitle("", "Could not find that item name.");
                }
                return true;
            case "shop":
            case "store":
                if (userPermCheck("elitemobs.shop.command", commandSender))
                    if (!AdventurersGuildCommand.adventurersGuildTeleport((Player) commandSender))
                        ProceduralShopMenu.shopInitializer((Player) commandSender);
                return true;
            case "customshop":
            case "cshop":
            case "customstore":
            case "cstore":
                if (userPermCheck("elitemobs.customshop.command", commandSender))
                    if (!AdventurersGuildCommand.adventurersGuildTeleport((Player) commandSender))
                        CustomShopMenu.customShopInitializer((Player) commandSender);
                return true;
            case "wallet":
            case "bal":
            case "balance":
            case "currency":
            case "money":
            case "$":
                if (userPermCheck(CURRENCY_WALLET, commandSender))
                    CurrencyCommandsHandler.walletCommand(commandSender, args);
                return true;
            case "cointop":
            case "baltop":
            case "cashtop":
            case "currencytop":
            case "$top":
                if (permCheck(CURRENCY_COINTOP, commandSender))
                    //CurrencyCommandsHandler.coinTop(commandSender);
                    return true;
            case "version":
                if (permCheck(VERSION, commandSender))
                    VersionHandler.versionCommand(commandSender, args);
                return true;
            case "checktier":
            case "tiercheck":
            case "tier":
                if (args.length == 1) {
                    if (userPermCheck("elitemobs.checktier", commandSender))
                        new CheckTierCommand((Player) commandSender);
                } else if (permCheck("elitemobs.checktier.others", commandSender))
                    new CheckTierOthersCommand(commandSender, args);
                return true;
            case "checkmaxtier":
            case "maxtier":
                if (permCheck(CHECK_MAX_TIER, commandSender))
                    CheckMaxItemTierCommand.checkMaxItemTier(commandSender);
                return true;
            case "reload":
            case "restart":
                if (permCheck(RELOAD_CONFIGS, commandSender))
                    ReloadHandler.reload(commandSender);
                return true;
            case "killall":
            case "kill":
                KillHandler.killCommand(commandSender, args);
                return true;
            case "simloot":
            case "simulateloot":
            case "simdrop":
            case "simulatedrop":
                if (commandSender instanceof Player) {
                    if (userPermCheck(SIMLOOT, commandSender))
                        SimLootHandler.simLoot((Player) commandSender, Integer.parseInt(args[1]));
                } else if (permCheck(SIMLOOT, commandSender))
                    SimLootHandler.simLoot(commandSender, args);
                return true;
            case "check":
            case "checkcurrency":
            case "checkbal":
            case "checkbalance":
            case "check$":
                if (permCheck(CURRENCY_CHECK, commandSender))
                    CurrencyCommandsHandler.checkCommand(commandSender, args);
                return true;
            case "event":
            case "launchevent":
            case "startevent":
            case "triggerevent":
                TriggerEventHandler.triggerEventCommand(commandSender, args);
                return true;
            case "gettier":
            case "spawntier":
                if (userPermCheck(GET_TIER, commandSender))
                    TierSetSpawner.spawnTierItem(Integer.parseInt(args[1]), (Player) commandSender);
                return true;
            case "setmaxtier":
                if (permCheck(SET_MAX_TIER, commandSender))
                    SetMaxItemTierCommand.setMaxItemTier(Double.parseDouble(args[1]), commandSender);
                return true;
            case "giveloot":
                if (permCheck(GIVELOOT, commandSender))
                    GiveLootHandler.giveLootCommand(commandSender, args);
                return true;
            case "pay":
                if (userPermCheck(CURRENCY_PAY, commandSender))
                    CurrencyCommandsHandler.payCommand((Player) commandSender, args);
                return true;
            case "add":
                if (permCheck(CURRENCY_ADD, commandSender))
                    CurrencyCommandsHandler.addCommand(commandSender, args);
                return true;
            case "addall":
                if (permCheck(CURRENCY_ADD, commandSender))
                    CurrencyCommandsHandler.addAllCommand(commandSender, args);
                return true;
            case ("subtract"):
                if (permCheck(CURRENCY_SUBTRACT, commandSender))
                    CurrencyCommandsHandler.subtractCommand(commandSender, args);
                return true;
            case ("set"):
                if (permCheck(CURRENCY_SET, commandSender))
                    CurrencyCommandsHandler.setCommand(commandSender, args);
            case ("npc"):
                NPCCommands.parseNPCCommand(commandSender, args);
                return true;
            case "autosetup":
                if (!permCheck("elitemobs.autosetup", commandSender)) return true;
                File folder = new File(Bukkit.getWorldContainer().getAbsolutePath());
                File[] listOfFiles = folder.listFiles();
                boolean worldFolderExists = false;

                for (File listOfFile : listOfFiles) {
                    if (listOfFile.isDirectory() &&
                            listOfFile.getName().equals(AdventurersGuildConfig.guildWorldName)) {
                        commandSender.sendMessage("[EliteMobs] World " + AdventurersGuildConfig.guildWorldName + " found! Loading it in...");
                        worldFolderExists = true;
                        break;
                    }
                }

                if (!worldFolderExists) {
                    commandSender.sendMessage("[EliteMobs] Could not import world " + AdventurersGuildConfig.guildWorldName + " ! " +
                            "It is not in your worlds directory. If you wish to use the default world, you can find the link to download it on the resource page over at https://www.spigotmc.org/resources/%E2%9A%94elitemobs%E2%9A%94.40090/");
                    return true;
                }

                Bukkit.createWorld(new WorldCreator(AdventurersGuildConfig.guildWorldName));
                commandSender.sendMessage("[EliteMobs] Successfully imported the world!");
                commandSender.sendMessage("[EliteMobs] Now all you need to do is add the permission elitemobs.user to your users and you're all set!");
                return true;
            case "quest":
                if (userPermCheck("elitemobs.quest", commandSender)) {
                    if (args.length == 1) {
                        if (!AdventurersGuildCommand.adventurersGuildTeleport((Player) commandSender))
                            new QuestCommand((Player) commandSender);
                    } else new QuestStatusCommand((Player) commandSender, args);
                }
                return true;
            case "showitem":
            case "itemshow":
            case "shareitem":
            case "itemshare":
            case "share":
                ShareItem.showOnChat((Player) commandSender);
                return true;
            case "guild":
            case "rank":
            case "guildrank":
                if (!userPermCheck("elitemobs.guild.menu", commandSender)) return true;
                if (AdventurersGuildCommand.adventurersGuildTeleport((Player) commandSender)) {
                    new AdventurersGuildCommand((Player) commandSender);
                    return true;
                }
                GuildRankMenuHandler guildRankMenuHandler = new GuildRankMenuHandler();
                GuildRankMenuHandler.initializeGuildRankMenu((Player) commandSender);
                return true;
            case "trackcustomboss":
                CustomBossEntity.getCustomBoss(UUID.fromString(args[2])).realTimeTracking((Player) commandSender);
                return true;
            case "customboss":
                if (!userPermCheck("elitemobs.customboss", commandSender)) return true;
                CustomBossCommandHandler.handleCommand((Player) commandSender, args);
                return true;
            case "setrank":
                if (!userPermCheck("elitemobs.admin", commandSender)) return true;
                GuildRankCommands.setGuildRank(args);
                return true;
            case "status":
                new PlayerStatusScreen((Player) commandSender);
                return true;
            case "debug":
                if (!userPermCheck("elitemobs.admin", commandSender)) return true;
                new DebugScreen((Player) commandSender, args);
                return true;
            case "discord":
                if (!userPermCheck("elitemobs.admin", commandSender)) return true;
                if (args.length < 2) {
                    commandSender.sendMessage("EliteMobs discord room: https://discord.gg/9f5QSka");
                    commandSender.sendMessage("If you're trying to send a message via DiscordSRV, the correct commands is /em discord [message]");
                } else {
                    StringBuilder message = new StringBuilder();
                    for (int i = 0; i < args.length; i++)
                        if (i > 0)
                            message.append(args[i] + " ");
                    new DiscordSRVAnnouncement(message.toString());
                }
                return true;
            default:
                validCommands(commandSender);
                return true;

        }

    }

    public static boolean permCheck(String permission, CommandSender commandSender) {

        if (commandSender.hasPermission(permission)) return true;

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

    private static void validCommands(CommandSender commandSender) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            player.sendMessage("§7[EliteMobs] " + ConfigValues.translationConfig.getString(TranslationConfig.VALID_COMMANDS));
            if (silentPermCheck(STATS, commandSender))
                player.sendMessage("§2/EliteMobs stats");
            if (silentPermCheck(SHOP, commandSender))
                player.sendMessage("§2/EliteMobs shop");
            if (silentPermCheck(CUSTOMSHOP, commandSender))
                player.sendMessage("§2/EliteMobs customshop");
            if (silentPermCheck(CURRENCY_WALLET, commandSender))
                player.sendMessage("§2/EliteMobs wallet");
            if (silentPermCheck(CURRENCY_COINTOP, commandSender))
                player.sendMessage("§2/EliteMobs cointop");
            if (silentPermCheck(CURRENCY_PAY, commandSender))
                player.sendMessage("§2/EliteMobs pay §a<username>");
            if (silentPermCheck(CURRENCY_ADD, commandSender))
                player.sendMessage("§2/EliteMobs add §a<username>");
            if (silentPermCheck(CURRENCY_SUBTRACT, commandSender))
                player.sendMessage("§2/EliteMobs subtract §a<username>");
            if (silentPermCheck(CURRENCY_SET, commandSender))
                player.sendMessage("§2/EliteMobs set §a<username>");
            if (silentPermCheck(CURRENCY_CHECK, commandSender))
                player.sendMessage("§2/EliteMobs check §a<username>");
            if (silentPermCheck(RELOAD_CONFIGS, commandSender))
                player.sendMessage("§2/EliteMobs reload");
            if (silentPermCheck(KILLALL_AGGRESSIVEELITES, commandSender))
                player.sendMessage("§2/EliteMobs kill §aaggressive");
            if (silentPermCheck(KILLALL_PASSIVEELITES, commandSender))
                player.sendMessage("§2/EliteMobs kill §apassive");
            if (silentPermCheck(SIMLOOT, commandSender))
                player.sendMessage("§2/EliteMobs simloot §a<mob level>");
            if (silentPermCheck(GETLOOT, commandSender))
                player.sendMessage("§2/EliteMobs getloot §a<loot name (no loot name = AdventurersGuildMenu)>");
            if (silentPermCheck(GIVELOOT, commandSender))
                player.sendMessage("§2/EliteMobs giveloot §a<player name> random/<loot_name_underscore_for_spaces>");
            if (silentPermCheck(SPAWNMOB, commandSender))
                player.sendMessage("§2/EliteMobs SpawnMob §a<mobType> <mobLevel> §a[mobPower] [mobPower2(keep adding as many as you'd like)]");
            if (silentPermCheck(CHECK_MAX_TIER, commandSender))
                commandSender.sendMessage("§2/EliteMobs checkmaxtier");
            if (silentPermCheck(SET_MAX_TIER, commandSender))
                commandSender.sendMessage("§2/EliteMobs setmaxtier §a<tier>");
            if (silentPermCheck(GET_TIER, commandSender))
                commandSender.sendMessage("§2/EliteMobs gettier §a<tier>");
            if (silentPermCheck("elitemobs.quest", commandSender)) {
                commandSender.sendMessage("§2/EliteMobs quest");
                commandSender.sendMessage("§2/EliteMobs quest §astatus");
            }


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
        return commandSender.hasPermission(permission);
    }


}
