package com.czxbnb.guessinggame.models.item

import com.czxbnb.guessinggame.ITEM_TOKEN
import com.czxbnb.guessinggame.base.BaseRepository
import com.czxbnb.guessinggame.models.AppDatabase
import com.czxbnb.guessinggame.api.ItemApi
import com.czxbnb.guessinggame.base.BaseData
import com.czxbnb.guessinggame.manager.SharedPreferenceManager
import io.reactivex.Observable
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
        val itemDao = AppDatabase.getInstance()!!.itemDao()
        val sharedPreferenceManager = SharedPreferenceManager.getInstance()

        return itemApi.getItems("media", ITEM_TOKEN)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    // Api Needs improvement
                    // Should create a standalone API to check the question bank version
                    // In order to reduce the data traffic
                    if (result.version != sharedPreferenceManager!!.questionVersion) {
                        // Reset preference
                        sharedPreferenceManager.questionVersion = result.version
                        sharedPreferenceManager.currentProgress = 0
                        itemCallback.onLoadItemSuccess(result.data!!)
                    } else {
                        itemCallback.onLoadItemSuccess(itemDao.all)
                    }
                },
                { error -> itemCallback.onLoadItemError(error) }
            )
    }
}