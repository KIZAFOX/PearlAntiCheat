package fr.kizafox.pearlanticheat.tools.database

import fr.kizafox.pearlanticheat.PearlAntiCheat
import fr.kizafox.pearlanticheat.tools.database.data.RankUnit
import org.bukkit.entity.Player
import java.sql.ResultSet

class Account(private val player: Player) {

    companion object{
        val mySQL = PearlAntiCheat.mySQL
    }

    fun createAccount(){
        mySQL.dataSource().connection.use { connection ->
            connection.prepareStatement("INSERT INTO users (uuid, name, rank) VALUES (?, ?, ?)").use { statement ->
                statement.setString(1, player.uniqueId.toString())
                statement.setString(2, player.name)
                statement.setString(3, RankUnit.Default.rankName)

                statement.executeUpdate()
                statement.close()
            }
        }
    }

    fun hasAccount(): Boolean{
        mySQL.dataSource().connection.use { connection ->
            connection.prepareStatement("SELECT * FROM users WHERE uuid='${player.uniqueId}'").use { statement ->
                val result: ResultSet = statement.executeQuery()

                if(result.next()){
                    return result.getString("uuid") != null
                }
            }
        }
        return false
    }

    fun getUserName(): String{
        mySQL.dataSource().connection.use { connection ->
            connection.prepareStatement("SELECT * FROM users WHERE uuid='${player.uniqueId}'").use { statement ->
                val result: ResultSet = statement.executeQuery()

                if(result.next()){
                    return result.getString("name")
                }
            }
        }
        return ""
    }

    fun getRank(): Any? {
        mySQL.dataSource().connection.use { connection ->
            connection.prepareStatement("SELECT * FROM users WHERE uuid='${player.uniqueId}'").use { statement ->
                val result: ResultSet = statement.executeQuery()

                if(result.next()){
                    println(result.getString("rank"))
                    return result.getString("rank")
                }
            }
        }
        return RankUnit.Default
    }

}