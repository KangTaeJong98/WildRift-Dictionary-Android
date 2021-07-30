package com.taetae98.wildriftdictionary.dto

import java.io.Serializable

data class ChampionInformation(
    val cost: String,
    val wildCore: String,
    val ability: List<Ability>,
    val subAbility: List<Ability>,
    val skill: List<Skill>,
    val item: List<Item>,
    val rune: List<Rune>,
    val spell: List<Spell>
) : Serializable