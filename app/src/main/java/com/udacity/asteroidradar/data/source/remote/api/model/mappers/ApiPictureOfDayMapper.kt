package com.udacity.asteroidradar.data.source.remote.api.model.mappers

import com.udacity.asteroidradar.data.source.remote.api.model.mappers.ApiMapper
import com.udacity.asteroidradar.domain.models.PictureOfDay
import com.udacity.asteroidradar.network.models.ApiPictureOfDay

class ApiPictureOfDayMapper : ApiMapper<ApiPictureOfDay, PictureOfDay> {
  override fun mapToDomain(apiEntity: ApiPictureOfDay): PictureOfDay {
    return PictureOfDay(
        title = apiEntity.title, url = apiEntity.url, mediaType = apiEntity.mediaType)
  }
}
