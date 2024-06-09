package com.example.testrutube.data.model


import kotlinx.serialization.Serializable

@Serializable
data class City(
    val id: String,
    val city: String,
    val latitude: String,
    val longitude: String
)