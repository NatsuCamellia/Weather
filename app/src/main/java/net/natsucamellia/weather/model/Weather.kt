package net.natsucamellia.weather.model

data class Weather(
    val cityName: String,
    val temperature: Double,
    val mainCondition: String
)
