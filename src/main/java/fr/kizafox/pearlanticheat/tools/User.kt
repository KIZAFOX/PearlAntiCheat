package fr.kizafox.pearlanticheat.tools

import fr.kizafox.pearlanticheat.tools.checks.CheckType
import org.bukkit.Location
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.HashMap

class User(private val player: Player) {

    private var foodStart: Long = 0
    private lateinit var foodStartLocation: Location
    private var invalidFoodEatableCount: Int = 0

    private var bowStart: Long = 0
    private var bow: Boolean = false

    private val WARNINGS = HashMap<CheckType, Int>()

    fun getPlayer(): Player{
        return player
    }

    fun setFoodStarting(){
        this.foodStart = System.currentTimeMillis()
        this.foodStartLocation = player.location
    }

    fun getFoodStarting(): Long {
        return this.foodStart
    }

    fun isFoodStarted(): Boolean{
        return true
    }

    fun getFoodStartLocation(): Location {
        return this.foodStartLocation
    }

    fun addInvalidFoodEatableCount(){
        this.invalidFoodEatableCount++
    }

    fun resetInvalidFoodEatableCount(){
        this.invalidFoodEatableCount = 0
    }

    fun getInvalidFoodEatableCount(): Int{
        return this.invalidFoodEatableCount
    }

    fun getWarnings(checkType: CheckType): Int {
        this.WARNINGS.putIfAbsent(checkType, 0)
        return this.WARNINGS[checkType]!!
    }

    fun addWarning(checkType: CheckType){
        this.getWarnings(checkType)
        this.WARNINGS[checkType] = this.WARNINGS[checkType]!! + 1
    }

    fun isBow(): Boolean{
        return this.bow
    }

    fun setBow(bow: Boolean) {
        this.bow = bow
    }

    fun isBlockAboveSolid(x: Boolean, location: Location): Boolean{
        return x;
    }

    fun isBlockAboveSolid(location: Location): Boolean{
        return this.isBlockAboveSolid(true, location) || this.isBlockAboveSolid(false, location)
    }

    fun getBowStart(): Long{
        return this.bowStart
    }

    fun setBowStart(bowStart: Long){
        this.bowStart = bowStart
    }

}