package com.udacity.asteroidradar.ui.model

import com.udacity.asteroidradar.domain.models.AsteroidFilter

enum class UiAsteroidFilter {
  Week,
  Today;

  companion object {
    fun to(uiModel: UiAsteroidFilter): AsteroidFilter {
      return when (uiModel) {
        Today -> AsteroidFilter.Today
        Week -> AsteroidFilter.Week
      }
    }
  }
}
