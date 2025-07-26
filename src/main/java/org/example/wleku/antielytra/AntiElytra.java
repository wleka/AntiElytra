package org.example.wleku.antielytra;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class AntiElytra extends JavaPlugin {

    private static AntiElytra instace;

    private FileConfiguration config;

    private List<String> worlds;

    @Override
    public void onEnable() {

        instace = this;

        Bukkit.getLogger().info("AntiElytra has been activated!");

        String activator = getConfig().getString("drop");

        // Bukkit.getPluginManager().registerEvents(new AElytra_Event(), this);
        Bukkit.getPluginManager().registerEvents(new ElytraEvent(), this);

        saveDefaultConfig();

        getCommand("antielytra").setExecutor(new AElytra_Commands());
        getCommand("antielytra").setTabCompleter(new AElytra_tabcompleter());
    }

    private void loadConfigValues() {
        saveDefaultConfig();
        config = getConfig();
        worlds = config.getStringList("worlds");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AntiElytra getInstace() {
        return instace;
    }
}
