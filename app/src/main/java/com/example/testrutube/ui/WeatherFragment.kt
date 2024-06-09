package com.example.testrutube.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.testrutube.R
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
                    binding.weatherCount.visibility = View.GONE
                    binding.cityName.visibility = View.GONE
                    binding.reload.visibility = View.GONE

                }
                is Response.Success -> {
                    binding.pogressBar.visibility = View.GONE

                    binding.reload.updateLayoutParams<ConstraintLayout.LayoutParams> {
                        this.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                        this.topToBottom = ConstraintLayout.LayoutParams.UNSET
                    }

                    binding.weatherCount.visibility = View.VISIBLE
                    binding.cityName.visibility = View.VISIBLE
                    binding.reload.visibility = View.VISIBLE

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
                    binding.reload.visibility = View.VISIBLE
                    binding.errorText.visibility = View.VISIBLE

                    binding.reload.updateLayoutParams<ConstraintLayout.LayoutParams> {
                        this.bottomToBottom = ConstraintLayout.LayoutParams.UNSET
                        this.topToBottom = R.id.error_text
                    }
                }
            }
        }

        binding.reload.setOnClickListener {
            viewModel.getWeatherForeCast()
        }

    }

}