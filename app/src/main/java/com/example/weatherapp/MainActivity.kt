package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.ui.theme.WeatherAppTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewModel
        mainViewModel = MainViewModel()

        // Load the weather from API
        mainViewModel.updateWeather("44.6681392,-63.6139211")

        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayCurrentWeather()
                }
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DisplayCurrentWeather(){
        //
        // Get weather from ViewModel, and the UI will re-compose when ViewModel changes or weather data is loaded
        //

        val weather by mainViewModel.weatherStateFlow.collectAsState()

        val currentWeather = weather?.current
        val location = weather?.location

        // Testing Forecast day
        val forecastDays = weather?.forecast?.forecastDays
        Log.i("TESTING","Forecast Days: ${forecastDays?.size}")

        //
        // Render UI
        //

        var textFieldLocation by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        // Current location
                        Text("${location?.locName}, ${location?.locRegion}, ${location?.locCountry}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis)
                    }
                )
            },
        ) { innerPadding ->

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                    // Update Location Text Field
                    TextField(
                        value = textFieldLocation,
                        onValueChange = { textFieldLocation = it },
                        label = { Text("Change Location") }
                    )

                    Button(
                        onClick = {
                            mainViewModel.updateWeather(textFieldLocation)
                        }
                    ){
                        Text("Update")
                    }


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
        }

    }
}

