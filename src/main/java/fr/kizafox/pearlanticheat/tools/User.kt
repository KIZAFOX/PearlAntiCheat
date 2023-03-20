package fr.kizafox.pearlanticheat.tools

import org.bukkit.entity.Player

class User(private val player: Player) {

    fun getPlayer(): Player{
        return player
    }
}