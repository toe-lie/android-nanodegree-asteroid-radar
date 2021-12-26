package com.udacity.asteroidradar.data.repository

import android.net.Network
import com.udacity.asteroidradar.data.source.local.AsteroidLocalDataSource
import com.udacity.asteroidradar.data.source.local.PictureOfDayLocalDataSource
import com.udacity.asteroidradar.domain.models.Asteroid
import com.udacity.asteroidradar.domain.models.AsteroidFilter
import com.udacity.asteroidradar.domain.models.PictureOfDay
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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
        emitAll(asteroidLocalDataSource.getAsteroids().map { it -> it.map { it.toDomain() } })
    }
}