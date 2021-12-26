package com.udacity.asteroidradar.di

import android.content.Context
import androidx.work.WorkManager
import com.udacity.asteroidradar.appinitializers.AppInitializer
import com.udacity.asteroidradar.tasks.AsteroidTasks
import com.udacity.asteroidradar.tasks.AsteroidTasksImpl
import com.udacity.asteroidradar.tasks.TasksInitializer
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TasksModule {
  @Provides
  @Singleton
  fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
    WorkManager.getInstance(context)
}

@InstallIn(SingletonComponent::class)
@Module
abstract class TasksModuleBinds {

  @Binds @IntoSet abstract fun provideTasksInitializer(bind: TasksInitializer): AppInitializer

  @Binds @Singleton abstract fun provideTasks(bind: AsteroidTasksImpl): AsteroidTasks
}
