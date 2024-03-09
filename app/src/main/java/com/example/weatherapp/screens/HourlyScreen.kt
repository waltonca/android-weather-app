package com.example.weatherapp.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.models.ForecastDay
import com.example.weatherapp.models.HourItem

@Composable
fun HourlyWeatherScreen(mainViewModel: MainViewModel) {

    val weather by mainViewModel.weatherStateFlow.collectAsState()
    val forecastDays = weather?.forecast?.forecastDays

    // Loop the hour
    val forecastHour = weather?.forecast?.forecastDays?.get(0)?.hour

    if(forecastHour != null){
        LazyColumn {
            items(forecastHour){forecastHourItem ->
                DisplayHour(forecastHourItem)
            }
        }
    }
}

@Composable
fun DisplayHour(forecastHourItem: HourItem){
    val time = forecastHourItem.time
    val timeTemp = forecastHourItem.temp

    Text("Time: ${time}  Temp: ${timeTemp}")
}