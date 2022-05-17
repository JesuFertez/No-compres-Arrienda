package com.jesufertez.nocompresarrienda.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jesufertez.nocompresarrienda.databinding.ItemLayoutBinding
import com.jesufertez.nocompresarrienda.model.Car
import com.jesufertez.nocompresarrienda.util.OnItemClick
import com.squareup.picasso.Picasso

class CarAdapter(
    private val cars: List<Car>,
   private val onItemClick: OnItemClick
) : RecyclerView.Adapter<CarsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return CarsViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        holder.bind(cars[position])
    }

    override fun getItemCount(): Int = cars.size
}

class CarsViewHolder(
    private val binding: ItemLayoutBinding,
    private val onItemClick: OnItemClick
) : RecyclerView.ViewHolder(binding.root) {

    fun bind (car: Car){
        binding.run {
            tvId.text = car.id.toString()
            tvName.text = car.name
            tvMonthlyPrice.text = car.monthlyPrice
            tvCarType.text = car.carType
            tvBuildYear.text = car.buildYear.toString()
            tvDescription.text = car.description
            tvAbailableForRent.text = car.availableForRent.toString()
            tvKilometersByYear.text = car.kilometersByYear.toString()
            tvKilometersPrice.text = car.AdditionalKilometersPrice
            Picasso.get().load(car.image).into(ivCar)
            itemView.setOnClickListener { view: View? ->
                onItemClick.onItemClick(adapterPosition,car.id.toString())
            }
        }
    }
}