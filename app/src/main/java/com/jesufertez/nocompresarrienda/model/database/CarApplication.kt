package com.jesufertez.nocompresarrienda.model.database

import android.app.Application
import androidx.room.Room

class CarApplication : Application(){

    companion object{
        lateinit var database : CarDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, CarDatabase::class.java,"car_database").build()
    }
}