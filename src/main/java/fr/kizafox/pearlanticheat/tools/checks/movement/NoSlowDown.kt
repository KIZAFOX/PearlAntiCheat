package fr.kizafox.pearlanticheat.tools.checks.movement

import fr.kizafox.pearlanticheat.tools.Distance
import fr.kizafox.pearlanticheat.tools.Settings
import fr.kizafox.pearlanticheat.tools.User
import fr.kizafox.pearlanticheat.tools.checks.CheckResult
import fr.kizafox.pearlanticheat.tools.checks.CheckType
import fr.kizafox.pearlanticheat.tools.checks.Level
import org.bukkit.ChatColor

class NoSlowDown {
    companion object {
        fun registerMove(distance: Distance, user: User) {
            val xzDist: Double = if (distance.getXDifference() > distance.getZDifference()) distance.getXDifference() else distance.getZDifference()

            if(xzDist > Settings.MAX_XZ_EATING_SPEED && System.currentTimeMillis() - user.getFoodStarting() > 1200){
                user.addInvalidFoodEatableCount()
            }
        }

        fun runCheck(distance: Distance, user: User): CheckResult{
            val xzDist: Double = if (distance.getXDifference() > distance.getZDifference()) distance.getXDifference() else distance.getZDifference()

            if(user.getPlayer().isBlocking){
                if(xzDist > Settings.MAX_XZ_BLOCKING_SPEED){
                    return CheckResult(Level.DEFINITELY, "(whilst blocking)", CheckType.NOSLOWDOWN)
                }
            }
            return CheckResult(Level.PASSED, null, CheckType.NOSLOWDOWN)
        }
    }
}
