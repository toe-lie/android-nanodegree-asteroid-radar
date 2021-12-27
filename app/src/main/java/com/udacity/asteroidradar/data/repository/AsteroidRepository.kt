package com.udacity.asteroidradar.data.repository

import com.udacity.asteroidradar.data.source.local.AsteroidLocalDataSource
import com.udacity.asteroidradar.domain.models.AsteroidFilter
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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