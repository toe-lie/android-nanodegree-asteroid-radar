package com.udacity.asteroidradar.data.source.local

import com.udacity.asteroidradar.data.source.local.database.daos.AsteroidDao
import com.udacity.asteroidradar.data.source.local.database.model.CacheAsteroid
import javax.inject.Inject

class AsteroidLocalDataSource @Inject constructor(
    private val dao: AsteroidDao
) {
    fun getAsteroids() = dao.getAllAsteroids()

    suspend fun saveAsteroids(asteroids: List<CacheAsteroid>) {
        dao.insert(asteroids)
    }
}