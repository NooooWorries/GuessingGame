package com.czxbnb.guessinggame.ui.question.headline

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.czxbnb.guessinggame.R
import com.czxbnb.guessinggame.databinding.ItemHeadlineBinding
import com.czxbnb.guessinggame.ui.result.ResultActivity
import com.google.gson.Gson

class HeadlineAdapter : RecyclerView.Adapter<HeadlineAdapter.ViewHolder>() {
    private var headlineList: ArrayList<String> = ArrayList()
    private lateinit var binding: ItemHeadlineBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_headline, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(headlineList[position])

        binding.cvHeadline.setOnClickListener {
            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra("selectedIndex", position)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return  headlineList.size
    }

    fun updateHeadlineList(headlineList: List<String>) {
        this.headlineList.clear()
        this.headlineList.addAll(headlineList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemHeadlineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = HeadlineViewModel()

        fun bind(headline: String) {
            viewModel.bind(headline)
            binding.viewModel = viewModel
        }
    }
}