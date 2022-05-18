package com.jesufertez.nocompresarrienda.model.database

import androidx.room.ColumnInfo

data class AttributesEntity (
    @ColumnInfo val engine : String,
    @ColumnInfo val transmition: String,
    @ColumnInfo val entertainment:String,
    @ColumnInfo val security: String
        )