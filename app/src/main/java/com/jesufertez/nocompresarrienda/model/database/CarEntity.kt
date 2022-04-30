package com.jesufertez.nocompresarrienda.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.jesufertez.nocompresarrienda.model.Attributes

@Entity (tableName = "car_entity")

data class CarEntity (
    @ColumnInfo @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val image : String,
    @ColumnInfo val monthlyPrice : String,
    @ColumnInfo val carType: String,
    @ColumnInfo val buildYear : Int,
    @ColumnInfo val description:String,
    @ColumnInfo val availableForRent: Boolean,
    @ColumnInfo val kilometersByYear: Int,
    @ColumnInfo val AdditionalKilometersPrice: String,
    //@ColumnInfo val attributes : AttributesEntity
        )