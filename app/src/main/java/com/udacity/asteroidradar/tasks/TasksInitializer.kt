package com.udacity.asteroidradar.tasks

import android.app.Application
import com.udacity.asteroidradar.appinitializers.AppInitializer
import dagger.Lazy
import javax.inject.Inject

class TasksInitializer @Inject constructor(
    private val asteroidTasks: Lazy<AsteroidTasks>
) : AppInitializer {
    override fun init(application: Application) {
        asteroidTasks.get().setupDailyFetch()
    }
}
