package fr.kizafox.pearlanticheat.managers.listeners

import fr.kizafox.pearlanticheat.PearlAntiCheat
import fr.kizafox.pearlanticheat.tools.Settings
import fr.kizafox.pearlanticheat.tools.User
import fr.kizafox.pearlanticheat.tools.checks.CheckResult
import fr.kizafox.pearlanticheat.tools.checks.CheckType
import fr.kizafox.pearlanticheat.tools.checks.Level
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent


object PlayerListeners : Listener {

    private val instance = PearlAntiCheat.Companion

    @EventHandler
    fun onLogin(event: PlayerJoinEvent){
        val player = event.player
        instance.USERS[player.uniqueId] = User(player)
    }

    @EventHandler
    fun onLogout(event: PlayerQuitEvent){
        val player = event.player
        instance.USERS.remove(player.uniqueId)
    }

    @EventHandler
    fun onInteract(event: PlayerInteractEvent){
        val player = event.player
        val user: User = instance.getUser(player)

        if(event.action == Action.RIGHT_CLICK_AIR ||
            event.action == Action.RIGHT_CLICK_BLOCK &&
            Settings.FOODS.contains(player.itemInHand.type)){
            user.setFoodStarting()
            user.resetInvalidFoodEatableCount()
        }
    }

    @EventHandler
    fun onFoodLevelChange(event: FoodLevelChangeEvent){
        val player = event.entity
        val user: User = instance.USERS[player.uniqueId]!!

        if(Settings.FOODS.contains(user.getPlayer().itemInHand.type)){
            if(user.getInvalidFoodEatableCount() != 0){
                event.isCancelled = true

                user.getPlayer().teleport(user.getFoodStartLocation())

                instance.log(CheckResult(Level.DEFINITELY, "(${user.getInvalidFoodEatableCount()} times in a row)", CheckType.NOSLOWDOWN), user)
            }
        }
    }
}