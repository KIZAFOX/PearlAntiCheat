package fr.kizafox.pearlanticheat

import fr.kizafox.pearlanticheat.managers.Managers
import fr.kizafox.pearlanticheat.tools.User
import fr.kizafox.pearlanticheat.tools.database.Account
import fr.kizafox.pearlanticheat.tools.database.MySQL
import fr.kizafox.pearlanticheat.tools.database.data.UserData
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.UUID

/**
 * Welcome to the main class of this plugin.
 * Last update : 03/21/2023
 * @author: KIZAFOX
 */
class PearlAntiCheat : JavaPlugin() {

    companion object {
        var instance: PearlAntiCheat? = null
        val USERS = HashMap<UUID, User>()
        val USER_DATA = HashMap<Account, UserData>()

        val mySQL = MySQL()

        fun getUser(player: Player): User {
            for(users: User in USERS.values){
                if(users.getPlayer() == player || users.getPlayer().uniqueId == player.uniqueId){
                    return users
                }
            }
            return null!!
        }
    }

    override fun onEnable() {
        instance = this

        this.saveDefaultConfig()
        this.config.options().copyDefaults(true)

        Managers(this)

        Bukkit.getOnlinePlayers().forEach { players ->
            run {
                USERS[players.uniqueId] = User(players)
            }
        }

        mySQL.init("default", "jdbc:mysql://localhost/pearlanticheat?serverTimezone=UTC", "root", "")

        logger.info("Plugin enabled !")
    }

    override fun onDisable() {
        mySQL.close("default")
        logger.info("Plugin disabled !")
    }
}
