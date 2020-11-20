package com.mcsimf.openweathermap.core.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_URL = "http://api.openweathermap.org/"

/**
 * Creates icon full url from icon id.
 */
fun String.iconUrl(): String {
    return "https://openweathermap.org/img/wn/$this@2x.png"
}


val apiModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideWeatherApi(get()) }
}


fun provideOkHttpClient() = OkHttpClient.Builder().let {
    it.addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    })
    it.build()
}


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val gson = GsonBuilder().setLenient().create()
    return Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
}


fun provideWeatherApi(retrofit: Retrofit): WeatherAPI = retrofit.create(WeatherAPI::class.java)