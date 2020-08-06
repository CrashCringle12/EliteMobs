package com.magmaguy.elitemobs.powers.bosspowers;

import com.magmaguy.elitemobs.MetadataHandler;
import com.magmaguy.elitemobs.api.EliteMobDamagedByPlayerEvent;
import com.magmaguy.elitemobs.config.powers.PowersConfig;
import com.magmaguy.elitemobs.custombosses.CustomBossEntity;
import com.magmaguy.elitemobs.mobconstructor.EliteMobEntity;
import com.magmaguy.elitemobs.powers.BossPower;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class SummonAssistant extends BossPower implements Listener {

    public SummonAssistant() {
        super(PowersConfig.getPower("summon_assistant.yml"));
    }

    @EventHandler
    public void onDamage(EliteMobDamagedByPlayerEvent event) {
        if (event.isCancelled()) return;
        SummonAssistant summonAssistant = (SummonAssistant) event.getEliteMobEntity().getPower(this);
        if (summonAssistant == null) return;
        if (!eventIsValid(event, summonAssistant)) return;
        if (ThreadLocalRandom.current().nextDouble() > 0.25) return;

        summonAssistant.doCooldown(20 * 20, event.getEliteMobEntity());
        doSummonParticles(event.getEliteMobEntity());
    }

    private void doSummonParticles(EliteMobEntity eliteMobEntity) {
        eliteMobEntity.getLivingEntity().setAI(false);
        new BukkitRunnable() {
            int counter = 0;

            @Override
            public void run() {
                counter++;
                eliteMobEntity.getLivingEntity().getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE,
                        eliteMobEntity.getLivingEntity().getLocation().add(new Vector(0, 1, 0)), 50, 0.01, 0.01, 0.01, 1);
                if (counter < 20 * 3) return;
                cancel();
                doSummon(eliteMobEntity);
                eliteMobEntity.getLivingEntity().setAI(true);
            }
        }.runTaskTimer(MetadataHandler.PLUGIN, 0, 1);

    }

    private void doSummon(EliteMobEntity eliteMobEntity) {

        for (int i = 0; i < 1; i++) {
            Location spawnLocation = eliteMobEntity.getLivingEntity().getLocation();

            CustomBossEntity customBossEntity = CustomBossEntity.constructCustomBoss("pam.yml", spawnLocation, eliteMobEntity.getLevel());

            double x = ThreadLocalRandom.current().nextDouble() - 0.5;
            double z = ThreadLocalRandom.current().nextDouble() - 0.5;

            customBossEntity.getLivingEntity().setVelocity(new Vector(x, 0.5, z));
        }

    }

}
