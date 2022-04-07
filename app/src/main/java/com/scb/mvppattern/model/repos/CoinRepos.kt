package com.scb.mvppattern.model.repos

import android.util.Log
import com.scb.mvppattern.interfaces.CoinContractor
import com.scb.mvppattern.interfaces.Constant
import com.scb.mvppattern.model.api.CoinAPI
import com.scb.mvppattern.model.api.CoinService
import com.scb.mvppattern.model.datamodel.BaseDataEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinRepos : CoinContractor.CoinModel {

    private var apiclient: CoinService? = null

    init {
        apiclient = CoinAPI.client.create(CoinService::class.java)
    }

    override fun getCoin(presenter: CoinContractor.Presenter) {
        val call = apiclient?.getCoin(Constant.API_KEY)
        call?.enqueue(object : Callback<BaseDataEntity> {
            override fun onResponse(
                call: Call<BaseDataEntity>,
                response: Response<BaseDataEntity>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.apply {
                        presenter.getCoinsSuccess(
                            stats.bestCoins, coins
                        )
                    }
                } else {
                    presenter.getCoinsFailed()
                }
            }

            override fun onFailure(call: Call<BaseDataEntity>, t: Throwable) {
                presenter.getCoinsFailed()
                Log.d("error: ", t.toString())
            }
        })
    }
}