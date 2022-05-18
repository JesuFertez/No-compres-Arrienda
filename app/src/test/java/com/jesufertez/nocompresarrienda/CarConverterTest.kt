package com.jesufertez.nocompresarrienda

import com.jesufertez.nocompresarrienda.model.Car
import com.jesufertez.nocompresarrienda.model.database.CarEntity
import com.jesufertez.nocompresarrienda.util.CarConverter
import org.junit.Assert.*
import org.junit.Test

class CarConverterTest{

    @Test
    fun convertirCarEntitySucces() {
        val car = mutableListOf<Car>()
        val resultExpected = mutableListOf<CarEntity>()
        val result = CarConverter.converterCarToEntity(car)
        assertEquals("Convertir un car en una entidad",resultExpected,result)
    }

    @Test
    fun convertirCarSuccesTest(){
        val carEntity= mutableListOf<CarEntity>()
        val resultExpected = mutableListOf<Car>()
        val result = CarConverter.converterEntityToCar(carEntity)
        assertEquals("Convertir una entidad en car",resultExpected,result)
    }
}