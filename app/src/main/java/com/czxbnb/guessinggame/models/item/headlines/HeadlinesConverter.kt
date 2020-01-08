package com.czxbnb.guessinggame.models.item.headlines

import androidx.room.TypeConverter

class HeadlinesConverter {

    @TypeConverter
    fun fromArray(strings: ArrayList<String>): String {
        var string = ""
        for (s in strings) string += "$s|||"
        return string
    }

    @TypeConverter
    fun toArray(concatenatedStrings: String):  ArrayList<String> {
        val myStrings : ArrayList<String> = ArrayList()
        for (s: String in concatenatedStrings.split("|||")) {
            if (s.isNotEmpty()) {
                myStrings += s
            }
        }

        return myStrings
    }
}