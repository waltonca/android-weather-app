package com.example.weatherapp.retrofit

import com.example.weatherapp.models.Weather
import retrofit2.http.GET
import retrofit2.http.Query

// This interface contains the individual API REST calls we can make

// Sample call to GET weather forecast:
// https://api.weatherapi.com/v1/forecast.json?key=57790511bb914ec88a3211429241101&q=44.6681392,-63.6139211&days=3&aqi=no&alerts=no
// http://api.weatherapi.com/v1/forecast.json?key=0bb6e459a3cb4333be0145722240902&q=44.6681392,-63.6139211&days=1&aqi=no&alerts=no (mine key)
// parameters: key, q, days, aqi, alerts

interface APIService {

    @GET("v1/forecast.json")
    suspend fun getWeather (
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("days") days: String,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Weather

}