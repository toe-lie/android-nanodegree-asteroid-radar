package com.udacity.asteroidradar.tasks

import androidx.work.*
import javax.inject.Inject

class AsteroidTasksImpl @Inject constructor(private val workManager: WorkManager) : AsteroidTasks {
  override fun setupDailyFetch() {
    val request =
        OneTimeWorkRequest.Builder(FetchAsteroids::class.java)
            .setConstraints(FetchAsteroids.buildConstraints())
            .addTag(FetchAsteroids.TAG)
            .build()
    workManager.enqueue(request)
  }
}
