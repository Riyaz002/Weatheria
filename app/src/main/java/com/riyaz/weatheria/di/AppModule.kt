package com.riyaz.weatheria.di

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.riyaz.data.database.WeatheriaDao
import com.riyaz.data.database.WeatheriaDatabase
import com.riyaz.data.remote.OpenMateoApi
import com.riyaz.data.remote.WeatherApiService
import com.riyaz.data.repository.WeatheriaRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
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
    fun provideWeatheriaApiService(
        httpClient: OkHttpClient
    ): WeatherApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient)
            .build()
            .create(OpenMateoApi::class.java)
    }


    @Provides
    @Singleton
    fun providesWeatheriaRepository(
        weatheriaApiService: WeatherApiService,
        weatheriaDao: WeatheriaDao
    ): com.riyaz.domain.WeatheriaRepository = WeatheriaRepositoryImpl(
        Dispatchers.IO,
        weatheriaApiService,
        weatheriaDao
    )

    @Provides
    @Singleton
    fun providesGetForecastUseCase(
        weatheriaRepository: com.riyaz.domain.WeatheriaRepository
    ): com.riyaz.domain.usecase.GetForecastUseCase =
        com.riyaz.domain.usecase.GetForecastUseCase(weatheriaRepository)
}