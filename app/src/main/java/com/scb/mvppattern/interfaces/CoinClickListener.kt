package com.scb.mvppattern.interfaces

import com.scb.mvppattern.model.datamodel.Coins

interface CoinClickListener {
    fun onCoinClickListener(data: Coins)
}