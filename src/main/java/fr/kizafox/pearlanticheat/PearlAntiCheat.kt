package fr.kizafox.pearlanticheat

import fr.kizafox.pearlanticheat.managers.Managers
import fr.kizafox.pearlanticheat.tools.Settings
import fr.kizafox.pearlanticheat.tools.User
import fr.kizafox.pearlanticheat.tools.checks.CheckResult
import fr.kizafox.pearlanticheat.tools.database.Account
import fr.kizafox.pearlanticheat.tools.database.MySQL
import fr.kizafox.pearlanticheat.tools.database.data.UserData
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

        fun log(checkResult: CheckResult, user: User) {
            user.addWarning(checkResult.type)
            val message = "${ChatColor.DARK_GRAY}[${ChatColor.RED}PAC${ChatColor.DARK_GRAY}] ${ChatColor.BLUE}${user.getPlayer().name} ${ChatColor.WHITE}has violated check ${ChatColor.RED}${checkResult.type.typeName} ${ChatColor.WHITE}! ${ChatColor.RED}${checkResult.message}"
            Bukkit.getOnlinePlayers().forEach { players ->
                run {
                    if(players.isOp){
                        players.sendMessage(message)
                    }
                }
            }
            Bukkit.getConsoleSender().sendMessage(message)

            if(user.getWarnings(checkResult.type) > Settings.MAX_WARNINGS){
                Account(user.getPlayer()).addReportCount(1)

                user.getPlayer().kickPlayer(
                    "${ChatColor.RED}${ChatColor.BOLD}(!)${ChatColor.RESET}${ChatColor.RED} You have been kicked!\n\n" +
                            "${ChatColor.GOLD}* ${ChatColor.YELLOW}Kicked by ${ChatColor.GRAY}Admin\n" +
                            "${ChatColor.GOLD}* ${ChatColor.YELLOW}Kicked on ${ChatColor.GRAY}${LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}\n" +
                            "${ChatColor.GOLD}* ${ChatColor.YELLOW}Reason ${ChatColor.DARK_RED}${checkResult.type.typeName} ${ChatColor.RED}(Hack)\n\n" +
                    "${ChatColor.GRAY}${ChatColor.ITALIC}AntiCheat made by @KIZAFOX !"
                )
            }
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

        mySQL.init("default", "jdbc:mysql://localhost/pearlanticheat?serverTimezone=UTC", "root", "")

        logger.info("Plugin enabled !")
    }

    override fun onDisable() {
        mySQL.close("default")
        logger.info("Plugin disabled !")
    }
}
