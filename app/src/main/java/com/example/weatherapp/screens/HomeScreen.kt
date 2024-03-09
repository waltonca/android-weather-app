package com.example.weatherapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.MainViewModel
import kotlin.math.roundToInt

@Composable
fun HomeWeatherScreen(mainViewModel: MainViewModel) {
    Text("Current Weather Forecast")
    /*
    val weather by mainViewModel.weatherStateFlow.collectAsState()

    val currentWeather = weather?.current

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            // Update Location Text Field
//            TextField(
//                value = textFieldLocation,
//                onValueChange = { textFieldLocation = it },
//                label = { Text("Change Location") }
//            )
//
//            Button(
//                onClick = {
//                    mainViewModel.updateWeather(textFieldLocation)
//                }
//            ){
//                Text("Update")
//            }


            // Weather icon (using Coil)
            val imgUrl = "https://" + currentWeather?.condition?.icon
            imgUrl.replace("64x64","128x128") // get larger image version
            AsyncImage(
                model = imgUrl,
                contentDescription = "Current weather image",
                modifier = Modifier.size(128.dp))

            // weather condition text
            Text(currentWeather?.condition?.text.toString(),
                fontSize = 25.sp)

            // temperature: ex. 5°C
            Text("${currentWeather?.temperature?.roundToInt()}°C",
                fontSize = 50.sp)

            // feel like temp
            Text("Feels like ${currentWeather?.feelsLike?.roundToInt()}°C",
                fontSize = 20.sp)

            // Wind direction
            Text("Wind ${currentWeather?.windDirection} ${currentWeather?.windSpeed?.roundToInt()} kph",
                fontSize = 20.sp)
        }
    }

     */
}