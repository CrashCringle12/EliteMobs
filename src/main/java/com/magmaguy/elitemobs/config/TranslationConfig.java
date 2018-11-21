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

package com.magmaguy.elitemobs.config;

import org.bukkit.configuration.Configuration;

import java.util.Arrays;

/**
 * Created by MagmaGuy on 12/05/2017.
 */
public class TranslationConfig {

    private static final String ELITE_MOB_NAME = "Elite mob names.";
    public static final String NAME_BLAZE = ELITE_MOB_NAME + "Blaze";
    public static final String NAME_CAVE_SPIDER = ELITE_MOB_NAME + "CaveSpider";
    public static final String NAME_CREEPER = ELITE_MOB_NAME + "Creeper";
    public static final String NAME_ENDERMAN = ELITE_MOB_NAME + "Enderman";
    public static final String NAME_ENDERMITE = ELITE_MOB_NAME + "Endermite";
    public static final String NAME_HUSK = ELITE_MOB_NAME + "Husk";
    public static final String NAME_IRON_GOLEM = ELITE_MOB_NAME + "IronGolem";
    public static final String NAME_PIG_ZOMBIE = ELITE_MOB_NAME + "PigZombie";
    public static final String NAME_POLAR_BEAR = ELITE_MOB_NAME + "PolarBear";
    public static final String NAME_SILVERFISH = ELITE_MOB_NAME + "Silverfish";
    public static final String NAME_SKELETON = ELITE_MOB_NAME + "Skeleton";
    public static final String NAME_SPIDER = ELITE_MOB_NAME + "Spider";
    public static final String NAME_STRAY = ELITE_MOB_NAME + "Stray";
    public static final String NAME_WITCH = ELITE_MOB_NAME + "Witch";
    public static final String NAME_WITHER_SKELETON = ELITE_MOB_NAME + "WitherSkeleton";
    public static final String NAME_ZOMBIE = ELITE_MOB_NAME + "Zombie";
    public static final String NAME_VEX = ELITE_MOB_NAME + "Vex";
    public static final String NAME_VINDICATOR = ELITE_MOB_NAME + "Vindicator";
    public static final String NAME_DROWNED = ELITE_MOB_NAME + "Drowned";

    public static final String NAME_CHICKEN = ELITE_MOB_NAME + "Chicken";
    public static final String NAME_COW = ELITE_MOB_NAME + "Cow";
    public static final String NAME_MUSHROOM_COW = ELITE_MOB_NAME + "MushroomCow";
    public static final String NAME_PIG = ELITE_MOB_NAME + "Pig";
    public static final String NAME_SHEEP = ELITE_MOB_NAME + "Sheep";

    public static final String CONFIG_NAME = "translation.yml";
    CustomConfigLoader customConfigLoader = new CustomConfigLoader();
    Configuration configuration = customConfigLoader.getCustomConfig(CONFIG_NAME);

    public void initializeConfig() {

        configuration.addDefault(NAME_BLAZE, "Level $level Elite Blaze");
        configuration.addDefault(NAME_CAVE_SPIDER, "Level $level Elite Cave Spider");
        configuration.addDefault(NAME_CREEPER, "Level $level Elite Creeper");
        configuration.addDefault(NAME_ENDERMAN, "Level $level Elite Enderman");
        configuration.addDefault(NAME_ENDERMITE, "Level $level Elite Endermite");
        configuration.addDefault(NAME_HUSK, "Level $level Elite Husk");
        configuration.addDefault(NAME_IRON_GOLEM, "Level $level Elite Iron Golem");
        configuration.addDefault(NAME_PIG_ZOMBIE, "Level $level Elite Zombie Pigman");
        configuration.addDefault(NAME_POLAR_BEAR, "Level $level Elite Polar Bear");
        configuration.addDefault(NAME_SILVERFISH, "Level $level Elite Silverfish");
        configuration.addDefault(NAME_SKELETON, "Level $level Elite Skeleton");
        configuration.addDefault(NAME_SPIDER, "Level $level Elite Spider");
        configuration.addDefault(NAME_STRAY, "Level $level Elite Stray");
        configuration.addDefault(NAME_WITCH, "Level $level Elite Witch");
        configuration.addDefault(NAME_WITHER_SKELETON, "Level $level Elite Wither Skeleton");
        configuration.addDefault(NAME_ZOMBIE, "Level $level Elite Zombie");
        configuration.addDefault(NAME_VEX, "Level $level Elite Vex");
        configuration.addDefault(NAME_VINDICATOR, "Level $level Elite Vindicator");
        configuration.addDefault(NAME_DROWNED, "Level $level Elite Drowned");

        configuration.addDefault(NAME_CHICKEN, "Super Chicken");
        configuration.addDefault(NAME_COW, "Super Cow");
        configuration.addDefault(NAME_MUSHROOM_COW, "Super Mooshroom");
        configuration.addDefault(NAME_PIG, "Super Pig");
        configuration.addDefault(NAME_SHEEP, "Super Sheep");

        configuration.addDefault("ScoreBoard.Health", "Health: ");

        configuration.addDefault("Taunts.Target", Arrays.asList(
                "OI! Get over here!",
                "What's that I see? A coward?",
                "Sir, prepare your fisticuffs!",
                "Let's settle this like cavemen!",
                "En garde!",
                "Look out! A creeper, behind you!",
                "Aren't you a right ugly mug!",
                "You look delicious.",
                "Prepare for trouble!",
                "Let the best click spammer win!",
                "SPARTAAAAAA",
                "I am actually level 9001",
                "Not an Elite Mob",
                "Poorly disguised Elite Mob",
                "Ah, fresh prey!",
                "The hunter becomes the hunted!",
                "Killing me crashes the server",
                "I'm actually a disguised player!",
                "I'm not going to drop any loot!",
                "Why can't we be friends?",
                "Sssssss",
                "This is my last day on the job!",
                "Stop right there criminal scum!",
                "Prepare to die!",
                "Praise the Sun!",
                "Git gud",
                "Alea jacta est!",
                "Facta, non verba!",
                "Skulls for the skull throne!",
                "Blood for the blood throne!",
                "A sacrifice for Herobrine!",
                "I shall pierce the heavens!",
                "Just who do you think I am!?",
                "A weakling! He's mine.",
                "Dibs on the weakling!",
                "Accio player!",
                "A duel to the death!",
                "I challenge you to a duel!",
                "Pika pika!",
                "Did you just call me an infernal",
                "What did you just call me?",
                "I know what you did you monster!",
                "This is how your story ends!",
                "Prepare for trouble!",
                "I've been preparing for this!",
                "I am prepared. Are you?",
                "A worthy foe!",
                "A worthy opponent!",
                "A newbie! It's my lucky day!",
                "Your stuff or your life!",
                "This is a robbery!",
                "It's you! You monster!",
                "So hungry, give me your flesh!",
                "There he is!",
                "Must've been my imagination",
                "Hi I'm Mr Meeseeks!",
                "I'm Mr Meeseeks, look at me!",
                "Your diamonds or your life!",
                "For king and country!",
                "Hit me with your best shot!",
                "Witness me!",
                "Witness me blood bag!",
                "I go all shiny and chrome!"));

        configuration.addDefault("Taunts.Damaged", Arrays.asList(
                "Ow!",
                "Oi!",
                "Stop that!",
                "Why?!",
                "You fight like a Dairy Farmer!",
                "You are a pain in the backside!",
                "En garde! Touche!",
                "Hacks!",
                "My health is bugged, unkillable!",
                "Your hits only make me stronger!",
                "Sticks and stones...",
                "'tis but a flesh wound!",
                "Emperor protects!",
                "Herobrine, aid me!",
                "Behind you! A creeper!",
                "I have made a terrible mistake.",
                "What doesn't kill me...",
                "Zombies dig scars",
                "I shall show you my true power!",
                "Prepare for my ultimate attack!",
                "My reinforcements are arriving!",
                "You're going to pay for that!",
                "That's going to leave a mark!",
                "An eye for an eye!",
                "Health insurance will cover that",
                "I felt that one in my bones",
                "'tis but a scratch",
                "What was that? A soft breeze?",
                "You'll never defeat me like that",
                "Pathetic.",
                "Weak.",
                "That didn't even dent my armor!",
                "This is going to be an easy one",
                "My grandchildren will feel that",
                "Are you even trying?",
                "An admin just fully healed me",
                "Haxxor!",
                "Me? Damaged? Hacks!",
                "I can't be defeated!",
                "Good thing I'm using hacks!",
                "Watch out! Herobrine behind you!",
                "My life for Aiur!",
                "Your home is getting griefed!",
                "Why can't we be friends?",
                "Valhalla!",
                "Notch save me!",
                "No retreat!",
                "Hit me with your best shot!"
        ));

        configuration.addDefault("Taunts.BowDamaged", Arrays.asList(
                "Fight me like a Player!",
                "Afraid to come up-close?",
                "I can smell your fear from here!",
                "Did you forget your sword?",
                "Coward! Bow are no fair!",
                "Bows? That's so 2011...",
                "Bows are for the weak of mind!",
                "Bows are for the weak of spirit!",
                "Bows are for the weak of heart!",
                "I thought we agreed no bows!",
                "Bows? You'll regret that",

                //regular hits
                "Ow!",
                "Oi!",
                "Stop that!",
                "Why?!",
                "You fight like a Dairy Farmer!",
                "You are a pain in the backside!",
                "En garde! Touche!",
                "Hacks!",
                "My health is bugged, unkillable!",
                "Your hits only make me stronger!",
                "Sticks and stones...",
                "'tis but a flesh wound!",
                "Emperor protects!",
                "Herobrine, aid me!",
                "Behind you! A creeper!",
                "I have made a terrible mistake.",
                "What doesn't kill me...",
                "Zombies dig scars",
                "I shall show you my true power!",
                "Prepare for my ultimate attack!",
                "My reinforcements are arriving!",
                "You're going to pay for that!",
                "That's going to leave a mark!",
                "An eye for an eye!",
                "Health insurance will cover that",
                "I felt that one in my bones",
                "'tis but a scratch",
                "What was that? A soft breeze?",
                "You'll never defeat me like that",
                "Pathetic.",
                "Weak.",
                "That didn't even dent my armor!",
                "This is going to be an easy one",
                "My grandchildren will feel that",
                "Are you even trying?",
                "An admin just fully healed me",
                "Haxxor!",
                "Me? Damaged? Hacks!",
                "I can't be defeated!",
                "Good thing I'm using hacks!",
                "Watch out! Herobrine behind you!",
                "My life for Aiur!",
                "Your home is getting griefed!",
                "Why can't we be friends?",
                "Valhalla!",
                "Notch save me!",
                "No retreat!",
                "Hit me with your best shot!"
        ));

        configuration.addDefault("Taunts.Hit", Arrays.asList(
                "A solid hit!",
                "He shoots, and he scores!",
                "You'll feel that in the morning!",
                "Victory approaches!",
                "Victory shall be mine!",
                "That was only half of my power!",
                "You came to the wrong hood!",
                "You messed with the wrong mob!",
                "Get ready to get mobbed on!",
                "This was your last mistake!",
                "Feel the burn!",
                "John Cena",
                "How are you still standing?",
                "Just give up already!",
                "Give up!",
                "I have the high ground!",
                "I will end you!",
                "Hope you came prepared for pain!",
                "The hunter becomes the hunted!",
                "Blood for the blood throne!",
                "Skulls for the skull throne!",
                "A good player is a dead player!",
                "Git gud!",
                "Praise the Sun!",
                "The end draws near!",
                "You are not prepared.",
                "Zug zug!",
                "Hardly worth my time!",
                "I thought this was a challenge?",
                "Pro tip: don't get hit",
                "Ima firin mah lazer bwooooooooo",
                "For the Emperor!",
                "Herobrine shall be pleased!",
                "This is the end for you!",
                "Are you even trying?",
                "Weakling!",
                "Pathetic.",
                "Maybe this will wake you up.",
                "This is just the beggining!",
                "We're just getting started!",
                "That was just a warm-up!",
                "You are no match for me!",
                "RKO out of nowhere!",
                "I can do this all night long!",
                "A fine punching bag!",
                "I am the night!",
                "I wonder what will break first",
                "Revenge for Steve! You monster!",
                "Join the mob side!",
                "Too easy, too easy!",
                "Too easy!",
                "You can't defeat me!",
                "Pikachuuuuuu",
                "Avada kedavra!",
                "Crucio!",
                "Jierda!",
                "A critical hit!",
                "This is what skills looks like!",
                "9001 damage!",
                "Still standing?",
                "Face your defeat!",
                "A taste of pain to come!",
                "I'll make you endangered!"
        ));

        configuration.addDefault("Taunts.Death", Arrays.asList(
                "Alas, poor Yorick!",
                "The rest is silence",
                "I shall return",
                "Unforgivable",
                "I live, I die, I live again!",
                "WITNESS ME",
                "I ride to valhalla!",
                "VALHALLA!",
                "VALHALLA! DELIVERANCE",
                "You win, this time",
                "A blight upon your land!",
                "A pox upon thee!",
                "You'll join me soon enough!",
                "This is not over",
                "Death is but a setback",
                "Not like this...",
                "I just wanted to go into space",
                "We could've been friends",
                "Your sins crawling on your back",
                "See you in a bit",
                "See you later",
                "I'll be waiting for you",
                "I will be avenged!",
                "I won't go gently into the night",
                "Should've gotten life insurance",
                "It was my last day on the job",
                "It was my last day here",
                "Et tu, Brute?",
                "RIP",
                "x.x",
                "(x.x)",
                "xox",
                "Fainted",
                "GG, WP",
                "GG",
                "WP",
                "GG no RE",
                "HAX",
                "Hacker",
                "Not fair I was lagging",
                "You win, this time",
                "You monster...",
                "Mediocre..."
        ));

        configuration.addDefault("ZombieFriends.Friend 1", "BFF #1");
        configuration.addDefault("ZombieFriends.Friend 2", "BFF #2");

        configuration.addDefault("ZombieFriends.DeathMessage", Arrays.asList(
                "Noooo!",
                "Mediocre!",
                "Zacharias!",
                "He's deader than before!"
        ));

        configuration.addDefault("ZombieFriends.ZombieDialog", Arrays.asList(
                "Let's play ZombieCraft later!",
                "Feel the power of friendship!",
                "El pueblo, unido!",
                "I called my friends over!",
                "BFF power!",
                "One for all!",
                "Get him!",
                "Screw you guys, I'm going home!"
        ));

        configuration.addDefault("ZombieFriends.FriendDialog", Arrays.asList(
                "Don't mess with our friends!",
                "We got your back Zach!",
                "Backup has arrived!",
                "One for all, one for all!",
                "This is going to be easy!",
                "Give up we have the high ground!",
                "You wanna go bruv?",
                "Worldstaaaaaaaaar!",
                "What are you doing to our friend",
                "Feel the power of friendship!",
                "Friendship power at 100%!",
                "Zombies, assemble!",
                "We got your back mate!",
                "Together we are better!",
                "The more the merrier!",
                "I got you fam!",
                "All for one!"
        ));

        configuration.addDefault("ZombieNecronomicon.Summoned zombie", "Summoned Zombie");
        configuration.addDefault("ZombieNecronomicon.Summoned skeleton", "Summoned Skeleton");
        configuration.addDefault("ZombieNecronomicon.Summoning chant", "Nor is it to be thought..." +
                "that man is either the oldest " +
                "or the last of earth's masters, or that the common bulk of life and substance walks alone. The Old Ones" +
                " were, the Old Ones are, and the Old Ones shall be. Not in the spaces we know, but between them, they " +
                "walk serene and primal, undimensioned and to us unseen. Yog-Sothoth knows the gate. Yog-Sothoth is the " +
                "gate. Yog-Sothoth is the key and guardian of the gate. Past, present, future, all are one in Yog-Sothoth. " +
                "He knows where the Old Ones broke through of old, and where They shall break through again. He knows " +
                "where They had trod earth's fields, and where They still tread them, and why no one can behold Them as " +
                "They tread. By Their smell can men sometimes know Them near, but of Their semblance can no man know, " +
                "saving only in the features of those They have begotten on mankind; and of those are there many sorts, " +
                "differing in likeness from man's truest eidolon to that shape without sight or substance which is Them. " +
                "They walk unseen and foul in lonely places where the Words have been spoken and the Rites howled through " +
                "at their Seasons. The wind gibbers with Their voices, and the earth mutters with Their consciousness. " +
                "They bend the forest and crush the city, yet may not forest or city behold the hand that smites. Kadath " +
                "in the cold waste hath known Them, and what man knows Kadath? The ice desert of the South and the sunken " +
                "isles of Ocean hold stones whereon Their seal is engraver, but who hath seen the deep frozen city or the " +
                "sealed tower long garlanded with seaweed and barnacles? Great Cthulhu is Their cousin, yet can he spy " +
                "Them only dimly. Iä! Shub-Niggurath! As a foulness shall ye know Them. Their hand is at your throats, " +
                "yet ye see Them not; and Their habitation is even one with your guarded threshold. Yog-Sothoth is the " +
                "key to the gate, whereby the spheres meet. Man rules now where They ruled once; They shall soon rule " +
                "where man rules now. After summer is winter, after winter summer. They wait patient and potent, for " +
                "here shall They reign again.");

        configuration.addDefault("ZombieTeamRocket.Intro", Arrays.asList(
                "Prepare for trouble!",
                "Make it double!",
                "To protect the world",
                "from devastation!",
                "To unite all people",
                "within our nation!",
                "To denounce the evils",
                "of truth and love!",
                "To extend our reach",
                "to the stars above!",
                "Jesse!",
                "James!",
                "Team Rocket blasts off",
                "at the speed of light!",
                "Surrender now",
                "or prepare to fight!",
                "Meowth! That's right!"
        ));

        configuration.addDefault("ZombieTeamRocket.Jesse name", "Jesse");
        configuration.addDefault("ZombieTeamRocket.James name", "James");
        configuration.addDefault("ZombieTeamRocket.Meowth name", "Meowth");
        configuration.addDefault("ZombieTeamRocket.DeathMessage", "Team rocket blasts off again!");

        configuration.addDefault("ZombieParents.Dad Name", "Zombie's Dad");
        configuration.addDefault("ZombieParents.Mom Name", "Zombie's Mom");

        configuration.addDefault("ZombieParents.DeathMessage", Arrays.asList(
                "You monster!",
                "My baby!",
                "What have you done!?",
                "Revenge!",
                "Nooooo!",
                "You will pay for that!",
                "Eh, he was adopted",
                "He's dead! Again!",
                "He's deader than before!",
                "You broke him!"
        ));

        configuration.addDefault("ZombieParents.ZombieDialog", Arrays.asList(
                "You're embarrassing me!",
                "He's bullying me!",
                "He's the one picking on me!",
                "I can deal with this alone!",
                "Leave me alone, I got this!",
                "Stop following me around!",
                "God this is so embarassing!",
                "He took my lunch money!",
                "He's bullying me!"
        ));

        configuration.addDefault("ZombieParents.ZombieDadDialog", Arrays.asList(
                "Get away from my son!",
                "Stand up for yourself son!",
                "I'll deal with him!",
                "Stop picking on my son!",
                "Why are you doing this?",
                "I'll talk to your parents!",
                "You go kiddo!",
                "Show him who's boss kiddo!",
                "Nice punch kiddo!"
        ));

        configuration.addDefault("ZombieParents.ZombieMomDialog", Arrays.asList(
                "Hands off my child!",
                "Are you hurt sweety?",
                "Did he hurt you sweety?",
                "Let me see that booboo sweety",
                "I'll talk to his parents!",
                "You forgot your jacket sweety!",
                "Posture, sweetheart",
                "Break it up!",
                "Stop this!",
                "Did you take out the garbage?"
        ));

        configuration.options().copyDefaults(true);
        UnusedNodeHandler.clearNodes(configuration);
        customConfigLoader.saveDefaultCustomConfig(CONFIG_NAME);
        customConfigLoader.saveCustomConfig(CONFIG_NAME);

    }

}
