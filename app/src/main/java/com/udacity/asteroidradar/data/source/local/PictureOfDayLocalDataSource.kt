package com.udacity.asteroidradar.data.source.local

import com.udacity.asteroidradar.data.source.local.database.daos.PictureOfDayDao
import com.udacity.asteroidradar.data.source.local.database.model.CachePictureOfDay
import javax.inject.Inject

class PictureOfDayLocalDataSource
@Inject
constructor(private val pictureOfDayDao: PictureOfDayDao) {

  fun getPictureOfDay() = pictureOfDayDao.getPictureOfDay()
  suspend fun savePictureOfDay(pictureOfDay: CachePictureOfDay) {
    pictureOfDayDao.insert(pictureOfDay)
  }
}
