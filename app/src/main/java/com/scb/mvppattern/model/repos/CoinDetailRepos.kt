package com.scb.mvppattern.model.repos

import android.util.Log
import com.scb.mvppattern.interfaces.CoinDetailContractor
import com.scb.mvppattern.interfaces.Constant
import com.scb.mvppattern.model.api.CoinAPI
import com.scb.mvppattern.model.api.CoinService
import com.scb.mvppattern.model.datamodel.BaseDataCoinDetailEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinDetailRepos : CoinDetailContractor.CoinModel {

    private var apiclient: CoinService? = null

    init {
        apiclient = CoinAPI.client.create(CoinService::class.java)
    }

    override fun getCoinWithUuid(presenter: CoinDetailContractor.Presenter, uuid: String) {
        val call = apiclient?.getCoinWithUuid(Constant.API_KEY, uuid = uuid)
        call?.enqueue(object : Callback<BaseDataCoinDetailEntity> {
            override fun onResponse(
                call: Call<BaseDataCoinDetailEntity>,
                response: Response<BaseDataCoinDetailEntity>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.coin?.apply {
                        presenter.getCoinsDetailSuccess(this)
                    }
                } else {
                    presenter.getCoinsDetailFailed()
                }
            }

            override fun onFailure(call: Call<BaseDataCoinDetailEntity>, t: Throwable) {
                presenter.getCoinsDetailFailed()
                Log.d("error: ", t.toString())
            }
        })
    }
}