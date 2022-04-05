package com.scb.mvppattern.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.scb.mvppattern.R
import com.scb.mvppattern.adapter.MyAdapter
import com.scb.mvppattern.interfaces.CoinContractor
import com.scb.mvppattern.model.datamodel.Coins
import com.scb.mvppattern.presenter.MainPresenter

class MainActivity : AppCompatActivity(), CoinContractor.View {

    @BindView(R.id.rvList)
    lateinit var rvList: RecyclerView

    @BindView(R.id.pbLoadingView)
    lateinit var pbLoadingView: ProgressBar

    @BindView(R.id.swipeRefreshLayout)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        mainPresenter = MainPresenter(this)
        initView()
    }

    private fun initView() {
        mainPresenter.getAllCoins()
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            mainPresenter.getAllCoins()
        }
    }

    override fun updateViewData(coins: List<Coins>?) {
        rvList.layoutManager = LinearLayoutManager(this)
        coins?.let {
            val myAdapter = MyAdapter(it, this)
            rvList.adapter = myAdapter
            rvList.isNestedScrollingEnabled = false
        }
    }

    override fun showProgressBarLoading(isLoading: Boolean) {
        pbLoadingView.visibility = if (isLoading) VISIBLE else GONE
    }
}