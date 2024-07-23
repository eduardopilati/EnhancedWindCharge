package dev.pilati.enhancedwindcharge;

import org.bukkit.plugin.java.JavaPlugin;

import dev.pilati.enhancedwindcharge.command.WindChargeCommand;
import dev.pilati.enhancedwindcharge.command.WindChargeTabCompleter;

public final class EnhancedWindCharge extends JavaPlugin {

    private static EnhancedWindCharge instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        getCommand("windcharge").setExecutor(new WindChargeCommand());
        getCommand("windcharge").setTabCompleter(new WindChargeTabCompleter());

        getServer().getPluginManager().registerEvents(new WindChargeEvents(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static EnhancedWindCharge getInstance() {
        return instance;
    }
}
