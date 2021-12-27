package com.udacity.asteroidradar

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.udacity.asteroidradar.appinitializers.AppInitializers
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AsteroidRadarApp : Application(), Configuration.Provider {

  @Inject lateinit var initializers: AppInitializers

  @Inject lateinit var workerFactory: HiltWorkerFactory

  override fun onCreate() {
    super.onCreate()
    initializers.init(this)

    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
  }

  override fun getWorkManagerConfiguration() =
      Configuration.Builder().setWorkerFactory(workerFactory).build()
}
