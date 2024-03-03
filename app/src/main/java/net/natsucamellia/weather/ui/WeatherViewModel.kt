package net.natsucamellia.weather.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import net.natsucamellia.weather.WeatherApplication
import net.natsucamellia.weather.data.WeatherRepository
import net.natsucamellia.weather.model.Weather
import java.io.IOException

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    init {
        getWeather()
    }

    private fun getWeather() {
        viewModelScope.launch {
            weatherUiState = try {
                WeatherUiState.Success(weatherRepository.getWeather("Taipei"))
            } catch (e: IOException) {
                WeatherUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WeatherApplication)
                val weatherRepository = application.container.weatherRepository
                WeatherViewModel(weatherRepository)
            }
        }
    }

    sealed interface WeatherUiState {
        data class Success(val weather: Weather): WeatherUiState
        object Error: WeatherUiState
        object Loading: WeatherUiState
    }
}