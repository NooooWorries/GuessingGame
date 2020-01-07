package com.czxbnb.guessinggame.models.item

import android.accounts.NetworkErrorException
import com.czxbnb.guessinggame.ITEM_TOKEN
import com.czxbnb.guessinggame.base.BaseRepository
import com.czxbnb.guessinggame.models.AppDatabase
import com.czxbnb.guessinggame.api.ItemApi
import com.czxbnb.guessinggame.base.AppExecutors
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
    private val sharedPreferenceManager = SharedPreferenceManager.getInstance()
    private val itemDao = AppDatabase.getInstance()!!.itemDao()

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
        return itemApi.getItems("media", ITEM_TOKEN).concatMap {
            apiItemList ->
                if (apiItemList.version != sharedPreferenceManager!!.questionVersion) {
                    onUpdateQuestion(apiItemList)
                    Observable.just(apiItemList.items)
                } else {
                    Observable.fromCallable {itemDao.all}
                        .concatMap {
                            dbItemList ->
                            if (dbItemList.isNotEmpty()) {
                                Observable.just(dbItemList)
                            } else {
                                onUpdateQuestion(apiItemList)
                                Observable.just(apiItemList.items)
                            }
                        }
                }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result != null) {
                        itemCallback.onLoadItemSuccess(result)
                    } else{
                        itemCallback.onLoadItemError(NetworkErrorException("Data not available"))
                    }
                },
                { error -> itemCallback.onLoadItemError(error) }
            )
    }

    private fun onUpdateQuestion(data: BaseData<List<Item>>) {
        if (data.items!!.isEmpty()) {
            return;
        }
        AppExecutors().diskIO.execute {
            itemDao.removeAll()
            itemDao.insert(data.items)
        }
        sharedPreferenceManager!!.questionVersion = data.version
        sharedPreferenceManager.currentProgress = 0
    }


}