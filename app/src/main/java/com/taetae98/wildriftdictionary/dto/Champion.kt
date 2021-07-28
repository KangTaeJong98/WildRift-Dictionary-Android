package com.taetae98.wildriftdictionary.dto

data class Champion(
    val id: String,
    val name: String,
    val lines: List<Line>,
) {
    val headerImage = "https://poro.gg/images/lol/champion/splash-modified/$id.jpg"

    enum class Line {
        NONE, ALL, TOP, JUNGLE, MID, AD, SUPPORTER
    }
}