package com.udacity.asteroidradar.data.source.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.domain.models.Asteroid

@Entity(tableName = "asteroid")
data class CacheAsteroid(
    @field:ColumnInfo(name = "id") @PrimaryKey val id: Long,
    @field:ColumnInfo(name = "code_name") val codename: String,
    @field:ColumnInfo(name = "close_approach_date") val closeApproachDate: String,
    @field:ColumnInfo(name = "absolute_magnitude") val absoluteMagnitude: Double,
    @field:ColumnInfo(name = "estimated_diameter") val estimatedDiameter: Double,
    @field:ColumnInfo(name = "relative_velocity") val relativeVelocity: Double,
    @field:ColumnInfo(name = "distance_from_earth") val distanceFromEarth: Double,
    @field:ColumnInfo(name = "is_potentially_hazardous") val isPotentiallyHazardous: Boolean
) {
  fun toDomain(): Asteroid {
    return Asteroid(
        id = id,
        codename = codename,
        closeApproachDate = closeApproachDate,
        absoluteMagnitude = absoluteMagnitude,
        estimatedDiameter = estimatedDiameter,
        relativeVelocity = relativeVelocity,
        distanceFromEarth = distanceFromEarth,
        isPotentiallyHazardous = isPotentiallyHazardous)
  }

  companion object {
    fun fromDomain(domainModel: Asteroid): CacheAsteroid {
      return CacheAsteroid(
          id = domainModel.id,
          codename = domainModel.codename,
          closeApproachDate = domainModel.closeApproachDate,
          absoluteMagnitude = domainModel.absoluteMagnitude,
          estimatedDiameter = domainModel.estimatedDiameter,
          relativeVelocity = domainModel.relativeVelocity,
          distanceFromEarth = domainModel.distanceFromEarth,
          isPotentiallyHazardous = domainModel.isPotentiallyHazardous)
    }
  }
}
