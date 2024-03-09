package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName


// the whole json file is a obj(root), we named it as Weather
data class Weather (
    val current: Current,
    val location: Location,

    val forecast: Forecast
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

data class Forecast (
////    array ?
////     the json can contain: key-value, another object, collection
////     key-value:"wind_mph": 8.1,
////     another object: "condition": {
////                "text": "Partly cloudy",
////                "icon": "//cdn.weatherapi.com/weather/64x64/day/116.png",
////                "code": 1003
////            },
////     collection: "forecastday": [
////                {
////                    "date": "2024-02-23",
////                    "date_epoch": 1708646400,
////                    "day": {
//
    @SerializedName("forecastday") val forecastDays: List<ForecastDay>
)

data class ForecastDay (
    val date: String,
    val day: Day,
    val hour: List<HourItem>
)

data class Day (
    @SerializedName("maxtemp_c") val maxTemp: Float,
    @SerializedName("mintemp_c") val minTemp: Float
)

data class HourItem (
    val time: String,
    @SerializedName("temp_c") val temp: Float
)