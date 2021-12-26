package com.udacity.asteroidradar.data.source.remote.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiAsteroid(
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)
