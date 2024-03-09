package com.example.weatherapp

import android.R.id
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.screens.DailyWeatherScreen
import com.example.weatherapp.screens.HomeWeatherScreen
import com.example.weatherapp.screens.HourlyWeatherScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme


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
                    WeatherApp()
                }
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun WeatherApp(){

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
                                painterResource(id = R.drawable.baseline_account_balance_24),
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
                                painterResource(id = R.drawable.baseline_assessment_24),
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
                    HomeWeatherScreen(mainViewModel)
                }
                composable(route = "hourly") {
                    HourlyWeatherScreen(mainViewModel)
                }
                composable(route = "daily") {

                    DailyWeatherScreen(mainViewModel)
                }
            }
        }

    }
}

