package com.udacity.asteroidradar.domain.usecases

import com.udacity.asteroidradar.data.source.local.AsteroidLocalDataSource
import com.udacity.asteroidradar.data.source.local.database.model.CacheAsteroid
import com.udacity.asteroidradar.data.source.remote.AsteroidRemoteDataSource
import com.udacity.asteroidradar.data.source.remote.api.model.ApiAsteroid
import com.udacity.asteroidradar.data.source.remote.api.model.mappers.ApiAsteroidMapper
import javax.inject.Inject

class RefreshAsteroidsUseCase
@Inject
constructor(
    private val remoteDataSource: AsteroidRemoteDataSource,
    private val localDataSource: AsteroidLocalDataSource,
    private val apiAsteroidMapper: ApiAsteroidMapper
) {
  suspend operator fun invoke() {
    val apiAsteroids = remoteDataSource.getAsteroids()
    localDataSource.saveAsteroids(apiAsteroids.map { mapAsteroid(it) })
    cleanObsoleteAsteroids()
  }

  private fun cleanObsoleteAsteroids() {
    localDataSource.deleteObsoleteAsteroids()
  }

  private fun mapAsteroid(apiAsteroid: ApiAsteroid): CacheAsteroid {
    return CacheAsteroid.fromDomain(apiAsteroidMapper.mapToDomain(apiAsteroid))
  }
}
