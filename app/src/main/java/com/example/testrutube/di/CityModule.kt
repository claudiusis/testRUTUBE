package com.example.testrutube.di

import com.example.testrutube.data.model.CityRepository
import com.example.testrutube.data.model.WeatherRepository
import com.example.testrutube.data.repositury.CitiesHttpClient
import com.example.testrutube.data.repositury.CityRepositoryImpl
import com.example.testrutube.data.repositury.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@InstallIn(SingletonComponent::class)
@Module
class CityModule {

    @Provides
    fun getHttpClient(citiesHttpClient: CitiesHttpClient): HttpClient = citiesHttpClient.getHttpClient()

    @Provides
    fun getCityRepository(cityRepositoryImpl: CityRepositoryImpl) : CityRepository = cityRepositoryImpl

    @Provides
    fun getWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl) : WeatherRepository = weatherRepositoryImpl

}