package com.udacity.asteroidradar.tasks

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.udacity.asteroidradar.domain.usecases.RefreshAsteroidsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.*
import java.util.concurrent.TimeUnit

@HiltWorker
class FetchAsteroids
@AssistedInject
constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val refreshAsteroids: RefreshAsteroidsUseCase
) : CoroutineWorker(context, params) {
  companion object {
    const val TAG = "fetch-asteroids"
    const val DAILY_FETCH_TAG = "daily-fetch-asteroids"

    fun buildConstraints() =
        Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
  }

  override suspend fun doWork(): Result {
    Log.d(TAG, "FetchAsteroid task is running...")
    return try {
      refreshAsteroids()
      setDailySchedule()
      Log.d(TAG, "doWork: Refresh success")
      Result.success()
    } catch (e: Exception) {
      Log.d(TAG, "doWork: Refresh failed: ${e.message}")
      Result.retry()
    }
  }

  private fun setDailySchedule() {
    val currentDate = Calendar.getInstance()
    val dueDate = Calendar.getInstance()
    // Set Execution around 00:00:00
    dueDate.set(Calendar.HOUR_OF_DAY, 0)
    dueDate.set(Calendar.MINUTE, 0)
    dueDate.set(Calendar.SECOND, 0)
    if (dueDate.before(currentDate)) {
      dueDate.add(Calendar.HOUR_OF_DAY, 24)
    }
    val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis
    val dailyWorkRequest =
        OneTimeWorkRequestBuilder<FetchAsteroids>()
            .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
            .addTag(TAG)
            .setConstraints(buildConstraints())
            .build()
    WorkManager.getInstance(applicationContext).enqueue(dailyWorkRequest)
  }
}
