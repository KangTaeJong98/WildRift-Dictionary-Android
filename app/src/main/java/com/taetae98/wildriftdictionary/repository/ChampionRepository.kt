package com.taetae98.wildriftdictionary.repository

import com.taetae98.wildriftdictionary.dto.Champion
import com.taetae98.wildriftdictionary.manager.LocaleManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChampionRepository @Inject constructor() {
    private lateinit var data: List<Champion>

    suspend fun update() {
        withContext(Dispatchers.IO) {
            val arrayList = ArrayList<Champion>()
            val document = Jsoup.connect("https://poro.gg/champions?hl=${LocaleManager.string()}").get()

            Jsoup.connect("https://poro.gg/wildrift/champions/garen?hl=${LocaleManager.string()}").get()
                .select("a.wildrift-champion > img.wildrift-champion__image")
                .forEach {
                    val id = findId(it)
                    val name = findName(it)

                    val lineElement = document.select("a.champion-list__item:has(div:has(img[alt=$name]))")
                    val lines = findLine(lineElement.first()!!)
                    arrayList.add(Champion(id, name, lines))
                }

            data = arrayList
        }
    }

    private fun findId(element: Element): String {
        return element.attr("src").substringAfter("champion/").substringBefore(".").trim()
    }

    private fun findName(element: Element): String {
        return element.attr("alt").trim()
    }

    private fun findLine(element: Element): List<Champion.Line> {
        return element.attr("data-positions").split(",").map { position ->
            when(position) {
                "top" -> {
                    Champion.Line.TOP
                }
                "jng" -> {
                    Champion.Line.JUNGLE
                }
                "mid" -> {
                    Champion.Line.MID
                }
                "adc" -> {
                    Champion.Line.AD
                }
                "sup" -> {
                    Champion.Line.SUPPORTER
                }
                else -> {
                    Champion.Line.NONE
                }
            }
        }
    }

    fun findByLines(line: Champion.Line): List<Champion> {
        if (!::data.isInitialized) {
            runBlocking {
                update()
            }
        }

        return if (line == Champion.Line.ALL) {
            data
        } else {
            data.filter { it.lines.contains(line) }
        }
    }
}