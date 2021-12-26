package com.udacity.asteroidradar.data.source.remote.api.model.mappers

import com.udacity.asteroidradar.data.source.remote.api.model.mappers.ApiMapper
import com.udacity.asteroidradar.domain.models.Asteroid
import com.udacity.asteroidradar.domain.models.PictureOfDay
import com.udacity.asteroidradar.network.models.ApiAsteroid
import com.udacity.asteroidradar.network.models.ApiPictureOfDay
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
