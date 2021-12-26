package com.udacity.asteroidradar.data.source.remote.api.model.mappers

import com.udacity.asteroidradar.domain.models.Asteroid
import com.udacity.asteroidradar.data.source.remote.api.model.ApiAsteroid
import javax.inject.Inject

class ApiAsteroidMapper @Inject constructor(): ApiMapper<ApiAsteroid, Asteroid> {
    override fun mapToDomain(apiEntity: ApiAsteroid): Asteroid {
        return Asteroid(
            id = apiEntity.id,
            codename = apiEntity.codename,
            closeApproachDate = apiEntity.closeApproachDate,
            absoluteMagnitude = apiEntity.absoluteMagnitude,
            estimatedDiameter = apiEntity.estimatedDiameter,
            relativeVelocity = apiEntity.relativeVelocity,
            distanceFromEarth = apiEntity.distanceFromEarth,
            isPotentiallyHazardous = apiEntity.isPotentiallyHazardous
        )
    }
}
