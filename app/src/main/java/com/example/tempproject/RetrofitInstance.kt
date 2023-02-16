package com.example.tempproject

import com.example.tempproject.bininfo.BinInfo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://lookup.binlist.net"

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchBINAPI: SearchBINAPI = retrofit.create(SearchBINAPI::class.java)
}

interface SearchBINAPI {
    @GET("/{number}")
    fun getUserBIN(@Path("number") number: Int): Call<BinInfo> //4571 7360
}