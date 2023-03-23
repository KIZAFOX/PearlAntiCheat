package fr.kizafox.pearlanticheat.managers

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import fr.kizafox.pearlanticheat.PearlAntiCheat
import fr.kizafox.pearlanticheat.managers.commands.CommandPAC
import fr.kizafox.pearlanticheat.managers.listeners.MoveListener
import fr.kizafox.pearlanticheat.managers.listeners.PlayerListeners
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import kotlin.math.sqrt

class Managers(instance: PearlAntiCheat) {
    init {
        val pluginManager = instance.server.pluginManager

        pluginManager.registerEvents(PlayerListeners, instance)
        pluginManager.registerEvents(MoveListener, instance)

        instance.getCommand("pac")?.setExecutor(CommandPAC)

        /**
         * ProtocolLibrary.getProtocolManager().addPacketListener(
         *             object : PacketAdapter(
         *                 instance,
         *                 ListenerPriority.NORMAL,
         *                 PacketType.Play.Client.USE_ENTITY
         *             ) {
         *                 override fun onPacketReceiving(event: PacketEvent) {
         *                     val player = event.player
         *                     val entityId = event.packet.integers.read(0)
         *                     val targetEntity = Bukkit.getPlayer()
         *
         *                     if (entityId != null && targetEntity is Player) {
         *                         val distanceSquared = targetEntity.location.distanceSquared(player.location)
         *
         *                         if (distanceSquared > 25) {
         *                             Bukkit.broadcastMessage("${player.name} a été détecté avec une reach de ${sqrt(distanceSquared)}")
         *                             // Vous pouvez ajouter ici des actions supplémentaires en cas de détection de triche.
         *                         }
         *                     }
         *                 }
         *             }
         *         )
         */
    }
}