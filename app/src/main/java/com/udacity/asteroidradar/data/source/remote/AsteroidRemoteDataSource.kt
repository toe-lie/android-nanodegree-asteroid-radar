package com.udacity.asteroidradar.data.source.remote

import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.data.source.remote.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.data.source.remote.api.services.AsteroidService
import com.udacity.asteroidradar.data.source.remote.api.model.ApiAsteroid
import org.json.JSONObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class AsteroidRemoteDataSource @Inject constructor(
    private val asteroidService: AsteroidService
) {
    suspend fun getAsteroids(): List<ApiAsteroid> {
        val dateFormatter = DateTimeFormatter.ofPattern(Constants.API_QUERY_DATE_FORMAT)
        val startDate = dateFormatter.format(LocalDate.now())
        val endDate = dateFormatter.format(LocalDate.now().plusDays(7))

        val json = asteroidService.getFeed(startDate, endDate)
        return parseAsteroidsJsonResult(JSONObject(json))
    }
}