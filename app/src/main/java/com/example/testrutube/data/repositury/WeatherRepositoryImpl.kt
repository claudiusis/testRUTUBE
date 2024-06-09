package com.example.testrutube.data.repositury

import com.example.testrutube.data.model.APIKey
import com.example.testrutube.data.model.BaseURL
import com.example.testrutube.data.model.City
import com.example.testrutube.data.model.Response
import com.example.testrutube.data.model.WeatherRepository
import com.example.testrutube.data.model.weather.Weather
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val client: HttpClient) : WeatherRepository {

    override suspend fun getWeatherForecast(city: City?): Response<Weather> {
        return try {
            val url = BaseURL.WEATHER.plus("&lat=${city?.latitude}").plus("&lon=${city?.longitude}").plus("&units=metric").plus("&appid=${APIKey.KEY}")
            city?.let {
                Response.Success(
                    client.get {
                        url(url)
                    }.body<Weather>()
                )
            } ?: throw Exception("Error of getting city")
        } catch (e : Exception){
            e.printStackTrace()
            Response.Error(e)
        }
    }
}