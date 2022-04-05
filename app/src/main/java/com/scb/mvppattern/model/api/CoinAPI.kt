package com.scb.mvppattern.model.api

import com.scb.mvppattern.interfaces.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinAPI {
    companion object{

        val client: Retrofit get() {
            return Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}