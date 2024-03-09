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
import java.util.Date

@Composable
fun HourlyWeatherScreen(mainViewModel: MainViewModel) {

    val weather by mainViewModel.weatherStateFlow.collectAsState()
    val forecastDays = weather?.forecast?.forecastDays

    // Want to display 24 hours after now
    val now = Date()
    var count = 0;

    if(forecastDays != null){
        LazyColumn {
            // list the days
            items(forecastDays) {forecastDay->

                if(count < 24) {
                    Text(forecastDay.date)
                }
                // list the hours of each day
                for(hour in forecastDay.hours){
                    if(Date(hour.epoch * 1000).after(now)) {
                        if(count < 24){
                            DisplayHour(hour)
                        }
                        count++
                    }

                }

            }

        }
    }

}

@Composable
fun DisplayHour(hour: Hour){
    Text("Time: ${hour.time} Temp: ${hour.temp}")
}
