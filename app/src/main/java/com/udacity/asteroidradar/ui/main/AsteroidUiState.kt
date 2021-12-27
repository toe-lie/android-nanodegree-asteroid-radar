package com.udacity.asteroidradar.ui.main

import com.udacity.asteroidradar.domain.models.PictureOfDay
import com.udacity.asteroidradar.ui.model.UiAsteroid
import com.udacity.asteroidradar.ui.model.UiAsteroidFilter

data class AsteroidUiState(
    val pictureOfDay: PictureOfDay? = null,
    val asteroidItems: List<UiAsteroid> = listOf(),
    val currentFilter: UiAsteroidFilter = UiAsteroidFilter.Week
)
