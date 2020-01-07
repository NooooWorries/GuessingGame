package com.czxbnb.guessinggame.models.item

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.czxbnb.guessinggame.models.item.headlines.Headlines
import retrofit2.http.Field
import java.io.Serializable

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @Field("correctAnswerIndex")
    val correctAnswerIndex: Int,

    @Field("imageUrl")
    val imageUrl: String,

    @Field("standFirst")
    val standFirst: String,

    @Field("storyUrl")
    val storyUrl: String,

    @Field("section")
    val section: String,

    @Field("headlines")
    val headlines: ArrayList<String>

) : Serializable