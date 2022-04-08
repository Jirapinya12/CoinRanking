package com.scb.mvppattern.presenter

import com.scb.mvppattern.interfaces.CoinDetailContractor
import com.scb.mvppattern.model.datamodel.Coin
import com.scb.mvppattern.model.repos.CoinDetailRepos

class CoinDetailPresenter(view: CoinDetailContractor.View) : CoinDetailContractor.Presenter {

    private var view: CoinDetailContractor.View = view
    private var model: CoinDetailContractor.CoinModel = CoinDetailRepos()

    override fun getCoinsDetail(uuid: String?) {
        view.showProgressBarLoading(isLoading = true)
        uuid?.let { model.getCoinWithUuid(this, uuid = it) }
    }

    override fun getCoinsDetailSuccess(coin: Coin) {
        view.apply {
            updateViewCoinDetailData(coin)
            showProgressBarLoading(isLoading = false)
        }
    }

    override fun getCoinsDetailFailed() {
        view.apply {
            showProgressBarLoading(isLoading = false)
            showGetCoinsDetailFailed()
        }
    }
}