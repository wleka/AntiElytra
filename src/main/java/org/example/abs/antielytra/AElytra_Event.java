package org.example.abs.antielytra;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class AElytra_Event implements Listener {

    private final AntiElytra plugin;
    private final FileConfiguration config;
    private final List<String> worlds;

    public AElytra_Event() {
        this.plugin = AntiElytra.getInstace();
        this.config = plugin.getConfig();
        this.worlds = config.getStringList("worlds");
    }

    @EventHandler
    public void disableElytra(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        String world = p.getWorld().getName();

        if (worlds.contains(world) && p.isGliding()) {
            p.setGliding(false);
            if (config.contains("messages.no_elytra")) {
                String msg = config.getString("messages.no_elytra", "§cElytra is disabled in this world!");
                p.sendMessage(msg);
            }
        }
    }

    @EventHandler
    public void onTeleported(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        String world = p.getWorld().getName();

        if (worlds.contains(world) && config.contains("messages.disableWorld")) {
            String msg = config.getString("messages.disableWorld", "§cElytra is not allowed here!");
            p.sendMessage(msg);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String world = p.getWorld().getName();

        if (worlds.contains(world) && config.contains("messages.disableWorld")) {
            String msg = config.getString("messages.disableWorld", "§cElytra is not allowed here!");
            p.sendMessage(msg);
        }
    }
}
