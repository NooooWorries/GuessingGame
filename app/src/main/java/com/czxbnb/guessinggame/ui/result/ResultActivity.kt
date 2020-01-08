package com.czxbnb.guessinggame.ui.result

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import com.czxbnb.guessinggame.R
import com.czxbnb.guessinggame.base.BaseActivity
import com.czxbnb.guessinggame.databinding.ActivityResultBinding
import com.czxbnb.guessinggame.ui.question.QuestionActivity
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.item_news.view.*


class ResultActivity :
    BaseActivity<ResultViewModel, ActivityResultBinding>(ResultViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.viewModel = getActivityViewModel()
        intent.extras?.let { getActivityViewModel().getData(it) }

        // When item data available
        getActivityViewModel()._itemLiveData.observe(this, Observer { item ->
            dataBinding.root.cv_item.setOnClickListener {
                // Add on click listener for news
                // Click to read the article
                val intent = Intent("android.intent.action.VIEW",
                    Uri.parse(item.storyUrl))
                startActivity(intent)
            }
        })

        // When result available
        getActivityViewModel()._isMatchLiveData.observe(this, Observer {
            // Add click listener for next question
            btn_next.setOnClickListener {
                val intent = Intent(this, QuestionActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        })
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_result
    }

}