package com.example.weatherapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.models.ForecastDay
import kotlin.math.roundToInt

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
    // Codition icon
    val imgUrl = "https:"+ day.condition.icon
    imgUrl.replace("64*64","128*128")

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(15.dp)
    ) {
        // Date
        Text( "${date}")

        // Condition
        AsyncImage(
            model = imgUrl,
            contentDescription = "Weather icon",
            modifier = Modifier.size(60.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        // Min temp - Max temp
        Text("${day.minTemp}°C - ${day.maxTemp}°C")

        Spacer(modifier = Modifier.width(10.dp))

        // Wind
        Column () {
            Text(day.condition.text)
            Text("Wind ${day.windMaxSpeed.roundToInt()}")
        }
    }
}