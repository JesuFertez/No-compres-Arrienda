package com.jesufertez.nocompresarrienda.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesufertez.nocompresarrienda.interactor.CarRepository
import com.jesufertez.nocompresarrienda.model.Car
import com.jesufertez.nocompresarrienda.model.database.CarApplication
import com.jesufertez.nocompresarrienda.util.CarConverter
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response

class CarsViewModel(private val carsRepository: CarRepository) : ViewModel() {

    private val mutableState = MutableLiveData<List<Car>>()
    fun state(): LiveData<List<Car>> = mutableState

    fun getCars() {
        viewModelScope.launch {
            val cars = carsRepository.getCars()
            handleState(cars)
        }
    }

    private fun handleState(cars: Response<List<Car>>) {
        if (cars.isSuccessful) {
            handleBody(cars.body())
        }
    }

    private fun handleBody(body: List<Car>?) {
        body?.let { safeBody ->
            mutableState.postValue(safeBody)
           viewModelScope.launch {
                CarApplication.database.registroDao()
                    .insertCars(CarConverter.converterCarToEntity(safeBody))
            }
        }
    }
    fun getDatacars(){
        viewModelScope.launch {
            val carsData= carsRepository.getDataCars()
            val body = CarConverter.converterEntityToCar(carsData)
            handleBody(body)
        }
    }
}