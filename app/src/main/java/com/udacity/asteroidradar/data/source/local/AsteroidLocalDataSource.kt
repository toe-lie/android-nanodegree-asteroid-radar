package com.udacity.asteroidradar.data.source.local

import com.udacity.asteroidradar.data.source.local.database.daos.AsteroidDao
import javax.inject.Inject

class AsteroidLocalDataSource @Inject constructor(
    private val dao: AsteroidDao
) {
    suspend fun getAsteroids() = dao.getAllAsteroids()
}