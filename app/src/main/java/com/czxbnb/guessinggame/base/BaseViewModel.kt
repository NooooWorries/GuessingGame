package com.czxbnb.guessinggame.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.czxbnb.guessinggame.injection.component.DaggerViewModelComponent
import com.czxbnb.guessinggame.injection.component.ViewModelComponent
import com.czxbnb.guessinggame.injection.module.RepositoryModule
import com.czxbnb.guessinggame.ui.question.QuestionViewModel
import com.czxbnb.guessinggame.ui.splash.SplashViewModel
import java.net.ConnectException

abstract class BaseViewModel : ViewModel() {
    private val component: ViewModelComponent = DaggerViewModelComponent
        .builder()
        .repositoryModule(RepositoryModule)
        .build()

    val errorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is QuestionViewModel -> component.inject(this)
            is SplashViewModel -> component.inject(this)
        }
    }

    fun onErrorOccurred(e: Throwable) {
        if (e is ConnectException){
            errorMessage.value = "Please check your network"
        } else {
            errorMessage.value = "Unknown error occurred, please try again later."
        }
    }
}