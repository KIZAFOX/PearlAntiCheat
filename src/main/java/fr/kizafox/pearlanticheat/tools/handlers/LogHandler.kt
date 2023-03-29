package fr.kizafox.pearlanticheat.tools.handlers

import fr.kizafox.pearlanticheat.PearlAntiCheat
import fr.kizafox.pearlanticheat.tools.Settings
import fr.kizafox.pearlanticheat.tools.User
import fr.kizafox.pearlanticheat.tools.checks.CheckResult
import fr.kizafox.pearlanticheat.tools.database.Account
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class LogHandler {

    companion object{
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

                user.getPlayer().kickPlayer(ChatHandler.translate(PearlAntiCheat.instance!!.config.getString("kick-message"))
                    .replace("%kicker%", "Admin")
                    .replace("%date%", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                    .replace("%type%", checkResult.type.typeName))
            }
        }
    }
}