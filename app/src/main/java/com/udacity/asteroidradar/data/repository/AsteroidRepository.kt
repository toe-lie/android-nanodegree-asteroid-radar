package com.udacity.asteroidradar.data.repository

import android.net.Network
import com.udacity.asteroidradar.data.source.local.AsteroidLocalDataSource
import com.udacity.asteroidradar.data.source.local.PictureOfDayLocalDataSource
import com.udacity.asteroidradar.domain.models.Asteroid
import com.udacity.asteroidradar.domain.models.AsteroidFilter
import com.udacity.asteroidradar.domain.models.PictureOfDay
import kotlinx.coroutines.flow.*
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AsteroidRepository @Inject constructor(
    private val asteroidLocalDataSource: AsteroidLocalDataSource
) {
    fun getAsteroids(filter: AsteroidFilter) = flow {
        val cachedAsteroidsFlow = if (filter == AsteroidFilter.Saved) {
            asteroidLocalDataSource.getAllAsteroids()
        } else {
            asteroidLocalDataSource.getAsteroids(filter.startDate, filter.endDate)
        }

        emitAll(cachedAsteroidsFlow.map { it -> it?.map { it.toDomain() } })
    }
}