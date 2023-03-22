package fr.kizafox.pearlanticheat.tools.checks.player

import fr.kizafox.pearlanticheat.tools.Settings
import fr.kizafox.pearlanticheat.tools.User
import fr.kizafox.pearlanticheat.tools.checks.CheckResult
import fr.kizafox.pearlanticheat.tools.checks.CheckType
import fr.kizafox.pearlanticheat.tools.checks.Level

class FastUse {
    companion object{
        fun runBow(user: User): CheckResult{
            val now: Long = System.currentTimeMillis()

            if(now - user.getBowStart() < Settings.BOW_MIN){
                CheckResult(Level.DEFINITELY, "FastBow", CheckType.FASTUSE)
            }
            return CheckResult(Level.PASSED, null, CheckType.FASTUSE)
        }

        fun runFood(user: User): CheckResult{
            val now: Long = System.currentTimeMillis()

            if(now - user.getFoodStarting() < Settings.FOOD_MIN){
                CheckResult(Level.DEFINITELY, "FastEat", CheckType.FASTUSE)
            }
            return CheckResult(Level.PASSED, null, CheckType.FASTUSE)
        }
    }

}