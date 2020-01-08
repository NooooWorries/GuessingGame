package com.czxbnb.guessinggame.ui.question

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.czxbnb.guessinggame.R
import com.czxbnb.guessinggame.base.BaseActivity
import com.czxbnb.guessinggame.databinding.ActivityQuestionBinding
import com.czxbnb.guessinggame.manager.SharedPreferenceManager
import kotlinx.android.synthetic.main.activity_question.*


class QuestionActivity :
        BaseActivity<QuestionViewModel, ActivityQuestionBinding>(QuestionViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.viewModel = getActivityViewModel()
        dataBinding.rvHeadline.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Add on click listener for skip
        btn_skip.setOnClickListener {
            SharedPreferenceManager.getInstance()!!.currentProgress += 1

            val intent = intent
            finish()
            startActivity(intent)
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_question
    }
}