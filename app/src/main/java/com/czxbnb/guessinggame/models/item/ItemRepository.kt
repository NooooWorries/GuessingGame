package com.czxbnb.guessinggame.models.item

import com.czxbnb.guessinggame.base.BaseRepository
import com.czxbnb.guessinggame.network.api.ItemApi
import javax.inject.Inject

class ItemRepository private constructor() : BaseRepository() {
    @Inject
    lateinit var itemApi: ItemApi

    companion object {
        @Volatile
        private lateinit var instance: ItemRepository

        fun getInstance(): ItemRepository {
            if (!::instance.isInitialized) {
                synchronized(ItemRepository::class.java) {
                    if (!::instance.isInitialized) {
                        instance = ItemRepository()
                    }
                }
            }
            return instance
        }
    }
}