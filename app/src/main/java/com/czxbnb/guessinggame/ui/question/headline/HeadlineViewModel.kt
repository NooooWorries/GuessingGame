package com.czxbnb.guessinggame.ui.question.headline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.czxbnb.guessinggame.base.BaseViewModel

class HeadlineViewModel : BaseViewModel() {
    private val headlineLiveData = MutableLiveData<String>()
    val _headlineLiveData : LiveData<String> = headlineLiveData

    fun bind(headline: String) {
        headlineLiveData.value = headline
    }
}