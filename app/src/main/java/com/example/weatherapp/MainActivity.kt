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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.weatherapp.screens.DailyWeatherScreen
import com.example.weatherapp.screens.HomeWeatherScreen
import com.example.weatherapp.screens.HourlyWeatherScreen
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

        // The NavHostController manages the screen navigation
        val navController: NavHostController = rememberNavController()

        //
        // Get weather from ViewModel, and the UI will re-compose when ViewModel changes or weather data is loaded
        //

        val weather by mainViewModel.weatherStateFlow.collectAsState()
        val location = weather?.location

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
            bottomBar = {
                BottomAppBar(
                    actions = {
                        IconButton(onClick = { navController.navigate("home") }) {
                            Icon(
                                painterResource(id = R.drawable.baseline_wb_sunny_24),
                                contentDescription = "Home"
                            )
                        }
                        IconButton(onClick = { navController.navigate("hourly") }) {
                            Icon(
                                painterResource(id = R.drawable.baseline_access_time_filled_24),
                                contentDescription = "Hourly"
                            )
                        }
                        IconButton(onClick = { navController.navigate("daily") }) {
                            Icon(
                                painterResource(id = R.drawable.ic_android_black_24dp),
                                contentDescription = "Daily"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            // NavHost will display the correct screen
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = "home") {
                    HomeWeatherScreen()
                }
                composable(route = "hourly") {
                    HourlyWeatherScreen()
                }
                composable(route = "daily") {

                    DailyWeatherScreen()
                }
            }
        }

    }
}

