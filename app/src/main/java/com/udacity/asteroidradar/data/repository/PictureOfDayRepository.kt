package com.udacity.asteroidradar.data.repository

import com.udacity.asteroidradar.data.source.local.PictureOfDayLocalDataSource
import com.udacity.asteroidradar.data.source.local.database.model.CachePictureOfDay
import com.udacity.asteroidradar.data.source.remote.PictureOfDayRemoteDataSource
import com.udacity.asteroidradar.data.source.remote.api.model.mappers.ApiPictureOfDayMapper
import com.udacity.asteroidradar.data.source.remote.api.model.ApiPictureOfDay
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull

@Singleton
class PictureOfDayRepository
@Inject
constructor(
    private val pictureOfDayLocalDataSource: PictureOfDayLocalDataSource,
    private val pictureOfDayRemoteDataSource: PictureOfDayRemoteDataSource,
    private val apiPictureOfDayMapper: ApiPictureOfDayMapper
) {

  fun getPictureOfDay() = flow {
    val apiPictureOfDay = pictureOfDayRemoteDataSource.getPictureOfDay()
    pictureOfDayLocalDataSource.savePictureOfDay(mapPictureOfDay(apiPictureOfDay))

    emitAll(pictureOfDayLocalDataSource.getPictureOfDay().mapNotNull { it?.toDomain() })
  }

  private fun mapPictureOfDay(apiPictureOfDay: ApiPictureOfDay): CachePictureOfDay {
    return CachePictureOfDay.fromDomain(apiPictureOfDayMapper.mapToDomain(apiPictureOfDay))
  }
}
