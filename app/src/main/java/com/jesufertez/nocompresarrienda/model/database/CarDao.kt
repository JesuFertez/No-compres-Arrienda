package com.jesufertez.nocompresarrienda.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jesufertez.nocompresarrienda.model.Car

@Dao
interface CarDao {

    @Query("SElECT * FROM car_entity")
    suspend fun getAll(): List<CarEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertCars(cars:List<CarEntity>)
}