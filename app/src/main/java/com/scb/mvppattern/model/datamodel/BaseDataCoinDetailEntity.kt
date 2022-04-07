package com.scb.mvppattern.model.datamodel

import com.google.gson.annotations.SerializedName


data class BaseDataCoinDetailEntity(
    @SerializedName("status") var status: String? = null,
    @SerializedName("data") var data: DataCoinDetail?
)

data class DataCoinDetail(
    @SerializedName("coin") var coin: Coin?
)

data class Coin(
    @SerializedName("uuid") var uuid: String? = null,
    @SerializedName("symbol") var symbol: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("color") var color: String? = null,
    @SerializedName("iconUrl") var iconUrl: String? = null,
    @SerializedName("websiteUrl") var websiteUrl: String? = null,
    @SerializedName("links") var links: ArrayList<Links> = arrayListOf(),
    @SerializedName("supply") var supply: Supply? = Supply(),
    @SerializedName("numberOfMarkets") var numberOfMarkets: Int? = null,
    @SerializedName("numberOfExchanges") var numberOfExchanges: Int? = null,
    @SerializedName("24hVolume") var twentyFourHVolume: String? = null,
    @SerializedName("marketCap") var marketCap: String? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("btcPrice") var btcPrice: String? = null,
    @SerializedName("priceAt") var priceAt: Int? = null,
    @SerializedName("change") var change: String? = null,
    @SerializedName("rank") var rank: Int? = null,
    @SerializedName("sparkline") var sparkline: ArrayList<String> = arrayListOf(),
    @SerializedName("allTimeHigh") var allTimeHigh: AllTimeHigh? = AllTimeHigh(),
    @SerializedName("coinrankingUrl") var coinrankingUrl: String? = null,
    @SerializedName("tier") var tier: Int? = null,
    @SerializedName("lowVolume") var lowVolume: Boolean? = null,
    @SerializedName("listedAt") var listedAt: Int? = null
)

data class AllTimeHigh(
    @SerializedName("price") var price: String? = null,
    @SerializedName("timestamp") var timestamp: Int? = null
)

data class Supply(
    @SerializedName("confirmed") var confirmed: Boolean? = null,
    @SerializedName("total") var total: String? = null,
    @SerializedName("circulating") var circulating: String? = null
)


data class Links(
    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("url") var url: String? = null
)