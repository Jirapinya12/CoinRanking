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

class MyAdapter(
    var value: List<Coins>,
    var context: Context,
    var coinClickListener: CoinClickListener
) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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

        fun bindItem(model: Coins, coinClickListener: CoinClickListener) {
            tvTitle.text = model.name
            tvDesc.text = model.btcPrice.toString()
            iv.loadImageFromUrl(model.iconUrl, progressDrawable)
            itemView.setOnClickListener {
                coinClickListener.onCoinClickListener(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(value[position], coinClickListener)
    }

    override fun getItemCount(): Int {
        return value.size
    }
}