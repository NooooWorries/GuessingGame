package com.czxbnb.guessinggame.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.czxbnb.guessinggame.ui.question.QuestionViewModel
import com.czxbnb.guessinggame.ui.question.headline.HeadlineViewModel
import com.czxbnb.guessinggame.ui.result.ResultViewModel
import com.czxbnb.guessinggame.ui.splash.SplashViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(QuestionViewModel::class.java) -> {
                return QuestionViewModel() as T
            }
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                return SplashViewModel() as T
            }
            modelClass.isAssignableFrom(HeadlineViewModel::class.java) -> {
                return HeadlineViewModel() as T
            }
            modelClass.isAssignableFrom(ResultViewModel::class.java) -> {
                return ResultViewModel() as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}