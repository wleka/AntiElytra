package org.example.wleku.antielytra

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.example.wleku.antielytra.javaclass.AElytra_Commands
import org.example.wleku.antielytra.javaclass.AElytra_tabcompleter

class AElytraMain : JavaPlugin() {

    companion object {
        lateinit var instance: AElytraMain
    }

    override fun onEnable() {

        instance = this

        Bukkit.getLogger().info("AntiElytra has been enabled")
        Bukkit.getPluginManager().registerEvents(ElytraEvent(), this)

        saveDefaultConfig()

        getCommand("antielytra")?.setExecutor(AElytra_Commands())
        getCommand("antielytra")?.tabCompleter = AElytra_tabcompleter()
    }

    override fun onDisable() {

    }
}