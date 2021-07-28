package com.taetae98.wildriftdictionary.repository

import com.taetae98.wildriftdictionary.dto.Item
import com.taetae98.wildriftdictionary.manager.LocaleManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor() {
    private lateinit var data: List<Item>

    suspend fun update() {
        val arrayList = ArrayList<Item>()
        withContext(Dispatchers.IO) {
            Jsoup.connect("https://poro.gg/wildrift/items?hl=${LocaleManager.getSmallToBig()}").get()
                .select("div.mb-3")
                .forEach { typeElement ->
                    val type = when (typeElement.select("h2.wildrift-items__group").text()) {
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
                    typeElement.select("div.wildrift-items__box").forEach { levelElement ->
                        val level = when (levelElement.select("header.wildrift-items__box__header").text()) {
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
                        levelElement.select("li > img").forEach { itemElement ->
                            val image = itemElement.attr("src")
                            val name = itemElement.attr("alt")
                            val informationElement = Jsoup.parse(itemElement.attr("title"))
                            val cost = informationElement.select("div.wdr-tooltip__item__info > p").text()
                            val stats = informationElement.select("div.wdr-tooltip__stats").text()
                            val description = informationElement.select("div.wdr-tooltip__description").text()

                            arrayList.add(Item(type, level, image, name, cost, stats, description))
                        }
                    }
                }
        }

        data = arrayList
    }

    fun findAll(): List<Item> {
        if (!::data.isInitialized) {
            runBlocking(Dispatchers.IO) {
                update()
            }
        }

        return data
    }
}