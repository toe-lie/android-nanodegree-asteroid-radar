package com.udacity.asteroidradar.data.source.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.data.source.local.database.model.CachePictureOfDay
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PictureOfDayDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insert(vararg pictureOfDay: CachePictureOfDay)

  @Query("SELECT * FROM picture_of_day LIMIT 1")
  abstract fun getPictureOfDay(): Flow<CachePictureOfDay?>

}