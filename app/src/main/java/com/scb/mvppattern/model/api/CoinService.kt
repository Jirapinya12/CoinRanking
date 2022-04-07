package com.scb.mvppattern.model.api

import com.scb.mvppattern.model.datamodel.BaseDataCoinDetailEntity
import com.scb.mvppattern.model.datamodel.BaseDataEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CoinService {

    @GET("v2/coins")
    fun getCoin(@Header("x-access-token") apiKey: String): Call<BaseDataEntity>

    @GET("v2/coin/{uuid}")
    fun getCoinWithUuid(
        @Header("x-access-token") apiKey: String,
        @Path("uuid") uuid: String
    ): Call<BaseDataCoinDetailEntity>
}