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
import com.scb.mvppattern.model.datamodel.BestCoins
import com.scb.mvppattern.view.getProgressDrawable
import com.scb.mvppattern.view.loadImageFromUrl

class TopCoinsAdapter(
    private var value: List<BestCoins>,
    var context: Context,
    var coinClickListener: CoinClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    companion object {
        const val ITEMS_PER_PAGE = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemWidth: Int = parent.width / ITEMS_PER_PAGE
        val view = LayoutInflater.from(context).inflate(R.layout.item_top_coin, parent, false)
        val layoutParams: ViewGroup.LayoutParams = view.layoutParams
        layoutParams.width = itemWidth
        view.layoutParams = layoutParams
        return AnimalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return value.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is AnimalViewHolder -> holder.bindItem(
                value[position],
                coinClickListener
            )
            else -> throw IllegalArgumentException(
                "No viewholder to show this data, did you forgot to add it to the onBindViewHolder?"
            )
        }
    }

    inner class AnimalViewHolder(itemView: View) : BaseViewHolder<BestCoins>(itemView) {

        @BindView(R.id.tvTitle)
        lateinit var tvTitle: TextView

        @BindView(R.id.iv)
        lateinit var ivCoin: ImageView

        private val progressDrawable = getProgressDrawable(itemView.context)

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun bindItem(item: BestCoins, coinClickListener: CoinClickListener) {
            tvTitle.text = item.name
            ivCoin.loadImageFromUrl(item.iconUrl, progressDrawable)
            itemView.setOnClickListener {
                coinClickListener.onCoinClickListener(item.uuid)
            }
        }
    }
}