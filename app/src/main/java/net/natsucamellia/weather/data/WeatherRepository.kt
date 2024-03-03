package net.natsucamellia.weather.data

import net.natsucamellia.weather.model.Weather
import net.natsucamellia.weather.network.WeatherApiService
import org.json.JSONObject

interface WeatherRepository {
    suspend fun getWeather(city: String): Weather
}

class NetworkWeatherRepository(
    private val weatherApiService: WeatherApiService,
    private val apiKey: String
) : WeatherRepository {
    override suspend fun getWeather(city: String): Weather {
        val jsonObject = JSONObject(weatherApiService.getWeatherForCity(city, apiKey))
        val cityName: String = jsonObject.getString("name");
        val temperature = jsonObject.getJSONObject("main").getDouble("temp")
        val mainCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main")
        return Weather(cityName, temperature, mainCondition)
    }
}