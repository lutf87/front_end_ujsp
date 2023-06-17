package com.lutf.staff.presenter

import com.lutf.staff.UpdateAddActivity
import com.lutf.staff.model.ResultStatus
import com.lutf.staff.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Presenter2(val crudView: UpdateAddActivity) {

    // add data
    fun addData(name: String, hp: String, alamat: String) {
        NetworkConfig
            .getService()
            .addStaff(name, hp, alamat)
            .enqueue(object : Callback<ResultStatus> {
                override fun onResponse(
                    call: Call<ResultStatus>,
                    response: Response<ResultStatus>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.onSuccessAdd(response.body()?.pesan?:"")
                    } else {
                        crudView.onErrorAdd(response.body()?.pesan?:"")
                    }
                }

                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorAdd(t.localizedMessage)
                }
            })
    }

    // update data
    fun updatedata(id: String, name: String, hp: String, alamat: String) {
        NetworkConfig.getService()
            .updateStaff(id, name, hp, alamat)
            .enqueue(object : retrofit2.Callback<ResultStatus> {
                override fun onResponse(
                    call: Call<ResultStatus>,
                    response: Response<ResultStatus>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.onSuccessAdd(response.body()?.pesan?:"")
                    } else {
                        crudView.onErrorAdd(response.body()?.pesan?:"")
                    }
                }

                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorAdd(t.localizedMessage)
                }

            })
    }
}