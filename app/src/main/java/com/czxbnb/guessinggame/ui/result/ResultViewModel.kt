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

    // Boolean for match result
    private val isMatchLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val _isMatchLiveData = isMatchLiveData

    // Live data for current score
    private val currentScoreLiveData: MutableLiveData<Int> = MutableLiveData()
    val _currentScoreLiveData : LiveData<Int> = currentScoreLiveData

    // Preference manager
    private val preferenceManager = SharedPreferenceManager.getInstance()

    fun getData(bundle: Bundle) {
        itemRepository.getItemById(
            SharedPreferenceManager.getInstance()!!.currentProgress,
            object : ItemCallback {
                override fun onLoadItemSuccess(item: Item) {
                    itemLiveData.value = item
                    isMatchLiveData.value = bundle.getInt("selectedIndex") == item.correctAnswerIndex

                    // Set scores and progress
                    if (isMatchLiveData.value == true) {
                        preferenceManager!!.currentScore += 2
                    } else {
                        if (preferenceManager!!.currentScore >= 1) {
                            preferenceManager.currentScore -=1
                        }
                    }
                    preferenceManager!!.currentProgress += 1
                    currentScoreLiveData.value = preferenceManager!!.currentScore
                }

                override fun onLoadItemError(e: Throwable) {
                    errorMessage.value = e.message
                }
            })
    }
}