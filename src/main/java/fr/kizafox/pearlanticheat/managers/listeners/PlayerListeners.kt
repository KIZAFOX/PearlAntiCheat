package fr.kizafox.pearlanticheat.managers.listeners

import fr.kizafox.pearlanticheat.PearlAntiCheat
import fr.kizafox.pearlanticheat.tools.runnable.KBukkitRunnable
import fr.kizafox.pearlanticheat.tools.User
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent


object PlayerListeners : Listener {

    private val instance = PearlAntiCheat.Companion

    @EventHandler
    fun onLogin(event: PlayerJoinEvent){
        val player = event.player
        instance.USER[player.uniqueId] = User(player)
    }

    @EventHandler
    fun onLogout(event: PlayerQuitEvent){
        val player = event.player
        instance.USER.remove(player.uniqueId)
    }
}