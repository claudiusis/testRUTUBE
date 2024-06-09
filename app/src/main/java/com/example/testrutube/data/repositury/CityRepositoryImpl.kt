package com.example.testrutube.data.repositury

import android.util.Log
import com.example.testrutube.data.model.BaseURL
import com.example.testrutube.data.model.City
import com.example.testrutube.data.model.CityRepository
import com.example.testrutube.data.model.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(private val client : HttpClient) : CityRepository {

    override suspend fun getCities(): Response<List<City>> {
        return try {
            val result = client.get {
                url(BaseURL.CITIES)
            }.body<String>()
            val cities : List<City> = Json.decodeFromString<List<City>>(result).sortedBy { it.city }.filter { it.city.isNotEmpty() }
            Log.d("QWERTY", cities.toString())
            Response.Success(
                cities
            )
        } catch (e : Exception){
            e.printStackTrace()
            Response.Error(e)
        }
    }
}