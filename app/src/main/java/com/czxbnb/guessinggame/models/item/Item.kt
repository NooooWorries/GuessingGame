package com.czxbnb.guessinggame.models.item

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.czxbnb.guessinggame.models.item.headlines.Headlines
import java.io.Serializable

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val correctAnswerIndex: Int,

    val imageUrl: String,

    val standFirst: String,

    val storyUrl: String,

    val section: String,


    val headlines: Headlines
) : Serializable