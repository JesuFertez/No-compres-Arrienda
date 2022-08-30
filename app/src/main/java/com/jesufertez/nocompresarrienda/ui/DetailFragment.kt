package com.jesufertez.nocompresarrienda.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.jesufertez.nocompresarrienda.R
import com.jesufertez.nocompresarrienda.databinding.FragmentDetailBinding
import com.jesufertez.nocompresarrienda.interactor.CarRepository
import com.jesufertez.nocompresarrienda.model.Car
import com.jesufertez.nocompresarrienda.model.database.CarApplication
import com.jesufertez.nocompresarrienda.remote.ApiCar
import com.jesufertez.nocompresarrienda.remote.RetrofitClient
import com.jesufertez.nocompresarrienda.util.CarConverter
import com.jesufertez.nocompresarrienda.util.NetworkComprobation
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class DetailFragment : Fragment() {

    private var rawBinding: FragmentDetailBinding? = null
    private val binding get() = rawBinding!!
    private val network = NetworkComprobation()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rawBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id: String = arguments?.getString("idDetalle").toString()
        comprovation(id.toInt())
        initSendEmail()
        initButtonBack()
    }

    private fun comprovation(id:Int) {
        if (network.isOnline(this.requireContext())) {
            initSearchById(id.toString())
        } else {
            setupDataDetailCars(id)
        }
    }

    private fun setupDataDetailCars(id:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            var response = CarApplication.database.registroDao().getId(id)
            var newRespone=CarConverter.converterOneEntityToOneCar(response)
            CoroutineScope(Dispatchers.Main).launch {
                handleUi(newRespone)
            }
        }
    }

    private fun initButtonBack() {
        binding.btnBack.setOnClickListener {
            initBack()
        }
    }

    private fun initBack() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_detailFragment_to_carFragment)
    }

    private fun initSearchById(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<Car> =
                RetrofitClient.retrofit.create(ApiCar::class.java).getDetailCar("$id")
            val safeResponse: Car? = call.body()
            CoroutineScope(Dispatchers.Main).launch {
                if (call.isSuccessful) {
                    if (safeResponse != null) {
                        handleUi(safeResponse)
                    }
                }
            }
        }
    }

    private fun handleUi(safeResponse: Car) {
        binding.run {
            binding.tvId.text = safeResponse.id.toString()
            tvName.text = safeResponse.name
            tvMonthlyPrice.text = safeResponse.monthlyPrice
            tvCarType.text = safeResponse.carType
            tvBuildYear.text = safeResponse.buildYear.toString()
            tvDescription.text = safeResponse.description
            tvAbailableForRent.text = safeResponse.availableForRent.toString()
            tvKilometersByYear.text = safeResponse.kilometersByYear.toString()
            Picasso.get().load(safeResponse.image).into(ivCar)
            tvEngine.text = safeResponse.attributes.engine
            tvTransmition.text = safeResponse.attributes.transmition
            tvEntertainment.text = safeResponse.attributes.entertainment
            tvSecurity.text = safeResponse.attributes.security
        }
    }

    private fun initSendEmail() {
        binding.btnSendMail.setOnClickListener {
            sendEmail(this.requireView())
        }
    }

    private fun sendEmail(view: View) = startActivity(
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Quiero arrendar este vehículo ${id}")
            putExtra(
                Intent.EXTRA_TEXT,
                "Hola \n Quisiera arrendar este vehículo ${binding.tvName}, me gustaría que me contactaran a este correo o al siguiente número ********* \n Quedo atento."
            )
            putExtra(Intent.EXTRA_EMAIL, arrayOf("info@nocompresarrienda.cl"))
        }
    )
}