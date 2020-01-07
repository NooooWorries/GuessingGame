package com.czxbnb.guessinggame.models.item

data class Item(
    val correctAnswerIndex: Int,
    val imageUrl: String,
    val standFirst: String,
    val storyUrl: String,
    val section: String,
    val headlines: List<String>
)