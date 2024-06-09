package com.example.testrutube.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.testrutube.data.model.Response
import com.example.testrutube.databinding.FragmentWeatherBinding
import com.example.testrutube.viewModel.CitiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var binding : FragmentWeatherBinding
    private val viewModel : CitiesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.curCity.observe(viewLifecycleOwner){
            viewModel.getWeatherForeCast()
        }

        viewModel.weather.observe(viewLifecycleOwner){
            when(it){
                is Response.Loading -> {
                    binding.errorText.visibility = View.GONE
                    binding.pogressBar.visibility = View.VISIBLE

                }
                is Response.Success -> {
                    binding.pogressBar.visibility = View.GONE

                    try {
                        val weather = "${Math.round(it.data.main.temp ?: throw Exception("Error of formatting"))}°C"
                        binding.weatherCount.text = weather
                    } catch (e : Exception){
                        viewModel.setError(e)
                    }

                    binding.cityName.text = viewModel.curCity.value?.city
                }
                is Response.Error -> {
                    binding.pogressBar.visibility = View.GONE
                    binding.errorText.visibility = View.VISIBLE
                }
            }
        }

        binding.reload.setOnClickListener {
            viewModel.getWeatherForeCast()
        }

    }

}