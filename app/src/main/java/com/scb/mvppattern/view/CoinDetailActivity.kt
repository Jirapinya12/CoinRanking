package com.scb.mvppattern.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.scb.mvppattern.R
import com.scb.mvppattern.model.datamodel.Coins

class CoinDetailActivity : AppCompatActivity() {

    @BindView(R.id.tvNameHead)
    lateinit var tvNameHead: TextView

    @BindView(R.id.tvNameData)
    lateinit var tvNameData: TextView

    @BindView(R.id.tvBtcPriceData)
    lateinit var tvBtcPriceData: TextView

    @BindView(R.id.tvSymbolData)
    lateinit var tvSymbolData: TextView

    @BindView(R.id.tvMarketCapData)
    lateinit var tvMarketCapData: TextView

    @BindView(R.id.tvPriceData)
    lateinit var tvPriceData: TextView

    @BindView(R.id.tvChangeData)
    lateinit var tvChangeData: TextView

    @BindView(R.id.tv24hVolumeData)
    lateinit var tv24hVolumeData: TextView

    @BindView(R.id.tvCoinrankingUrlData)
    lateinit var tvCoinRankingUrlData: TextView

    @BindView(R.id.tvColorData)
    lateinit var tvColorData: TextView

    @BindView(R.id.ivCoin)
    lateinit var ivCoin: ImageView

    @BindView(R.id.ivColor)
    lateinit var ivColor: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        ButterKnife.bind(this)
        initView()
    }

    private fun initView() {
        val coins = intent?.getParcelableExtra<Coins>(EXTRA_COIN_MODEL)
        coins?.apply {
            tvNameHead.text = name
            tvNameData.text = name
            tvBtcPriceData.text = btcPrice.toString()
            tvSymbolData.text = symbol
            tvMarketCapData.text = marketCap
            tvPriceData.text = price.toString()
            tvChangeData.text = change.toString()
            tv24hVolumeData.text = twentyFourHVolume
            tvCoinRankingUrlData.text = coinrankingUrl
            tvColorData.text = color
            ivColor.setBackgroundColor(Color.parseColor(color))
            ivCoin.loadImageFromUrl(iconUrl, getProgressDrawable(context = this@CoinDetailActivity))
        }
    }

    companion object {
        const val EXTRA_COIN_MODEL = "EXTRA_COIN_MODEL"

        @JvmStatic
        fun createIntent(
            context: Context,
            coins: Coins
        ): Intent {
            return Intent(context, CoinDetailActivity::class.java).apply {
                putExtra(EXTRA_COIN_MODEL, coins)
            }
        }
    }
}