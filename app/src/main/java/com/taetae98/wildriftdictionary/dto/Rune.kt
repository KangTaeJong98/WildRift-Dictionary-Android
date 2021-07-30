package com.taetae98.wildriftdictionary.dto

import java.io.Serializable

data class Rune(
    val group: Group,
    val image: String,
    val name: String,
    val type: String,
    val description: String
) : Serializable {
    enum class Group {
        NONE, KEY_STONE, DOMINATION, RESOLVE, INSPIRATION
    }
}