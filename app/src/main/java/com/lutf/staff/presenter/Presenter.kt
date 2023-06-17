package com.lutf.staff.presenter

import com.lutf.staff.MainActivity
import com.lutf.staff.model.ResultStaff
import retrofit2.Call
import retrofit2.Response
import com.lutf.staff.model.ResultStatus
import com.lutf.staff.network.NetworkConfig
import retrofit2.Callback

class Presenter(val crudView: MainActivity) {
    // get data
    fun getData() {
        NetworkConfig.getService().getData()
            .enqueue(object : retrofit2.Callback<ResultStaff> {
                override fun onResponse(call: Call<ResultStaff>, response: Response<ResultStaff>) {
                    if (response.isSuccessful) {
                        val status = response.body()?.status
                        if (status == 200) {
                            val data = response.body()?.staff
                            crudView.onSuccessGet(data)
                        } else {
                            crudView.onFailedGet("Error $status")
                        }
                    }
                }

                override fun onFailure(call: Call<ResultStaff>, t: Throwable) {
                    NetworkConfig.getService()
                        .getData()
                        .enqueue(object : Callback<ResultStatus> {
                            override fun onResponse(
                                call: Call<ResultStatus>,
                                response: Response<ResultStatus>
                            ) {
                                if (response.isSuccessful && response.body()?.status == 200) {
                                    crudView.onSuccessDelete(response.body()?.pesan?:"")
                                } else {
                                    crudView.onErrorDelete(response.body()?.pesan?:"")
                                }
                            }

                            override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                                crudView.onErrorDelete(t.localizedMessage)
                            }

                        })
                }

            })
    }

    // delete data
    fun hapusData(id: String?){
        NetworkConfig.getService()
            .deleteStaff(id)
            .enqueue(object : retrofit2.Callback<ResultStatus> {
                override fun onResponse(
                    call: Call<ResultStatus>,
                    response: Response<ResultStatus>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.onSuccessDelete(response.body()?.pesan?:"")
                    } else {
                        crudView.onErrorDelete(response.body()?.pesan?:"")
                    }
                }

                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDelete(t.localizedMessage)
                }

            })
    }
}

private fun <T> Call<T>.enqueue(callback: Callback<ResultStatus>) {

}



