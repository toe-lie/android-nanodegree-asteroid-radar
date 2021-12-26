package com.udacity.asteroidradar.ui.model.mappers

import com.udacity.asteroidradar.domain.models.Asteroid
import com.udacity.asteroidradar.ui.model.UiAsteroid
import javax.inject.Inject

class UiAsteroidMapper @Inject constructor() : UiMapper<Asteroid, UiAsteroid> {
  override fun mapToView(domainModel: Asteroid): UiAsteroid {
    return UiAsteroid(
        id = domainModel.id,
        codename = domainModel.codename,
        closeApproachDate = domainModel.closeApproachDate,
        absoluteMagnitude = domainModel.absoluteMagnitude,
        estimatedDiameter = domainModel.estimatedDiameter,
        relativeVelocity = domainModel.relativeVelocity,
        distanceFromEarth = domainModel.distanceFromEarth,
        isPotentiallyHazardous = domainModel.isPotentiallyHazardous,
    )
  }
}
