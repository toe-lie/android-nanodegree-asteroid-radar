package com.udacity.asteroidradar.domain.models

import java.time.LocalDate

enum class AsteroidFilter(val startDate: LocalDate, val endDate: LocalDate, val totalDay: Int) {
  Week (LocalDate.now(), LocalDate.now().plusDays(7), 7),
  Today(LocalDate.now(), LocalDate.now(), 1)
}