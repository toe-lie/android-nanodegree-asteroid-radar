package com.udacity.asteroidradar.di

import android.content.Context
import android.os.Debug
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.udacity.asteroidradar.data.source.local.database.AsteroidRadarDb
import com.udacity.asteroidradar.data.source.local.database.AsteroidRadarRoomDb
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DaoModule {
  @Provides fun provideAsteroid(db: AsteroidRadarDb) = db.asteroidDao()
  @Provides fun providePictureOfDayDao(db: AsteroidRadarDb) = db.pictureOfDayDao()
}

@InstallIn(SingletonComponent::class)
@Module
object DbModule {

  @Singleton
  @Provides
  fun provideDatabase(@ApplicationContext context: Context): AsteroidRadarRoomDb {
    val builder =
      Room.databaseBuilder(context, AsteroidRadarRoomDb::class.java, "asteroid.db")
        .enableMultiInstanceInvalidation()
        .fallbackToDestructiveMigration()
    if (Debug.isDebuggerConnected()) {
      builder.allowMainThreadQueries()
    }
    return builder.build()
  }
}

@InstallIn(SingletonComponent::class)
@Module
abstract class DbModuleBinds {
  @Binds abstract fun bindAsteroidRadarDatabase(db: AsteroidRadarRoomDb): AsteroidRadarDb
}
