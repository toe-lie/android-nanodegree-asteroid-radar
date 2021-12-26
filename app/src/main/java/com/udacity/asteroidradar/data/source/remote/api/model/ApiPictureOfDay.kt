package com.udacity.asteroidradar.data.source.remote.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiPictureOfDay(
    @Json(name = "media_type") val mediaType: String,
    val title: String,
    val url: String
)
