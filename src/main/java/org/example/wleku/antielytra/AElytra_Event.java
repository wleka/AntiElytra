package org.example.wleku.antielytra;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class AElytra_Event implements Listener {

    private FileConfiguration config;

    private List<String> worlds;

    public static AntiElytra plugin = AntiElytra.getInstace();

    @EventHandler
    public void disableElytra(PlayerMoveEvent e) {

        Player p = e.getPlayer();
        String world = p.getWorld().getName();

        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();
        this.worlds = this.config.getStringList("worlds");

        if(worlds.contains(world) && p.isGliding()) {
            p.setGliding(false);
            p.sendMessage(plugin.getConfig().getString("messages.no_elytra"));
        }
    }

    @EventHandler
    public void onTeleported(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        String world = p.getWorld().getName();

        String msg = plugin.getConfig().getString("messages.disableWorld");

        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();
        this.worlds = this.config.getStringList("worlds");

        if(worlds.contains(world)) {
            p.sendMessage(msg);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String world = p.getWorld().getName();

        String msg = plugin.getConfig().getString("messages.disableWorld");

        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();
        this.worlds = this.config.getStringList("worlds");

        if(worlds.contains(world)) {
            p.sendMessage(msg);
        }
    }



}
