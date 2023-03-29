package fr.kizafox.pearlanticheat.managers.listeners

import fr.kizafox.pearlanticheat.PearlAntiCheat
import fr.kizafox.pearlanticheat.tools.Distance
import fr.kizafox.pearlanticheat.tools.User
import fr.kizafox.pearlanticheat.tools.checks.CheckResult
import fr.kizafox.pearlanticheat.tools.checks.movement.NoSlowDown
import fr.kizafox.pearlanticheat.tools.checks.movement.SpeedCheck
import fr.kizafox.pearlanticheat.tools.handlers.LogHandler
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent


object MoveListener : Listener {

    private val instance = PearlAntiCheat.Companion

    @EventHandler
    fun onMove(event: PlayerMoveEvent){
        val user: User = instance.USERS[event.player.uniqueId]!!
        val distance = Distance(event)

        val speed: CheckResult = SpeedCheck.runCheck(distance, user)
        val noSlowDown: CheckResult = NoSlowDown.runCheck(distance, user)

        NoSlowDown.registerMove(distance, user)

        if(speed.failed()){
            event.setTo(event.from)
            LogHandler.log(speed, user)
        }else if(noSlowDown.failed()){
            event.setTo(event.from)
            LogHandler.log(noSlowDown, user)
        }
    }
}
