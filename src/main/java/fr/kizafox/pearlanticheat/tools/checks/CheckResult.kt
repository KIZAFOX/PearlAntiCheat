package fr.kizafox.pearlanticheat.tools.checks

class CheckResult(private val level: Level, val message: String?, val type: CheckType) {

    fun failed(): Boolean {
        return level != Level.PASSED;
    }
}