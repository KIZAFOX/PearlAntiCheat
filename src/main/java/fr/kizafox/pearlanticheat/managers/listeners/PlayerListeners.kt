package fr.kizafox.pearlanticheat.managers.listeners

import fr.kizafox.pearlanticheat.PearlAntiCheat
import fr.kizafox.pearlanticheat.tools.Settings
import fr.kizafox.pearlanticheat.tools.User
import fr.kizafox.pearlanticheat.tools.checks.CheckResult
import fr.kizafox.pearlanticheat.tools.checks.CheckType
import fr.kizafox.pearlanticheat.tools.checks.Level
import fr.kizafox.pearlanticheat.tools.checks.player.FastUse
import fr.kizafox.pearlanticheat.tools.database.Account
import fr.kizafox.pearlanticheat.tools.database.data.UserData
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemHeldEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent


object PlayerListeners : Listener {

    private var account: Account? = null;
    private val instance = PearlAntiCheat.Companion

    @EventHandler
    fun onLogin(event: PlayerJoinEvent){
        val player = event.player
        instance.USERS[player.uniqueId] = User(player)

        account = Account(player)

        account!!.createAccount()

        instance.USER_DATA[account!!] = UserData(account!!.getUUID()!!, account!!.getUserName(), account!!.getRank(), account!!.getReportCount())
    }

    @EventHandler
    fun onLogout(event: PlayerQuitEvent){
        val player = event.player
        instance.USERS.remove(player.uniqueId)
        instance.USER_DATA.remove(account!!)
    }

    @EventHandler
    fun onInteract(event: PlayerInteractEvent){
        val player = event.player
        val user: User = instance.getUser(player)

        if(event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK){
            if(Settings.FOODS.contains(player.itemInHand.type)) {
                user.setFoodStarting()
                user.resetInvalidFoodEatableCount()
            }else if(player.itemInHand.type == Material.BOW && player.inventory.contains(Material.BOW)){
                user.setBowStart(System.currentTimeMillis())
                user.setBow(true)
            }
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

        val checkResult: CheckResult = FastUse.runFood(user)

        if(checkResult.failed()){
            event.isCancelled = true
            instance.log(checkResult, user)
        }
    }

    @EventHandler
    fun onShoot(event: ProjectileLaunchEvent) {
        val player = event.entity
        val user: User = instance.USERS[player.uniqueId]!!
        val checkResult: CheckResult = FastUse.runBow(user)

        if(checkResult.failed()){
            event.isCancelled = true
            instance.log(checkResult, user)
        }
    }

    @EventHandler
    fun onItemSwitch(event: PlayerItemHeldEvent) {
        instance.getUser(event.player).setBow(false)
    }
}