package com.example.weather.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

interface JsonConverter {
    val factory: Converter.Factory
}

class JsonConverterImpl : JsonConverter {
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    override val factory: MoshiConverterFactory = MoshiConverterFactory.create(moshi)
}