package fr.kizafox.pearlanticheat.tools.handlers

import org.bukkit.ChatColor

class ChatHandler {
    companion object{
        fun translate(text: String?): String {
            return ChatColor.translateAlternateColorCodes('&', text!!)
        }
    }
}