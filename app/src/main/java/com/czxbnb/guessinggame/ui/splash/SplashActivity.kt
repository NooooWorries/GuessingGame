package com.czxbnb.guessinggame.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.czxbnb.guessinggame.R
import com.czxbnb.guessinggame.base.BaseActivity
import com.czxbnb.guessinggame.databinding.ActivitySplashBinding
import com.czxbnb.guessinggame.ui.question.QuestionActivity
import com.dd.processbutton.iml.ActionProcessButton

class SplashActivity :
    BaseActivity<SplashViewModel, ActivitySplashBinding>(SplashViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.viewModel = getActivityViewModel()

        // Configure loading button
        val btnEnroll = dataBinding.btnEnroll
        btnEnroll.setMode(ActionProcessButton.Mode.ENDLESS)
        btnEnroll.setOnClickListener {
            getActivityViewModel()._progress.observe(this, Observer {
                btnEnroll.progress = it
                if (it == 100) {
                    val intent = Intent(this, QuestionActivity::class.java)
                    startActivity(intent)
                }
            })
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_splash
    }
}