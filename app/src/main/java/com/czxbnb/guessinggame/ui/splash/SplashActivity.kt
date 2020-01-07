package com.czxbnb.guessinggame.ui.splash

import android.os.Bundle
import com.czxbnb.guessinggame.R
import com.czxbnb.guessinggame.base.BaseActivity
import com.czxbnb.guessinggame.databinding.ActivitySplashBinding

class SplashActivity :
    BaseActivity<SplashViewModel, ActivitySplashBinding>(SplashViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.viewModel = getActivityViewModel()
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_splash
    }

}