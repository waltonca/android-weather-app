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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
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

    //Format week day
    val weekDay = formatWeekDate(date)


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(15.dp)
    ) {
        // Date
        Text( "${weekDay}",
            modifier = Modifier.width(90.dp))

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

fun formatWeekDate(inputDate: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CANADA)
    val date = dateFormat.parse(inputDate)
    val calendar = Calendar.getInstance()
    calendar.time = date ?: Date()

    val today = Calendar.getInstance()

    return when {
        isSameDay(calendar, today) -> "Today"
        isSameDay(calendar, today.apply { add(Calendar.DAY_OF_YEAR, 1) }) -> "Tomorrow"
        else -> SimpleDateFormat("E", Locale.US).format(date)
    }
}
fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}