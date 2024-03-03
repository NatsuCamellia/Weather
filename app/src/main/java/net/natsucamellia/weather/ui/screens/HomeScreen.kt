package net.natsucamellia.weather.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import net.natsucamellia.weather.model.Weather
import net.natsucamellia.weather.ui.WeatherViewModel
import kotlin.math.roundToInt

@Composable
fun HomeScreen(
    weatherUiState: WeatherViewModel.WeatherUiState,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
    ) {
        when (weatherUiState) {
            is WeatherViewModel.WeatherUiState.Success -> SuccessScreen(weather = weatherUiState.weather)
            is WeatherViewModel.WeatherUiState.Loading -> Text(text = "Loading...")
            is WeatherViewModel.WeatherUiState.Error -> Text(text = "Error fetching weather!")
        }
    }

}

@Composable
fun SuccessScreen(
    weather: Weather,
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(
        spec = when (weather.mainCondition.lowercase()) {
            "thunderstorm" -> LottieCompositionSpec.Url("https://lottie.host/32f1752d-2f56-4e1e-a1a6-bd5ab9911050/eDT9OEfLYj.json")
            "rain" -> LottieCompositionSpec.Url("https://lottie.host/bb12c2ca-74b1-4376-bd65-6fe5669c4c4e/yjwgQh3E4W.json")
            "clouds" -> LottieCompositionSpec.Url("https://lottie.host/d52c8b34-5198-42c3-ba9a-e8349b7dd9e9/6RAZO4Zkjl.json")
            else -> LottieCompositionSpec.Url("https://lottie.host/4883fe27-360c-4aa7-94e1-c1dbc9bba4fc/PwghpZDl0M.json")
        }
    )
    Column (
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        LocationBlock(city = weather.cityName)
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
        Text(
            text = "${weather.temperature.roundToInt()}ºC",
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Composable
fun LocationBlock(
    city: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.LocationOn,
            contentDescription = "Location"
        )
        Text(
            text = city,
            style = MaterialTheme.typography.titleLarge
        )
    }
}