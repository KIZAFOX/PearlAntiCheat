package fr.kizafox.pearlanticheat.tools.checks.movement

import fr.kizafox.pearlanticheat.tools.Distance
import fr.kizafox.pearlanticheat.tools.Settings
import fr.kizafox.pearlanticheat.tools.User
import fr.kizafox.pearlanticheat.tools.checks.CheckResult
import fr.kizafox.pearlanticheat.tools.checks.CheckType
import fr.kizafox.pearlanticheat.tools.checks.Level
import kotlin.math.round


class SpeedCheck {

    companion object{
        fun runCheck(distance: Distance, user: User?): CheckResult {
            val xzSpeed: Double = if (distance.getXDifference() > distance.getZDifference()) distance.getXDifference() else distance.getZDifference()
            val exactSpeed: Double = round(xzSpeed * 1000.0) / 1000.0

            if ((if (distance.getXDifference() > distance.getZDifference()) distance.getXDifference() else distance.getZDifference()) > Settings.MAX_XZ_SPEED) {
                return CheckResult(Level.DEFINITELY, "His speed=${exactSpeed}", CheckType.SPEED)
            }
            return CheckResult(Level.PASSED, null, CheckType.SPEED)
        }
    }
}