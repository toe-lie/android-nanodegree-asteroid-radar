package com.udacity.asteroidradar.data.source.local.database

import com.udacity.asteroidradar.data.source.local.database.daos.AsteroidDao
import com.udacity.asteroidradar.data.source.local.database.daos.PictureOfDayDao


interface AsteroidRadarDb {
  fun asteroidDao(): AsteroidDao
  fun pictureOfDayDao(): PictureOfDayDao
}
