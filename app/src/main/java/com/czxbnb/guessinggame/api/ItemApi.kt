package com.czxbnb.guessinggame.api

import com.czxbnb.guessinggame.base.BaseData
import com.czxbnb.guessinggame.models.item.Item
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemApi {
    @GET("/v0/b/nca-dna-apps-dev.appspot.com/o/game.json")
    fun getItems(
        @Query("alt") alt: String,
        @Query("token") token: String
    ): Observable<BaseData<List<Item>>>
}