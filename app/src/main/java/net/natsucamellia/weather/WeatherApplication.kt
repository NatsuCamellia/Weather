package net.natsucamellia.weather

import android.app.Application
import net.natsucamellia.weather.data.AppContainer
import net.natsucamellia.weather.data.DefaultAppContainer

class WeatherApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}