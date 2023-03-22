package fr.kizafox.pearlanticheat.tools.database.data

import java.util.UUID

class UserData(private val id: Int, private val uuid: UUID, private val name: String) {

    fun getId(): Int {
        return id
    }

    fun getUUID(): UUID {
        return uuid
    }

    fun getName(): String {
        return name
    }
}