package com.udacity.asteroidradar.di

import com.udacity.asteroidradar.appinitializers.AppInitializer
import com.udacity.asteroidradar.appinitializers.NightModeInitializer
import com.udacity.asteroidradar.tasks.TasksInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
abstract class AppInitializerModule {

    @Binds
    @IntoSet
    internal abstract fun provideTasksInitializer(bind: TasksInitializer): AppInitializer

    @Binds
    @IntoSet
    internal abstract fun provideNightModeInitializer(bind: NightModeInitializer): AppInitializer

}
