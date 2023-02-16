package com.example.tempproject

import androidx.lifecycle.ViewModel
import com.example.tempproject.bininfo.Bank
import com.example.tempproject.bininfo.BinInfo
import com.example.tempproject.bininfo.Country
import com.example.tempproject.bininfo.Number
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel() : ViewModel() {

    var lastBINInfo = MutableStateFlow(
        BinInfo(bank = Bank(), country = Country(), number = Number())
    )
    var isFailure = MutableStateFlow("Запроса не было")

    fun searchEnterBIN(enterBIN: Int) {
        RetrofitInstance.searchBINAPI.getUserBIN(enterBIN).enqueue(object : Callback<BinInfo> {
            override fun onResponse(call: Call<BinInfo>, response: Response<BinInfo>) {
                if (response.isSuccessful) {
                    lastBINInfo.value = response.body()!!
                    isFailure.value = "Успех"
                }
            }

            override fun onFailure(call: Call<BinInfo>, t: Throwable) {
                isFailure.value = t.toString()
            }
        })
    }
}
