package fr.kizafox.pearlanticheat.managers.commands

import fr.kizafox.pearlanticheat.PearlAntiCheat
import fr.kizafox.pearlanticheat.tools.database.Account
import fr.kizafox.pearlanticheat.tools.database.data.RankUnit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object CommandPAC : CommandExecutor{

    private val instance = PearlAntiCheat.Companion

    /**
     * Executes the given command, returning its success.
     * <br></br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return true if a valid command, otherwise false
     */
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(sender !is Player){
            sender.sendMessage("${ChatColor.RED}Only a player can execute this command!")
            return true
        }

        val player: Player = sender
        val account = Account(player)

        if(!player.isOp && account.getRank() != RankUnit.Moderator){
            player.sendMessage("${ChatColor.RED}You don't have access to this command!")
            return true
        }

        if (args != null) {
            if(args.size == 1 && args[0] == "reload"){
                instance.instance!!.reloadConfig()

                player.sendMessage("${ChatColor.GREEN}The plugin has reloaded successfully!")
            }else{
                player.sendMessage("${ChatColor.RED}/pac (reload)")
            }
        }

        return false
    }

}
