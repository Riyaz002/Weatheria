package com.riyaz.weatheria.di

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.riyaz.weatheria.data.database.WeatheriaDao
import com.riyaz.weatheria.data.database.WeatheriaDatabase
import com.riyaz.weatheria.data.remote.OpenMateoApi
import com.riyaz.weatheria.data.remote.WeatherApiService
import com.riyaz.weatheria.data.repository.WeatheriaRepositoryImpl
import com.riyaz.weatheria.domain.repository.WeatheriaRepository
import com.riyaz.weatheria.domain.usecase.GetForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
@Named("test")
object TestAppModule {
    @Provides
    @Singleton
    fun provideOpenMeteoApi(
        httpClient: OkHttpClient
    ): OpenMateoApi {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient)
            .build()
            .create(OpenMateoApi::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(context))
        .build()

    @Provides
    @Singleton
    fun providesWeatheriaDatabase(
        @ApplicationContext context: Context
    ): WeatheriaDatabase = Room.databaseBuilder(
        context,
        WeatheriaDatabase::class.java,
        WeatheriaDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun providesWeatheriaDao(
        database: WeatheriaDatabase
    ): WeatheriaDao = database.weatheriaDao

    @Provides
    @Singleton
    fun providesWeatheriaRepository(
        weatheriaApiService: WeatherApiService,
        weatheriaDao: WeatheriaDao
    ): WeatheriaRepository = WeatheriaRepositoryImpl(
        Dispatchers.IO,
        weatheriaApiService,
        weatheriaDao
    )

    @Provides
    @Singleton
    fun providesGetForecastUseCase(
        weatheriaRepository: WeatheriaRepository
    ): GetForecastUseCase = GetForecastUseCase(weatheriaRepository)
}