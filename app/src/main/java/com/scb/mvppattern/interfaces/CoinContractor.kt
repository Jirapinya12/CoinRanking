package com.scb.mvppattern.interfaces

import com.scb.mvppattern.model.datamodel.BestCoins
import com.scb.mvppattern.model.datamodel.Coins

interface CoinContractor {
    interface CoinModel {
        fun getCoin(presenter: Presenter)
    }
    interface View{
        fun updateViewData(bestCoins: List<BestCoins>?, coins: List<Coins>?)
        fun showProgressBarLoading(isLoading: Boolean)
    }

    interface Presenter{
        fun getAllCoins()
        fun getCoinsSuccess(bestCoins: List<BestCoins>?, coins: List<Coins>?)
        fun getCoinsFailed()
    }
}