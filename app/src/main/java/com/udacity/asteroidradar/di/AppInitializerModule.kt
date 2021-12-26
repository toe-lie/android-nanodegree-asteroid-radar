package com.udacity.asteroidradar.di

import com.udacity.asteroidradar.appinitializers.AppInitializer
import com.udacity.asteroidradar.tasks.TasksInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class AppInitializerModule {
    @Binds
    internal abstract fun provideTasksInitializer(bind: TasksInitializer): AppInitializer

}
