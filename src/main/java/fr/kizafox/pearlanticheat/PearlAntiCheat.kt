package fr.kizafox.pearlanticheat

import fr.kizafox.pearlanticheat.managers.Managers
import fr.kizafox.pearlanticheat.tools.User
import fr.kizafox.pearlanticheat.tools.checks.CheckResult
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.UUID

/**
 * Welcome to the main class of this plugin.
 * @author: KIZAFOX
 */
class PearlAntiCheat : JavaPlugin() {

    companion object {
        var instance: PearlAntiCheat? = null
        val USERS = HashMap<UUID, User>()

        fun log(checkResult: CheckResult, user: User) {
            val message = "${ChatColor.DARK_GRAY}[${ChatColor.RED}PAC${ChatColor.DARK_GRAY}] ${ChatColor.BLUE}${user.getPlayer().name} ${ChatColor.WHITE}has violated check ${ChatColor.RED}${checkResult.type.typeName} ${ChatColor.WHITE}! ${ChatColor.RED}${checkResult.message}"
            Bukkit.getOnlinePlayers().forEach { players ->
                run {
                    if(players.isOp){
                        players.sendMessage(message)
                    }
                }
            }
            Bukkit.getConsoleSender().sendMessage(message)
        }

        fun getUser(player: Player): User {
            for(users: User in USERS.values){
                if(users.getPlayer() == player || users.getPlayer().uniqueId == player.uniqueId){
                    return users;
                }
            }
            return null!!
        }
    }

    override fun onEnable() {
        instance = this

        Managers(this)

        Bukkit.getOnlinePlayers().forEach { players ->
            run {
                USERS[players.uniqueId] = User(players)
            }
        }

        logger.info("Plugin enabled !")
    }

    override fun onDisable() {
        logger.info("Plugin disabled !")
    }
}
