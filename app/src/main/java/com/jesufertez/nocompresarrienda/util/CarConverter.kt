package com.jesufertez.nocompresarrienda.util

import com.jesufertez.nocompresarrienda.model.Attributes
import com.jesufertez.nocompresarrienda.model.Car
import com.jesufertez.nocompresarrienda.model.database.AttributesEntity
import com.jesufertez.nocompresarrienda.model.database.CarEntity

class CarConverter {

    companion object {
        fun converterCarToEntity(cars: List<Car>): List<CarEntity> {
            val carEntity = mutableListOf<CarEntity>()
            cars.map {
                carEntity.add(
                    CarEntity(
                        it.id,
                        it.name,
                        it.image,
                        it.monthlyPrice,
                        it.carType,
                        it.buildYear,
                        it.description,
                        it.availableForRent,
                        it.kilometersByYear,
                        it.AdditionalKilometersPrice,
                        AttributesEntity(
                            it.attributes.engine, it.attributes.transmition,
                            it.attributes.entertainment, it.attributes.security
                        )
                    )
                )
            }
            return carEntity
        }

        fun converterEntityToCar(cars: List<CarEntity>): List<Car> {
            val car = mutableListOf<Car>()
            cars.map {
                car.add(
                    Car(
                        it.id,
                        it.name,
                        it.image,
                        it.monthlyPrice,
                        it.carType,
                        it.buildYear,
                        it.description,
                        it.availableForRent,
                        it.kilometersByYear,
                        it.AdditionalKilometersPrice,
                        Attributes(
                            it.attributes.engine, it.attributes.transmition,
                            it.attributes.entertainment, it.attributes.security
                        )
                    )
                )
            }
            return car
        }

        fun converterOneEntityToOneCar(carEntity: CarEntity): Car {
            with(carEntity) {
                return Car(
                    id,
                    name,
                    image,
                    monthlyPrice,
                    carType,
                    buildYear,
                    description,
                    availableForRent,
                    kilometersByYear,
                    AdditionalKilometersPrice,
                    Attributes(
                        attributes.engine,
                        attributes.transmition,
                        attributes.entertainment,
                        attributes.security
                    )
                )
            }
        }

    }
}