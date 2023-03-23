package fr.kizafox.pearlanticheat.tools.database.data

import java.util.UUID

class UserData(private val uuid: UUID, private val name: String, private val rank: Any?, private val reportCount: Int) {

    fun getUUID(): UUID {
        return uuid
    }

    fun getName(): String {
        return name
    }

    fun getRank(): RankUnit {
        return rank as RankUnit
    }

    fun getReportCont(): Int {
        return reportCount
    }
}