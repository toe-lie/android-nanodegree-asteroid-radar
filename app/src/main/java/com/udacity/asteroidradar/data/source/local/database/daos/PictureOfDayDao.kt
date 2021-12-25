package com.udacity.asteroidradar.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.database.model.CachePictureOfDay
import com.udacity.asteroidradar.domain.models.Asteroid
import com.udacity.asteroidradar.domain.models.PictureOfDay
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PictureOfDayDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insert(vararg pictureOfDay: CachePictureOfDay)

  @Query("SELECT * FROM picture_of_day LIMIT 1")
  abstract fun getPictureOfDay(): Flow<CachePictureOfDay>

}