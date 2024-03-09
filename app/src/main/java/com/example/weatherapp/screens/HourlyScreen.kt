package com.example.weatherapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.models.ForecastDay
import com.example.weatherapp.models.Hour

@Composable
fun HourlyWeatherScreen(mainViewModel: MainViewModel) {

    val weather by mainViewModel.weatherStateFlow.collectAsState()
    val forecastDays = weather?.forecast?.forecastDays


    if(forecastDays != null){
        LazyColumn {
            // list the days
            items(forecastDays) {forecastDay->

                Text(forecastDay.date)

                // list the hours of each day
                for(hour in forecastDay.hours){
                    Text("Time: ${hour.time} Temp: ${hour.temp}")
                }

            }

        }
    }

}
