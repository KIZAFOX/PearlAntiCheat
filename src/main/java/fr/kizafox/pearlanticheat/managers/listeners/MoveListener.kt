package fr.kizafox.pearlanticheat.managers.listeners

import fr.kizafox.pearlanticheat.PearlAntiCheat
import fr.kizafox.pearlanticheat.tools.Distance
import fr.kizafox.pearlanticheat.tools.User
import fr.kizafox.pearlanticheat.tools.checks.CheckResult
import fr.kizafox.pearlanticheat.tools.checks.movement.SpeedCheck
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent


object MoveListener : Listener {

    private val instance = PearlAntiCheat.Companion

    @EventHandler
    fun onMove(event: PlayerMoveEvent){
        val user: User = instance.USER[event.player.uniqueId]!!
        val distance = Distance(event)
        val speed: CheckResult = SpeedCheck.runCheck(distance, user)

        if(speed.failed()){
            event.setTo(event.from)
            instance.log(speed, user)
        }
    }
}
