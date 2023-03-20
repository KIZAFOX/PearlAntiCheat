package fr.kizafox.pearlanticheat.managers

import fr.kizafox.pearlanticheat.PearlAntiCheat
import fr.kizafox.pearlanticheat.managers.commands.CommandPAC
import fr.kizafox.pearlanticheat.managers.listeners.MoveListener
import fr.kizafox.pearlanticheat.managers.listeners.PlayerListeners

class Managers(instance: PearlAntiCheat) {
    init {
        val pluginManager = instance.server.pluginManager

        pluginManager.registerEvents(PlayerListeners, instance)
        pluginManager.registerEvents(MoveListener, instance)

        instance.getCommand("pac")?.setExecutor(CommandPAC)
    }
}