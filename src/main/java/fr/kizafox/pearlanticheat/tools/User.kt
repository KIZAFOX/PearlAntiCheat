package fr.kizafox.pearlanticheat.tools

import org.bukkit.Location
import org.bukkit.entity.Player

class User(private val player: Player) {

    private var foodStart: Long = 0
    private lateinit var foodStartLocation: Location
    private var invalidFoodEatableCount: Int = 0

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
        return this.foodStart != null
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

}