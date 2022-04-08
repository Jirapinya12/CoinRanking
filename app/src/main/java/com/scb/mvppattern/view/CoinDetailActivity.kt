package com.scb.mvppattern.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import butterknife.BindView
import butterknife.ButterKnife
import com.scb.mvppattern.R
import com.scb.mvppattern.interfaces.CoinDetailContractor
import com.scb.mvppattern.model.datamodel.Coin
import com.scb.mvppattern.presenter.CoinDetailPresenter

class CoinDetailActivity : AppCompatActivity(), CoinDetailContractor.View {

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

    @BindView(R.id.pbLoadingView)
    lateinit var pbLoadingView: ProgressBar

    @BindView(R.id.nestedScrollViewCoinDetail)
    lateinit var nestedScrollViewCoinDetail: NestedScrollView

    @BindView(R.id.layout_error)
    lateinit var layoutError: LinearLayout

    @BindView(R.id.tvErrorTapToRetry)
    lateinit var tvErrorTapToRetry: TextView

    private lateinit var coinDetailPresenter: CoinDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        ButterKnife.bind(this)
        initView()
    }

    private fun initView() {
        val uuid = intent?.getStringExtra(EXTRA_UUID)
        coinDetailPresenter = CoinDetailPresenter(this)
        coinDetailPresenter.getCoinsDetail(uuid)
        tvErrorTapToRetry.setOnClickListener {
            coinDetailPresenter.getCoinsDetail(uuid)
            Toast.makeText(
                this@CoinDetailActivity,
                "Click Retry",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        const val EXTRA_UUID = "EXTRA_COIN_MODEL"

        @JvmStatic
        fun createIntent(
            context: Context,
            uuid: String
        ): Intent {
            return Intent(context, CoinDetailActivity::class.java).apply {
                putExtra(EXTRA_UUID, uuid)
            }
        }
    }

    override fun updateViewCoinDetailData(coins: Coin) {
        nestedScrollViewCoinDetail.visibility = VISIBLE
        layoutError.visibility = GONE
        coins.apply {
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
            color?.let {
                ivColor.setBackgroundColor(Color.parseColor(it))
            }
            iconUrl?.let {
                ivCoin.loadImageFromUrl(
                    it,
                    getProgressDrawable(context = this@CoinDetailActivity)
                )
            }
        }
    }

    override fun showProgressBarLoading(isLoading: Boolean) {
        pbLoadingView.visibility = if (isLoading) VISIBLE else GONE
    }

    override fun showGetCoinsDetailFailed() {
        nestedScrollViewCoinDetail.visibility = GONE
        layoutError.visibility = VISIBLE
    }
}