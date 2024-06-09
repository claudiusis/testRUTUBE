package com.example.testrutube.data.model.weather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerialName("base")
    val base: String? = null,
    @SerialName("clouds")
    val clouds: Clouds,
    @SerialName("cod")
    val cod: Int? = null,
    @SerialName("coord")
    val coord: Coord,
    @SerialName("dt")
    val dt: Int? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("main")
    val main: Main,
    @SerialName("name")
    val name: String? = null,
    @SerialName("rain")
    val rain: Rain? = null,
    @SerialName("sys")
    val sys: Sys? = null,
    @SerialName("timezone")
    val timezone: Int? = null,
    @SerialName("visibility")
    val visibility: Int? = null,
    @SerialName("weather")
    val weather: List<WeatherX> = listOf(),
    @SerialName("wind")
    val wind: Wind? =null
)