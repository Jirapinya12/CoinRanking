package com.scb.mvppattern.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.scb.mvppattern.R

class HorizontalAdapter(
    private val context: Context,
    private val topCoinsAdapter: TopCoinsAdapter,
) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val view = LayoutInflater.from(context).inflate(R.layout.list_top_coin, parent, false)
        return ConcatViewHolder(view)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(topCoinsAdapter)
            else -> throw IllegalArgumentException(
                "No viewholder to show this data, did you forgot to add it to the onBindViewHolder?"
            )
        }
    }

    inner class ConcatViewHolder(itemView: View) : BaseConcatHolder<TopCoinsAdapter>(itemView) {

        @BindView(R.id.rv_top_coin)
        lateinit var rvConcat: RecyclerView

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun bind(adapter: TopCoinsAdapter) {
            rvConcat.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvConcat.adapter = adapter
        }
    }
}