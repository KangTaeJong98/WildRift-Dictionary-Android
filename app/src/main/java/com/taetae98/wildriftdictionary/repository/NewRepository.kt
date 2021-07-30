package com.taetae98.wildriftdictionary.repository

import com.taetae98.wildriftdictionary.dto.News
import com.taetae98.wildriftdictionary.manager.LocaleManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewRepository @Inject constructor() {
    private lateinit var data: List<News>

    suspend fun update() {
        withContext(Dispatchers.IO) {
            val arrayList = ArrayList<News>()

            Jsoup.connect("https://wildrift.leagueoflegends.com/${LocaleManager.string(true)}/news/").get()
                .select("a.articleCardWrapper-1JIOy")
                .forEach {
                    val image = findImage(it)
                    val title = findTitle(it)
                    val url = findURL(it)

                    arrayList.add(News(image, title, url))
                }

            data = arrayList
        }
    }

    private fun findImage(element: Element): String {
        return element.select("img.image-NeGf2").attr("src").trim()
    }

    private fun findTitle(element: Element): String {
        return element.select("h4.heading-05.font-normal.title--HVLV").text().trim()
    }

    private fun findURL(element: Element): String {
        return if (element.attr("href").contains("https")) {
            element.attr("href")
        } else {
            "https://wildrift.leagueoflegends.com${element.attr("href")}"
        }.trim()
    }

    fun findAll(): List<News> {
        if (!::data.isInitialized) {
            runBlocking {
                update()
            }
        }

        return data
    }
}