package com.example.weather.di

import com.example.weather.BuildConfig
import com.example.weather.data.JsonConverter
import com.example.weather.data.JsonConverterImpl
import com.example.weather.data.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
    private const val API_URL = "https://thisdavej.azurewebsites.net/api/weather/"

    @Provides
    fun provideJsonConverter(): JsonConverter = JsonConverterImpl()

    @Provides
    fun provideRetrofit(jsonConverter: JsonConverter): Retrofit {
        if (BuildConfig.DEBUG)
            initLogging()
        return Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(jsonConverter.factory)
            .client(httpClient.build())
            .build()
    }

    private fun initLogging() {
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
    }

    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)
}