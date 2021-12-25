package com.udacity.asteroidradar.data.repository

import com.udacity.asteroidradar.data.source.local.AsteroidLocalDataSource
import com.udacity.asteroidradar.domain.models.Asteroid
import com.udacity.asteroidradar.domain.models.AsteroidFilter
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AsteroidRepository @Inject constructor(
    private val asteroidLocalDataSource: AsteroidLocalDataSource
) {
    fun getAsteroids(filter: AsteroidFilter) = flow {
        emitAll(asteroidLocalDataSource.getAsteroids().map { it.map { it.toDomain() } })
    }

}