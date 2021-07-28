package com.taetae98.wildriftdictionary.dto

data class Rune(
    val group: Group,
    val image: String,
    val name: String,
    val type: String,
    val description: String
) {
    enum class Group {
        NONE, KEY_STONE, DOMINATION, RESOLVE, INSPIRATION
    }
}