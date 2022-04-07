package com.scb.mvppattern.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.scb.mvppattern.interfaces.CoinClickListener

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindItem(item: T, coinClickListener: CoinClickListener)
}