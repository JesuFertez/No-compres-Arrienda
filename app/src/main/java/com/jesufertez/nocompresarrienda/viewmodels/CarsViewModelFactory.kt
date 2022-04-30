package com.jesufertez.nocompresarrienda.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jesufertez.nocompresarrienda.interactor.CarRepository
import java.lang.IllegalArgumentException

class CarsViewModelFactory (private val repository:CarRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CarsViewModel::class.java)){
            return CarsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}