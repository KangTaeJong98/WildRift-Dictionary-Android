package com.taetae98.wildriftdictionary.dto

import java.io.Serializable

data class Spell(
    val image: String,
    val name: String,
    val cooltime: String,
    val description: String
) : Serializable