package dev.pilati.enhancedwindcharge;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.WindCharge;
import org.bukkit.inventory.ItemStack;

import dev.pilati.enhancedwindcharge.properties.PropertiesUtil;
import dev.pilati.enhancedwindcharge.properties.WindChargeProperties;

public class WindChargeUtil {

    public static void giveWindChargeItem(WindChargeProperties properties, ArrayList<Player> targets, int amount) {
        ItemStack windChargeItem = new ItemStack(Material.WIND_CHARGE);
        windChargeItem.setAmount(amount);
        
        PropertiesUtil.setAllProperties(windChargeItem, properties);

        targets.forEach(target -> target.getInventory().addItem(windChargeItem));
    }

    public static void launchWindCharge(Player player, ItemStack item) {
        WindCharge projectile = player.launchProjectile(
            WindCharge.class,
            player.getLocation().getDirection().normalize()
        );

        WindChargeProperties properties = PropertiesUtil.getProperties(item);
        
        projectile.setVelocity(
            projectile.getVelocity().multiply(
                properties.getSpeed()
            )
        );

        PropertiesUtil.setAllProperties(projectile, properties);

        if(properties.isShowParticles()) {
            new WindChargeParticlesTask(projectile, properties)
                .runTaskTimer(EnhancedWindCharge.getInstance(), 1, properties.getInterval());
        }
    }

    public static void explodeWindCharge(WindCharge windCharge) {
        WindChargeProperties properties = PropertiesUtil.getProperties(windCharge);

        windCharge.getWorld().createExplosion(
            windCharge.getLocation(),
            properties.getRadius(),
            false,
            false,
            windCharge
        );
    }
}
