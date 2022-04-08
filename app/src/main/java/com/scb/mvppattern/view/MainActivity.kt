package com.scb.mvppattern.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.scb.mvppattern.R
import com.scb.mvppattern.adapter.CoinsAdapter
import com.scb.mvppattern.adapter.HeaderAdapter
import com.scb.mvppattern.adapter.HorizontalAdapter
import com.scb.mvppattern.adapter.TopCoinsAdapter
import com.scb.mvppattern.interfaces.CoinClickListener
import com.scb.mvppattern.interfaces.CoinContractor
import com.scb.mvppattern.model.datamodel.BestCoins
import com.scb.mvppattern.model.datamodel.Coins
import com.scb.mvppattern.presenter.MainPresenter

class MainActivity : AppCompatActivity(), CoinContractor.View, CoinClickListener {

    @BindView(R.id.rvList)
    lateinit var rvList: RecyclerView

    @BindView(R.id.pbLoadingView)
    lateinit var pbLoadingView: ProgressBar

    @BindView(R.id.swipeRefreshLayout)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @BindView(R.id.coinNestedScrollView)
    lateinit var coinNestedScrollView: NestedScrollView

    @BindView(R.id.layout_error)
    lateinit var layoutError: LinearLayout

    @BindView(R.id.tvErrorTapToRetry)
    lateinit var tvErrorTapToRetry: TextView

    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        mainPresenter = MainPresenter(this)
        initView()
        tvErrorTapToRetry.setOnClickListener {
            mainPresenter.getAllCoins()
            Toast.makeText(
                this@MainActivity,
                "Click Retry",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initView() {
        mainPresenter.getAllCoins()
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            mainPresenter.getAllCoins()
        }
    }

    override fun updateViewData(bestCoins: List<BestCoins>?, coins: List<Coins>?) {
        val topCoinsAdapter = bestCoins?.let { TopCoinsAdapter(it, this, this) }
        val coinsAdapter = coins?.let { CoinsAdapter(it, this, this) }
        val concatAdapter = ConcatAdapter(
            HeaderAdapter(this, "Top coins"),
            topCoinsAdapter?.let {
                HorizontalAdapter(
                    this,
                    it
                )
            },
            HeaderAdapter(this, "Coins"),
            coinsAdapter,
        )
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = concatAdapter
        layoutError.visibility = GONE
        coinNestedScrollView.visibility = VISIBLE
    }

    override fun showProgressBarLoading(isLoading: Boolean) {
        pbLoadingView.visibility = if (isLoading) VISIBLE else GONE
    }

    override fun showGetCoinsFailed() {
        coinNestedScrollView.visibility = GONE
        layoutError.visibility = VISIBLE
    }

    override fun onCoinClickListener(uuid: String) {
        startActivity(CoinDetailActivity.createIntent(this, uuid))
    }
}