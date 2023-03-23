package fr.kizafox.pearlanticheat.tools.database

import fr.kizafox.pearlanticheat.PearlAntiCheat
import fr.kizafox.pearlanticheat.tools.database.data.RankUnit
import org.bukkit.entity.Player
import java.sql.ResultSet
import java.util.UUID

class Account(private val player: Player) {

    companion object{
        val mySQL = PearlAntiCheat.mySQL
    }

    fun createAccount(){
        if(!this.hasAccount()){
            mySQL.dataSource().connection.use { connection ->
                connection.prepareStatement("INSERT INTO users (uuid, name, rank, reportCount) VALUES (?, ?, ?, ?)").use { statement ->
                    statement.setString(1, player.uniqueId.toString())
                    statement.setString(2, player.name)
                    statement.setString(3, RankUnit.Default.rankName)
                    statement.setInt(4, 0)

                    statement.executeUpdate()
                    statement.close()
                }
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

                statement.close()
            }
        }
        return false
    }

    fun getUUID(): UUID? {
        mySQL.dataSource().connection.use { connection ->
            connection.prepareStatement("SELECT * FROM users WHERE uuid='${player.uniqueId}'").use { statement ->
                val result: ResultSet = statement.executeQuery()

                if(result.next()){
                    return UUID.fromString(result.getString("uuid"))
                }

                statement.close()
            }
        }
        return null
    }

    fun getUserName(): String{
        mySQL.dataSource().connection.use { connection ->
            connection.prepareStatement("SELECT * FROM users WHERE uuid='${player.uniqueId}'").use { statement ->
                val result: ResultSet = statement.executeQuery()

                if(result.next()){
                    return result.getString("name")
                }

                statement.close()
            }
        }
        return ""
    }

    fun getRank(): Any? {
        mySQL.dataSource().connection.use { connection ->
            connection.prepareStatement("SELECT * FROM users WHERE uuid='${player.uniqueId}'").use { statement ->
                val result: ResultSet = statement.executeQuery()

                if(result.next()){
                    return result.getString("rank")
                }
            }
        }
        return RankUnit.Default
    }

    fun setRank(rank: RankUnit){
        mySQL.dataSource().connection.use { connection ->
            connection.prepareStatement("UPDATE users SET rank=? WHERE uuid=?").use { statement ->
                statement.setString(1, rank.getName())
                statement.setString(2, player.uniqueId.toString())

                statement.executeUpdate()
                statement.close()
            }
        }
    }

    fun getReportCount(): Int{
        mySQL.dataSource().connection.use { connection ->
            connection.prepareStatement("SELECT reportCount FROM users WHERE uuid=?").use { statement ->
                statement.setString(1, player.uniqueId.toString())

                val result: ResultSet = statement.executeQuery()
                var reportCount = 0

                if(result.next()){
                    reportCount = result.getInt("reportCount")
                }

                return reportCount
            }
        }
    }

    fun addReportCount(reportCount: Int){
        mySQL.dataSource().connection.use { connection ->
            connection.prepareStatement("UPDATE users SET reportCount=reportCount+? WHERE uuid=?").use { statement ->
                statement.setInt(1, reportCount)
                statement.setString(2, player.uniqueId.toString())

                statement.executeUpdate()
                statement.close()
            }
        }
    }
}