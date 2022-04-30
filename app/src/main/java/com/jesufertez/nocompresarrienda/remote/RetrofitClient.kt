package com.jesufertez.nocompresarrienda.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val URL_BASE ="https://cars-api-test.herokuapp.com/"
    private val retro = RetrofitClient

    val retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCarsApi(): ApiCar{
        return retrofit.create(ApiCar::class.java)
    }
}