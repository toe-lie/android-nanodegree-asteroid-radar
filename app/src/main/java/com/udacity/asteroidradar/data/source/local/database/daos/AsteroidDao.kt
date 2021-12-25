package com.udacity.asteroidradar.data.source.local.database.daos

import androidx.room.*
import com.udacity.asteroidradar.data.source.local.database.model.CacheAsteroid
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AsteroidDao {

  @Query("SELECT * FROM asteroid")
  abstract fun getAllAsteroids(): Flow<List<CacheAsteroid>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insert(vararg asteroids: CacheAsteroid)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insert(asteroids: List<CacheAsteroid>)
}