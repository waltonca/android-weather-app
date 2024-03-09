package com.example.weatherapp.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.models.ForecastDay

@Composable
fun DailyWeatherScreen(mainViewModel: MainViewModel) {

    val weather by mainViewModel.weatherStateFlow.collectAsState()
    val forecastDays = weather?.forecast?.forecastDays

    if(forecastDays != null){
        LazyColumn {
            items(forecastDays){forecastDay ->
                DisplayDay(forecastDay)
            }
        }
    }
}

@Composable
fun DisplayDay(forecastDay: ForecastDay){
    val date = forecastDay.date
    val day = forecastDay.day

    Text("Date: ${date} Max Temp: ${day.maxTemp}")
}