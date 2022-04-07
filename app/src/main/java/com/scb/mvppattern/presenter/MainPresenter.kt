package com.scb.mvppattern.presenter

import com.scb.mvppattern.interfaces.CoinContractor
import com.scb.mvppattern.model.datamodel.BestCoins
import com.scb.mvppattern.model.datamodel.Coins
import com.scb.mvppattern.model.repos.CoinRepos

class MainPresenter(view : CoinContractor.View): CoinContractor.Presenter {

    private var view:  CoinContractor.View = view
    private var model:  CoinContractor.CoinModel = CoinRepos()

    override fun getAllCoins() {
        view.showProgressBarLoading(isLoading = true)
        model.getCoin(this)
    }

    override fun getCoinsSuccess(bestCoins: List<BestCoins>?, coins: List<Coins>?) {
        view.apply {
            updateViewData(bestCoins, coins)
            showProgressBarLoading(isLoading = false)
        }
    }

    override fun getCoinsFailed() {
        view.showProgressBarLoading(isLoading = true)
    }
}