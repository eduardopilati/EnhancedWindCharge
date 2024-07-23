package dev.pilati.enhancedwindcharge;

import org.bukkit.entity.WindCharge;
import org.bukkit.scheduler.BukkitRunnable;

import dev.pilati.enhancedwindcharge.properties.WindChargeProperties;

public class WindChargeParticlesTask extends BukkitRunnable {

    private WindCharge windCharge;

    private WindChargeProperties properties;

    WindChargeParticlesTask(WindCharge windCharge, WindChargeProperties properties) {
        this.windCharge = windCharge;
        this.properties = properties;
    }

    @Override
    public void run() {
        if (windCharge == null || windCharge.isDead()) {
            this.cancel();
            return;
        }

        windCharge.getWorld().spawnParticle(
            properties.getParticleType(),
            windCharge.getLocation(),
            properties.getAmount()
        );
    }
}
