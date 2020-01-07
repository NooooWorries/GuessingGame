package com.czxbnb.guessinggame.models.item

import android.content.Context
import com.czxbnb.guessinggame.ITEM_TOKEN
import com.czxbnb.guessinggame.base.BaseRepository
import com.czxbnb.guessinggame.network.api.ItemApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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

    fun loadItemList(
        itemCallback: ItemCallback
    ): Disposable {
        return itemApi.getItems("media", ITEM_TOKEN)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result -> itemCallback.onLoadItemSuccess(result)},
                {error -> itemCallback.onLoadItemError(error)}
            )
    }
}