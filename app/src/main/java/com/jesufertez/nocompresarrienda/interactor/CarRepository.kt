package com.jesufertez.nocompresarrienda.interactor

import com.jesufertez.nocompresarrienda.model.database.CarApplication
import com.jesufertez.nocompresarrienda.remote.ApiCar

class CarRepository ( private val apiCar: ApiCar){

    suspend fun getCars() =apiCar.getCars()
    suspend fun getDataCars()= CarApplication.database.registroDao().getAll()
    suspend fun getDetailId(id:Int)= CarApplication.database.registroDao().getId(id)

}