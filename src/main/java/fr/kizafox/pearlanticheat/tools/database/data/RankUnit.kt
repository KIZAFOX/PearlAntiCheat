package fr.kizafox.pearlanticheat.tools.database.data

enum class RankUnit(val rankName: String) {

    Default("Default");

    fun getName(): String {
        return this.rankName
    }
}