package com.taetae98.wildriftdictionary.dto

import com.taetae98.wildriftdictionary.manager.LocaleManager
import java.io.Serializable

data class Champion(
    val id: String,
    val name: String,
    val lines: List<Line>,
) : Serializable {
    val headerImage = "https://poro.gg/images/lol/champion/splash-modified/$id.jpg"
    val splashImage = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/${id}_0.jpg"
    val universeURL = "https://universe.leagueoflegends.com/${LocaleManager.getSmallToBig()}/champion/${id.lowercase()}"

    enum class Line {
        NONE, ALL, TOP, JUNGLE, MID, AD, SUPPORTER
    }
}