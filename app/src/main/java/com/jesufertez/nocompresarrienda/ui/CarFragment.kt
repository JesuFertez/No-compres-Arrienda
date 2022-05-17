package com.jesufertez.nocompresarrienda.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jesufertez.nocompresarrienda.R
import com.jesufertez.nocompresarrienda.databinding.FragmentCarBinding
import com.jesufertez.nocompresarrienda.interactor.CarRepository
import com.jesufertez.nocompresarrienda.model.Car
import com.jesufertez.nocompresarrienda.remote.RetrofitClient
import com.jesufertez.nocompresarrienda.util.NetworkComprobation
import com.jesufertez.nocompresarrienda.util.OnItemClick
import com.jesufertez.nocompresarrienda.viewmodels.CarsViewModel
import com.jesufertez.nocompresarrienda.viewmodels.CarsViewModelFactory

class CarFragment : Fragment(), OnItemClick {

    private val carsApi = RetrofitClient.getCarsApi()
    private val carsrepository = CarRepository(carsApi)
    private val carsViewModelFactory = CarsViewModelFactory(carsrepository)
    private val carsViewModel: CarsViewModel by activityViewModels {
        carsViewModelFactory
    }
    private val network = NetworkComprobation()
    private var rawBinding: FragmentCarBinding? = null
    private val binding get() = rawBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rawBinding = FragmentCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        comprobation()
        setupRecyclerView()
    }

     private fun comprobation() {
        if (network.isOnline(this.requireContext())) {
            setupViewModel()
        } else {
          setupDataCars()
        }
    }

    private fun setupDataCars() {
        carsViewModel.state().observe(viewLifecycleOwner) {
            it?.let { safeResponse ->
                handleUi(safeResponse)

                if (safeResponse.isEmpty()) {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle(R.string.dialogo_no_data)
                        .setMessage(R.string.message_no_data)
                        .show()
                }
            }
        }
        carsViewModel.getDatacars()
    }

    private fun setupViewModel() {
        carsViewModel.state().observe(viewLifecycleOwner) {
            it?.let { safeCarResponse ->
                handleUi(safeCarResponse)
            }
        }
        carsViewModel.getCars()
    }

    private fun handleUi(safeCarResponse: List<Car>) {
        val adapter = CarAdapter(safeCarResponse, this)
        binding.rvListCars.adapter = adapter
    }

    private fun setupRecyclerView() {
        binding.rvListCars.layoutManager = LinearLayoutManager(context)
    }

    override fun onItemClick(position: Int, id: String) {
        val bundle = bundleOf("idDetalle" to id)
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_carFragment_to_detailFragment, bundle)
    }
}