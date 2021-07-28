package com.taetae98.wildriftdictionary.repository

import com.taetae98.wildriftdictionary.dto.News
import com.taetae98.wildriftdictionary.manager.LocaleManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewRepository @Inject constructor() {
    private lateinit var data: List<News>

    suspend fun update() {
        withContext(Dispatchers.IO) {
            val arrayList = ArrayList<News>()

            Jsoup.connect("https://wildrift.leagueoflegends.com/${LocaleManager.getSmallToSmall()}/news/").get()
                .select("a.articleCardWrapper-1JIOy")
                .forEach {
                    val image = it.select("img.image-NeGf2").attr("src")
                    val title = it.select("h4.heading-05.font-normal.title--HVLV").text()
                    val url = if (it.attr("href").contains("https")) {
                        it.attr("href")
                    } else {
                        "https://wildrift.leagueoflegends.com${it.attr("href")}"
                    }

                    arrayList.add(News(image, title, url))
                }

            data = arrayList
        }
    }

    fun findAll(): List<News> {
        if (!::data.isInitialized) {
            runBlocking(Dispatchers.IO) {
                update()
            }
        }

        return data
    }
}