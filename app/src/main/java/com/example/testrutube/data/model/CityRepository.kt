package com.example.testrutube.data.model

interface CityRepository {
    suspend fun getCities() : Response<List<City>>
}