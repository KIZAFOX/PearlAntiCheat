package fr.kizafox.pearlanticheat.tools

import org.bukkit.Location
import org.bukkit.event.player.PlayerMoveEvent
import kotlin.math.abs

class Distance(private val from: Location, private val to: Location?) {

    private val xDifference: Double = abs(from.x - to!!.x)
    private val yDifference: Double = abs(from.y - to!!.y)
    private val zDifference: Double = abs(from.z - to!!.z)
    private val isGoingUp: Boolean = to!!.y > from.y
    private val isGoingDown: Boolean = from.y > to!!.y

    constructor(e: PlayerMoveEvent) : this(e.from, e.to)

    fun getTo(): Location? {
        return to
    }

    fun getFrom(): Location {
        return from
    }

    fun getXDifference(): Double {
        return xDifference
    }

    fun getZDifference(): Double {
        return zDifference
    }

    fun getYDifference(): Double {
        return yDifference
    }

    fun isGoingDown(): Boolean {
        return isGoingDown
    }

    fun isGoingUp(): Boolean {
        return isGoingUp
    }

    fun isMovingHorizontally(): Boolean {
        return xDifference != 0.0 || zDifference != 0.0
    }
}