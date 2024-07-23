package dev.pilati.enhancedwindcharge;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.WindCharge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import dev.pilati.enhancedwindcharge.messages.MessageUtil;
import dev.pilati.enhancedwindcharge.properties.PropertiesUtil;
import dev.pilati.enhancedwindcharge.properties.WindChargeProperties;

public class WindChargeEvents implements Listener{

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (!event.hasItem()) {
            return;
        }

        if (event.getItem().getType() != Material.WIND_CHARGE) {
            return;
        }

        ItemStack item = event.getItem();
        if(!PropertiesUtil.isFlagged(item)) {
            return;
        }

        event.setCancelled(true);

        if (!event.getPlayer().hasPermission("enhancedwindcharge.use")) {
            event.getPlayer().sendMessage(
                MessageUtil.getMessage("item.noUsePermission")
            );
            return;
        }
        
        WindChargeUtil.launchWindCharge(event.getPlayer(), item);

        if(event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.getItem().setAmount(event.getItem().getAmount() -1);
        }
    }

    @EventHandler
    public void onEntityExplodeEvent(EntityExplodeEvent event) {
        if (!(event.getEntity() instanceof WindCharge windCharge)) {
            return;
        }

        if (!PropertiesUtil.isFlagged((WindCharge) event.getEntity())) {
            return;
        }

        WindChargeUtil.explodeWindCharge(windCharge);

        event.setCancelled(true);
        windCharge.remove();
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof WindCharge windCharge)) {
            return;
        }

        if (!PropertiesUtil.isFlagged(windCharge)) {
            return;
        }

        event.setCancelled(true);

        if(!(event.getEntity() instanceof LivingEntity)) {
            return;
        }

        WindChargeProperties properties = PropertiesUtil.getProperties(windCharge);
        
        Vector entityLocation = event.getEntity().getLocation().toVector();
        Vector damagerLocation = event.getDamager().getLocation().toVector();
        
        Vector knockback = damagerLocation.subtract(entityLocation).normalize().multiply(properties.getStrength() * -1);
        event.getEntity().setVelocity(knockback);
    }
}
