package com.taetae98.wildriftdictionary.manager

import java.util.*

object LocaleManager {
    private fun locale(): Locale {
        return when(Locale.getDefault()) {
            Locale.KOREAN, Locale.KOREA -> {
                Locale.KOREA
            }
            else -> {
                Locale.US
            }
        }
    }

    fun string(toLower: Boolean = false): String {
        val locale = when(locale()) {
            Locale.KOREA -> {
                "ko-KR"
            }
            Locale.US -> {
                "en-US"
            }
            else -> {
                ""
            }
        }

        return if (toLower) {
            locale.lowercase()
        } else {
            locale
        }
    }
}