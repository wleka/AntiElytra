package org.example.wleku.antielytra

import net.kyori.adventure.text.Component
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent

class ElytraEvent : Listener {

    lateinit var config: FileConfiguration
    lateinit var worlds: List<String>
    var plugin = AElytraMain.instance

    private val disabledWorld: String = plugin.config.getString("messages.disableWorld").toString()
    private val disabled: String = plugin.config.getString("messages.disableElytra").toString()

    @EventHandler
    fun disableElytra(event: PlayerMoveEvent) {
        val player = event.player
        val world = player.world.name

        plugin.saveDefaultConfig()
        this.config = plugin.config
        this.worlds = config.getStringList("worlds")

        if (player is Player) {
            if (worlds.contains(world) && player.isGliding) {
                event.isCancelled = true
                player.isGliding = false
                player.sendActionBar(disabled)
            }
        }
    }

    @EventHandler
    fun onTeleported(event: PlayerChangedWorldEvent) {
        val player = event.player
        val world = player.world.name

        plugin.saveDefaultConfig()
        val configs = plugin.config
        val worlds = configs.getStringList("worlds")

        if (worlds.contains(world)) {
            player.sendMessage(disabledWorld)
        }
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val world = player.world.name

        plugin.saveDefaultConfig()
        val configs = plugin.config
        val worlds = configs.getStringList("worlds")

        if (worlds.contains(world)) {
            player.sendMessage(disabledWorld)
        }
    }
}