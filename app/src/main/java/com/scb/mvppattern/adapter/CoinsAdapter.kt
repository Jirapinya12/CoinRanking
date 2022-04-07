package com.scb.mvppattern.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.scb.mvppattern.R
import com.scb.mvppattern.interfaces.CoinClickListener
import com.scb.mvppattern.model.datamodel.Coins
import com.scb.mvppattern.view.getProgressDrawable
import com.scb.mvppattern.view.loadImageFromUrl

class CoinsAdapter(
    var value: List<Coins>,
    var context: Context,
    var coinClickListener: CoinClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return CoinViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return value.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is CoinViewHolder -> holder.bindItem(
                value[position],
                coinClickListener
            )
            else -> throw IllegalArgumentException(
                "No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    inner class CoinViewHolder(itemView: View) : BaseViewHolder<Coins>(itemView) {

        @BindView(R.id.iv)
        lateinit var iv: ImageView

        @BindView(R.id.tvTitle)
        lateinit var tvTitle: TextView

        @BindView(R.id.tvDesc)
        lateinit var tvDesc: TextView

        private val progressDrawable = getProgressDrawable(itemView.context)

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun bindItem(model: Coins, coinClickListener: CoinClickListener) {
            tvTitle.text = model.name
            tvDesc.text = model.btcPrice.toString()
            iv.loadImageFromUrl(model.iconUrl, progressDrawable)
            itemView.setOnClickListener {
                coinClickListener.onCoinClickListener(model)
            }
        }
    }
}