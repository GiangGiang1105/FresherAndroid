package com.example.giangnnh_advanceandroid_day10.di

import com.example.giangnnh_advanceandroid_day10.data.service.ICityService
import com.example.giangnnh_advanceandroid_day10.data.service.IWeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL_WEATHER = "https://api.openweathermap.org/data/2.5/"
    private const val BASE_URL_CITY = "http://dzdemo.com/"

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @WeatherOKHttpClient
    @Provides
    @Singleton
    fun providesWeatherOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @CitiesOKHttpClient
    @Provides
    @Singleton
    fun providesCitiesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun providesWeatherService(@WeatherOKHttpClient okHttpClient: OkHttpClient): IWeatherService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BASE_URL_WEATHER)
            .build()
            .create(IWeatherService::class.java)

    @Provides
    @Singleton
    fun providesCitiesService(@CitiesOKHttpClient okHttpClient: OkHttpClient): ICityService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL_CITY)
        .build().create(ICityService::class.java)
}