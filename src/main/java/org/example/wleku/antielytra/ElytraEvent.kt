package org.example.wleku.antielytra

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent

class ElytraEvent : Listener {

    private val plugin = AntiElytra.getInstace()

    @EventHandler
    fun disableElytra(event: PlayerMoveEvent) {
        val player = event.player
        val world = player.world.name
        val worlds = plugin.config.getStringList("worlds")

        if (worlds.equals(world) && player.isGliding) {
            player.isGliding = false
            player.sendMessage(plugin.config.equals("messages.disableElytra").toString())
        }
    }

    @EventHandler
    fun onTeleported(event: PlayerChangedWorldEvent) {
        val player = event.player
        val world = player.world.name
        val worlds = plugin.config.getStringList("worlds")

        if (worlds.equals(world)) {
            player.sendMessage(plugin.config.equals("messages.disableWorld").toString())
        }
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val world = player.world.name
        val worlds = plugin.config.getStringList("worlds")

        if (worlds.equals(world)) {
            player.sendMessage(plugin.config.equals("messages.disableWorld").toString())
        }
    }
}