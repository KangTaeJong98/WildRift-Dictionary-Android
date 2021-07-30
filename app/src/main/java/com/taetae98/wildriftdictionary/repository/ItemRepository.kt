package com.taetae98.wildriftdictionary.repository

import com.taetae98.wildriftdictionary.dto.Item
import com.taetae98.wildriftdictionary.manager.LocaleManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor() {
    private lateinit var data: List<Item>

    suspend fun update() {
        val arrayList = ArrayList<Item>()
        withContext(Dispatchers.IO) {
            Jsoup.connect("https://poro.gg/wildrift/items?hl=${LocaleManager.string()}").get()
                .select("div.mb-3")
                .forEach { typeElement ->
                    val type = findType(typeElement)
                    typeElement.select("div.wildrift-items__box").forEach { levelElement ->
                        val level = findLevel(levelElement)
                        levelElement.select("li > img").forEach { itemElement ->
                            val image = findImage(itemElement)
                            val name = findName(itemElement)

                            val informationElement = Jsoup.parse(itemElement.attr("title"))
                            val cost = findCost(informationElement)
                            val stats = findStats(informationElement)
                            val description = findDescription(informationElement)

                            arrayList.add(Item(type, level, image, name, cost, stats, description))
                        }
                    }
                }
        }

        data = arrayList
    }

    private fun findType(element: Element): Item.Type {
        return when (element.select("h2.wildrift-items__group").text().trim()) {
            "물리 아이템", "Physical Items" -> {
                Item.Type.PHYSICAL
            }
            "마법 아이템", "Magic Items" -> {
                Item.Type.MAGIC
            }
            "방어 아이템", "Defense Items" -> {
                Item.Type.DEFENSE
            }
            "신발", "Boots Items" -> {
                Item.Type.BOOTS
            }
            else -> {
                Item.Type.NONE
            }
        }
    }
    private fun findLevel(element: Element): Item.Level {
        return when (element.select("header.wildrift-items__box__header").text().trim()) {
            "최고 단계", "Upgraded" -> {
                Item.Level.UPGRADED
            }
            "중간 단계", "Mid Tier" -> {
                Item.Level.MID
            }
            "기본", "Basic" -> {
                Item.Level.BASIC
            }
            "마법 부여", "Enchantments" -> {
                Item.Level.ENCHANTMENTS
            }
            else -> {
                Item.Level.NONE
            }
        }
    }
    private fun findImage(element: Element): String {
        return element.attr("src").trim()
    }
    private fun findName(element: Element): String {
        return element.attr("alt").trim()
    }
    private fun findCost(element: Element): String {
        return element.select("div.wdr-tooltip__item__info > p").text().trim()
    }
    private fun findStats(element: Element): String {
        return StringBuilder().apply {
            element.select("div.wdr-tooltip__stats").html().split("<br>").forEach {
                appendLine(it.trim())
            }
        }.toString().trim()
    }
    private fun findDescription(element: Element): String {
        return element.select("div.wdr-tooltip__description").text().trim()
    }

    fun findAll(): List<Item> {
        if (!::data.isInitialized) {
            runBlocking {
                update()
            }
        }

        return data
    }
}