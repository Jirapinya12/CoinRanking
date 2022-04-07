package com.scb.mvppattern.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.scb.mvppattern.R

class HeaderAdapter(
    private val context: Context,
    private val header: String,
) :
    RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeaderAdapter.HeaderViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(header)
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tvHeader)
        lateinit var tvHeader: TextView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(header: String) {
            tvHeader.text = header
        }
    }
}