package com.jesufertez.nocompresarrienda.remote

import com.jesufertez.nocompresarrienda.model.Car
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiCar {
    @GET("cars")
    suspend fun getCars():Response<List<Car>>

    @GET("cars/{id}")
    suspend fun getDetailCar(@Path("id") id :String):Response<Car>
}
