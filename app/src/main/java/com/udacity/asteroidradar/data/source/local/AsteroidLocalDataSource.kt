package com.udacity.asteroidradar.data.source.local

import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.data.source.local.database.daos.AsteroidDao
import com.udacity.asteroidradar.data.source.local.database.model.CacheAsteroid
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class AsteroidLocalDataSource @Inject constructor(private val dao: AsteroidDao) {

  private fun getAsteroids(startDate: String, endDate: String) = dao.getAsteroids(startDate, endDate)

  fun getAsteroids(startDate: LocalDate, endDate: LocalDate): Flow<List<CacheAsteroid>?> {
    val dateFormatter = DateTimeFormatter.ofPattern(Constants.API_QUERY_DATE_FORMAT)
    val startDateString = dateFormatter.format(startDate)
    val endDateString = dateFormatter.format(endDate)
    return getAsteroids(startDateString, endDateString)
  }

  suspend fun saveAsteroids(asteroids: List<CacheAsteroid>) {
    dao.insert(asteroids)
  }

  fun deleteObsoleteAsteroids() {
    dao.deleteObsoleteAsteroids()
  }
}
