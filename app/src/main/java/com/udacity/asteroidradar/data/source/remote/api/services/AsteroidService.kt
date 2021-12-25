package com.udacity.asteroidradar.data.source.remote.api.services

import retrofit2.http.GET
import retrofit2.http.Query

/** A retrofit service to fetch data from NASA API. */
interface AsteroidService {

    /** Retrieve a list of Asteroids based on their closest approach date to Earth. */
    @GET("neo/rest/v1/feed")
    suspend fun getFeed(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): String

    /** Retrieve Astronomy Picture of the Day */
    // @GET("planetary/apod") suspend fun getPictureOfDay(): ApiPictureOfDay
}