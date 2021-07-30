package com.taetae98.wildriftdictionary.repository

import com.taetae98.wildriftdictionary.dto.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChampionInformationRepository @Inject constructor(
    private val itemRepository: ItemRepository,
    private val runeRepository: RuneRepository
) {
    private val data = HashMap<String, ChampionInformation>()

    private suspend fun update(hash: String) {
        withContext(Dispatchers.IO) {
            val document = Jsoup.connect("https://poro.gg/wildrift/champions/$hash").get()

            data[hash] = ChampionInformation(
                findCost(document),
                findWildCore(document),
                findAbility(document),
                findSubAbility(document),
                findSkill(document),
                findItem(document),
                findRune(document),
                findSpell(document)
            )
        }
    }

    private fun findCost(document: Document): String {
        return document.select("div.wildrift-detail__cost > div.mr-2 > span").text()
    }

    private fun findWildCore(document: Document): String {
        return document.select("div.wildrift-detail__cost > div.ml-1 > span").text()
    }

    private fun findAbility(document: Document): List<Ability> {
        val arrayList = ArrayList<Ability>()
        document.select("ul.wildrift-detail__stats1 > li").forEach {
            arrayList.add(
                Ability(
                    it.select("b").text(),
                    it.select("span").text()
                )
            )
        }

        return arrayList
    }

    private fun findSubAbility(document: Document): List<Ability> {
        val arrayList = ArrayList<Ability>()
        document.select("ul.wildrift-detail__stats2 > li").forEach {
            arrayList.add(
                Ability(
                    it.select("b").text(),
                    it.select("span").text()
                )
            )
        }

        return arrayList
    }

    private fun findSkill(document: Document): List<Skill> {
        val arrayList = ArrayList<Skill>()
        document.select("div.wildrift-detail__skills__info").forEach {
            val image = "https:" + it.select("div.wildrift-detail__skills__info__image > img").attr("src")
            val div = it.select("div.wildrift-detail__skills__info__content").first()!!.children()
            val name = div[0].text()
            val description = div[2].text()

            val data = div[1].toString()
            val array = div[1].text().split(" ")
            val cooltime = array.first()
            val type = when {
                data.contains("fa-tint") -> {
                    Skill.Type.MP
                }
                data.contains("fa-heart") -> {
                    Skill.Type.HP
                }
                data.contains("fa-bolt") -> {
                    Skill.Type.ENERGY
                }
                data.contains("fa-fire") -> {
                    Skill.Type.ANGER
                }
                else -> {
                    Skill.Type.NONE
                }
            }
            val cost = if (type == Skill.Type.NONE) {
                ""
            } else {
                array.last()
            }

            arrayList.add(Skill(image, name, cooltime, cost, type, description))
        }

        return arrayList
    }

    private fun findItem(document: Document): List<Item> {
        val arrayList = ArrayList<Item>()
        document.select("div.wildrift-detail__recommend__item__content")[0].children().forEach {
            val name = it.attr("alt")
            arrayList.addAll(itemRepository.findLikeName(name))
        }

        return arrayList
    }

    private fun findRune(document: Document): List<Rune> {
        val arrayList = ArrayList<Rune>()
        document.select("div.wildrift-detail__recommend__item__content")[1].children().forEach {
            val name = it.attr("alt")
            runeRepository.findAll().find { rune ->
                rune.name == name
            }?.let { rune ->
                arrayList.add(rune)
            }
        }

        return arrayList
    }

    private fun findSpell(document: Document): List<Spell> {
        val arrayList = ArrayList<Spell>()
        document.select("div.wildrift-detail__recommend__item__content")[2].children().forEach {
            val element = Jsoup.parse(it.attr("title"))
            val image = element.select("div.wdr-tooltip__item__image > img").attr("src")
            val (name, cooltime) = element.select("div.wdr-tooltip__item__info").run {
                listOf(
                    select("h1").text(),
                    select("p").text().split(" ").last()
                )
            }
            val description = element.select("div.wdr-tooltip__description").text()

            arrayList.add(Spell(image, name, cooltime, description))
        }

        return arrayList
    }

    fun findById(id: String): ChampionInformation {
        val hash = id.lowercase()
        if (data[hash] == null) {
            runBlocking {
                update(hash)
            }
        }

        return data.getValue(hash)
    }
}