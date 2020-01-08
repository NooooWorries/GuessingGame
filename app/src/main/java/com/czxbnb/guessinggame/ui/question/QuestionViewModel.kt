package com.czxbnb.guessinggame.ui.question

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.czxbnb.guessinggame.base.BaseViewModel
import com.czxbnb.guessinggame.manager.SharedPreferenceManager
import com.czxbnb.guessinggame.models.item.Item
import com.czxbnb.guessinggame.models.item.ItemCallback
import com.czxbnb.guessinggame.models.item.ItemRepository
import javax.inject.Inject


class QuestionViewModel : BaseViewModel() {
    @Inject
    lateinit var itemRepository: ItemRepository

    // Live data for item
    private val itemLiveData: MutableLiveData<Item> = MutableLiveData()
    val _itemLiveData : LiveData<Item> = itemLiveData

    init {
        getItem()
    }

    private fun getItem () {
        itemRepository.getItemById(SharedPreferenceManager.getInstance()!!.currentProgress,
           object : ItemCallback {
               override fun onLoadItemSuccess(item: Item) {
                   itemLiveData.value = item
               }

               override fun onLoadItemError(e: Throwable) {
                  errorMessage.value = e.message
               }
           })
    }
}