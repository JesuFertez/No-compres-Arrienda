package com.jesufertez.nocompresarrienda.model

data class Car(
    val id: Int,
    val name: String,
    val image : String,
    val monthlyPrice : String,
    val carType: String,
    val buildYear : Int,
    val description:String,
    val availableForRent: Boolean,
    val kilometersByYear: Int,
    val AdditionalKilometersPrice: String,
    val attributes : Attributes
        ){
}
