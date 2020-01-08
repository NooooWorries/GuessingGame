package com.czxbnb.guessinggame.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.czxbnb.guessinggame.base.BaseViewModel
import com.czxbnb.guessinggame.models.item.Item
import com.czxbnb.guessinggame.models.item.ItemListCallback
import com.czxbnb.guessinggame.models.item.ItemRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SplashViewModel : BaseViewModel() {
    @Inject
    lateinit var itemRepository: ItemRepository // Item repository
    private lateinit var itemSubscription: Disposable // Item list subscription

    // Live data for items
    private val itemsLiveData: MutableLiveData<List<Item>> = MutableLiveData()
    val _itemsLiveData: LiveData<List<Item>> = itemsLiveData

    // Live data for loading visibility
    private val progress: MutableLiveData<Int> = MutableLiveData()
    val _progress: LiveData<Int> = progress

    init {
        getItemList()
    }

    private fun getItemList() {
        progress.value = 1
        itemSubscription = itemRepository.loadItemList(object : ItemListCallback {
            override fun onLoadItemListSuccess(itemList: List<Item>) {
                itemsLiveData.value = itemList
                progress.value = 100
            }

            override fun onLoadItemError(e: Throwable) {
                errorMessage.value = e.message
                progress.value = -1
            }
        })
    }
}