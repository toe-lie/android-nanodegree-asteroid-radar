package com.udacity.asteroidradar.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.data.source.local.database.model.CacheAsteroid
import com.udacity.asteroidradar.data.source.local.database.model.CachePictureOfDay

@Database(
    entities = [CacheAsteroid::class, CachePictureOfDay::class], version = 1, exportSchema = false)
abstract class AsteroidRadarRoomDb : RoomDatabase(), AsteroidRadarDb
