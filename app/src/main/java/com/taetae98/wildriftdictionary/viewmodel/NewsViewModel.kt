package com.taetae98.wildriftdictionary.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taetae98.wildriftdictionary.dto.News
import com.taetae98.wildriftdictionary.manager.LocaleManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    stateHandle: SavedStateHandle
): ViewModel() {
    val data = flow<List<News>> {
        try {
            val data = ArrayList<News>()
            viewModelScope.launch(Dispatchers.IO) {
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

                        data.add(News(image, title, url))
                    }

            }.join()
            emit(data)
        } catch (e: Exception) {

        }
    }
}