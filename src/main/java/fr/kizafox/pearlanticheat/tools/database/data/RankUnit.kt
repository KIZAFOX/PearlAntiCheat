package fr.kizafox.pearlanticheat.tools.database.data

enum class RankUnit(val rankName: String) {

    Default("Default"),
    Moderator("Moderator");

    fun getName(): String {
        return this.rankName
    }
}