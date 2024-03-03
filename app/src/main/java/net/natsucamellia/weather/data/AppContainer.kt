package net.natsucamellia.weather.data

import net.natsucamellia.weather.network.WeatherApiService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

interface AppContainer {
    val weatherRepository: WeatherRepository
}

class DefaultAppContainer: AppContainer {
    private val apiKey = TODO("Fill in your Open Weather Map API key")
    private val baseUrl = "https://api.openweathermap.org/data/2.5/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(baseUrl)
        .build()
    private val weatherApiService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }

    override val weatherRepository: WeatherRepository by lazy {
        NetworkWeatherRepository(weatherApiService, apiKey)
    }
}