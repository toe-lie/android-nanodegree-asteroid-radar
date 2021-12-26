package com.udacity.asteroidradar.data.source.remote

import com.udacity.asteroidradar.data.source.remote.api.services.AsteroidService
import javax.inject.Inject

class PictureOfDayRemoteDataSource @Inject constructor(
    private val asteroidService: AsteroidService
) {
    suspend fun getPictureOfDay() = asteroidService.getPictureOfDay()
}