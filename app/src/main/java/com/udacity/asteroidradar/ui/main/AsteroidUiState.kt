package com.udacity.asteroidradar.ui.main

import com.udacity.asteroidradar.domain.models.PictureOfDay
import com.udacity.asteroidradar.ui.model.UiAsteroid

data class AsteroidUiState(
    val pictureOfDay: PictureOfDay? = null,
    val asteroidItems: List<UiAsteroid> = listOf()
)
