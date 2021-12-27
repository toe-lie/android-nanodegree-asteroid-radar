package com.udacity.asteroidradar.domain.models

import java.time.LocalDate

enum class AsteroidFilter(val startDate: LocalDate, val endDate: LocalDate) {
  Week (LocalDate.now(), LocalDate.now().plusDays(7)),
  Today(LocalDate.now(), LocalDate.now() ),
  Saved(LocalDate.MIN, LocalDate.MAX)
}