package net.natsucamellia.weather.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import net.natsucamellia.weather.ui.screens.HomeScreen

@Composable
fun WeatherApp() {
    val weatherViewModel: WeatherViewModel = viewModel(factory = WeatherViewModel.Factory)

    HomeScreen(
        weatherUiState = weatherViewModel.weatherUiState
    )
}