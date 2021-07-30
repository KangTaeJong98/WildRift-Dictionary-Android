package com.taetae98.wildriftdictionary.dto

import java.io.Serializable

data class Item(
    val type: Type,
    val level: Level,
    val image: String,
    val name: String,
    val cost: String,
    val stats: String,
    val description: String
) : Serializable {
    enum class Type {
        NONE, PHYSICAL, MAGIC, DEFENSE, BOOTS
    }

    enum class Level {
        NONE, UPGRADED, MID, BASIC, ENCHANTMENTS
    }
}