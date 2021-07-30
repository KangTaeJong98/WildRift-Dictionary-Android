package com.taetae98.wildriftdictionary.dto

import java.io.Serializable

data class Skill(
    val image: String,
    val name: String,
    val cooltime: String,
    val cost: String,
    val type: Type,
    val description: String,
) : Serializable {
    enum class Type {
        NONE, HP, MP, ENERGY, ANGER
    }
}