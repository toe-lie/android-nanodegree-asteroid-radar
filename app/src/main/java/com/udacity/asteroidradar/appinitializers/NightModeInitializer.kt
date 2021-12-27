package com.udacity.asteroidradar.appinitializers

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import javax.inject.Inject

class NightModeInitializer @Inject constructor() : AppInitializer {
  override fun init(application: Application) {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
  }
}
