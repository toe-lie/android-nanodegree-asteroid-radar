package com.udacity.asteroidradar.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.data.source.remote.api.interceptors.AuthenticationInterceptor
import com.udacity.asteroidradar.data.source.remote.api.interceptors.NetworkStatusInterceptor
import com.udacity.asteroidradar.data.source.remote.api.services.AsteroidService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

  @Singleton
  @Provides
  fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
    val builder = OkHttpClient.Builder()
    builder.readTimeout(30, TimeUnit.SECONDS)
    return builder
  }

  @Singleton
  @Provides
  fun provideOkHttpClient(
    app: Application,
    builder: OkHttpClient.Builder,
    networkStatusInterceptor: NetworkStatusInterceptor,
    logging: HttpLoggingInterceptor,
    authenticationInterceptor: AuthenticationInterceptor
  ): OkHttpClient {
    builder.addInterceptor(
      ChuckerInterceptor.Builder(app)
        .collector(ChuckerCollector(app))
        .maxContentLength(250000L)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(false)
        .build()
    )
    builder.addInterceptor(networkStatusInterceptor)
    builder.addInterceptor(authenticationInterceptor)
    builder.addInterceptor(logging)
    return builder.build()
  }

  @Provides
  @Singleton
  fun provideRetrofitBuilder(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit.Builder {
    return Retrofit.Builder()
      .baseUrl(Constants.BASE_URL)
      .addConverterFactory(ScalarsConverterFactory.create())
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .client(okHttpClient)
  }

  @Provides
  @Singleton
  fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
    return builder.build()
  }
}

@InstallIn(SingletonComponent::class)
@Module
object ApiServiceModule {

  @Provides
  fun provideAsteroidService(retrofit: Retrofit): AsteroidService =
    retrofit.create(AsteroidService::class.java)

}
