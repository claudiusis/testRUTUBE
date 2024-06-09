package com.example.testrutube.data.model

import com.example.testrutube.data.model.weather.Weather

interface WeatherRepository {
    suspend fun getWeatherForecast(city : City?) : Response<Weather>
}
