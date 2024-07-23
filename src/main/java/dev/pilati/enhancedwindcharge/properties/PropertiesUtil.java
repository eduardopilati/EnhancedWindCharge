package dev.pilati.enhancedwindcharge.properties;

import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.WindCharge;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import dev.pilati.enhancedwindcharge.EnhancedWindCharge;

public class PropertiesUtil {

    private static NamespacedKey getKey(String key) {
        return new NamespacedKey(EnhancedWindCharge.getInstance(), key);
    }

    public static boolean hasBoolean(PersistentDataContainer dataContainer, String key) {
        return dataContainer.has(getKey(key), PersistentDataType.BOOLEAN);
    }

    public static void setInt(PersistentDataContainer dataContainer, String key, int value) {
        dataContainer.set(getKey(key), PersistentDataType.INTEGER, value);
    }

    public static void setFloat(PersistentDataContainer dataContainer, String key, float value) {
        dataContainer.set(getKey(key), PersistentDataType.FLOAT, value);
    }

    public static void setBoolean(PersistentDataContainer dataContainer, String key, boolean value) {
        dataContainer.set(getKey(key), PersistentDataType.BOOLEAN, value );
    }

    public static void setString(PersistentDataContainer dataContainer, String key, String value) {
        dataContainer.set(getKey(key), PersistentDataType.STRING, value);
    }

    public static int getInt(PersistentDataContainer dataContainer, String key) {
        return dataContainer.get(getKey(key), PersistentDataType.INTEGER);
    }

    public static float getFloat(PersistentDataContainer dataContainer, String key) {
        return dataContainer.get(getKey(key), PersistentDataType.FLOAT);
    }
 
    public static boolean getBoolean(PersistentDataContainer dataContainer, String key) {
        return dataContainer.get(getKey(key), PersistentDataType.BOOLEAN);
    }

    public static String getString(PersistentDataContainer dataContainer, String key) {
        return dataContainer.get(getKey(key), PersistentDataType.STRING);
    }

    public static void setItemFlag(PersistentDataContainer dataContainer) {
        setBoolean(dataContainer, PropertiesKeys.FLAG.getKey(), true);
    }

    public static boolean isFlagged(PersistentDataContainer dataContainer) {
        return hasBoolean(dataContainer, PropertiesKeys.FLAG.getKey()) &&
                    getBoolean(dataContainer, PropertiesKeys.FLAG.getKey());
    }

    public static void setAllProperties(PersistentDataContainer dataContainer, WindChargeProperties properties) {
        setItemFlag(dataContainer);
        setFloat(dataContainer, PropertiesKeys.STRENGTH.getKey(), properties.getStrength());
        setFloat(dataContainer, PropertiesKeys.SPEED.getKey(), properties.getSpeed());
        setFloat(dataContainer, PropertiesKeys.RADIUS.getKey(), properties.getRadius());
        setBoolean(dataContainer, PropertiesKeys.SHOW_PARTICLES.getKey(), properties.isShowParticles());
        setString(dataContainer, PropertiesKeys.PARTICLE_TYPE.getKey(), properties.getParticleType().name());
        setInt(dataContainer, PropertiesKeys.PARTICLE_AMOUNT.getKey(), properties.getAmount());
        setInt(dataContainer, PropertiesKeys.PARTICLE_INTERVAL.getKey(), properties.getInterval());
    }

    public static WindChargeProperties getProperties(PersistentDataContainer dataContainer) {
        WindChargeProperties properties = new WindChargeProperties();
        properties.setStrength(getFloat(dataContainer, PropertiesKeys.STRENGTH.getKey()));
        properties.setSpeed(getFloat(dataContainer, PropertiesKeys.SPEED.getKey()));
        properties.setRadius(getFloat(dataContainer, PropertiesKeys.RADIUS.getKey()));
        properties.setShowParticles(getBoolean(dataContainer, PropertiesKeys.SHOW_PARTICLES.getKey()));
        properties.setParticleType(Particle.valueOf(getString(dataContainer, PropertiesKeys.PARTICLE_TYPE.getKey())));
        properties.setAmount(getInt(dataContainer, PropertiesKeys.PARTICLE_AMOUNT.getKey()));
        properties.setInterval(getInt(dataContainer, PropertiesKeys.PARTICLE_INTERVAL.getKey()));
        return properties;
    }

    public static boolean isFlagged(ItemStack item) {
        return isFlagged(item.getItemMeta().getPersistentDataContainer());
    }

    public static void setAllProperties(ItemStack item, WindChargeProperties properties) {
        ItemMeta itemMeta = item.getItemMeta();
        setAllProperties(itemMeta.getPersistentDataContainer(), properties);
        item.setItemMeta(itemMeta);
    }

    public static WindChargeProperties getProperties(ItemStack item) {
        return getProperties(item.getItemMeta().getPersistentDataContainer());
    }

    public static boolean isFlagged(WindCharge item) {
        return isFlagged(item.getPersistentDataContainer());
    }

    public static void setAllProperties(WindCharge windCharge, WindChargeProperties properties) {
        setAllProperties(windCharge.getPersistentDataContainer(), properties);
    }

    public static WindChargeProperties getProperties(WindCharge windCharge) {
        return getProperties(windCharge.getPersistentDataContainer());
    }
}
