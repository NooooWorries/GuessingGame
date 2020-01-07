package com.czxbnb.guessinggame.models.item.headlines

import androidx.room.TypeConverter

class HeadlinesConverter {
    @TypeConverter
    fun storedStringToHeadlines(value: String): Headlines {
        val headlineList = value.split("\\s*,\\s*")
        return Headlines(headlineList)
    }

    @TypeConverter
    fun headlinesToStoredString(value: Headlines): String {
        var result = ""

        for (headlineStr in value.headline) {
            result += "$headlineStr,"
        }
        return result
    }
}