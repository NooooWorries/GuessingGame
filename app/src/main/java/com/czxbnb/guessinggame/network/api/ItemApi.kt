package com.czxbnb.guessinggame.network.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ItemApi {
    @GET("/v0/b/nca-dna-apps-dev.appspot.com/o/game.json")
    fun getItems(
        @Query("alt") alt: String,
        @Query("token") token: String
    )
}