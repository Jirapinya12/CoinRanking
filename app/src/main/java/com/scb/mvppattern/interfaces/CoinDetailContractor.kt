package com.scb.mvppattern.interfaces

import com.scb.mvppattern.model.datamodel.Coin

interface CoinDetailContractor {
    interface CoinModel {
        fun getCoinWithUuid(presenter: CoinDetailContractor.Presenter, uuid: String)
    }

    interface View {
        fun updateViewCoinDetailData(coins: Coin)
        fun showProgressBarLoading(isLoading: Boolean)
        fun showGetCoinsDetailFailed()
    }

    interface Presenter {
        fun getCoinsDetail(uuid: String?)
        fun getCoinsDetailSuccess(coin: Coin)
        fun getCoinsDetailFailed()
    }
}
