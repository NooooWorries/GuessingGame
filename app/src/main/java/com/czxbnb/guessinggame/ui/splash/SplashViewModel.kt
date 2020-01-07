package com.czxbnb.guessinggame.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.czxbnb.guessinggame.base.BaseData
import com.czxbnb.guessinggame.base.BaseViewModel
import com.czxbnb.guessinggame.models.item.Item
import com.czxbnb.guessinggame.models.item.ItemCallback
import com.czxbnb.guessinggame.models.item.ItemRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SplashViewModel : BaseViewModel() {
    @Inject
    lateinit var itemRepository: ItemRepository // Item repository
    private lateinit var itemSubscription: Disposable // Item list subscription

    // Live data
    private val itemsLiveData: MutableLiveData<List<Item>> = MutableLiveData()
    val _itemsLiveData: LiveData<List<Item>> = itemsLiveData

    init {
        getItemList()
    }

    private fun getItemList() {
        itemSubscription = itemRepository.loadItemList(object : ItemCallback {
            override fun onLoadItemSuccess(itemList: List<Item>) {
                itemsLiveData.value = itemList
                Log.d("---->", itemList.size.toString() + "---->")
            }

            override fun onLoadItemError(e: Throwable) {
                errorMessage.value = e.message
            }
        })
    }
}