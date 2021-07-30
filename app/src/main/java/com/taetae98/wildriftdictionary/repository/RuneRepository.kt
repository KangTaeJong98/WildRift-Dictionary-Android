package com.taetae98.wildriftdictionary.repository

import com.taetae98.wildriftdictionary.dto.Rune
import com.taetae98.wildriftdictionary.manager.LocaleManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RuneRepository @Inject constructor() {
    private lateinit var data: List<Rune>

    suspend fun update() {
        val arrayList = ArrayList<Rune>()
        withContext(Dispatchers.IO) {
            Jsoup.connect("https://poro.gg/wildrift/runes?hl=${LocaleManager.string()}").get()
                .select("div.wildrift-runes__group")
                .forEach { groupElement ->
                    val group = when (groupElement.select("header.wildrift-runes__group__header").text()) {
                        "핵심", "KeyStone" -> {
                            Rune.Group.KEY_STONE
                        }
                        "지배", "Resolve" -> {
                            Rune.Group.RESOLVE
                        }
                        "결의", "Domination" -> {
                            Rune.Group.DOMINATION
                        }
                        "영감", "Inspiration" -> {
                            Rune.Group.INSPIRATION
                        }
                        else -> {
                            Rune.Group.NONE
                        }
                    }

                    groupElement.select("div.wildrift-runes__group__content li").forEach { runeElement ->
                        val image = runeElement.select("img").attr("src")
                        val name = runeElement.select("div.wildrift-runes__group__rune span").text()
                        val type = runeElement.select("div.wildrift-runes__group__type").text()
                        val description = runeElement.select("div.wildrift-runes__group__description").text()

                        arrayList.add(Rune(group, image, name, type, description))
                    }
                }
        }

        data = arrayList
    }

    fun findLikeName(name: String): List<Rune> {
        return findAll().filter { it.name.contains(name) }
    }

    fun findByGroup(group: Rune.Group): List<Rune> {
        return findAll().filter { it.group == group }
    }

    private fun findAll(): List<Rune> {
        if (!::data.isInitialized) {
            runBlocking {
                update()
            }
        }

        return data
    }
}