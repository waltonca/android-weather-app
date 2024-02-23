package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class Weather (
    val current: Current,
    val location: Location

)

//data class Location ()
data class Location (
    @SerializedName("name") val locName: String,
    @SerializedName("region") val locRegion: String,
    @SerializedName("country") val locCountry: String
)

data class Current (
    @SerializedName("temp_c") val temperature: Float,
    @SerializedName("wind_kph") val windSpeed: Float,
    @SerializedName("wind_dir") val windDirection: String,
    @SerializedName("feelslike_c") val feelsLike: Float,
    val condition: Condition

)

data class Condition (
    val text: String,
    val icon: String
)

//data class Forecast ()