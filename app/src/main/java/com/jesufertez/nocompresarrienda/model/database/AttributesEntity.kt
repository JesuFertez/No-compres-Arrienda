package com.jesufertez.nocompresarrienda.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attributes_entity")
data class AttributesEntity (
    @ColumnInfo val engine : String,
    @ColumnInfo val transmition: String,
    @ColumnInfo val entertainment:String,
    @ColumnInfo val security: String
        )