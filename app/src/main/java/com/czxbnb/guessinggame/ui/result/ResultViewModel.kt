package com.czxbnb.guessinggame.ui.result

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.czxbnb.guessinggame.base.BaseViewModel
import com.czxbnb.guessinggame.manager.SharedPreferenceManager
import com.czxbnb.guessinggame.models.item.Item
import com.czxbnb.guessinggame.models.item.ItemCallback
import com.czxbnb.guessinggame.models.item.ItemRepository
import com.google.gson.Gson
import javax.inject.Inject

class ResultViewModel : BaseViewModel() {
    @Inject
    lateinit var itemRepository: ItemRepository

    // Live data for item
    private val itemLiveData: MutableLiveData<Item> = MutableLiveData()
    val _itemLiveData: LiveData<Item> = itemLiveData

    // Data for correct answer
    private val selectedIndexLiveData: MutableLiveData<Int> = MutableLiveData()
    val _selectedIndexLiveData: LiveData<Int> = selectedIndexLiveData

    fun getData(bundle: Bundle) {
        itemRepository.getItemById(
            SharedPreferenceManager.getInstance()!!.currentProgress,
            object : ItemCallback {
                override fun onLoadItemSuccess(item: Item) {
                    itemLiveData.value = item
                    selectedIndexLiveData.value = bundle.getInt("selected")
                }

                override fun onLoadItemError(e: Throwable) {
                    errorMessage.value = e.message
                }
            })
    }
}