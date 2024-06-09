package com.example.testrutube.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testrutube.R
import com.example.testrutube.data.model.Response
import com.example.testrutube.databinding.FragmentCitiesBinding
import com.example.testrutube.ui.recycler.CitiesAdapter
import com.example.testrutube.ui.recycler.StickyLabelDecorator
import com.example.testrutube.viewModel.CitiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CitiesFragment : Fragment() {

    private lateinit var binding : FragmentCitiesBinding
    private val cityViewModel : CitiesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCitiesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.citiesRv.layoutManager = LinearLayoutManager(requireContext())

        binding.reload.setOnClickListener {
            cityViewModel.getCities()
        }

        cityViewModel.cities.observe(viewLifecycleOwner){
            when(it){
                is Response.Loading -> {
                    binding.errorText.visibility = View.GONE
                    binding.reload.visibility = View.GONE
                    binding.pogressBar.visibility = View.VISIBLE

                }
                is Response.Success -> {
                    binding.pogressBar.visibility = View.GONE
                    binding.citiesRv.adapter = CitiesAdapter(it.data){ city ->
                        cityViewModel.setNewCity(city)
                        findNavController().navigate(R.id.action_citiesFragment_to_weatherFragment)
                    }
                    val decoration = StickyLabelDecorator(binding.citiesRv.adapter as CitiesAdapter)
                    binding.citiesRv.addItemDecoration(decoration)
                }
                is Response.Error -> {
                    binding.pogressBar.visibility = View.GONE
                    binding.errorText.visibility = View.VISIBLE
                    binding.reload.visibility = View.VISIBLE
                }
            }
        }

    }



}