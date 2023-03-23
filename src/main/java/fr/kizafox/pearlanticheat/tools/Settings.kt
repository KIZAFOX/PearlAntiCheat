package fr.kizafox.pearlanticheat.tools

import org.bukkit.Material

class Settings {

    companion object{
        var MAX_XZ_SPEED: Double = 0.808
        var MAX_XZ_EATING_SPEED: Double = 0.10117 // 0.10117
        var MAX_XZ_BLOCKING_SPEED: Double = 0.12
        var MAX_XZ_BOW_SPEED: Double = 0.15

        var MAX_WARNINGS: Int = 15
        var FOOD_MIN: Long = 1000
        var BOW_MIN: Long = 100

        var FOODS: MutableList<Material> = ArrayList()

        init{
            FOODS.add(Material.LEGACY_RAW_CHICKEN)
            FOODS.add(Material.LEGACY_RAW_BEEF)
            FOODS.add(Material.LEGACY_RAW_FISH)

            FOODS.add(Material.LEGACY_COOKED_FISH)
            FOODS.add(Material.COOKED_MUTTON)
            FOODS.add(Material.COOKED_CHICKEN)
            FOODS.add(Material.COOKED_RABBIT)
            FOODS.add(Material.COOKED_BEEF)

            FOODS.add(Material.GOLDEN_CARROT)
            FOODS.add(Material.GOLDEN_APPLE)
            FOODS.add(Material.ENCHANTED_GOLDEN_APPLE)

            FOODS.add(Material.CARROT)
            FOODS.add(Material.APPLE)
            FOODS.add(Material.POTATO)
            FOODS.add(Material.LEGACY_POTATO_ITEM)
            FOODS.add(Material.POISONOUS_POTATO)
            FOODS.add(Material.BAKED_POTATO)

            FOODS.add(Material.LEGACY_MUSHROOM_SOUP)
        }
    }
}