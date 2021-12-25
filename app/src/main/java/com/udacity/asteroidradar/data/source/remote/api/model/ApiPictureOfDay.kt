package com.udacity.asteroidradar.network.models

import com.squareup.moshi.Json

data class ApiPictureOfDay(
    @Json(name = "media_type") val mediaType: String,
    val title: String,
    val url: String
)
