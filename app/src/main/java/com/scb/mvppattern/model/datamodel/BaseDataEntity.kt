package com.scb.mvppattern.model.datamodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class BaseDataEntity(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: Data
)

data class Data(
    @SerializedName("stats") val stats: Stats,
    @SerializedName("coins") val coins: List<Coins>
)

@Parcelize
data class Coins(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("name") val name: String,
    @SerializedName("color") val color: String,
    @SerializedName("iconUrl") val iconUrl: String,
    @SerializedName("marketCap") val marketCap: String,
    @SerializedName("price") val price: Double,
    @SerializedName("listedAt") val listedAt: Int,
    @SerializedName("tier") val tier: Int,
    @SerializedName("change") val change: Double,
    @SerializedName("rank") val rank: Int,
    @SerializedName("sparkline") val sparkline: List<Double>,
    @SerializedName("lowVolume") val lowVolume: Boolean,
    @SerializedName("coinrankingUrl") val coinrankingUrl: String,
    @SerializedName("24hVolume") val twentyFourHVolume : String,
    @SerializedName("btcPrice") val btcPrice: Double
): Parcelable

data class Stats(
    @SerializedName("total") val total: Int,
    @SerializedName("referenceCurrencyRate") val referenceCurrencyRate: Int,
    @SerializedName("totalCoins") val totalCoins: Int,
    @SerializedName("totalMarkets") val totalMarkets: Int,
    @SerializedName("totalExchanges") val totalExchanges: Int,
    @SerializedName("totalMarketCap") val totalMarketCap: String,
    @SerializedName("total24hVolume") val total24hVolume: String,
    @SerializedName("btcDominance") val btcDominance: Double,
    @SerializedName("bestCoins") val bestCoins: List<BestCoins>,
    @SerializedName("newestCoins") val newestCoins: List<NewestCoins>
)

data class BestCoins(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("name") val name: String,
    @SerializedName("iconUrl") val iconUrl: String,
    @SerializedName("coinrankingUrl") val coinrankingUrl: String
)

data class NewestCoins(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("name") val name: String,
    @SerializedName("iconUrl") val iconUrl: String,
    @SerializedName("coinrankingUrl") val coinrankingUrl: String
)