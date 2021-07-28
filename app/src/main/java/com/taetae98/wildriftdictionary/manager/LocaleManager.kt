package com.taetae98.wildriftdictionary.manager

import java.util.*

object LocaleManager {
    private fun getLocale(): Locale {
        return when(Locale.getDefault()) {
            Locale.KOREAN, Locale.KOREA -> {
                Locale.KOREA
            }
            else -> {
                Locale.US
            }
        }
    }

    fun getSmallToBig(): String {
        return when(getLocale()) {
            Locale.KOREA -> {
                "ko-KR"
            }
            Locale.US -> {
                "en-US"
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }

    fun getSmallToSmall(): String {
        return when(getLocale()) {
            Locale.KOREA -> {
                "ko-kr"
            }
            Locale.US -> {
                "en-us"
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }
}