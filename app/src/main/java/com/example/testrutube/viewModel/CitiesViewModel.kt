package com.example.testrutube.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testrutube.data.model.City
import com.example.testrutube.data.model.CityRepository
import com.example.testrutube.data.model.Response
import com.example.testrutube.data.model.WeatherRepository
import com.example.testrutube.data.model.weather.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(private val cityRepository: CityRepository, private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _cities : MutableLiveData<Response<List<City>>> = MutableLiveData()
    val cities : LiveData<Response<List<City>>> = _cities

    private val _curCity : MutableLiveData<City> = MutableLiveData()
    val curCity : LiveData<City> = _curCity

    private val _weather : MutableLiveData<Response<Weather>> = MutableLiveData()
    val weather : LiveData<Response<Weather>> = _weather

    init {
        getCities()
    }

    fun getCities() {
        viewModelScope.launch(Dispatchers.IO) {
            _cities.postValue(Response.Loading)
            _cities.postValue(cityRepository.getCities())
        }
    }

    fun getWeatherForeCast() {
        viewModelScope.launch(Dispatchers.IO) {
            _weather.postValue(Response.Loading)
            _weather.postValue(weatherRepository.getWeatherForecast(_curCity.value))
        }
    }

    fun setNewCity(city: City) {
        _curCity.value = city
    }

    fun setError(e : Exception) {
        _weather.postValue(Response.Error(e))
    }

}