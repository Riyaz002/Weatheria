package com.riyaz.weatheria

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.riyaz.data.database.WeatheriaDao
import com.riyaz.data.database.WeatheriaDatabase
import com.riyaz.data.remote.IpGeolocationApi
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
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
@Named("test")
object TestAppModule {
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
    fun provideOpenMateoApiService(
        httpClient: OkHttpClient
    ): OpenMateoApi = Retrofit.Builder()
        .baseUrl(OpenMateoApi.BASE_URL)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(OpenMateoApi::class.java)

    @Provides
    @Singleton
    fun provideIpGeolocationApiService(
        httpClient: OkHttpClient
    ): IpGeolocationApi = Retrofit.Builder()
        .baseUrl(IpGeolocationApi.BASE_URL)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(IpGeolocationApi::class.java)

    @Provides
    @Singleton
    fun provideWeatheriaApiService(
        openMateoApi: OpenMateoApi,
        googleApi: IpGeolocationApi
    ): WeatherApiService = WeatherApiService(openMateoApi, googleApi)

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
}